parser grammar Parser2;

options {
    tokenVocab = Scanner;
}

program : statement*                                                                #progAST
;
statement : LET letStatement                                                        #stLETAST
    | RETURN returnStatement                                                        #stRETURNAST
    | expressionStatement                                                           #stExprAST

;

letStatement : identifier ASIGNACION expression ( PyCOMA | )                                #lsAsignAST
;
returnStatement : expression ( PyCOMA | )                                           #rsExprAST
;
expressionStatement : expression ( PyCOMA | )                                       #esExprAST
;
expression: additionExpression comparison                                          #exprAddAST
;
comparison
locals[String comparacion = ""]
: (compOperator additionExpression)*                                               #compAST
;
additionExpression: multiplicationExpression additionFactor                        #addExprAST
;
additionFactor : (addOperator multiplicationExpression)*                            #addFactorAST
;
multiplicationExpression: elementExpression multiplicationFactor                   #multExprAST
;
multiplicationFactor : (mulOperator elementExpression)*                             #multFactorAST
;
elementExpression:  (primitiveExpression elementAccess)                            #elemExprPEElemAccessAST
                    |(primitiveExpression callExpression )                          #elemExprPECallExpAST
                    |(primitiveExpression)                                          #elemExprPEEmptyAST
;
elementAccess: PARCUADIZQ expression PARCUADDER                                    #elemAccessAST
;
callExpression: PARIZQ expressionList PARDER                                      #callExprAST
;
primitiveExpression:
      INT                                                                           #pExprINTAST
    | STRING                                                                        #pExprSTRINGAST
    | ID                                                                            #pExprIDAST
    | TRUE                                                                          #pExprTRUEAST
    | FALSE                                                                         #pExprFALSEAST
    | PARIZQ expression PARDER                                                      #pExprGroupAST
    | arrayLiteral                                                                  #pExprArrayLitAST
    | arrayFunctions PARIZQ expressionList PARDER                                   #pExprArrayFuncAST
    | functionLiteral                                                               #pExprFuncLitAST
    | hashLiteral                                                                   #pExprHashLitAST
    | printExpression                                                               #pExprPrintExprAST
    | ifExpression                                                                  #pExprIfExprAST
;
arrayFunctions
locals [boolean push = false]
:
    LEN                                                                             #afLENAST
    | FIRST                                                                         #afFIRSTAST
    | LAST                                                                          #afLASTAST
    | REST                                                                          #afRESTAST
    | PUSH                                                                          #afPUSHAST
;
arrayLiteral : PARCUADIZQ expressionList PARCUADDER                                 #arrayLitAST
;
functionLiteral : FN PARIZQ functionParameters PARDER blockStatement                #funcLitAST
;
functionParameters	: identifier moreIdentifiers                                            #funcParamAST
;
moreIdentifiers	: (COMA identifier)*                                                #moreIdentsAST
;
hashLiteral		: CORCHETEIZQ hashContent moreHashContent CORCHETEDER               #hashLitAST
;
hashContent	: expression DOSPTOS expression                                         #hashContentAST
;
moreHashContent	: (COMA hashContent)*                                               #moreHashContentAST
;
expressionList
locals [int cont = 0, boolean esPush = false, boolean esAF = false]
:
    expression moreExpressions                                                      #exprListMoreExprAST
    |                                                                               #exprListEmptyAST
;
moreExpressions
locals [int cont = 0, boolean esAF = false]
: (COMA expression)*                                                #moreExprAST
;
printExpression : PUTS PARIZQ expression PARDER                                     #printExprAST
;
ifExpression : IF expression blockStatement (ELSE blockStatement | )                #ifExprAST
;

blockStatement
locals [boolean returns = false]
: CORCHETEIZQ statement* CORCHETEDER                                                #blockStatementAST
;
addOperator : SUM                                                                   #opSumAST
        | RES                                                                       #opSubAST
        ;
mulOperator : MUL                                                                   #opMulAST
        | DIV                                                                       #opDivAST
        ;
compOperator: MENOR|MAYOR|MENIGUAL|MAYIGUAL|IGUAL                                   #opCompAST
;
identifier
locals [ParserRuleContext decl=null]
        : ID                                                                        #idAST
        ;