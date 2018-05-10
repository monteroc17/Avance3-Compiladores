package checker;
import org.antlr.v4.runtime.*;
import java.util.LinkedList;

public class SymbolTable {

    private LinkedList<Ident> tabla;
    private int nivelActual;

    public class Ident{
        int nivel;
        Token tok;
        int type;
        ParserRuleContext decl; //por si fuera necesario saber más acerca del contexto del identificador en el árbol

        public Ident(int n, Token t, int ty, ParserRuleContext d) {
            nivel = n;
            tok = t;
            type = ty;
            decl = d;
        }

        public String toString(){
            return this.tok.getText() + "," + this.nivel;
        }
    }

    public SymbolTable()
    {
        this.nivelActual = 0;
        this.tabla = new LinkedList<Ident>();
    }

    public Ident insertar(String nombre, int tipo, ParserRuleContext declaracion)
    {
        Token token = new CommonToken(0,nombre);
        Ident i = new Ident(nivelActual,token,tipo,declaracion);
        this.tabla.push(i); //deben ser una tabla estilo pila
        return this.tabla.get(0);
    }

    public Ident insertar(Token token, int tipo, ParserRuleContext declaracion)
    {
        Ident i = new Ident(nivelActual,token,tipo,declaracion);

        tabla.push(i); //deben ser una tabla estilo pila
        return this.tabla.get(0);

    }

    public void eliminar(String nombre){
        for(Ident id : this.tabla)
            if (id.tok.getText().equals(nombre)) {
                this.getTabla().remove(id);
                break;
            }
    }

    public void openScope(){
        this.nivelActual++;
    }

    public void closeScope(){
        Ident element = this.tabla.get(0);
        while (element != null && element.nivel == nivelActual){
            tabla.pop();
            if(!this.tabla.isEmpty())
                element = this.tabla.get(0);
            else
                element= null;
        }
        this.nivelActual--;
    }

    public Ident buscar(String nombre)
    {
        Ident temp=null;
        for(Ident id : this.tabla)
            if (id.tok.getText().equals(nombre)) {
                temp = id;
                break;
            }
        return temp;
    }

    public void imprimir() {
        System.out.println("****** ESTADO DE TABLA DE SÍMBOLOS ******");
        if (!this.tabla.isEmpty()) {
            for (Ident i : this.tabla) {
                String nivel = "";
                for (int j = 0; j < i.nivel; j++) {
                    nivel += "\t";
                }
                System.out.println(nivel + "Nombre: " + i.tok.getText() + ", Tipo:" + convertirTipo(i.type) + " - Nivel: " + i.nivel);
            }
            System.out.println("------------------------------------------");
        }
        else
            System.out.println("Tabla vacía");
    }

    public String convertirTipo(int tipo){
        switch (tipo){
            case -2:
                return "sin tipo";
            case 0:
                return "neutro";
            case 1:
                return "int";
            case 2:
                return "string";
            case 4:
                return "bool";
            case 6:
                return "array";
            case 7:
                return "hash";
            default:
                return "Error";
        }
    }

    public LinkedList<Ident> getTabla() {
        return this.tabla;
    }

}
