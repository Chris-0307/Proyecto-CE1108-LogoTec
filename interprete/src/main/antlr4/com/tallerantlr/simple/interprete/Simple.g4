grammar Simple;



@lexer::members {
    @Override
    public org.antlr.v4.runtime.Token emit() {
        if (getType() == ERROR) {
            throw new RuntimeException("Identificador supera 10 caracteres: " + getText());
        }
        return super.emit();
    }
}


@parser::header{
    import java.util.*;
    import com.tallerantlr.simple.interprete.ast.*;
}

@parser::members {
    // Variables y procedimientos globales del programa
    Map<String, Object> globals = new HashMap<>();
    // firma = nombre + '#' + aridad (p.ej., "cuadrado#0", "mueve#2")
    Map<String, ProcedureDef> procTable = new HashMap<>();
    // Para contar declaraciones de variables (requisito: al menos una)
    int varDeclCount = 0;

    static String sig(String name, int arity) { return name + "#" + arity; }
}

// ======= Reglas de alto nivel =======

// Un programa es: (procedimientos o sentencias) separados por EOL (newline) o ';'
program
returns [List<ASTNode> body]
@init { $body = new ArrayList<>(); }
    :   (EOL)*                                                   // <<< acepta líneas en blanco al inicio
        (   (p=procedureDef)
          | (s=statement { if ($s.node != null) $body.add($s.node); })
        )
        (   (SEP | EOL)+                                         // <<< uno o más separadores entre elementos
            (   (p2=procedureDef)
              | (s2=statement { if ($s2.node != null) $body.add($s2.node); })
            )
        )*
        (SEP | EOL)*                                             // <<< separadores finales opcionales
        EOF
      {
        if (varDeclCount == 0) {
            throw new RuntimeException("Error: el programa debe declarar al menos una variable (regla del enunciado).");
        }
        for (ASTNode n : $body) n.execute(globals);
      }
    ;

// ======= Sentencias =======

statement
returns [ASTNode node]
    :   printlnStmt     { $node = $printlnStmt.node; }
    |   varDecl         { $node = $varDecl.node; }
    |   varAssign       { $node = $varAssign.node; }
    |   procCallStmt    { $node = $procCallStmt.node; }
    |   hazStmt         { $node = $hazStmt.node; }
    |   inicStmt        { $node = $inicStmt.node; }
    |   incStmt         { $node = $incStmt.node; }  
    
    ;

incStmt
returns [ASTNode node]
    :   INC LBRACK n1=ID RBRACK (SEP)?
        { $node = new Inc($n1.text, new Constant(1)); }            // inc [var]
    |   INC LBRACK n1=ID n2=expression RBRACK (SEP)?
        { $node = new Inc($n1.text, $n2.node); }                    // inc [var expr]
    ;



inicStmt
returns [ASTNode node]
    :   INIC id=ID ASSIGN v=expression (SEP)?
        { $node = new InicAssign($id.text, $v.node); }
    ;


hazStmt
returns [ASTNode node]
    :   HAZ id=ID v=expression (SEP)?
        { $node = new HazAssign($id.text, $v.node); }
    ;



// println( expr )
printlnStmt
returns [ASTNode node]
    :   PRINTLN PAR_OPEN expression PAR_CLOSE (SEP)?
        { $node = new Println($expression.node); }
    ;

// var x = expr   |   var x
varDecl
returns [ASTNode node]
    :   VAR id=ID ASSIGN e=expression (SEP)?
        {
            varDeclCount++;
            $node = new VarAssign($id.text, $e.node);
        }
    |   VAR id=ID (SEP)?
        {
            varDeclCount++;
            $node = new VarDecl($id.text);
        }
    ;


// x = expr
varAssign
returns [ASTNode node]
    :   id=ID ASSIGN e=expression (SEP)?
        { $node = new VarAssign($id.text, $e.node); }
    ;

// Llamada de procedimiento como sentencia:  nombre  |  nombre(arg1, arg2, ...)
procCallStmt
returns [ASTNode node]
@init {
    java.util.List<ASTNode> argNodes = new java.util.ArrayList<>();
    int arity = 0;
}
    :   id=ID
        (
            // con paréntesis y argumentos
            PAR_OPEN a=argList PAR_CLOSE
            {
                argNodes = $a.list;
                arity = argNodes.size();
            }
          |
            // sin paréntesis ni argumentos
            /* vacío */
            {
                argNodes = new java.util.ArrayList<>();
                arity = 0;
            }
        )
        (SEP)?
        {
            String key = sig($id.text, arity);
            ProcedureDef def = procTable.get(key);
            if (def == null) {
                throw new RuntimeException("Procedimiento no definido: " + $id.text + " con aridad " + arity);
            }
            $node = new ProcedureCall(def, argNodes);
        }
    ;

// ======= Procedimientos =======

// para nombre [param1, param2, ...]
//   (sentencias)
// fin
procedureDef
returns [java.util.List<String> formalParams]
@init {
    java.util.List<ASTNode> body = new java.util.ArrayList<ASTNode>();
    $formalParams = new java.util.ArrayList<String>(); // default: sin parámetros
}
    :   PARA name=ID
        (
            // Alternativa 1: con parámetros [ ... ]
            p=optParams (EOL)*
            {
                $formalParams = $p.ids; // lista ya inicializada por optParams
            }
          |
            // Alternativa 2: sin parámetros
            (EOL)*
            {
                $formalParams = new java.util.ArrayList<String>();
            }
        )
        ( s=statement { if ($s.node != null) body.add($s.node); } (EOL)* )*
        FIN (SEP|EOL)*
        {
            String key = sig($name.text, $formalParams.size());
            if (procTable.containsKey(key)) {
                throw new RuntimeException("Procedimiento duplicado con la misma firma: " + key);
            }
            ProcedureDef def = new ProcedureDef($name.text, $formalParams, body, globals, procTable);
            procTable.put(key, def);
        }
    ;


optParams
returns [java.util.List<String> ids]
@init { $ids = new java.util.ArrayList<String>(); }
    :   LBRACK
          ( id1=ID           { $ids.add($id1.text); }
            ( COMMA idn=ID   { $ids.add($idn.text); } )*
          )?
        RBRACK
    ;


// ======= Expresiones (reuso tus nodos) =======

expression
returns [ASTNode node]
    :   a=addExpr                       { $node = $a.node; }
        ( EQ b=addExpr                  { $node = new Equal($node, $b.node); } )*
    ;

addExpr
returns [ASTNode node]
    :   t1=multExpr                     { $node = $t1.node; }
        ( PLUS t2=multExpr              { $node = new Addition($node, $t2.node); }
        | AT   t3=multExpr              { $node = new Sumaplicacion($node, $t3.node); } // si ya lo tienes
        )*
    ;

multExpr
returns [ASTNode node]
    :   t1=term                         { $node = $t1.node; }
        ( MULT t2=term                  { $node = new Multiplication($node, $t2.node); }
        | PERM t3=term                  { $node = new Permutation($node, $t3.node); }
        )*
    ;

term
returns [ASTNode node]
    :   NUMBER  { $node = new Constant(Integer.parseInt($NUMBER.text)); }
    |   BOOLEAN { $node = new Constant(Boolean.parseBoolean($BOOLEAN.text.toLowerCase())); }
    |   STRING  {
                  String txt = $STRING.text;
                  String content = txt.substring(1, txt.length()-1).replace("\\\"", "\"");
                  $node = new Constant(content);
                }
    |   ID      { $node = new VarRef($ID.text); }
    |   PAR_OPEN expression PAR_CLOSE  { $node = $expression.node; }
    |   IGUALESQ e1=expression e2=expression { $node = new Equal($e1.node, $e2.node); }
    |   AZAR e=expression              { $node = new Azar($e.node); }       // <-- NUEVO
    ;



// ======= Utilitarios =======

// Lista de argumentos de llamada
argList
returns [List<ASTNode> list]
@init { $list = new ArrayList<>(); }
    :   e1=expression { $list.add($e1.node); }
        ( COMMA en=expression { $list.add($en.node); } )*
    ;

// Separador entre sentencias: ';' o fin de línea
SEP : SEMICOLON ;
EOL : NEWLINE ;

PARA: 'para';
FIN:  'fin';
VAR:  'var';
PRINTLN: 'println';
HAZ: [Hh] 'az';  
INIC: 'inic';
INC: [Ii][Nn][Cc];
AZAR: [Aa][Zz][Aa][Rr];




STRING
  : '"' ( '\\"' | ~["\r\n] )* '"'   // cadena con \" escapado
  ;

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
AT: '@';

IGUALESQ: [Ii][Gg][Uu][Aa][Ll][Ee][Ss]'?';
EQ: '==';
ASSIGN: '=';

LBRACK: '[';
RBRACK: ']';
COMMA: ',';

PAR_OPEN: '(';
PAR_CLOSE: ')';
SEMICOLON: ';';


// --- Fragmentos ---
fragment ID_START : [a-z];
fragment ID_CHAR  : [a-zA-Z0-9_&@];

// IDs válidos (<= 10)
ID
  : ID_START ID_CHAR*
    { getText().length() <= 10 }?
  ;

// IDs inválidos (> 10) => conviértelos a token ERROR
INVALID_ID
  : ID_START ID_CHAR*
    { getText().length() > 10 }?
    -> type(ERROR)
  ;


NUMBER: [0-9]+;
BOOLEAN: [Tt][Rr][Uu][Ee] | [Ff][Aa][Ll][Ss][Ee];

// Comentario de línea (en canal oculto)
COMMENT: '//' ~[\r\n]* -> channel(HIDDEN);

// NEWLINE preservado para poder usarlo como separador de sentencia
NEWLINE: '\r'? '\n';

// Espacios/tabuladores ignorados
WS: [ \t]+ -> skip;


ERROR : . ;
