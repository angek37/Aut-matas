package net.Analizador;

public class Stack {
	private String[] stack;
	private int t;
	
	public Stack(int n){
		stack = new String[n];
		t=-1;
	}
	
	public void Push(String v){
		if(t == stack.length-1){
			System.out.println("Pila llena");
		}else{
			stack[++t] =  v;
		}
	}
	
	public String Pop(){
		if(t < 0){
			System.out.println("Pila Vacia");
			return "/";
		}else{
			return stack[t--];
		}
	}

}
