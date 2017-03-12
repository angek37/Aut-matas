package net.equivalencias;

import net.encapsulados.AFD;

public class Impresiones {			//Esta clase obtiene todos los valores que ya se han capturado
	String arr[] = {"la","ta"};
	public Impresiones(AFD a){
		super();
		print(a);
	}
	
	public void print(AFD a){
		a.setE(arr);
		System.out.print(a.getE().length);
//		System.out.print("Alfabeto:\n");
//		for(int x = 0; x < a.getE().length;x++){
//			System.out.print(a.getE()[x]+" ");
//		}
//		System.out.print("\nEstados:\n");
//		for(int x = 0; x<a.getK().length; x++){
//			System.out.print(a.getK());
//		}
//		System.out.print("\nDelta:\n");
//		for(int x = 0; x<a.getDelta().length; x++){
//			System.out.print(a.getDelta()[0][x]+"\t"+a.getDelta()[1][x]+"\t"+a.getDelta()[2][x]);
//			System.out.print("\n");
//		}
	}
}
