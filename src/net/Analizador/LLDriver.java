package net.Analizador;

import net.Analizador.Gramatica.*;

public class LLDriver {
	private String[] term;
	private String[] nterm;
	private String[][] prod;
	private Stack s;
	private String x;
	private String a;
	
	public LLDriver(){
		Gramatica g = new Gramatica();
		term = g.getTerminals();
		nterm = g.getNoTerminals();
		prod = g.getProductions();
		s = new Stack(nterm.length);
		Procedure();
	}
	
	public void Procedure(){
		s.Push(nterm[0]);
		
		while(!s.isEmpty()){
			if(inNoTerminals(x)){
				
			}else{
				if(x == a){
					s.Pop();
				}else{
					System.out.println("Syntax Error");
				}
			}
		}
	}
	
	public boolean inNoTerminals(String x){
		boolean r = false;
		for(int c = 0; c < nterm.length; c++){
			if(x.equalsIgnoreCase(nterm[c])){
				r = true;
			}
		}
		return r;
	}
	
	public static void main(String[] mr){
		LLDriver lld = new LLDriver();
	}
	
}
