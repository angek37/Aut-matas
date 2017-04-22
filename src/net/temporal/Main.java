package net.temporal;

public class Main {

	public static void main(String[] args) {
		Automata aut = new Automata();
        
        String s1=aut.leerTxt("E:\\txtdeprueba\\cadena.txt");
        aut.cad = s1.toCharArray();
        System.out.println(s1);
        
    	aut.caracteres();
    	aut.Automataa();
    	aut.salida();
    	
        
	}
}
