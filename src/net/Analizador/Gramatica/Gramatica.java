package net.Analizador.Gramatica;

public class Gramatica {
	private char del = '>';
	private String[] noterm = new String[1];	//Almacena los simbolos terminales
	private int co = 0;	// Contador global del método isRepeat, de las veces
	private String[][] prod;
	private String[][] prodCo;
	private int ca = 0; //Contador global del método InsertProd cuenta la cantidad de simbolos 
						//de cada produccion y se reinicia al cambio de fila de gramatica
	private String[] term = new String[1];
	private int ce = 0;
	
	public Gramatica(){
		Archivo a = new Archivo();
		String[] g = a.getGramar();
		NoTerminales(g);
		Producciones(g);
		ProduccionesDer(g);
		Terminales();
	}
	
	public void Terminales(){
		for(int x = 0; x < prod.length; x++){
			for(int y = 0; y < prod[x].length; y++){
				isTerm(prod[x][y]);
			}
		}
	}
	
	public void isTerm(String a){
		boolean fl = true;
		String[] aux;
		for(int x = 0; x < noterm.length; x++){
			if(noterm[x].equals(a)){
				fl = false;		// Si está en los NO terminales no es un simbolo terminal
			}
		}
		
		if(fl && ce > 0){		// Verifica que haya al menos un simbolo ya registrado
			for(int x = 0; x < term.length; x++){
				if(term[x].equals(a)){
					fl = false;		// Si YA está registrado no vuelve a insertarlo
				}
			}
		}
		
		if(fl && a != ""){
			ce++;
			aux = new String[ce];
			for(int y = 0; y < term.length; y++){
				aux[y] = term[y];
			}
			aux[ce-1] = a;
			term = new String[ce];
			for(int y = 0; y < term.length; y++){
				term[y] = aux[y];
			}
		}
	}
	
	public void Producciones(String[] g){
		prodCo = new String[g.length][0];
		int y=0;
		String aux = "";
		for(int x = 0; x < g.length; x++){
			while(y < g[x].length()){
				if(g[x].charAt(y) != del && g[x].charAt(y) != ' '){
					aux+=g[x].charAt(y);
					if(y == g[x].length()-1){
						InsertProdCom(x, aux);
						aux = "";
					}
				}else{
					if(aux != ""){
						InsertProdCom(x, aux);
					}
					aux = "";
				}
				y++;
			}
			y=0;
			ca = 0;
		}
	}
	
	public void InsertProdCom(int c, String a){	/* Recibe en 'c' la fila de la producción y el simbolo, lo mete 
		* en una columna de la fila de la producción en la que se encuentra*/
			ca++;
			String[] aux = new String[prodCo[c].length+1];
			for(int x = 0; x < prodCo[c].length; x++){
				aux[x] = prodCo[c][x];
			}
			aux[ca-1] = a;
			prodCo[c] = new String[ca];
			for(int x = 0; x < prodCo[c].length; x++){
				prodCo[c][x] = aux[x];
			}
		}
	
	public void ProduccionesDer(String[] g){
		int pos = 0;
		int y = 0;
		String aux = "";
		for(int x = 0; x < g.length; x++){
			while(y < g[x].length()){
				if(g[x].charAt(y) == del){
					pos = y + 1;
					while(g[x].charAt(pos) == ' ' && pos < g[x].length()-1){
						pos ++;
					}
				}
				y++;
			}
			y = 0;
			for(int z = pos; z < g[x].length(); z++){
				if(g[x].charAt(z) != ' '){
					aux += g[x].charAt(z);
					if(z == g[x].length()-1){
						InsertProd(x, aux);
						aux = "";
					}
				}else{
					InsertProd(x, aux);
					aux = "";
				}
			}
			ca = 0;
		}
	}
	
	public void InsertProd(int c, String a){	/* Recibe en 'c' la fila de la producción y el simbolo, lo mete 
	* en una columna de la fila de la producción en la que se encuentra*/
		ca++;
		String[] aux = new String[prod[c].length+1];
		for(int x = 0; x < prod[c].length; x++){
			aux[x] = prod[c][x];
		}
		aux[ca-1] = a;
		prod[c] = new String[ca];
		for(int x = 0; x < prod[c].length; x++){
			prod[c][x] = aux[x];
		}
	}
	
	public void NoTerminales(String[] g){	//Método para extrar los simbolos terminales
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
		prod = new String[g.length][0];
	}
	
	public void isRepeat(String a){		//	Auxiliar del método "NoTerminales" revisa si el simbolo 
										//extraido no esta ya registrado
		String[] aux;
		boolean fl = false;
		
		for(int x = 0; x < noterm.length; x++){
			if(a.equals(noterm[x])){
				fl = true;
			}
		}
		if(fl == false){
			co++;
			aux = new String[co];
			
			for(int y = 0; y < noterm.length; y++){
				aux[y] = noterm[y];
			}
			aux[co-1] = a;

			noterm = new String[co];
			noterm = aux;
			aux = null;
		}
	}
	
	public String[] getTerminals(){
		return term;
	}
	
	public String[] getNoTerminals(){
		return noterm;
	}
	
	public String[][] getRightProductions(){
		String[][] aux = new String[prod.length+1][0];
		for(int x = 0; x < prod.length; x++){
			aux[x+1] = new String[prod[x].length];
			for(int y = 0; y < prod[x].length; y++){
				aux[x+1][y] = prod[x][y];
			}
		}
		prod = null;
		return aux;
	}
	
	public String[][] getProductions(){
		return prodCo;
	}

	public void PrintArray(String[] a){		// Método para imprimir arreglo unidimensional
		for(int x = 0; x < a.length; x++){
			System.out.println(a[x]);
		}
	}
	
	public void PrintBArray(String[][] a){	// Método para imprimir arreglo bidimensional
		for(int x = 0; x < a.length; x++){
			for(int y = 0; y < a[x].length; y++){
				System.out.print(a[x][y] + " | ");
			}
			System.out.println("\n");
		}
	}
	
	public static void main(String mr[]){
		Gramatica g = new Gramatica();
		g.PrintArray(g.term);
		g.PrintArray(g.noterm);
		g.PrintBArray(g.prod);
		g.PrintBArray(g.prodCo);
	}
}