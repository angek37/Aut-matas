/*Para calcular la cantidad de combinaciones posibles aplique la formula de combinaciones
 * C = (m!)/(n!(m-n)!)
 * donde m es la cantidad de elementos
 * y n es la agrupacion de elementos
 * */

package net.AFD.minimizacion;

import net.encapsulados.*;

public class Combinaciones {
	AFD a = new AFD();
	
	public void llena(){
		String[] aux = {"0","1"};
		a.setE(aux);
		String[] aux2 = {"A", "B", "C", "D", "E"};
		a.setK(aux2);
	}

	
	public void Combina(){
		int f=(factorial(a.getK().length))/(factorial(2)*factorial(a.getK().length-2));	// Calcula la cantidad de combinaciones por formula
		System.out.print("\nCombinaciones "+f+"\n");
		String[][] c = new String[2][f];
		int n = a.getK().length-1;	// n son las veces que se repite cada elemento m-1
		int n2 = a.getK().length-1;	// n2 son las veces que se debe repetir que son m-1
		for(int x = 0; x < f;){
			for(int y = 0; y < n; y++){
				for(int z = 0; z < n2; z++){	// Cada vez que avanza disminuye uno así (n-1)
					c[0][x] = a.getK()[y];		// La primera columna se llena 'y' veces del mismo valor, cuando sale del ultimo for cuenta + 1, & cambia al siguiente estado
					c[1][x] = a.getK()[y+(z+1)];// La segunda columna se llena con 'y' que es el primer elemento de la combinación en el que se encuentra el for, MÁS la suma del valor en el que esta contando + 1
					x++;
				}
				n2--;	// disminuye n2 para que la repetición del primer elemento de la combinación vaya disminuyendo 
			}
		}
		
		for(int x = 0; x < f; x++){
			System.out.print(c[0][x]+"\t"+c[1][x]+"\n");
		}
	}
	
	public static int factorial(int n) {	// Regresa el resultado del factorial de un número
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
	
	public static void main(String mr[]){
		Combinaciones c = new Combinaciones();
		c.llena();
		c.Combina();
	}
}
