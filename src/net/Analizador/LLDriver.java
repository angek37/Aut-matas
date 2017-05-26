package net.Analizador;

import net.Analizador.Gramatica.*;
import net.Analizador.Automatas.*;

public class LLDriver {
	private String[] term;
	private String[] nterm;
	private String[][] prod;
	private Stack s;
	private String x;
	private String a;
	private Automata AU = new Automata();
	private int[][] predic = 
		{{1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,22}, 
		{-1,-1,4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,2,3,5,8,-1,11,-1,14,-1,18,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,16,-1,-1,-1},
		{-1,2,3,6,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,11,-1,14,-1,17,-1,-1},
		{-1,-1,-1,-1,-1,1-1,-1,13,-1,16,-1,-1,-1},
		{-1,2,3,7,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1,11,-1,14,-1,19,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,15,-1,2-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,15,-1,15,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,14,-1,23,-1,-1},
		{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}};
	
	
	public LLDriver(){
		Gramatica g = new Gramatica();
		term = g.getTerminals();
		nterm = g.getNoTerminals();
		prod = g.getRightProductions();
		s = new Stack();
		AU.leerarchivo();
		Procedure();
	}
	
	public void Procedure(){
		s.Push(SymbolInit());
		a = s.Read();
		x = AU.getToken();
		while(!s.isEmpty()){
			if(inNoTerminals(x)){
				if(predic[ReturnPos(term, x)][ReturnPos(nterm, a)] != -1){
					x = prod[predic[ReturnPos(term, x)][ReturnPos(nterm, a)]][0];
					for(int y = 0; y < prod[predic[ReturnPos(term, x)][ReturnPos(nterm, a)]].length; y++){
						s.Push(prod[predic[ReturnPos(term, x)][ReturnPos(nterm, a)]][y]);
					}
				}else{
					System.out.println("Syntax Error");
				}
			}else{
				if(x == a){
					s.Pop();
					a = s.Read();
				}else{
					System.out.println("Syntax Error");
				}
			}
		}
	}
	
	public boolean inNoTerminals(String a){
		boolean r = false;
		for(int c = 0; c < nterm.length; c++){
			if(a.equalsIgnoreCase(nterm[c])){
				r = true;
			}
		}
		return r;
	}
	
	public boolean inProd(String a){
		boolean r = false;
		for(int c = 0; c < prod.length; c++){
			for(int y = 0; y < prod[c].length; y++){
				if(a.equalsIgnoreCase(prod[c][y])){
					r = true;
					break;
				}
			}
		}
		
		return r;
	}
	
	public int ReturnPos(String a[], String b){
		int r = 0;
		for(int x = 0; x < a.length; x++){
			if(b.equalsIgnoreCase(a[x])){
				r = x;
			}
		}
		return r;
	}
	
	public String SymbolInit(){
		String a = "";
		for(int c = 0; c < nterm.length; c++){
			String aux = nterm[c];
			if(!inProd(aux)){
				a = aux;
			}
		}
		return a;
	}
	
	public static void main(String[] mr){
		LLDriver lld = new LLDriver();
		System.out.println(lld.ReturnPos(lld.nterm, "system_goal"));
		System.out.println(lld.prod[22][0]);
	}
	
}
