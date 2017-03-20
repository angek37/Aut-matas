package net.AFD.minimizacion;

import net.encapsulados.*;

public class Minimiza {
	String[][] starters;
	
	public Minimiza(AFD a){
		int c = Combina(a);
		Process(a, c);
	}
	
	public void Process(AFD a, int c){
		if(Compatibles(a, "q0", "q2")){
			System.out.println("Compatibles");
		}else{
			System.out.println("No compatibles");
		}
	}
	
	public boolean Compatibles(AFD afd, String a, String b){
		boolean fl = false, fl2 = false, r = false;
		for(int x = 0; x < afd.getF().length; x++){
			if(a.equals(afd.getF()[x]) && fl != true){	// Entra solo si encuentra el estado en el conjunto de finales Y si no lo había encontrado antes
				fl = true;
			}
			if(b.equals(afd.getF()[x]) && fl2 != true){
				fl2 = true;
			}
		}
		
		if((fl && fl2) || (!fl && !fl2)){
			r = true;
		}
		
		return r;
	}
	
	public int Combina(AFD a){
		int f=(factorial(a.getK().length))/(factorial(2)*factorial(a.getK().length-2));
		starters = new String[2][f];
		int n = a.getK().length-1;
		int n2 = a.getK().length-1;
		for(int x = 0; x < f;){
			for(int y = 0; y < n; y++){
				for(int z = 0; z < n2; z++){
					starters[0][x] = a.getK()[y];
					starters[1][x] = a.getK()[y+(z+1)];
					x++;
				}
				n2--;
			}
		}
		return f;
	}
	
	public static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
	
	public static void main(String[] mr){
		AFD a = new AFD();
		String[] E = {"a","b"};
		a.setE(E);
		String[] K = {"q0", "q1", "q2"};
		a.setK(K);
		String s = "q0";
		a.setS(s);
		String[] F = {"q1", "q2"};
		a.setF(F);
		Minimiza m = new Minimiza(a);
	}

}
