package interpreter;

import java.util.LinkedList;


public class DataStorage {

    private LinkedList<Value> data;
    private int actualIndex;

    public class Value{
        String name;
        Object value;
        int index;

        public Value(String n, Object v, int i) {
            this.name = new String(n);
            this.value = v;
            this.index = i;
        }
    }

    public DataStorage()
    {
        this.actualIndex = 0;
        this.data = new LinkedList<Value>();
    }

    public void addData(String nombre, Object value)
    {
        Value v = new Value(nombre,value,this.actualIndex);
        this.data.add(v);
        //this.actualIndex++;
    }

    public Value getData(int index) {
        return this.data.get(index);
    }

    public Value getData(String name) {
        Value res= null;
        int currentScope=0;
        for(Value ele: this.data){
            if (ele.name.equals(name)){
                //Se tiene que buscar la variable que este en el scope mas cercano al actual, si no es el actual
                if(ele.index==this.getActualStorageIndex())
                    return ele;
                if(ele.index>=currentScope){
                    res=ele;
                    currentScope=ele.index;
                }
            }
        }
        return res;

    }

    public void openScope(){
        this.actualIndex++;
    }

    public void closeScope(){
        for(int i=0;i<this.data.size();i++){
            if(this.data.get(i).index==this.getActualStorageIndex()){
                this.data.remove(i);
            }
        }
//
//        Value element = this.data.get(0);
//        while (element != null && element.index == actualIndex){
//            data.pop();
//            if(!this.data.isEmpty())
//                element = this.data.get(0);
//            else
//                element= null;
//        }
        this.actualIndex--;
    }

    public void cleanData(){
        while (this.data.size()!=0)
            this.data.pop();
        this.actualIndex=0;
    }

    public String toString() {
        String message = new String("");
        message += "****** ESTADO DE DATA STORAGE ******\n";
        if (!this.data.isEmpty()) {
            for (Value i : this.data) {
                message += i.name + " --> " + i.value.toString() + "Scope: " + i.index + "\n";
            }
            message += "------------------------------------------";
        } else
            message += "Data Storage is Empty!!";
        return message;
    }

    public int getActualStorageIndex(){
        return this.actualIndex;
    }
    public LinkedList<Value> getData() {
        return this.data;
    }

}
