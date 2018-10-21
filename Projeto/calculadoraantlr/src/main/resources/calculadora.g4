grammar calculadora;

/*
*Analisador sintático
**/

prog: expr;
expr: expr op=(MULT|DIV) expr     # OpBin
    | expr op=(MAIS|MENOS) expr   # OpBin
    | PARENTABER expr PARENTFECH  # par
    | INT                         # num
    ;

/*
*Analisador léxico
**/

INT : ('0'..'9')+ ;
MAIS : '+' ;
MENOS : '-' ;
MULT : '*' ;
DIV : '/' ;
PARENTABER : '(' ;
PARENTFECH : ')' ;