package interpreter;

import java.util.Stack;

/**
 * Created by oviquez on 3/5/2018.
 */
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
        System.out.println("---------------IMPRIMIR STACK------------------");
        evalStack.forEach(item->
            System.out.println(item)
        );
    }
}
