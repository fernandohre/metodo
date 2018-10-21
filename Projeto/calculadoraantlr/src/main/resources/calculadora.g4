grammar calculadora;

prog: expr;
expr: expr op=('*'|'/') expr  # OpBin
    | expr op=('+'|'-') expr  # OpBin
    | '(' expr ')'            # par
    | INT                     # num
    ;

INT : ('0'..'9')+ ;