package interpreter;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import generated.Parser2;
import generated.Parser2BaseVisitor;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;


public class Interpreter extends Parser2BaseVisitor {
    private DataStorage dataS = null;
    private EvaluationStack evalStack=null;
    public Interpreter(){
        this.dataS=new DataStorage();
        this.evalStack = new EvaluationStack();
    }


    private boolean isInteger(Object element)
    {
        if(element instanceof  Integer)
            return true;
        else return false;
    }

    private Integer evaluar(Integer v1, Integer v2, String op){

        if(op.equals("+")){
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
        ctx.storageIndex=dataS.getActualStorageIndex();
        dataS.addData(ctx.identifier().getText(), new Object());
        if(ctx.expression().start.getText().equals("fn")){//si es una función no se guarda el ctx
            dataS.getData(ctx.identifier().getText()).value=ctx.expression();
        }else {
            visit(ctx.expression());
            Object val =  evalStack.popValue();
            //CAMBIAR EL VALOR EN EL ALMACEN
            //dataS.getData(((Parser2.LsAsignASTContext)ctx.identifier().decl).storageIndex).value=val;
            dataS.getData(ctx.identifier().getText()).value=val;
        }

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
        visit(ctx.additionExpression());
        visit(ctx.comparison());
        return null;
    }

    @Override
    public Object visitCompAST(Parser2.CompASTContext ctx) {
        if(!ctx.compOperator().isEmpty()){
            //visit(ctx.additionExpression(0)); cambie esto

            if(!(ctx.compOperator().size()==1)){
                for(int i = 0;i<=ctx.compOperator().size()-1;i++){
                    visit(ctx.additionExpression(i));//cambie esto att josue estaba i+1
                    Object v2 = this.evalStack.popValue();
                    Object v1 = this.evalStack.popValue();

                    if((v2 instanceof Integer)&&(v1 instanceof Integer)){

                        if(evaluar((Integer) v1,(Integer) v2,ctx.compOperator(i).getText())!=null){
                            Integer res =evaluar((Integer) v1,(Integer) v2,ctx.compOperator(i).getText());
                            if(res!=null){
                                if(res==0)
                                    this.evalStack.pushValue(true);
                                else this.evalStack.pushValue(false);
                            }

                        }
                    }
                    else {
                        //MOSTRAR ERROR
                        break;
                    }
                }
            } else{
                visit(ctx.additionExpression(0));
                Object v2 = this.evalStack.popValue();
                Object v1 = this.evalStack.popValue();
                if((v2 instanceof Integer)&&(v1 instanceof Integer)){
                    if(evaluar((Integer) v1,(Integer) v2,ctx.compOperator(0).getText())!=null){
                        Integer res =evaluar((Integer) v1,(Integer) v2,ctx.compOperator(0).getText());
                        if (res != null) {
                            if(res==0)
                                this.evalStack.pushValue(true);
                            else this.evalStack.pushValue(false);
                        }

                    }
                }
            }
        }
        return null;
    }

    @Override
    public Object visitAddExprAST(Parser2.AddExprASTContext ctx) {
        visit(ctx.multiplicationExpression());
        visit(ctx.additionFactor());
        return null;
    }

    @Override
    public Object visitAddFactorAST(Parser2.AddFactorASTContext ctx) {
        if(!ctx.addOperator().isEmpty()){
            //visit(ctx.multiplicationExpression(0)); comenté esto att josue y sirvió bien sumar

            if(!(ctx.addOperator().size()==1)){
                for(int i=0; i<= ctx.addOperator().size()-1; i++){
                    visit(ctx.multiplicationExpression(i));//cambié esto att josue estaba i+1
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
                visit(ctx.multiplicationExpression(0));
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
        visit(ctx.elementExpression());
        visit(ctx.multiplicationFactor());
        return null;
    }

    @Override
    public Object visitMultFactorAST(Parser2.MultFactorASTContext ctx) {
        if(!ctx.mulOperator().isEmpty()){
            //visit(ctx.elementExpression(0)); // esto lo cambie, no va aqui

            if(!(ctx.mulOperator().size()==1)){
                for(int i=0; i<= ctx.mulOperator().size()-1; i++){
                    visit(ctx.elementExpression(i));//cambié esto att josue estaba i+1
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
                visit(ctx.elementExpression(0));
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
        //visit(ctx.primitiveExpression());
        if(this.dataS.getData(ctx.primitiveExpression().getText())!=null){//si la variable existe
            visit((Parser2.ExprAddASTContext)this.dataS.getData(ctx.primitiveExpression().getText()).value);//Visita la función
        } else {
            //ERROR La variable no existe
        }
        //visit(ctx.callExpression());
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
        Object valor= this.evalStack.popValue();
        Object lista= this.evalStack.popValue();
        if(lista instanceof ArrayList){
            if(valor instanceof Integer){
                if((Integer)valor<((ArrayList)lista).size())
                    this.evalStack.pushValue(((ArrayList) lista).get((Integer) valor));
            }
        } else if(lista instanceof HashMap){
            HashMap dic=(HashMap) lista;
            Iterator it = dic.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                if(valor.equals(pair.getKey())){
                    this.evalStack.pushValue(pair.getValue());
                    return null;
                } else if(valor.equals(pair.getValue())){
                    this.evalStack.pushValue(pair.getKey());
                    return null;
                }
                it.remove();
            }
        }

        return null;
    }

    @Override
    public Object visitCallExprAST(Parser2.CallExprASTContext ctx) {
        //Abrir Scope
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
        DataStorage.Value temp = dataS.getData(ctx.identifier().getText());
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
        ctx.expressionList().esArray=false;
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
        ctx.expressionList().esArray=true;
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitFuncLitAST(Parser2.FuncLitASTContext ctx) {
        this.dataS.openScope();
        visit(ctx.functionParameters());
        visit(ctx.blockStatement());
        this.dataS.closeScope();
        return null;
    }

    @Override
    public Object visitFuncParamAST(Parser2.FuncParamASTContext ctx) {
        if(this.dataS.getData(ctx.identifier().getText())!=null)
            visit(ctx.moreIdentifiers());
        else {
            //Error
        }
        return null;
    }

    @Override
    public Object visitMoreIdentsAST(Parser2.MoreIdentsASTContext ctx) {
        for(Parser2.IdentifierContext id: ctx.identifier()){
            if(this.dataS.getData(id.getText())==null){
                //Error
                break;
            }
        }
        return null;
    }

    @Override
    public Object visitHashLitAST(Parser2.HashLitASTContext ctx) {
        HashMap dictionary=new HashMap();
        this.evalStack.pushValue(dictionary);
        visit(ctx.hashContent());
        visit(ctx.moreHashContent());
        return null;
    }

    @Override
    public Object visitHashContentAST(Parser2.HashContentASTContext ctx) {
        visit(ctx.expression(0));
        visit(ctx.expression(1));
        Object value=this.evalStack.popValue();
        Object key=this.evalStack.popValue();
        HashMap dic=(HashMap) this.evalStack.popValue();
        dic.put(key,value);
        this.evalStack.pushValue(dic);
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
        if(ctx.esArray){
            Object elemento=this.evalStack.popValue();
            ArrayList lista=(ArrayList) this.evalStack.popValue();
            lista.add(elemento);
            this.evalStack.pushValue(lista);
            ctx.moreExpressions().esAF=false;
            visit(ctx.moreExpressions());
        } else if(ctx.esAF) {
            ctx.moreExpressions().esAF=true;
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
            if (esTrue){
                this.dataS.openScope();
                visit(ctx.blockStatement(0));
                this.dataS.closeScope();
            }

            else if(!esTrue && ctx.blockStatement().size()==2) {
                this.dataS.openScope();
                visit(ctx.blockStatement(1));
                this.dataS.closeScope();
            }
        }


        return null;
    }

    @Override
    public Object visitBlockStatementAST(Parser2.BlockStatementASTContext ctx) {
        for(Parser2.StatementContext ele : ctx.statement()){
            visit(ele);
            if(ele.start.getText().equals("return")){
                ctx.returns=true;
                //Reingresar el valor que de el retorno para asignarselo a la funcion. PREGUNTAR SI ES ASI---------------------
                this.evalStack.pushValue(this.dataS.getData().getLast().value);
                break;
            }
        }
        return null;
    }

    @Override
    public Object visitCompOperator(Parser2.CompOperatorContext ctx) {
        return null;
    }
}
