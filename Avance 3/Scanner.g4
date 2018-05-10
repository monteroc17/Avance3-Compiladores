lexer grammar Scanner; //Gramatica para lexer


//simbolos
PyCOMA : ';';//expresion regular para reconocer un';'
COMA : ',';
ASIGNACION : '=';
MAYIGUAL : '=>';
MENIGUAL : '=<';
IGUAL : '==';
MAYOR : '>';
MENOR : '<';
DOSPTOS : ':';//dos puntoa
SUM : '+';//suma
RES : '-';//resta
MUL : '*';//multiplicacion
DIV : '/';//division
PARIZQ : '(';//parentesis izquierdo
PARDER : ')';//parentesis derecho
PARCUADIZQ : '[';//parentesis izquierdo
PARCUADDER : ']';//parentesis derecho
CORCHETEIZQ : '{';
CORCHETEDER : '}';
COMILLADOBLE : '"';
//INICIOCOMANI : '/*';//inicio de comentario anidado
//FINCOMANI : '*/';//fin de comentario anidado
//SALTOLINEA : '\n';

//Palabras reservadas
IF : 'if';//expresion para reconocer un if
WHILE : 'while';
LET : 'let';
ELSE : 'else';
RETURN : 'return';
TRUE : 'true';
FALSE : 'false';
FN : 'fn';//Posible solucion
PUTS : 'puts';
LEN : 'len';
FIRST : 'first';
LAST : 'last';
REST : 'rest';
PUSH : 'push';
INT: '0' | '1'..'9' ('0'..'9')*;
STRING : '"' ('""'|~'"')* '"' ;
ID : LETTER (LETTER|DIGIT)*;//expresion regular para reconocer identificadores

fragment LETTER : 'a'..'z'| 'A'..'Z';//macro para reconocer letras
fragment DIGIT : '0'..'9';//macro para reconocer letras

COMENTARIO_LINEA:   '//'.*?'\r'?'\n' -> skip ;

COMENTARIO_MULTILINEA: '/*'( COMENTARIO_MULTILINEA | . )*?'*/' -> skip;
WS : [ \t\n\r]+ -> skip; //expresion regular para el espacio en blanco. El skip significa que ignora lo que reconoció. El + significa
                        //una o más veces
LINE_COMMENT : '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN);//posible comentario de linea extraido de> https://stackoverflow.com/questions/16496579/matching-arbitrary-text-both-symbols-and-spaces-with-antlr
























