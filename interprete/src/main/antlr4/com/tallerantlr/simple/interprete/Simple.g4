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

    // int varDeclCount = 0;      // ← REMOVE
    int hazCount = 0;             // ← NEW

    static String sig(String name, int arity) { return name + "#" + arity; }

    public boolean executeOnParse = true;
    public boolean enforceVarDecl = true;   // mantenemos el nombre por compatibilidad
    
     public Map<String, ProcedureDef> getProcTable() {
        return procTable;
    }
}


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
        if (executeOnParse) {
          if (enforceVarDecl && hazCount == 0) {
            throw new RuntimeException("Error: el programa debe declarar al menos una variable con 'Haz'.");
          }
          for (ASTNode n : $body) n.execute(globals);
        }
      }
    ;


// ======= Sentencias =======
statement
returns [ASTNode node]

    :   printlnStmt     { $node = $printlnStmt.node; }
    |   varAssign       { $node = $varAssign.node; }
    |   procCallStmt    { $node = $procCallStmt.node; }
    |   siStmt          { $node = $siStmt.node; }
    |   hazHastaStmt    { $node = $hazHastaStmt.node; }
    |   hastaStmt       { $node = $hastaStmt.node; }
    |   hazMientrasStmt { $node = $hazMientrasStmt.node; }
    |   mientrasStmt    { $node = $mientrasStmt.node; }
    |   hazStmt             { $node = $hazStmt.node; }
    |   inicStmt            { $node = $inicStmt.node; }
    |   incStmt             { $node = $incStmt.node; }
    |   avanzaStmt          { $node = $avanzaStmt.node; }
    |   retrocedeStmt       { $node = $retrocedeStmt.node; }
    |   giraDerechaStmt     { $node = $giraDerechaStmt.node; }
    |   giraIzquierdaStmt   { $node = $giraIzquierdaStmt.node; }
    |   ocultaTortugaStmt   { $node = $ocultaTortugaStmt.node; }
    |   ponPosStmt          { $node = $ponPosStmt.node; }
    |   ponRumboStmt        { $node = $ponRumboStmt.node; }
    |   rumboStmt           { $node = $rumboStmt.node; }
    |   ponXStmt            { $node = $ponXStmt.node; }
    |   ponYStmt            { $node = $ponYStmt.node; }
    |   bajaLapizStmt       { $node = $bajaLapizStmt.node; }
    |   subeLapizStmt       { $node = $subeLapizStmt.node; }
    |   ponColorLapizStmt   { $node = $ponColorLapizStmt.node; }
    |   centroStmt          { $node = $centroStmt.node; }
    |   esperaStmt          { $node = $esperaStmt.node; }
    |   ejecutaStmt         { $node = $ejecutaStmt.node; }
    |   repiteStmt          { $node = $repiteStmt.node; }     
    ;

repiteStmt
returns [ASTNode node]
@init { java.util.List<ASTNode> body = new java.util.ArrayList<>(); }
    :   REPITE n=expression
        (SEP | EOL)*           // ← permite salto(s) antes de [
        LBRACK
            (SEP | EOL)*       // ← NUEVO: líneas en blanco al inicio
            (   s=statement
                { if ($s.node != null) body.add($s.node); }
                (SEP | EOL)*   // ← NUEVO: separadores tras cada sentencia
            )+
        RBRACK
        (SEP)?
        { $node = new Repite($n.node, body); }
    ;


ejecutaStmt
returns [ASTNode node]
@init { java.util.List<ASTNode> list = new java.util.ArrayList<>(); }
    :   EJECUTA
        (SEP | EOL)*           // ← permite salto(s) antes de [
        LBRACK
            (SEP | EOL)*       // ← NUEVO: líneas en blanco al inicio
            (   s=statement
                { if ($s.node != null) list.add($s.node); }
                (SEP | EOL)*   // ← NUEVO: separadores tras cada sentencia
            )+
        RBRACK (SEP)?
        { $node = new Ejecuta(list); }
    ;

  
esperaStmt
returns [ASTNode node]
    :   ESPERA e=expression (SEP)?
        { $node = new Espera($e.node); }
    ;
    

centroStmt
returns [ASTNode node]
    :   CENTRO (SEP)?
        { $node = new Centro(); }
    ;


ponColorLapizStmt
returns [ASTNode node]
    :   (PONCOLORLAPIZ | PONCL) e=expression (SEP)?
        { $node = new PonColorLapiz($e.node); }
    ;


subeLapizStmt
returns [ASTNode node]
    :   (SUBELAPIZ | SB) (SEP)?
        { $node = new SubeLapiz(); }
    ;


bajaLapizStmt
returns [ASTNode node]
    :   (BAJALAPIZ | BL) (SEP)?
        { $node = new BajaLapiz(); }
    ;


ponYStmt
returns [ASTNode node]
    :   PONY e=expression (SEP)?
        { $node = new PonY($e.node); }
    ;


ponXStmt
returns [ASTNode node]
    :   PONX e=expression (SEP)?
        { $node = new PonX($e.node); }
    ;


rumboStmt
returns [ASTNode node]
    :   RUMBO (SEP)?
        { $node = new Rumbo(); }
    ;


ponRumboStmt
returns [ASTNode node]
    :   PONRUMBO e=expression (SEP)?
        { $node = new PonRumbo($e.node); }
    ;


// PonPOS [ X Y ]
// PonXY  X Y
ponPosStmt
returns [ASTNode node]
    :   PONPOS LBRACK x=expression y=expression RBRACK (SEP)?
        { $node = new PonPos($x.node, $y.node); }
    |   PONXY  x=expression y=expression (SEP)?
        { $node = new PonPos($x.node, $y.node); }
    ;


ocultaTortugaStmt
returns [ASTNode node]
    :   (OCULTATORTUGA | OT) (SEP)?
        { $node = new OcultaTortuga(); }
    ;


giraIzquierdaStmt
returns [ASTNode node]
    :   (GIRAIZQUIERDA | GI) e=expression (SEP)?
        { $node = new GiraIzquierda($e.node); }
    ;


giraDerechaStmt
returns [ASTNode node]
    :   (GIRADERECHA | GD) e=expression (SEP)?
        { $node = new GiraDerecha($e.node); }
    ;


retrocedeStmt
returns [ASTNode node]
    :   (RETROCEDE | RE) e=expression (SEP)?
        { $node = new Retrocede($e.node); }
    ;


incStmt
returns [ASTNode node]
    :   INC LBRACK n1=ID RBRACK (SEP)?
        { $node = new Inc($n1.text, new Constant(1)); }            // inc [var]
    |   INC LBRACK n1=ID n2=expression RBRACK (SEP)?
        { $node = new Inc($n1.text, $n2.node); }                    // inc [var expr]
    ;


avanzaStmt
returns [ASTNode node]
    :   (AVANZA | AV) e=expression (SEP)?
        { $node = new Avanza($e.node); }
    ;



inicStmt
returns [ASTNode node]
    :   INIC id=ID ASSIGN v=expression (SEP)?
        { $node = new InicAssign($id.text, $v.node); }
    ;


hazStmt
returns [ASTNode node]
    :   // HAZ con inicialización en la misma línea
        HAZ id=ID v=expression (SEP)?
        {
            hazCount++;
            $node = new HazAssign($id.text, $v.node);
        }
    |   // HAZ solo declara (sin valor)
        HAZ id=ID (SEP)?
        {
            hazCount++;
            $node = new HazAssign($id.text, null);
        }
    ;


// println( expr )
printlnStmt
returns [ASTNode node]
    :   PRINTLN PAR_OPEN expression PAR_CLOSE (SEP)? 
        { $node = new Println($expression.node); }
    ;


// x = expr
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
            /* sin argumentos */
            { argNodes = new java.util.ArrayList<>(); arity = 0; }
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

siStmt
returns [ASTNode node]
@init {
    java.util.List<ASTNode> thenBody = new java.util.ArrayList<ASTNode>();
    java.util.List<ASTNode> elseBody = new java.util.ArrayList<ASTNode>();
    boolean hasElse = false;
}
    :   SI PAR_OPEN cond=expression PAR_CLOSE
        (SEP | EOL)*           // ← permite salto(s) tras la cabecera
        LBRACK
            (SEP | EOL)*       // ← NUEVO: líneas en blanco al inicio del bloque THEN
            (   s1=statement
                { if ($s1.node != null) thenBody.add($s1.node); }
                (SEP | EOL)*   // separadores tras cada sentencia
            )*
        RBRACK
        ( (SEP | EOL)*         // separadores entre THEN y ELSE
          LBRACK
            (SEP | EOL)*       // ← NUEVO: líneas en blanco al inicio del bloque ELSE
            (   s2=statement
                { if ($s2.node != null) elseBody.add($s2.node); }
                (SEP | EOL)*   // separadores tras cada sentencia
            )*
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

// HAZ.MIENTRAS
//   [ ...cuerpo... ]
// ( condicion )   // debe evaluar a Boolean
hazMientrasStmt
returns [ASTNode node]
@init {
    java.util.List<ASTNode> body = new java.util.ArrayList<ASTNode>();
}
    :   HAZMIENTRAS
        (SEP | EOL)*                     // permite saltos después de HAZ.MIENTRAS
        LBRACK
            (SEP | EOL)*                 // líneas en blanco al inicio del bloque
            (   s=statement
                { if ($s.node != null) body.add($s.node); }
                (SEP | EOL)*             // separadores tras cada sentencia
            )*
        RBRACK
        (SEP | EOL)*                     // separadores entre bloque y condición
        PAR_OPEN cond=expression PAR_CLOSE
        (SEP)?
        {
            $node = new DoWhileStmt($cond.node, body);
        }
    ;
    
// MIENTRAS (condicion)
// [ ...cuerpo... ]
mientrasStmt
returns [ASTNode node]
@init {
    java.util.List<ASTNode> body = new java.util.ArrayList<ASTNode>();
}
    :   MIENTRAS
        (SEP | EOL)*                         // separadores tras 'MIENTRAS'
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
            $node = new WhileStmt($cond.node, body);
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
        (EOL)*                               // ← NUEVO: permitir saltos de línea
        (
            p=optParams (EOL)*               // ← si viene '[', parsea parámetros
            { $formalParams = $p.ids; }
          |
            /* sin parámetros */
            { $formalParams = new java.util.ArrayList<String>(); }
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

// ======= Expresiones =======
expression
returns [ASTNode node]
 :   left=relExpr
        {
            $node = $left.node;
        }
        ( EQ right=relExpr
            {
                $node = new Equal($node, $right.node);
            }
        )*
    |   s=sumaExpr
        {
            $node = $s.node;
        }
    |   productoExpr                    
    	{ 
    		$node = $productoExpr.node; 
    	}


    |   d=divisionExpr
        {
            $node = $d.node;
        }
    ;
    
   
productoExpr
returns [ASTNode node]
@init { java.util.List<ASTNode> nodes = new java.util.ArrayList<>(); }
    :   PRODUCTO e1=addExpr
        (e2=addExpr { nodes.add($e2.node); })*
      {
        nodes.add(0, $e1.node);       
        $node = new Producto(nodes);  
      }
    ;   
    
divisionExpr
returns [ASTNode node]
    :   DIVISION e1=addExpr e2=addExpr
        { $node = new Division($e1.node, $e2.node); }
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
        | AT   t3=multExpr              { $node = new Sumaplicacion($node, $t3.node); }
        )*
    ;

multExpr
returns [ASTNode node]
    :   t1=term                         { $node = $t1.node; }
        ( MULT t2=term                  { $node = new Multiplication($node, $t2.node); }
        | PERM t3=term                  { $node = new Permutation($node, $t3.node); }
        | DIV  t3=term                  { $node = new Division($node, $t3.node); }   // división infija
        | PERM t4=term                  { $node = new Permutation($node, $t4.node); }
        )*
    ;
    
    
   

// ======== Operaciones nuevas ========
term
returns [ASTNode node]
    :   NUMBER
          { $node = new Constant(Integer.parseInt($NUMBER.text)); }

    |   BOOLEAN
          { $node = new Constant(Boolean.parseBoolean($BOOLEAN.text.toLowerCase())); }

    |   STRING
          {
            String txt = $STRING.text;
            String content = txt.substring(1, txt.length()-1).replace("\\\"", "\"");
            $node = new Constant(content);
          }
          
    |   'Producto' PAR_OPEN first=expression (COMMA rest+=expression)* PAR_CLOSE
          {
            java.util.List<ASTNode> factors = new java.util.ArrayList<>();
            factors.add($first.node);
            if ($rest != null) {
              for (SimpleParser.ExpressionContext r : $rest) factors.add(r.node);
            }
            $node = new Producto(factors);
          }

    |   'Division' PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
          { $node = new Division($e1.node, $e2.node); }

    |   ID
          { $node = new VarRef($ID.text, $ID.getLine(), $ID.getCharPositionInLine()); }


    |   PAR_OPEN expression PAR_CLOSE
          { $node = $expression.node; }

    // Funciones/operadores “palabra ( … )”
    |   'Y' PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
          { $node = new And($e1.node, $e2.node); }

    |   'O' PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
          { $node = new Or($e1.node, $e2.node); }

    |   'Potencia' PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
          { $node = new Potencia($e1.node, $e2.node); }

    |   'Diferencia' PAR_OPEN first=expression (COMMA rest+=expression)* PAR_CLOSE
          {
            java.util.List<ASTNode> terms = new java.util.ArrayList<>();
            terms.add($first.node);
            if ($rest != null) {
              for (SimpleParser.ExpressionContext r : $rest) terms.add(r.node);
            }
            $node = new Diferencia(terms);
          }

    // Palabras estilo operador infijo simple
    |   MAYORQUEQ a=addExpr b=addExpr           { $node = new GreaterThan($a.node, $b.node); }
    |   MENORQUEQ a=addExpr b=addExpr           { $node = new LessThan($a.node, $b.node); }
    |   IGUALESQ  e1=expression e2=expression   { $node = new Equal($e1.node, $e2.node); }

    // azar como función palabra
    |   AZAR e=expression                       { $node = new Azar($e.node); }
    ;

// ======= Nuevas reglas integradas: sumaExpr y exprList =======
// forma: suma 1 2 3 ...
sumaExpr
returns [ASTNode node]
    :   SUMA exprList                   { $node = new Suma($exprList.list); }
    ;


// Lista de argumentos para suma (usa addExpr para respetar prioridad de operadores)
exprList
returns [List<ASTNode> list]
@init { $list = new ArrayList<>(); }
    :   e1=addExpr { $list.add($e1.node); }
        ( e2=addExpr { $list.add($e2.node); } )*
    ;

// ======= Utilitarios =======
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
HAZ: [Hh] 'az';  
INIC: 'inic';
INC: [Ii][Nn][Cc];
AZAR: [Aa][Zz][Aa][Rr];
AVANZA: [Aa][Vv][Aa][Nn][Zz][Aa];
AV:     [Aa][Vv];
RETROCEDE: [Rr][Ee][Tt][Rr][Oo][Cc][Ee][Dd][Ee];
RE:        [Rr][Ee];
GIRADERECHA: [Gg][Ii][Rr][Aa][Dd][Ee][Rr][Ee][Cc][Hh][Aa];
GD:          [Gg][Dd];
GIRAIZQUIERDA: [Gg][Ii][Rr][Aa][Ii][Zz][Qq][Uu][Ii][Ee][Rr][Dd][Aa];
GI:             [Gg][Ii];
OCULTATORTUGA: [Oo][Cc][Uu][Ll][Tt][Aa][Tt][Oo][Rr][Tt][Uu][Gg][Aa];
OT:             [Oo][Tt];
PONPOS: [Pp][Oo][Nn][Pp][Oo][Ss];
PONXY:  [Pp][Oo][Nn][Xx][Yy];
PONRUMBO: [Pp][Oo][Nn][Rr][Uu][Mm][Bb][Oo];
RUMBO: [Rr][Uu][Mm][Bb][Oo];
PONX: [Pp][Oo][Nn][Xx];
PONY: [Pp][Oo][Nn][Yy];
BAJALAPIZ: [Bb][Aa][Jj][Aa][Ll][Aa][Pp][Ii][Zz];
BL:        [Bb][Ll];
SUBELAPIZ: [Ss][Uu][Bb][Ee][Ll][Aa][Pp][Ii][Zz];
SB:        [Ss][Bb];
PONCOLORLAPIZ: [Pp][Oo][Nn][Cc][Oo][Ll][Oo][Rr][Ll][Aa][Pp][Ii][Zz];
PONCL:        [Pp][Oo][Nn][Cc][Ll];
CENTRO: [Cc][Ee][Nn][Tt][Rr][Oo];
ESPERA: [Ee][Ss][Pp][Ee][Rr][Aa];
EJECUTA: [Ee][Jj][Ee][Cc][Uu][Tt][Aa];
REPITE: [Rr][Ee][Pp][Ii][Tt][Ee];
DIVISION: 'división';   
DIV: '/'; 
PRODUCTO : 'producto';
SUMA: 'suma';



STRING
  : '"' ( '\\"' | ~["\r\n] )* '"'   // cadena con \" escapado
  ;

PLUS: '+';
MINUS: '-';
MULT: '*';
AT: '@';


SI : 'SI' ;

HAZHASTA : 'HAZ.HASTA' ;
HASTA : 'HASTA' ;

HAZMIENTRAS : 'HAZ.MIENTRAS' ;
MIENTRAS : 'MIENTRAS' ;

GT : '>' ;
LT : '<' ;
MAYORQUEQ : 'mayorque?' | 'MayorQue?' ;
MENORQUEQ : 'menorque?' | 'MenorQue?' ;


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

// IDs v�lidos (<= 10)
ID
  : ID_START ID_CHAR*
    { getText().length() <= 10 }?
  ;

// IDs inv�lidos (> 10) => ERROR
INVALID_ID
  : ID_START ID_CHAR*
    { getText().length() > 10 }?
    -> type(ERROR)
  ;

NUMBER: [0-9]+;
BOOLEAN: [Tt][Rr][Uu][Ee] | [Ff][Aa][Ll][Ss][Ee];

COMMENT: '//' ~[\r\n]* -> channel(HIDDEN);

NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;

ERROR : . ;
