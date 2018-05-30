package interpreter;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import generated.Parser2;
import generated.Parser2BaseVisitor;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Interpreter extends Parser2BaseVisitor {
    private DataStorage dataS = null;
    private EvaluationStack evalStack=null;
    public Interpreter(){
        this.dataS=new DataStorage();
        this.evalStack = new EvaluationStack();
    }



    private Integer evaluar(Integer v1, Integer v2, String op){


        if(op.equals("+")){
            System.out.println(v1+v2);
            return v1+v2;
        }
        else if(op.equals("-")){
            return v1-v2;
        }
        else if(op.equals("/")){
            return v1/v2;
        }
        else if(op.equals("*")){
            return v1*v2;
        }
        else if(op.equals(">")){
            if (v1>v2)
                return 0;//true
            else return 1;//false
        }
        else if(op.equals("<")){
            if (v1<v2)
                return 0;//true
            else return 1;//false
        }
        else if(op.equals("=>")){
            if (v1>=v2)
                return 0;//true
            else return 1;//false
        }
        else if(op.equals("=<")){
            if (v1<=v2)
                return 0;//true
            else return 1;//false
        }
        else if(op.equals("==")){
            if (v1==v2)
                return 0;//true
            else return 1;//false
        }
        else return null;

    }

    private String evaluar(String v1, String v2, String op){


        if(op.equals("+")){
            return v1+v2;
        }
        else return null;

    }

    private String evaluar(Integer v1, String v2, String op){

        if(op.equals("+")){
            return v1+v2;
        }
        else return null;

    }

    private String evaluar(String v1, Integer v2, String op){


        if(op.equals("+")){
            return v1+v2;
        }
        else return null;

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
        System.out.println("ENTRE!");
        visit(ctx.letStatement());
        return null;
    }

    @Override
    public Object visitStRETURNAST(Parser2.StRETURNASTContext ctx) {
        visit(ctx.returnStatement());
        return null;
    }

    @Override
    public Object visitStExprAST(Parser2.StExprASTContext ctx) {
        visit(ctx.expressionStatement());
        return null;
    }

    @Override
    public Object visitLsAsignAST(Parser2.LsAsignASTContext ctx) {
        System.out.println("ENTRE LSASIGN!");
        ctx.storageIndex=dataS.getActualStorageIndex();
        dataS.addData(ctx.identifier().getText(), new Object());
        visit(ctx.expression());
        Object val =  evalStack.popValue();
        //CAMBIAR EL VALOR EN EL ALMACEN
        //dataS.getData(((Parser2.LsAsignASTContext)ctx.identifier().decl).storageIndex).value=val;
        dataS.getData(ctx.identifier().getText()).value=val;
        System.out.println(this.dataS.toString());
        return null;
    }

    @Override
    public Object visitRsExprAST(Parser2.RsExprASTContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitEsExprAST(Parser2.EsExprASTContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitExprAddAST(Parser2.ExprAddASTContext ctx) {
        System.out.println("ENTRE! ADDEXP");
        visit(ctx.additionExpression());
        visit(ctx.comparison());
        return null;
    }

    @Override
    public Object visitCompAST(Parser2.CompASTContext ctx) {
        if(!ctx.compOperator().isEmpty()){
            visit(ctx.additionExpression(0));

            if(!(ctx.compOperator().size()==1)){
                for(int i = 0;i<=ctx.compOperator().size()-1;i++){
                    visit(ctx.additionExpression(i+1));
                    Object v2 = this.evalStack.popValue();
                    Object v1 = this.evalStack.popValue();
                    if((v2 instanceof Integer)&&(v1 instanceof Integer)){
                        if(evaluar((Integer) v1,(Integer) v2,ctx.compOperator(i).getText())!=null)
                            this.evalStack.pushValue(evaluar((Integer) v1,(Integer) v2,ctx.compOperator(i).getText()));
                    }
                    else {
                        //MOSTRAR ERROR
                        break;
                    }
                }
            } else{
                Object v2 = this.evalStack.popValue();
                Object v1 = this.evalStack.popValue();
                if((v2 instanceof Integer)&&(v1 instanceof Integer)){
                    if(evaluar((Integer) v1,(Integer) v2,ctx.compOperator(0).getText())!=null)
                        this.evalStack.pushValue(evaluar((Integer) v1,(Integer) v2,ctx.compOperator(0).getText()));
                }
            }
        }
        return null;
    }

    @Override
    public Object visitAddExprAST(Parser2.AddExprASTContext ctx) {
        System.out.println("ENTRE! ADDEXP");
        visit(ctx.multiplicationExpression());
        visit(ctx.additionFactor());
        return null;
    }

    @Override
    public Object visitAddFactorAST(Parser2.AddFactorASTContext ctx) {
        System.out.println("ENTRE! ADDFACTOR");
        if(!ctx.addOperator().isEmpty()){
            visit(ctx.multiplicationExpression(0));

            if(!(ctx.addOperator().size()==1)){
                for(int i=0; i<= ctx.addOperator().size()-1; i++){
                    visit(ctx.multiplicationExpression(i+1));
                    Object v2 = this.evalStack.popValue();
                    Object v1 = this.evalStack.popValue();
                    if((v1 instanceof String)&&(v2 instanceof String)){
                        if(evaluar((String) v1,(String)v2,ctx.addOperator(i).getText())!=null)
                            this.evalStack.pushValue(evaluar((String) v1,(String) v2,ctx.addOperator(i).getText()));
                    }else if((v1 instanceof Integer)&&(v2 instanceof String)){
                        if(evaluar((Integer) v1,(String)v2,ctx.addOperator(i).getText())!=null)
                            this.evalStack.pushValue(evaluar((Integer) v1,(String) v2,ctx.addOperator(i).getText()));
                    }else if((v1 instanceof String)&&(v2 instanceof Integer)){
                        if(evaluar((String) v1,(Integer)v2,ctx.addOperator(i).getText())!=null)
                            this.evalStack.pushValue(evaluar((String) v1,(Integer) v2,ctx.addOperator(i).getText()));
                    }else if((v1 instanceof Integer)&&(v2 instanceof Integer)){
                        if(evaluar((Integer) v1,(Integer) v2,ctx.addOperator(i).getText())!=null)
                            this.evalStack.pushValue(evaluar((Integer) v1,(Integer) v2,ctx.addOperator(i).getText()));
                    }else {
                        //MOSTRAR ERROR
                        break;
                    }

                }
            }else{
                Object v2 = this.evalStack.popValue();
                Object v1 = this.evalStack.popValue();
                if((v1 instanceof String)&&(v2 instanceof String)){
                    if(evaluar((String) v1,(String)v2,ctx.addOperator(0).getText())!=null)
                        this.evalStack.pushValue(evaluar((String) v1,(String) v2,ctx.addOperator(0).getText()));
                }else if((v1 instanceof Integer)&&(v2 instanceof String)){
                    if(evaluar((Integer) v1,(String)v2,ctx.addOperator(0).getText())!=null)
                        this.evalStack.pushValue(evaluar((Integer) v1,(String) v2,ctx.addOperator(0).getText()));
                }else if((v1 instanceof String)&&(v2 instanceof Integer)){
                    if(evaluar((String) v1,(Integer)v2,ctx.addOperator(0).getText())!=null)
                        this.evalStack.pushValue(evaluar((String) v1,(Integer) v2,ctx.addOperator(0).getText()));
                }else if((v1 instanceof Integer)&&(v2 instanceof Integer)){
                    if(evaluar((Integer) v1,(Integer) v2,ctx.addOperator(0).getText())!=null)
                        this.evalStack.pushValue(evaluar((Integer) v1,(Integer) v2,ctx.addOperator(0).getText()));
                }else {
                    //MOSTRAR ERROR

                }
            }

        }
        return null;
    }

    @Override
    public Object visitMultExprAST(Parser2.MultExprASTContext ctx) {
        System.out.println("ENTRE! MULTEXPR");
        visit(ctx.elementExpression());
        visit(ctx.multiplicationFactor());
        return null;
    }

    @Override
    public Object visitMultFactorAST(Parser2.MultFactorASTContext ctx) {
        System.out.println("ENTRE! MULFACTOR");
        if(!ctx.mulOperator().isEmpty()){
            visit(ctx.elementExpression(0));

            if(!(ctx.mulOperator().size()==1)){
                for(int i=0; i<= ctx.mulOperator().size()-1; i++){
                    visit(ctx.elementExpression(i+1));
                    Object v2 = this.evalStack.popValue();
                    Object v1 = this.evalStack.popValue();
                    if((v2 instanceof Integer)&&(v1 instanceof Integer)){
                        if(evaluar((Integer) v1,(Integer) v2,ctx.mulOperator(i).getText())!=null)
                            this.evalStack.pushValue(evaluar((Integer) v1,(Integer) v2,ctx.mulOperator(i).getText()));
                    }else {
                        //MOSTRAR ERROR
                        break;
                    }
                }
            } else {
                Object v2 = this.evalStack.popValue();
                Object v1 = this.evalStack.popValue();
                if((v2 instanceof Integer)&&(v1 instanceof Integer)){
                    if(evaluar((Integer) v1,(Integer) v2,ctx.mulOperator(0).getText())!=null)
                        this.evalStack.pushValue(evaluar((Integer) v1,(Integer) v2,ctx.mulOperator(0).getText()));
                }else {
                    //MOSTRAR ERROR
                }
            }
        }
        System.out.println("SALI DE MUL FACTOR");
        return null;
    }

    @Override
    public Object visitElemExprPEElemAccessAST(Parser2.ElemExprPEElemAccessASTContext ctx) {
        visit(ctx.primitiveExpression());
        visit(ctx.elementAccess());
        return null;
    }

    @Override
    public Object visitElemExprPECallExpAST(Parser2.ElemExprPECallExpASTContext ctx) {
        visit(ctx.primitiveExpression());
        visit(ctx.callExpression());
        return null;
    }

    @Override
    public Object visitElemExprPEEmptyAST(Parser2.ElemExprPEEmptyASTContext ctx) {
        visit(ctx.primitiveExpression());
        return null;
    }

    @Override
    public Object visitElemAccessAST(Parser2.ElemAccessASTContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitCallExprAST(Parser2.CallExprASTContext ctx) {
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitPExprINTAST(Parser2.PExprINTASTContext ctx) {
        this.evalStack.pushValue(Integer.parseInt(ctx.INT().getText()));
        return null;
    }

    @Override
    public Object visitPExprSTRINGAST(Parser2.PExprSTRINGASTContext ctx) {
        this.evalStack.pushValue(ctx.STRING().getText());
        return null;
    }

    @Override
    public Object visitPExprIDAST(Parser2.PExprIDASTContext ctx) {
        System.out.println("ID:"+ctx.identifier().getText());
        DataStorage.Value temp = dataS.getData(((Parser2.LsAsignASTContext)ctx.identifier().decl).storageIndex);
        this.evalStack.pushValue(temp.value);
        return null;
    }

    @Override
    public Object visitPExprTRUEAST(Parser2.PExprTRUEASTContext ctx) {
        this.evalStack.pushValue(true);
        return null;
    }

    @Override
    public Object visitPExprFALSEAST(Parser2.PExprFALSEASTContext ctx) {
        this.evalStack.pushValue(false);
        return null;
    }

    @Override
    public Object visitPExprGroupAST(Parser2.PExprGroupASTContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitPExprArrayLitAST(Parser2.PExprArrayLitASTContext ctx) {
        visit(ctx.arrayLiteral());
        return null;
    }

    @Override
    public Object visitPExprArrayFuncAST(Parser2.PExprArrayFuncASTContext ctx) {
        visit(ctx.arrayFunctions());
        ctx.expressionList().esAF=true;
        if(ctx.arrayFunctions().push){
            ctx.expressionList().esPush=true;
            visit(ctx.expressionList());
            Object lista= this.evalStack.popValue();
            Object valor= this.evalStack.popValue();
            if((lista instanceof ArrayList)){
                ((ArrayList) lista).add(valor);
                this.evalStack.pushValue(lista);
            } else{
                //Error
            }
        } else if(ctx.arrayFunctions().first){
            visit(ctx.expressionList());
            Object lista= this.evalStack.popValue();
            if(lista instanceof ArrayList){
                this.evalStack.pushValue(((ArrayList) lista).get(0));
            } else {
                //ERROR
            }
        } else if(ctx.arrayFunctions().last){
            visit(ctx.expressionList());
            Object lista= this.evalStack.popValue();
            if(lista instanceof ArrayList){
                this.evalStack.pushValue(((ArrayList) lista).get(((ArrayList) lista).size()-1));//ultimo valor
            } else {
                //ERROR
            }
        } else if(ctx.arrayFunctions().len){
            visit(ctx.expressionList());
            Object lista= this.evalStack.popValue();
            if(lista instanceof ArrayList){
                this.evalStack.pushValue(((ArrayList) lista).size());
            } else {
                //ERROR
            }
        }else if(ctx.arrayFunctions().rest){
            visit(ctx.expressionList());
            Object lista= this.evalStack.popValue();
            if(lista instanceof ArrayList){
                ((ArrayList) lista).remove(0);
                this.evalStack.pushValue(lista);
            } else {
                //ERROR
            }
        }

        return null;
    }

    @Override
    public Object visitPExprFuncLitAST(Parser2.PExprFuncLitASTContext ctx) {
        visit(ctx.functionLiteral());
        return null;
    }

    @Override
    public Object visitPExprHashLitAST(Parser2.PExprHashLitASTContext ctx) {
        visit(ctx.hashLiteral());
        return null;
    }

    @Override
    public Object visitPExprPrintExprAST(Parser2.PExprPrintExprASTContext ctx) {
        visit(ctx.printExpression());
        return null;
    }

    @Override
    public Object visitPExprIfExprAST(Parser2.PExprIfExprASTContext ctx) {
        visit(ctx.ifExpression());
        return null;
    }

    @Override
    public Object visitAfLENAST(Parser2.AfLENASTContext ctx) {
        ctx.len=true;
        return null;
    }

    @Override
    public Object visitAfFIRSTAST(Parser2.AfFIRSTASTContext ctx) {
        ctx.first=true;
        return null;
    }

    @Override
    public Object visitAfLASTAST(Parser2.AfLASTASTContext ctx) {
        ctx.last=true;
        return null;
    }

    @Override
    public Object visitAfRESTAST(Parser2.AfRESTASTContext ctx) {
        ctx.rest=true;
        return null;
    }

    @Override
    public Object visitAfPUSHAST(Parser2.AfPUSHASTContext ctx) {
        ctx.push=true;
        return null;
    }

    @Override
    public Object visitArrayLitAST(Parser2.ArrayLitASTContext ctx) {
        ArrayList<Object> lista = new ArrayList<>();
        this.evalStack.pushValue(lista);
        ctx.expressionList().esAF=false;
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitFuncLitAST(Parser2.FuncLitASTContext ctx) {
        visit(ctx.functionParameters());
        visit(ctx.blockStatement());
        return null;
    }

    @Override
    public Object visitFuncParamAST(Parser2.FuncParamASTContext ctx) {
        visit(ctx.moreIdentifiers());
        return null;
    }

    @Override
    public Object visitMoreIdentsAST(Parser2.MoreIdentsASTContext ctx) {
        return null;
    }

    @Override
    public Object visitHashLitAST(Parser2.HashLitASTContext ctx) {
        visit(ctx.hashContent());
        visit(ctx.moreHashContent());
        return null;
    }

    @Override
    public Object visitHashContentAST(Parser2.HashContentASTContext ctx) {
        visit(ctx.expression(0));
        visit(ctx.expression(1));
        return null;
    }

    @Override
    public Object visitMoreHashContentAST(Parser2.MoreHashContentASTContext ctx) {
        for(Parser2.HashContentContext ele : ctx.hashContent()){
            visit(ele);
        }
        return null;
    }

    @Override
    public Object visitExprListMoreExprAST(Parser2.ExprListMoreExprASTContext ctx) {
        visit(ctx.expression());
        if(!ctx.esAF){
            Object elemento=this.evalStack.popValue();
            ArrayList lista=(ArrayList) this.evalStack.popValue();
            lista.add(elemento);
            this.evalStack.pushValue(lista);
            ctx.moreExpressions().esAF=true;
            visit(ctx.moreExpressions());
        } else {
            visit(ctx.moreExpressions());
        }

        return null;
    }

    @Override
    public Object visitExprListEmptyAST(Parser2.ExprListEmptyASTContext ctx) {
        return null;
    }

    @Override
    public Object visitMoreExprAST(Parser2.MoreExprASTContext ctx) {
        for(int i = 0; i< ctx.expression().size();i++){
            visit(ctx.expression(i));
            if(!ctx.esAF){//si es una lista, se tienen que agregar los datos a la lista
                Object elemento=this.evalStack.popValue();
                ArrayList lista=(ArrayList) this.evalStack.popValue();
                lista.add(elemento);
                this.evalStack.pushValue(lista);
            }

        }
        return null;
    }

    @Override
    public Object visitPrintExprAST(Parser2.PrintExprASTContext ctx) {
        visit(ctx.expression());
        System.out.println(this.evalStack.popValue());
        return null;
    }

    @Override
    public Object visitIfExprAST(Parser2.IfExprASTContext ctx) {
        //ESTA SOLUCIÓN NO ES DEFINITIVA!!! se tiene que revisar
        visit(ctx.expression());
        //se saca el valor de esa expresion
        Object expression = this.evalStack.popValue();
        if(expression instanceof Boolean){//se verifica si la expresion es booleana
            Boolean esTrue= (Boolean) expression;
            if (esTrue)
                visit(ctx.blockStatement(0));
            else if(!esTrue && ctx.blockStatement().size()==2) visit(ctx.blockStatement(1));
        }


        return null;
    }

    @Override
    public Object visitBlockStatementAST(Parser2.BlockStatementASTContext ctx) {
        for(Parser2.StatementContext ele : ctx.statement()){
            visit(ele);
        }
        return null;
    }

    @Override
    public Object visitCompOperator(Parser2.CompOperatorContext ctx) {
        return null;
    }
}
