package interpreter;

import java.util.Stack;

public class EvaluationStack {
    private Stack<Object> evalStack;

    public EvaluationStack(){
        this.evalStack= new Stack<Object>();
    }

    public void pushValue(Object elem){
        this.evalStack.push(elem);
    }

    public Object popValue(){
        return this.evalStack.pop();
    }

    public void printStack(){

//        if(!evalStack.empty()){
//            System.out.println("---------------IMPRIMIR STACK------------------");
//            evalStack.forEach(item->
//                    System.out.println(item)
//            );
//            System.out.println("-----------------------------------------------");
//        }

    }
}
