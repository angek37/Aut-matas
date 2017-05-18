package net.Analizador.Automatas;

public class Main {

	public static void main(String[] args) {
		Automata aut = new Automata();
        
        String s1=aut.leerTxt("C:\\Users\\Gerardo\\workspace\\Automatas\\cadena.txt");
        aut.cad = s1.toCharArray();
        System.out.println(s1);
        
    	aut.caracteres();
    //	aut.salida();
    	aut.Automataa();

    	
        
	}
}
