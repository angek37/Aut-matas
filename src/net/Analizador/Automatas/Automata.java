package net.Analizador.Automatas;

import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;

public class Automata {

	static char[] cad;
	static char[] car;
	int fi = 0;
	int ini = 0;
	boolean tipstd = false;
	int estado = 0;
	
	public void caracteres() {
		String caracteres = "*,:=+-()/;\n$<>"; 
		car = caracteres.toCharArray();
	}

	public void Automataa() {
		boolean primeravez = false;
		do {
			switch (estado) {
			case 0:
				vacio();
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
				vacio();
				//System.out.println("Estado 1 " + cad[fi]);
				if (Character.isSpace(cad[fi])) {
					estado = 0;
					aceptado();
				}
				else if (cad[fi] == '.') {
					estado = 5;	
				}else if(Character.isDigit(cad[fi])){
					estado = 1;
					if (cad[fi] == '0') {
						estado = 1;
					}
				}else{
					estado=4;
				}
				fi++;
				break;
			case 2:
				tipstd = true;
				vacio();
				//System.out.println("Estado 2 " + cad[fi]);
				if (Character.isSpace(cad[fi])) {
					estado = 0;
					aceptado();
				}
				else if (Character.isLetter(cad[fi])) {
					estado = 2;
				}else if(Character.isDigit(cad[fi])){
					estado = 2;
				}else{
					estado = 4;
				}
				fi++;
				break;
			case 3:
				tipstd = true;
				vacio();
				//System.out.println("Estado 3 " + cad[fi]);
				if(cad[fi]=='='){
					estado= 3;
				}else if(Character.isSpace(cad[fi])) {// elemento vacio
					estado = 0;
					aceptado();
				}else{
					estado = 4;
				}
				fi++;
				break;
			case 4:
				tipstd=false;
				vacio();
				System.out.println("Estado de error el token no fue aceptado");
				aceptado();
				estado=0;
				fi++;
				//vacio();
				break;
			case 5:
				tipstd=true;
				vacio();
				//System.out.println("Estado 5 " + cad[fi]);
				if (Character.isSpace(cad[fi])) {
					estado = 0;
					aceptado();
				}else if(Character.isDigit(cad[fi])){
					estado = 5;
				}else{
					estado=4;
				}
				fi++;
				break;
			}
		} while (estado != 6);
	}
	
	public void aceptado() {
		String acepta = "";
		String temp = "";
		int initemp = 0;
		if (tipstd == true) {
			System.out.print("El token:");
			while (fi != ini) {
				if((Character.isSpace(cad[ini])||cad[ini]=='0')){
					ini++;
				}else{
					System.out.print("" + cad[ini]);
					ini++;		
				}	
			}
			acepta = "aceptada";
		} else {
			System.out.print("El token:");
			while (fi != ini) {
				if((Character.isSpace(cad[ini])||cad[ini]=='0')){
					ini++;
				}else{
					System.out.print("" + cad[ini]);
					ini++;	
				}
			}
			acepta = "no aceptada";
		}
		System.out.println("\nLlego aun estado de " + acepta);
	}

	public void salida() {
		System.out.print("La cadena a evaluar es: ");
		for (int r = 0; r < cad.length; r++) {
			System.out.print("" + cad[r]);
		}
		System.out.println("");
		System.out.println("Estado  || Elemento");
	}

	public void vacio() {
		try {
			if (cad[fi] < cad.length) {
				//System.out.println("vacio? ");

				//estado = 9;
			} else {

			}
		} catch (Exception e) {
			aceptado();

			System.out.println("\nSe termino la cadena");

			if (tipstd == true) {
				System.out.println("Termino en un estado: de aceptacion");
				fi++;
			} else {
				System.out.println("Termino en un estado: de no aceptacion o Error");
			}
			if (ini == fi - 1) {
				System.out.print("saliendo");
				System.exit(0);
			}
		}
	}

	public String leerTxt(String direccion) {
		String text = "";

		try {
			BufferedReader bf = new BufferedReader(new FileReader(direccion));
			String temp = "";
			String bfRead;
			while ((bfRead = bf.readLine()) != null) {
				temp = temp + bfRead; 
			}
			text = temp;
			bf.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error: No se encontro el archivo ");
		}
		return text;
	}
}
