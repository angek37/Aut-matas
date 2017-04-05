package net.AFD.minimizacion;

import net.encapsulados.*;

public class Impresiones {
	public Impresiones(AFD a){
		Prints(a);
	}
	
	public void Prints(AFD a){
		System.out.println("Alfabeto: ");
		for(int x = 0; x < a.getE().length; x++){
			System.out.print("\t"+a.getE()[x]);
		}
		System.out.print("\nEstados: ");
		for(int x = 0; x < a.getK().length; x++){
			System.out.print("\t"+a.getK()[x]);
		}
		System.out.print("\nInicial: "+a.getS());
		System.out.print("\nFinales: ");
		for(int x = 0; x < a.getF().length; x++){
			System.out.print("\t"+a.getF()[x]);
		}
		System.out.println("\nTabla de transiciones: \n");
		for(int x = 0; x < (a.getE().length*a.getK().length); x++){
			for(int y = 0; y < 3;y++){
				System.out.print("\t"+a.getDelta()[y][x]);
			}
			System.out.print("\n");
		}
	}
}
