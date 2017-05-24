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
	private int[][] predic = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0,0,4,0,0,0,0,0,0,0,0,0,0}, {0,2,3,5,8,0,11,0,14,0,18,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,9,0,12,0,16,0,0,0},{0,0,0,0,0,0,0,0,0,16,0,0,0},{0,2,3,6,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,11,0,14,0,17,0,0},
			{0,0,0,0,0,10,0,13,0,16,0,0,0},{0,2,3,7,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,11,0,14,0,19,0,0},{0,0,0,0,0,0,0,0,0,15,0,20,0},{0,0,0,0,0,0,0,0,0,15,0,15,0},
			{0,0,0,0,0,0,0,0,14,0,23,0,0}};
	
	
	public LLDriver(){
		Gramatica g = new Gramatica();
		term = g.getTerminals();
		nterm = g.getNoTerminals();
		prod = g.getProductions();
		s = new Stack(nterm.length);
		String s1 = AU.leerTxt("cadena.txt");
		AU.cad = s1.toCharArray();
		Procedure();
	}
	
	public void Procedure(){
		s.Push(nterm[0]);
		a = s.Read(s.TOS());
		x = AU.aceptado();
		while(!s.isEmpty()){
			if(inNoTerminals(x)){
				if(predic[ReturnPos(term, x)][ReturnPos(nterm, a)] != 0){
					
				}
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
	
	public int ReturnPos(String a[], String b){
		int r = 0;
		for(int x = 0; x < a.length; x++){
			if(b.equalsIgnoreCase(a[x])){
				r = x;
			}
		}
		return r;
	}
	
	public static void main(String[] mr){
		LLDriver lld = new LLDriver();
	}
	
}
