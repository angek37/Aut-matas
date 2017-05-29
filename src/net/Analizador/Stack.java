package net.Analizador;

public class Stack {

	
    private Nodo raiz;
    
    public Stack(){
        raiz=null;
    }
    
    public void Push(String x){
    	Nodo nuevo;
        nuevo = new Nodo();
        nuevo.info = x;
        if(x != ""){
        	if (raiz==null){
                nuevo.sig = null;
                raiz = nuevo;
            }
            else{
                nuevo.sig = raiz;
                raiz = nuevo;
            }
        }
    }
    
    public String Pop (){
        if (raiz!=null){
            String informacion = raiz.info;
            raiz = raiz.sig;
            return informacion;
        }
        else{
            return "/";
        }
    }
    
    public String Read(){
    	String a = "";
    	if (raiz!=null){
            a = raiz.info;
        }
        else{
            a = "";
        }
    	return a;
    }
    
    public boolean isEmpty(){
    	boolean r = false;
    	if(raiz==null){
    		r = true;
    	}
    	return r;
    }
    
    public static void main(String[] mr){
    	Stack s = new Stack();
    	s.Push("Hola");
    	s.Push("LOL");
    	System.out.print(s.Read());
    	System.out.print(s.Pop());
    }
}