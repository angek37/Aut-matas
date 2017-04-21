package net.Analizador.Gramatica;

public class Gramatica {
	private char del = '>';
	private String[] term = new String[1];
	private int co = 0;
	
	public Gramatica(){
		Archivo a = new Archivo();
		String[] g = a.getGramar();
		Nterminales(g);
	}
	
	public void Nterminales(String[] g){
		String aux = "";
		int y;
		for(int x = 0; x < g.length; x++){
			y = 0;
			while(g[x].charAt(y)!= del){
				if(g[x].charAt(y) != ' '){
					aux += g[x].charAt(y);
				}
				y++;
			}
			isRepeat(aux);
			aux = "";
		}
		
		System.out.println("\n\nTerminales: ");
		PrintArray(term);
	}
	
	public void isRepeat(String a){
		String[] aux;
		boolean fl = false;
		
		for(int x = 0; x < term.length; x++){
			if(a.equals(term[x])){
				fl = true;
			}
		}
		if(fl == false){
			co++;
			aux = new String[co];
			
			for(int y = 0; y < term.length; y++){
				aux[y] = term[y];
			}
			aux[co-1] = a;

			term = new String[co];
			term = aux;
			aux = null;
		}
	}
	
	public void PrintArray(String[] a){
		for(int x = 0; x < a.length; x++){
			System.out.println(a[x]);
		}
	}
	
	public static void main(String mr[]){
		Gramatica g = new Gramatica();
	}
}
