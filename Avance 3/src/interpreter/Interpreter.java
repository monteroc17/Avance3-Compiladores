package interpreter;

import com.sun.org.apache.xpath.internal.operations.Bool;
import generated.Parser2;
import generated.Parser2BaseVisitor;

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
        visit(ctx.expression());
        Integer val = (Integer) evalStack.popValue();
        //CAMBIAR EL VALOR EN EL ALMACEN
        dataS.getData(((Parser2.LsAsignASTContext)ctx.identifier().decl).storageIndex).value=val;
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
        visit(ctx.additionExpression(0));
        for(int i = 0;i<=ctx.compOperator().size()-1;i++){
            visit(ctx.additionExpression(i+1));
            Integer v2 = (Integer) this.evalStack.popValue();
            Integer v1 = (Integer) this.evalStack.popValue();
            if(evaluar(v1,v2,ctx.compOperator(i).getText())!=null)
                this.evalStack.pushValue(evaluar(v1,v2,ctx.compOperator(i).getText()));
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
        visit(ctx.multiplicationExpression(0));
        for(int i=0; i<= ctx.addOperator().size()-1; i++){
            visit(ctx.multiplicationExpression(i+1));
            Integer v2 = (Integer) this.evalStack.popValue();
            Integer v1 = (Integer) this.evalStack.popValue();
            if(evaluar(v1,v2,ctx.addOperator(i).getText())!=null)
                this.evalStack.pushValue(evaluar(v1,v2,ctx.addOperator(i).getText()));

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
        visit(ctx.elementExpression(0));
        for(int i=0; i<= ctx.mulOperator().size()-1; i++){
            visit(ctx.elementExpression(i+1));
            Integer v2 = (Integer) this.evalStack.popValue();
            Integer v1 = (Integer) this.evalStack.popValue();
            if(evaluar(v1,v2,ctx.mulOperator(i).getText())!=null)
                this.evalStack.pushValue(evaluar(v1,v2,ctx.mulOperator(i).getText()));
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
        DataStorage.Value temp = dataS.getData(((Parser2.LsAsignASTContext)ctx.identifier().decl).storageIndex);
        //Verificar de que tipo es o guardarlo sin importar el tipo y validar a la hora de comparar
        //Preguntar como hacer
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
        visit(ctx.expressionList());
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
        //hay que revisar la lista
        return null;
    }

    @Override
    public Object visitAfFIRSTAST(Parser2.AfFIRSTASTContext ctx) {
        return null;
    }

    @Override
    public Object visitAfLASTAST(Parser2.AfLASTASTContext ctx) {
        return null;
    }

    @Override
    public Object visitAfRESTAST(Parser2.AfRESTASTContext ctx) {
        return null;
    }

    @Override
    public Object visitAfPUSHAST(Parser2.AfPUSHASTContext ctx) {
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
            Object elemento=this.evalStack.popValue();
            ArrayList lista=(ArrayList) this.evalStack.popValue();
            lista.add(elemento);
            this.evalStack.pushValue(lista);
        }
        return null;
    }

    @Override
    public Object visitPrintExprAST(Parser2.PrintExprASTContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitIfExprAST(Parser2.IfExprASTContext ctx) {
        //ESTA SOLUCIÓN NO ES DEFINITIVA!!! se tiene que revisar
        Boolean type=(Boolean) visit(ctx.expression());
        if(type){
            visit(ctx.blockStatement(0));
        } else {
            visit(ctx.blockStatement(1));
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
