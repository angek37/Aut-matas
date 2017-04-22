package net.temporal;

import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;

public class Automata {

	static char[] cad;// cadena cinta del automata
	static char[] car;// caracteres especiales
	int fi = 0;// apuntador final
	int ini = 0;// apuntador inicial
	boolean tipstd = false;
	int estado = 0;// apuntador al estado del automata
	int pausar = 0;

	public void caracteres() {
		String caracteres = ".=+-/;\n$%<>";// cadacteres simples
		car = caracteres.toCharArray();
	}

	public char[] createArray() {
		char[] s;
		s = new char[40];
		for (int i = 0; i < 40; i++) {
			s[i] = (char) ('A' + i);
			System.out.println("char num " + i + " letle " + s[i]);
		}
		return s;
	}

	public void Automataa() {
		boolean primeravez = false;
		do {
			switch (estado) {
			case 0:
				vacio();
				System.out.println("Estado 0 " + cad[fi]);
				tipstd = true;// estado final
				if (Character.isSpace(cad[fi])) {// elemento vacio
					estado = 0;
				}
				if (Character.isDigit(cad[fi])) {// si es un numero no se si deba ignorar los 0s
					estado = 1;
					if (cad[fi] == 0) {
						estado = 0;
						System.out.println("Es 0 debe permanecer aqui");
					}
				}
				if (Character.isLowerCase(cad[fi])) {
					estado = 2;
				}
				if (Character.isUpperCase(cad[fi])) {
					estado = 2;
				}
				for (int i = 0; i < car.length; i++) { // para los caracteres
														// simples se necesita
														// forzosamente un array con los caracteres en especifico
					if (cad[fi] == car[i]) {
						estado = 3;
						break;
					}
				}
				fi++;
				break;
			case 1:
				tipstd = false;
				vacio();
				System.out.println("Estado 1 " + cad[fi]);
				if (Character.isSpace(cad[fi])) {// elemento vacio
					estado = 0;
				}
				if (Character.isDigit(cad[fi])) {// si es un numero no se si
					estado = 3;					// deba ignorar los 0s
					if (cad[fi] == 0) {
						estado = 0;
					}
				}
				fi++;
				vacio();
				break;
			case 2:
				tipstd = true;
				vacio();
				System.out.println("Estado 2 " + cad[fi]);
				if (Character.isSpace(cad[fi])) {// elemento vacio
					estado = 0;
					aceptado();
				}
				if (Character.isLowerCase(cad[fi])) {
					estado = 2;
				}
				if (Character.isUpperCase(cad[fi])) {
					estado = 2;
				}
				fi++;
				vacio();
				break;
			case 3:
				tipstd = true;
				vacio();
				System.out.println("Estado 3 " + cad[fi]);
				if (Character.isSpace(cad[fi])) {// elemento vacio
					estado = 0;
					aceptado();
				}
				//parte del estado de error o estado de terminar
				aceptado();
				estado = 0;
				fi++;
				if (estado == 0) {
					pausar++;
				}
				if (pausar == 5) {
					System.exit(0);
				}
				break;
			}
		} while (estado != 9);
	}

	public void aceptado() {
		String acepta = "";
		if (tipstd == true) {// cuando es aceptada la cadena
			System.out.print("La cadena es: ");
			while (fi != ini) {
				System.out.print("" + cad[ini]);
				ini++;
			}
			acepta = "aceptada";
		} else {// cuando no es aceptada la cadena
			System.out.print("La cadena es: ");
			while (fi != ini) {
				System.out.print("" + cad[ini]);
				ini++;
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

	public void vacio() {// vacio o estado de error

		try {
			if (cad[fi] <= cad.length) {
				System.out.println("Se termino de leer la cadena");

				System.exit(0);
				estado = 9;
			} else {
				// estado = 0;
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
				temp = temp + bfRead; // guardado el texto del archivo
			}
			text = temp;
			bf.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error: No se encontro el archivo ");
		}
		return text;
	}
}
