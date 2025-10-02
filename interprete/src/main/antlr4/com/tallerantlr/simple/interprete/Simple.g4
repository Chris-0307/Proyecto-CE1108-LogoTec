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
    |   siStmt          { $node = $siStmt.node; }
    |   hazHastaStmt    { $node = $hazHastaStmt.node; }
    |   hastaStmt       { $node = $hastaStmt.node; }
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
    :   VAR id=ID (ASSIGN e=expression)? (SEP)?
        {
            varDeclCount++;
            if ($e.node != null) $node = new VarAssign($id.text, $e.node);
            else                 $node = new VarDecl($id.text);
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

// Condicional:
// SI (cond) [ ...then... ] [ ...else... ]
siStmt
returns [ASTNode node]
@init {
    java.util.List<ASTNode> thenBody = new java.util.ArrayList<ASTNode>();
    java.util.List<ASTNode> elseBody = new java.util.ArrayList<ASTNode>();
    boolean hasElse = false;
}
    :   SI PAR_OPEN cond=expression PAR_CLOSE
        LBRACK
            ( s1=statement { if ($s1.node != null) thenBody.add($s1.node); } (SEP | EOL)* )*
        RBRACK
        ( (SEP | EOL)*                                   // separadores opcionales entre bloques
          LBRACK
            ( s2=statement { if ($s2.node != null) elseBody.add($s2.node); } (SEP | EOL)* )*
          RBRACK
          { hasElse = true; }
        )?
        (SEP)?
        {
            $node = hasElse
                ? new IfElseStmt($cond.node, thenBody, elseBody)
                : new IfStmt($cond.node, thenBody);
        }
    ;
    
// HAZ.HASTA
//   [ ...cuerpo... ]
// ( condicion )
hazHastaStmt
returns [ASTNode node]
@init {
    java.util.List<ASTNode> body = new java.util.ArrayList<ASTNode>();
}
    :   HAZHASTA
        (SEP | EOL)*                     // << permite salto(s) de línea después de HAZ.HASTA
        LBRACK
            (SEP | EOL)*                 // << permite líneas en blanco al inicio del bloque
            (   s=statement
                { if ($s.node != null) body.add($s.node); }
                (SEP | EOL)*             // separadores tras cada sentencia (incluye líneas en blanco)
            )*
        RBRACK
        (SEP | EOL)*                     // separadores opcionales entre bloque y condición
        PAR_OPEN cond=expression PAR_CLOSE
        (SEP)?
        {
            $node = new DoUntilStmt($cond.node, body);
        }
    ;

// HASTA (condicion)
// [ ...cuerpo... ]
hastaStmt
returns [ASTNode node]
@init {
    java.util.List<ASTNode> body = new java.util.ArrayList<ASTNode>();
}
    :   HASTA
        (SEP | EOL)*                         // separadores opcionales tras 'HASTA'
        PAR_OPEN cond=expression PAR_CLOSE
        (SEP | EOL)*
        LBRACK
            (SEP | EOL)*                     // líneas en blanco al inicio del bloque
            (   s=statement
                { if ($s.node != null) body.add($s.node); }
                (SEP | EOL)*                 // separadores tras cada sentencia
            )*
        RBRACK
        (SEP)?
        {
            $node = new UntilStmt($cond.node, body);
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
    :   left=relExpr                     { $node = $left.node; }
        ( EQ right=relExpr               { $node = new Equal($node, $right.node); } )*
    ;

relExpr
returns [ASTNode node]
    :   a=addExpr                        { $node = $a.node; }
        (   GT b=addExpr                 { $node = new GreaterThan($node, $b.node); }
        |   LT b=addExpr                 { $node = new LessThan($node, $b.node); }
        )*
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
    :   NUMBER                          { $node = new Constant(Integer.parseInt($NUMBER.text)); }
    |   BOOLEAN                         { $node = new Constant(Boolean.parseBoolean($BOOLEAN.text)); }
    |   ID                              { $node = new VarRef($ID.text); }
    |   PAR_OPEN expression PAR_CLOSE   { $node = $expression.node; }
    
    // --- Formas prefijas mayorque?/menorque?
    |   MAYORQUEQ a=addExpr b=addExpr   { $node = new GreaterThan($a.node, $b.node); }
    |   MENORQUEQ a=addExpr b=addExpr   { $node = new LessThan($a.node, $b.node); }
    
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

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
AT: '@';


SI : 'SI' ;
HAZHASTA : 'HAZ.HASTA' ;
HASTA : 'HASTA' ;


GT : '>' ;
LT : '<' ;
MAYORQUEQ : 'mayorque?' | 'MayorQue?' ;
MENORQUEQ : 'menorque?' | 'MenorQue?' ;


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
BOOLEAN: 'true' | 'false';

// Comentario de línea (en canal oculto)
COMMENT: '//' ~[\r\n]* -> channel(HIDDEN);

// NEWLINE preservado para poder usarlo como separador de sentencia
NEWLINE: '\r'? '\n';

// Espacios/tabuladores ignorados
WS: [ \t]+ -> skip;


ERROR : . ;
