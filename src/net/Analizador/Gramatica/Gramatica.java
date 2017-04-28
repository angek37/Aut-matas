package net.Analizador.Gramatica;

public class Gramatica {
	private char del = '>';
	private String[] term = new String[1]; // Almacena los simbolos terminales
	private int co = 0;
	private String[][] nterm;
	private int ca = 0;
	
	public Gramatica(){
		Archivo a = new Archivo();
		String[] g = a.getGramar();
		System.out.println("\nGramática\n");
		a.Imprimir();
		Nterminales(g);
		Terminales(g);
	}
	
	public void Terminales(String[] g){
		String aux;
		boolean fl, fl2;
		int c = 0;
		int c2;
		for(int x = 0; x < g.length; x++){
			fl = false;
			aux = "";
			for(int y = 0; y < g[x].length(); y++){
				if(g[x].charAt(y) == del){
					fl = true;
				}
				if(fl && g[x].charAt(y) != del){
					aux += g[x].charAt(y);
				}
			}
			c2 = c;
			if((c = WhoTerm(g, x)) != c2){
				ca = 0;
			}
			InTerminales(aux, c);
		}
		System.out.println("\n\nTerminales: ");
		PrintBArray(nterm);
	}

	public void InTerminales(String a, int term){
		String[] aux;
		if(a != ""){
			ca++;
			aux = new String[ca];
			for(int x = 0; x < nterm[term].length; x++){
				aux[x] = nterm[term][x];
			}
			nterm[term] = new String[ca];
			aux[ca-1] = a;
			for(int x = 0; x < nterm[term].length; x++){
				nterm[term][x] = aux[x];
			}
			aux = null;
		}
	}
	
	public int WhoTerm(String[] g , int a){
		int pos = 0;
		String aux = "";
		int y = 0;
		while(g[a].charAt(y) != del){
			if(g[a].charAt(y) != ' '){
				aux += g[a].charAt(y);
			}
			y++;
		}
		for(int x = 0; x < term.length; x++){
			if(aux.equals(term[x])){
				pos = x;
			}
		}
		return pos;
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
		
		System.out.println("\n\nNO Terminales: ");
		PrintArray(term);
		nterm = new String[term.length][];
		for(int x = 0; x < term.length; x++){
			nterm[x] = new String[1];
		}
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
	
	public void PrintBArray(String[][] a){
		for(int x = 0; x < a.length; x++){
			for(int y = 0; y < a[x].length; y++){
				System.out.print(a[x][y] + " | ");
			}
			System.out.println("\n");
		}
	}
	
	public static void main(String mr[]){
		Gramatica g = new Gramatica();
	}
}