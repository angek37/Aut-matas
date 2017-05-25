package net.Analizador.Automatas;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class Automata {
	
	static char[] cad;
	static char[] car = {':','=','+','-','(',')',';'};
	int fi = 0;
	int ini = 0;
	boolean tipstd = false;
	int estado = 0;
	String text = "";
	int y =0;
	String a="";
	 
	public void leerarchivo (){
		String text = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader("cadena.txt"));
			String temp = "";
			String bfRead;
			while ((bfRead = bf.readLine()) != null) {
				temp = temp + bfRead+" ";//denota la linea donde se encuentra el error
			}
			bf.close();
			//System.out.println("la cadena es: "+temp);
			cad=temp.toCharArray();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error: No se encontro el archivo ");
		}	
	}


	public String Automataa() {
		String token="";
		int no_ter =0; 
		if(fi!=cad.length){
		do {
			switch (estado) {
			case 0:
				//System.out.println("Estado 0 " + cad[fi]);
				tipstd = false;
				if (Character.isSpace(cad[fi])) {//Si es un espacio en blanco
					estado = 0;
				}else if (Character.isDigit(cad[fi])) {//Si es un numero
					estado = 1;
				}else if (Character.isLetter(cad[fi])) {//si es una letra 
					estado = 2;
				}else {
					estado = 4;
				}
				for (int i = 0; i < car.length; i++) { //Si es un caracter especial
					if (cad[fi] == car[i]) {
						estado = 3;
						break;
					}
				}
				fi++;
				break;
			case 1:
				tipstd = true;
				//System.out.println("Estado 1 " + cad[fi]);
				
				if (Character.isSpace(cad[fi])) {
					estado = 6;
				}
				else if (cad[fi] == '.') {
					estado = 5;	
				}else if(Character.isDigit(cad[fi])){
					estado = 1;
				}else{
					estado=4;
				}
				no_ter=1;
				fi++;
				break;
			case 2:
				tipstd = true;
				//System.out.println("Estado 2 " + cad[fi]);
				if (Character.isSpace(cad[fi])) {
					estado = 6;
				}
				else if (Character.isLetter(cad[fi])) {
					estado = 2;
				}else if(Character.isDigit(cad[fi])){
					estado = 2;
				}else{
					estado = 4;
				}
				no_ter=2;
				fi++;
				break;
			case 3:
				tipstd = true;
				//System.out.println("Estado 3 " + cad[fi]);
				if(cad[fi]=='='){
					estado= 3;
				}else if(Character.isSpace(cad[fi])) {// elemento vacio
					estado = 6;
				}else{
					estado = 4;
				}
				fi++;
				break;
			case 4:
				tipstd=false;
				System.out.println("Error lexico el token no fue aceptado");
				estado=6;
				fi++;
				break;
			case 5:
				tipstd=true;
				//System.out.println("Estado 5 " + cad[fi]);
				if (Character.isSpace(cad[fi])) {
					estado = 6;
				}else if(Character.isDigit(cad[fi])){
					estado = 5;
				}else{
					estado=4;
				}
				no_ter=5;
				fi++;
				break;
			}
		} while (estado != 6);
		}else{
			System.out.println("Fin del archivo");
		}
		String acepta = "";
		int initemp = 0;
		if (tipstd == true) {
			while (fi != ini) {
				if((Character.isSpace(cad[ini])||cad[ini]=='0')){
					ini++;
				}else{
					token+=cad[ini];
					ini++;
				}	
			}
			acepta = "aceptada";
		} else {
			while (fi != ini) {
				if((Character.isSpace(cad[ini])||cad[ini]=='0')){
					ini++;
				}else{	
					token+=cad[ini];
					ini++;
				}
			}
			acepta = "no aceptada";
		}
		if(no_ter==2){
			if(token.equals("begin")||token.equals("read")||token.equals("write")||token.equals("end")){
			}else{
				token="id";
			}
		}else if(no_ter==1){
			token="intLiteral";
		}else if(no_ter==5){
			token="RealNum";
		}else{
			
		}
		text=token;
		estado=0;
		return text;
	}
	
	public void salida() {//metodo de salida de la cadena completa
		System.out.print("La cadena a evaluar es: ");
		for (int r = 0; r < cad.length; r++) {
			System.out.print("" + cad[r]);
		}
		System.out.println("");
		System.out.println("Estado  || Elemento");
	}
	
	
	public static void main (String args[]){
		Automata aut = new Automata();
		
		aut.leerarchivo();
		
		System.out.println(" ");
		System.out.println("Token: "+aut.Automataa());
		System.out.println("token: "+aut.Automataa());
		System.out.println("token: "+aut.Automataa());
		System.out.println("token: "+aut.Automataa());
		System.out.println("token: "+aut.Automataa());
		System.out.println("token: "+aut.Automataa());
		System.out.println("token: "+aut.Automataa());
		System.out.println("token: "+aut.Automataa());
		System.out.println("token: "+aut.Automataa());
		
	}
}