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
    Map<String, Object> globals = new HashMap<>();
    Map<String, ProcedureDef> procTable = new HashMap<>();
    int varDeclCount = 0;

    static String sig(String name, int arity) { return name + "#" + arity; }
}

// ======= Reglas de alto nivel =======

program
returns [List<ASTNode> body]
@init { $body = new ArrayList<>(); }
    :   (EOL)*
        (   (p=procedureDef)
          | (s=statement { if ($s.node != null) $body.add($s.node); })
        )
        (   (SEP | EOL)+
            (   (p2=procedureDef)
              | (s2=statement { if ($s2.node != null) $body.add($s2.node); })
            )
        )*
        (SEP | EOL)*
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
    ;

printlnStmt
returns [ASTNode node]
    :   PRINTLN PAR_OPEN expression PAR_CLOSE (SEP)?
        { $node = new Println($expression.node); }
    ;

varDecl
returns [ASTNode node]
    :   VAR id=ID (ASSIGN e=expression)? (SEP)?
        {
            varDeclCount++;
            if ($e.node != null) $node = new VarAssign($id.text, $e.node);
            else                 $node = new VarDecl($id.text);
        }
    ;

varAssign
returns [ASTNode node]
    :   id=ID ASSIGN e=expression (SEP)?
        { $node = new VarAssign($id.text, $e.node); }
    ;

procCallStmt
returns [ASTNode node]
@init {
    java.util.List<ASTNode> argNodes = new java.util.ArrayList<>();
    int arity = 0;
}
    :   id=ID
        (
            PAR_OPEN a=argList PAR_CLOSE
            {
                argNodes = $a.list;
                arity = argNodes.size();
            }
          |
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

procedureDef
returns [java.util.List<String> formalParams]
@init {
    java.util.List<ASTNode> body = new java.util.ArrayList<ASTNode>();
    $formalParams = new java.util.ArrayList<String>();
}
    :   PARA name=ID
        (
            p=optParams (EOL)*
            {
                $formalParams = $p.ids;
            }
          |
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
@init { $ids = new ArrayList<>(); }
    :   LBRACK
          ( id1=ID           { $ids.add($id1.text); }
            ( COMMA idn=ID   { $ids.add($idn.text); } )*
          )?
        RBRACK
    ;

// ======= Expresiones =======

expression
returns [ASTNode node]
    :   sumaExpr                        { $node = $sumaExpr.node; }
    |   a=addExpr                       { $node = $a.node; }
        ( EQ b=addExpr                  { $node = new Equal($node, $b.node); } )*
    ;

// forma: suma 1 2 3 ...
sumaExpr
returns [ASTNode node]
    :   SUMA exprList                   { $node = new Suma($exprList.list); }
    ;

addExpr
returns [ASTNode node]
    :   t1=multExpr                     { $node = $t1.node; }
        ( PLUS t2=multExpr              { $node = new Addition($node, $t2.node); }
        | AT   t3=multExpr              { $node = new Sumaplicacion($node, $t3.node); }
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
    ;

// ======= Utilitarios =======

exprList
returns [List<ASTNode> list]
@init { $list = new ArrayList<>(); }
    :   e1=addExpr { $list.add($e1.node); }
        ( e2=addExpr { $list.add($e2.node); } )*
    ;

argList
returns [List<ASTNode> list]
@init { $list = new ArrayList<>(); }
    :   e1=expression { $list.add($e1.node); }
        ( COMMA en=expression { $list.add($en.node); } )*
    ;

// ======= Tokens =======

SEP : SEMICOLON ;
EOL : NEWLINE ;

PARA: 'para';
FIN:  'fin';
VAR:  'var';
PRINTLN: 'println';
SUMA: 'suma';

PLUS: '+';
MINUS: '-';
MULT: '*';
AT: '@';

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

ID
  : ID_START ID_CHAR*
    { getText().length() <= 10 }?
  ;

INVALID_ID
  : ID_START ID_CHAR*
    { getText().length() > 10 }?
    -> type(ERROR)
  ;

NUMBER: [0-9]+;
BOOLEAN: 'true' | 'false';

COMMENT: '//' ~[\r\n]* -> channel(HIDDEN);

NEWLINE: '\r'? '\n';

WS: [ \t]+ -> skip;

ERROR : . ;
