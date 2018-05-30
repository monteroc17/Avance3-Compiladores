package checker;
import generated.Parser2;
import generated.Parser2BaseVisitor;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Checker extends Parser2BaseVisitor{
    private SymbolTable tablaIDs = null;

    public Checker(){
        this.tablaIDs = new SymbolTable();
    }

    /*
    * Permite retornar un mensaje de error a partir del error obtenido
    * */
    public String Print_Errors(int error_code, int fila, int columna){
        switch (error_code){
            case 2:
                return "Tipo no válido en hash en fila: "+fila+", columna: "+columna;
            case 3 :
                return "La función llamada no retorna un valor en fila: "+fila+", columna: "+columna;
            case 4:
                return "No se puede declarar un identificador con ese nombre en fila: "+fila+", columna: "+columna;
            case 5:
                return "Ese identificador ya fue declarado en fila: "+fila+", columna: "+columna;
            case 6:
                return "Ese indetificador no ha sido declarado en fila: "+fila+", columna: "+columna;
            case 7:
                return "Error de tipos, valores no compatibles en fila: "+fila+", columna: "+columna;
            case 8:
                return "Esa función no ha sido declarada en fila: "+fila+", columna: "+columna;
            case 9:
                return "La variable que intenta retornar no ha sido declarada en la función en fila: "+fila+", columna: "+columna;
            case 10:
                return "Valor incompatible para acceder a la lista en fila: "+fila+", columna: "+columna;
            case 11:
                return "La expresión del IF no es válida en fila: "+fila+", columna: "+columna;
            case 12:
                return "Expresion no valida en fila: "+fila+", columna: "+columna;
            case 13:
                return "La expresión no tiene los argumentos correctos en fila: "+fila+", columna: "+columna;
            default:
                return "Error no identificado en fila: "+fila+", columna: "+columna;
        }
    }

    @Override
    public Object visitProgAST(Parser2.ProgASTContext ctx) {
        for(Parser2.StatementContext ele : ctx.statement()){
            visit(ele);
        }
        return null;
    }

    @Override
    public Object visitStLETAST(Parser2.StLETASTContext ctx) {
        int type =(Integer) visit(ctx.letStatement());
        return type;
    }

    @Override
    public Object visitStRETURNAST(Parser2.StRETURNASTContext ctx) {
        int type =(Integer)visit(ctx.returnStatement());
        return type;
    }

    @Override
    public Object visitStExprAST(Parser2.StExprASTContext ctx) {
        int type =(Integer) visit(ctx.expressionStatement());
        return type;
    }

    @Override
    public Object visitLsAsignAST(Parser2.LsAsignASTContext ctx) {
        int tipo=-1;
        SymbolTable.Ident i = this.tablaIDs.buscar(ctx.identifier().getText());
        if(i!=null){//Si la variable ya exisitia, comparar tipos y actualizar valor
            //compara si la variable existe
            tipo = (Integer) visit(ctx.expression());
            if(i.type!=tipo){//si el tipo es diferente
                System.out.println(Print_Errors(7, ((Token) ctx.identifier()).getLine(), ((Token) ctx.identifier()).getCharPositionInLine()));
            }
        }
        else{//si se esta declarando una variable nueva
            this.tablaIDs.insertar(ctx.identifier().getText(),0,ctx);
            tipo = (Integer) visit(ctx.expression());
            this.tablaIDs.eliminar(ctx.identifier().getText());
            if(tipo!=-1){
                if (tipo==8)//si lo que viene es una funcion con retorno
                    tipo=0;//transformela a neutro
                this.tablaIDs.insertar( ctx.identifier().getText(),tipo,ctx);
                //this.tablaIDs.insertar(((Token) ctx.identifier()),tipo,ctx);
                this.tablaIDs.imprimir();
            }
        }
        return tipo;
    }

    @Override
    public Object visitRsExprAST(Parser2.RsExprASTContext ctx) {
        int type = (Integer) visit(ctx.expression());
        return type;
    }

    @Override
    public Object visitEsExprAST(Parser2.EsExprASTContext ctx) {
        int type =(Integer) visit(ctx.expression());
        return type;
    }

    @Override
    public Object visitExprAddAST(Parser2.ExprAddASTContext ctx) {
        int aType = (Integer) visit(ctx.additionExpression());
        if(aType!=-1){
            int cType = (Integer) visit(ctx.comparison());
            if(cType!=-2){//si ctype es -2 no se estaba comparando con nada
                if(ctx.comparison().comparacion.equals("==")){//si es una igualdad
                    if(aType!=cType && (aType!=0 && cType!=0)){
                        System.out.println(Print_Errors(7,ctx.comparison().start.getLine(),ctx.comparison().start.getCharPositionInLine()));
                        return -1;
                    }
                } else {
                    if(aType!=cType && (aType!=0 && aType!=1) && (cType!=1 && cType!=0)){
                        System.out.println(Print_Errors(7,ctx.comparison().start.getLine(),ctx.comparison().start.getCharPositionInLine()));
                        return -1;
                    }
                }
              aType=4;
            }
        }
        else {
            return -1;
        }
        return aType;
    }

    @Override
    public Object visitCompAST(Parser2.CompASTContext ctx) {
        int type;
        if(ctx.compOperator().isEmpty()){
            type=-2;
        }else{
            ctx.comparacion = ctx.compOperator(0).getText();
            type= (Integer) visit(ctx.additionExpression(0));
            if(type!=-1){
                if(!(ctx.compOperator().size()==1)){
                    for(int i = 0;i<=ctx.compOperator().size()-1;i++){
                        int tempType = (Integer) visit(ctx.additionExpression(i+1));
                        if(tempType==-1){
                            return -1;
                        }
                        if(ctx.compOperator(i).getText().equals("==")){//nada mas deben ser iguales los tipos
                            if(type!=tempType && (type!=0 && tempType!=0)){
                                return -1;
                            }
                        } else{//los tipos deben ser iguales y deben ser enteros
                            if(type!=tempType && (type!=1 && type!=0) && (tempType!=1 && tempType!=0)){
                                return -1;
                            }
                        }
                        if(ctx.additionExpression(i+1).getText().equals(ctx.getStop().getText())){
                            break;
                        }
                    }
                }
            } else {
                System.out.println(Print_Errors(7,ctx.additionExpression(0).start.getLine(),ctx.additionExpression(0).start.getCharPositionInLine()));
                return -1;
            }
        }
        return type;
    }

    @Override
    public Object visitAddExprAST(Parser2.AddExprASTContext ctx) {
        int mType = (Integer) visit(ctx.multiplicationExpression());
        if(mType!=-1){
            if(mType==0){
                SymbolTable.Ident id = this.tablaIDs.buscar(ctx.multiplicationExpression().getText());
                if(id==null){
                    return -1;
                }
            }
            if(mType==2){
                if(ctx.additionFactor().start.getText().equals("-")){
                    System.out.println(Print_Errors(7,ctx.additionFactor().start.getLine(),ctx.additionFactor().start.getCharPositionInLine()));
                    return -1;
                }
            }
            int aType = (Integer) visit(ctx.additionFactor());

            if(aType!=mType && (mType!=0 && aType!=0)){//si son diferentes y ninguno es neutro
                if((aType!=1 && aType!=2)&&(mType!=1 && mType!=2)){
                    return -1;
                }
                if(aType==2) mType=aType;
            }
        }
        else {
            return -1;
        }
        return mType;
    }

    @Override
    public Object visitAddFactorAST(Parser2.AddFactorASTContext ctx) {
        int type = -1;
        int tempType = -1;
        if(ctx.addOperator().isEmpty()){
            type = 0;
        }else {
            type = (Integer) visit(ctx.multiplicationExpression(0));
            if(type!=-1){
                if(type==0){
                    SymbolTable.Ident id = this.tablaIDs.buscar(ctx.multiplicationExpression(0).getText());
                    if (id==null){
                        System.out.println(Print_Errors(6,ctx.multiplicationExpression(0).start.getLine(),ctx.multiplicationExpression(0).start.getCharPositionInLine())+"=>"+ctx.multiplicationExpression(0).getText());
                        return -1;
                    }
                }
                if(!(ctx.addOperator().size()==1)){
                    for(int i=0; i<= ctx.addOperator().size()-1; i++){
                        tempType = (Integer) visit(ctx.multiplicationExpression(i + 1));
                        if(tempType==0){
                            SymbolTable.Ident id = this.tablaIDs.buscar(ctx.multiplicationExpression(i + 1).getText());
                            if (id==null){
                                System.out.println(Print_Errors(6,ctx.multiplicationExpression(i+1).start.getLine(),ctx.multiplicationExpression(i+1).start.getCharPositionInLine())+"=>"+ctx.multiplicationExpression(0).getText());
                                return -1;
                            }
                        }
                        if(type==tempType){//Casos para cuando los tipos sean iguales
                            if(type==2 && !(ctx.addOperator(i).getText().equals("+"))){
                                System.out.println(Print_Errors(7,ctx.addOperator(i).start.getLine(),ctx.addOperator(i).start.getCharPositionInLine()));
                                return -1;
                            }else
                                type=tempType;
                        } else {//casos para cuando sean diferentes
                            if(ctx.addOperator(i).getText().equals("+")){//preguntar si son int y string
                                if((type==2 && (tempType==0 || tempType==1)) || (tempType==2 && (type==0 || type==1))){
                                    type=2;//se convierte en string por la concatenacion
                                } else {
                                    System.out.println(Print_Errors(7,ctx.multiplicationExpression(i).start.getLine(),ctx.multiplicationExpression(i).start.getCharPositionInLine()));
                                    return -1;
                                }
                            } else {//si no es suma
                                if(!((type==0 || tempType==0) || (type==1 || tempType==1))){
                                    //si no es una suma y ninguno es int o neutro
                                    System.out.println(Print_Errors(7,ctx.multiplicationExpression(i).start.getLine(),ctx.multiplicationExpression(i).start.getCharPositionInLine()));
                                    return -1;
                                }
                                if(type==2 || tempType==2){
                                    System.out.println(Print_Errors(7,ctx.multiplicationExpression(i).start.getLine(),ctx.multiplicationExpression(i).start.getCharPositionInLine()));
                                    return -1;
                                }
                            }
                            if(!(tempType==0))//para evitar que el resultado quede como neutro cuando no debiera
                                type=tempType;
                        }

                        if(ctx.multiplicationExpression(i+1).getText().equals(ctx.getStop().getText())){
                            break;
                        }
                    }
                }
            } else {
                System.out.println(Print_Errors(7,ctx.multiplicationExpression(0).start.getLine(),ctx.multiplicationExpression(0).start.getCharPositionInLine()));
                return -1;
            }
        }
        return type;
    }

    @Override
    public Object visitMultExprAST(Parser2.MultExprASTContext ctx) {
        int eType = (Integer) visit(ctx.elementExpression());
        if(eType!=-1){
            if(eType==0){
                SymbolTable.Ident id = this.tablaIDs.buscar(ctx.elementExpression().getText());
                if (id==null){
                    System.out.println(Print_Errors(6,ctx.elementExpression().start.getLine(),ctx.elementExpression().start.getCharPositionInLine())+"=>"+ctx.elementExpression().getText());
                    return -1;
                }
            }
            int mType = (Integer) visit(ctx.multiplicationFactor());
            if(mType!=eType && (eType!=0 & mType!=0)){
                return -1;
            }
        }
        else {
            return -1;
        }
        return eType;
    }

    @Override
    public Object visitMultFactorAST(Parser2.MultFactorASTContext ctx) {
        int type = -1;
        int tempType = -1;
        if(ctx.mulOperator().isEmpty()){
            type=0;
        }else {
            type = (Integer) visit(ctx.elementExpression(0));
            if(type!=-1){
                if(tempType==0){
                    SymbolTable.Ident id = this.tablaIDs.buscar(ctx.elementExpression(0).getText());
                    if (id==null){
                        System.out.println(Print_Errors(6,ctx.elementExpression(0).start.getLine(),ctx.elementExpression(0).start.getCharPositionInLine())+"=>"+ctx.elementExpression(0).getText());
                        return -1;
                    }
                }
                for(int i=0; i<= ctx.mulOperator().size()-1; i++){
                    if(!(i==ctx.mulOperator().size()-1)){
                        tempType = (Integer) visit(ctx.elementExpression(i + 1));

                        if(tempType==type){//si son iguales los tipos y no son int o neutro
                            if(type!=0 && type!=1){
                                System.out.println(Print_Errors(7,ctx.elementExpression(i+1).start.getLine(),ctx.elementExpression(i+1).start.getCharPositionInLine())+"=>"+ctx.elementExpression(i+1).getText());
                                return -1;
                            }
                            type=tempType;
                        }else{//si los tipos son diferentes
                            if((type!=1 && type!=0)||(tempType!=0 && tempType!=1)){//si alguno de los 2 no es ni int ni neutro
                                System.out.println(Print_Errors(7,ctx.elementExpression(i+1).start.getLine(),ctx.elementExpression(i+1).start.getCharPositionInLine())+"=>"+ctx.elementExpression(i+1).getText());
                                return -1;
                            }
                            type =tempType;
                        }
                        if(ctx.elementExpression(i+1).getText().equals(ctx.getStop().getText())){
                            break;
                        }
                    }

                }
            }else{
                System.out.println(Print_Errors(7,ctx.elementExpression(0).start.getLine(),ctx.elementExpression(0).start.getCharPositionInLine())+"=>"+ctx.elementExpression(0).getText());
                return -1;
            }
        }
        return type;
    }

    @Override
    public Object visitElemExprPEElemAccessAST(Parser2.ElemExprPEElemAccessASTContext ctx) {
        //el tipo no se verifica en esta etapa, por lo que se pasa como neutro
        int type = (Integer) visit(ctx.primitiveExpression());
        if(type == 0) {
            SymbolTable.Ident i = this.tablaIDs.buscar(ctx.primitiveExpression().getText());
            if(i!=null){
                type = (Integer) visit(ctx.elementAccess());
            } else{
                System.out.println(Print_Errors(6,ctx.primitiveExpression().start.getLine(),ctx.primitiveExpression().start.getCharPositionInLine())+"=>"+ctx.primitiveExpression().getText());
                return -1;
            }

        }
        else{
            if(type!=1){
                System.out.println(Print_Errors(10,ctx.primitiveExpression().start.getLine(),ctx.primitiveExpression().start.getCharPositionInLine()));
                return -1;
            }
        }

        return type;
    }

    @Override
    public Object visitElemExprPECallExpAST(Parser2.ElemExprPECallExpASTContext ctx) {
        int type = (Integer) visit(ctx.primitiveExpression());
        if(type == 0){
            SymbolTable.Ident i = this.tablaIDs.buscar(ctx.primitiveExpression().getText());
            if(i!=null){
                type = (Integer) visit(ctx.callExpression());
            }else {
                System.out.println(Print_Errors(6,ctx.primitiveExpression().start.getLine(),ctx.primitiveExpression().start.getCharPositionInLine())+"=>"+ctx.primitiveExpression().getText());
                return -1;
            }
        }
        else{
            if(type!=1){
                System.out.println(Print_Errors(10,ctx.primitiveExpression().start.getLine(),ctx.primitiveExpression().start.getCharPositionInLine()));
                return -1;
            }
        }
        return type;
    }

    @Override
    public Object visitElemExprPEEmptyAST(Parser2.ElemExprPEEmptyASTContext ctx) {
        int type = (Integer) visit(ctx.primitiveExpression());
        return type;
    }

    @Override
    public Object visitElemAccessAST(Parser2.ElemAccessASTContext ctx) {
        int type = (Integer) visit(ctx.expression());
        //revisar si viene [], o si la expresion que viene dentro es valida
        return type;//los element acces son neutros
    }

    @Override
    public Object visitCallExprAST(Parser2.CallExprASTContext ctx) {
        //revisar por cada expressionList si alguna expresion no estaba declarada
        int type = (Integer) visit(ctx.expressionList());
        if(type==6)
            type=-2;
        return type;
    }

    @Override
    public Object visitPExprINTAST(Parser2.PExprINTASTContext ctx) {
        return 1;
    }

    @Override
    public Object visitPExprSTRINGAST(Parser2.PExprSTRINGASTContext ctx) {
        return 2;
    }

    @Override
    public Object visitPExprIDAST(Parser2.PExprIDASTContext ctx) {

        SymbolTable.Ident res = tablaIDs.buscar(ctx.identifier().getText());
        if(res!=null){
            ctx.identifier().decl = res.decl;
        }
        return 0;
    }

    @Override
    public Object visitPExprTRUEAST(Parser2.PExprTRUEASTContext ctx) {
        return 4;
    }

    @Override
    public Object visitPExprFALSEAST(Parser2.PExprFALSEASTContext ctx) {
        return 4;
    }

    @Override
    public Object visitPExprGroupAST(Parser2.PExprGroupASTContext ctx) {
        int type=(Integer) visit(ctx.expression());
        return type;
    }

    @Override
    public Object visitPExprArrayLitAST(Parser2.PExprArrayLitASTContext ctx) {
        int type=(Integer) visit(ctx.arrayLiteral());
        return type;
    }

    @Override
    public Object visitPExprArrayFuncAST(Parser2.PExprArrayFuncASTContext ctx) {
        visit(ctx.arrayFunctions());
        ctx.expressionList().esAF=true;
        int type2;
        if(ctx.arrayFunctions().push){//si es un push
            ctx.expressionList().esPush=true;
            type2 = (Integer) visit(ctx.expressionList());
            if(ctx.expressionList().cont!=2){//un push debe tener 2 elementos
                System.out.println(Print_Errors(13,ctx.expressionList().start.getLine(),ctx.expressionList().start.getCharPositionInLine()));
                return -1;
            }
            else if(type2==-1){
                return -1;
            }
        } else {
            type2= (Integer) visit(ctx.expressionList());
            if(ctx.expressionList().cont!=1 || (type2!=6 && type2!=-1 && type2!=0)){
                System.out.println(Print_Errors(12,ctx.expressionList().start.getLine(),ctx.expressionList().start.getCharPositionInLine()));
                return -1;
            }
            else if(type2==-1){
                return -1;
            }

        }
        return -2;
    }


    @Override
    public Object visitPExprFuncLitAST(Parser2.PExprFuncLitASTContext ctx) {
        int type =(Integer) visit(ctx.functionLiteral());
        if(type!=-1 && type!=-2){
            return 8;//tipo funcion con retorno
        }
        return type;
    }

    @Override
    public Object visitPExprHashLitAST(Parser2.PExprHashLitASTContext ctx) {
        int type = (Integer) visit(ctx.hashLiteral());
        return type;
    }

    @Override
    public Object visitPExprPrintExprAST(Parser2.PExprPrintExprASTContext ctx) {
        int type =(Integer) visit(ctx.printExpression());
        return type;
    }

    @Override
    public Object visitPExprIfExprAST(Parser2.PExprIfExprASTContext ctx) {
        visit(ctx.ifExpression());
        return -2;
    }

    @Override
    public Object visitArrayLitAST(Parser2.ArrayLitASTContext ctx) {
        ctx.expressionList().esAF=false;
        int type= (Integer) visit(ctx.expressionList());
        return type;
    }

    @Override
    public Object visitFuncLitAST(Parser2.FuncLitASTContext ctx) {
        int typeb=-1;
        this.tablaIDs.openScope();
        int typef=(Integer) visit(ctx.functionParameters());
        if (typef!=-1){
            typeb=(Integer) visit(ctx.blockStatement());
            if(ctx.blockStatement().returns){
                typeb=0;
            }
        }
        this.tablaIDs.closeScope();
        return typeb;
    }

    @Override
    public Object visitFuncParamAST(Parser2.FuncParamASTContext ctx) {
        SymbolTable.Ident i = this.tablaIDs.buscar(ctx.identifier().getText());
        if(i==null){
            System.out.println(Print_Errors(6,((Token) ctx.identifier()).getLine(),((Token) ctx.identifier()).getCharPositionInLine())+"=>"+ctx.identifier().getText());
            return -1;
        }
        else{
            return visit(ctx.moreIdentifiers());
        }
    }

    @Override
    public Object visitMoreIdentsAST(Parser2.MoreIdentsASTContext ctx) {
        for(int i = 0;i < ctx.identifier().size(); i++){
            SymbolTable.Ident id = this.tablaIDs.buscar(ctx.identifier(i).getText());
            if(id==null){
                System.out.println(Print_Errors(6,((Token) ctx.identifier()).getLine(),((Token) ctx.identifier()).getCharPositionInLine())+"=>"+ctx.identifier(i).getText());
                return -1;
            }
        }
        return 0;
    }

    @Override
    public Object visitHashLitAST(Parser2.HashLitASTContext ctx) {
        int type=(Integer) visit(ctx.hashContent());
        int type2=(Integer)visit(ctx.moreHashContent());
        if (type==7 && type2==type) return type;
        else return-1;
    }

    @Override
    public Object visitHashContentAST(Parser2.HashContentASTContext ctx) {
        int type = (Integer) visit(ctx.expression(0));
        if(type==0 || type==1 || type==2){
            if(type==0){
                SymbolTable.Ident id = this.tablaIDs.buscar(ctx.expression(0).getText());
                if (id==null){
                    return -1;
                }
            }else {
                int type2=(Integer) visit(ctx.expression(1));
                if(type2!=-1){//si no da error
                    if(type==0){//si hay un ID
                        SymbolTable.Ident id = this.tablaIDs.buscar(ctx.expression(0).getText());
                        if (id==null){
                            return -1;
                        }
                    }
                }else{
                    System.out.println(Print_Errors(2,ctx.expression(0).start.getLine(),ctx.expression(0).start.getCharPositionInLine()));
                    return -1;
                }
            }
        } else {
            System.out.println(Print_Errors(2,ctx.expression(0).start.getLine(),ctx.expression(0).start.getCharPositionInLine()));
            return -1;
        }
        return 7;
    }

    @Override
    public Object visitMoreHashContentAST(Parser2.MoreHashContentASTContext ctx) {
        int type=7;
        for(Parser2.HashContentContext ele : ctx.hashContent()){
            type =(Integer) visit(ele);
            if(type==-1)
                return -1;
        }
        return type;
    }

    @Override
    public Object visitExprListMoreExprAST(Parser2.ExprListMoreExprASTContext ctx) {

        int type= (Integer) visit(ctx.expression());
        if(ctx.esAF){
            ctx.cont++;
            if(!ctx.moreExpressions().isEmpty() && type!=-1){
                ctx.moreExpressions().esAF=true;
                int type2 = (Integer) visit(ctx.moreExpressions());
                if (type2==-1 || (ctx.esPush && type2!=6 && type2!=0)){
                    return -1;
                }

                ctx.cont+=ctx.moreExpressions().cont;
            }
            else {
                if(type==6 || type==0)
                    return type;
                else//para las demas arrayFunctions se necesita de parametro una lista
                    return -1;

            }
            return type;
        }else {
            if(!ctx.moreExpressions().isEmpty() && type!=-1){
                ctx.moreExpressions().esAF=false;
                int type2 = (Integer) visit(ctx.moreExpressions());
                if (type2==-1){
                    return -1;
                }
            }
            return 6;
        }

    }

    @Override
    public Object visitMoreExprAST(Parser2.MoreExprASTContext ctx) {

        int type = 0;
        ctx.cont=0;
        for(int i = 0; i< ctx.expression().size();i++){
            type=(Integer) visit(ctx.expression(i));
            if(type==-1) return -1;
            if(ctx.esAF)
                ctx.cont++;
        }

        return type;
    }

    @Override
    public Object visitExprListEmptyAST(Parser2.ExprListEmptyASTContext ctx) {
        return 6;
    }

    @Override
    public Object visitPrintExprAST(Parser2.PrintExprASTContext ctx) {
        int type =(Integer) visit(ctx.expression());
        if(type==-2 || type==-1){
            System.out.println(Print_Errors(12,ctx.expression().start.getLine(),ctx.expression().start.getCharPositionInLine()));
            return -1;
        }
        else if(type==0){
            SymbolTable.Ident id = this.tablaIDs.buscar(ctx.expression().getText());
            if (id==null){
                System.out.println(Print_Errors(6,ctx.expression().start.getLine(),ctx.expression().start.getCharPositionInLine()));
                return -1;
            }
        }
        return -2;
    }

    @Override
    public Object visitIfExprAST(Parser2.IfExprASTContext ctx) {

        int type =(Integer)visit(ctx.expression());
        if(type == 4 || type == 0){//si la condicion es booleana o neutro
            this.tablaIDs.openScope();
            int type1 = (Integer) visit(ctx.blockStatement(0));
            this.tablaIDs.closeScope();
            if(ctx.blockStatement().size() == 2){
                if(type1!=-1){
                    this.tablaIDs.openScope();
                    int type2 = (Integer) visit(ctx.blockStatement(1));
                    this.tablaIDs.closeScope();
                    if(type2==-1)
                        return type2;
                }
                else return -1;
            }
        }
        else {
            System.out.println(Print_Errors(11,ctx.expression().start.getLine(),ctx.expression().start.getCharPositionInLine()));
            return -1;
        }
        if(ctx.blockStatement(0).returns || ctx.blockStatement(1).returns){
            return 0;
        }
        return -2;
    }

    @Override
    public Object visitBlockStatementAST(Parser2.BlockStatementASTContext ctx) {
        int type = -2;
        for(Parser2.StatementContext ele : ctx.statement()){
            int type2 = (Integer) visit(ele);
            if(ele.start.getText().equals("if")){
                if (type2==0)
                    type=8;
            }
            if(ele.start.getText().equals("return")){
                type=type2;
                ctx.returns=true;
            }

            if(type==-1){
                return -1;
            }
        }
        return type;
    }

    @Override
    public Object visitAfLENAST(Parser2.AfLENASTContext ctx) {
        return 1;
    }

    @Override
    public Object visitAfFIRSTAST(Parser2.AfFIRSTASTContext ctx) {
        return 0;
    }

    @Override
    public Object visitAfLASTAST(Parser2.AfLASTASTContext ctx) {
        return 0;
    }

    @Override
    public Object visitAfRESTAST(Parser2.AfRESTASTContext ctx) {
        return 6;
    }

    @Override
    public Object visitAfPUSHAST(Parser2.AfPUSHASTContext ctx) {
        ctx.push = true;
        return -2;
    }

    @Override
    public Object visitCompOperator(Parser2.CompOperatorContext ctx) {
        return null;
    }

    @Override
    public Object visitOpSumAST(Parser2.OpSumASTContext ctx) {
        return null;
    }

    @Override
    public Object visitOpSubAST(Parser2.OpSubASTContext ctx) {
        return null;
    }

    @Override
    public Object visitOpMulAST(Parser2.OpMulASTContext ctx) {
        return null;
    }

    @Override
    public Object visitOpDivAST(Parser2.OpDivASTContext ctx) {
        return null;
    }
}
