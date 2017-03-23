package net.AFD.minimizacion;

import net.encapsulados.*;

public class Minimiza {
	String[][] starters;
	String[] deleted;
	
	public Minimiza(AFD a){
		int c = Combina(a);
		Process(a, c);
	}
	
	public void Process(AFD a, int c){
		deleted = new String[c];
		boolean d = true;
		for(int x = 0; x < c; x++){
			for(int y = 0; y < c; y++){
				if(starters[1][x] == deleted[y]){
					d = false;
				}
			}
			if(Compatibles(a, starters[0][x], starters[1][x]) && d){
				if(Table(a, starters[0][x], starters[1][x])){
					deleted[x] = starters[1][x];
					DeleteK(a, starters[0][x] ,starters[1][x]);
				}
			}
		}
		Print(a);
	}
	
	public void Print(AFD a){
		for(int x = 0; x < a.getDelta()[0].length; x++){
			System.out.println("\n");
			for(int y = 0; y < 3; y++){
				System.out.print(a.getDelta()[y][x]+"\t");
			}
		}
	}
	
	public void DeleteK(AFD afd, String where,String del){
		for(int x = 0; x < afd.getDelta()[0].length; x++){
			if(del.equals(afd.getDelta()[0][x])){
				for(int y = 0; y < 3; y++){
					afd.getDelta()[y][x] = "nulo";
				}
			}
			if(del.equals(afd.getDelta()[2][x])){
				afd.getDelta()[2][x] = where;
			}
		}
	}
	
	public boolean Table(AFD afd, String a, String b){
		int l = afd.getE().length*2;
		boolean r=false;
		boolean keep = true;
		String[][] t = new String[l+2]/*Columna*/[10]/*Fila*/;
		String[][] pares = new String[2][100];
		
		pares[0][0] = a;
		pares[1][0] = b;
		int c = 0;
		int w = 0;
		t[0][c] = pares[0][c];//cambiar posicion de pares por variable
		t[1][c] = pares[1][c];
		while(keep){
			for(int x = 2; x< (l);){
				for(int y = 0; y < afd.getE().length; y++){
					for(int z = 0; z < 2; z++){
						t[x][c] = Destination(afd, t[z][c], afd.getE()[y]);
						pares[z][w] = Destination(afd, t[z][c], afd.getE()[y]);
						x++;
					}
					w++;
				}
			}
			
//			System.out.print("\n\n");
//			for(int x = 0; x < pares[0].length; x++){
//				if(pares[0][x] != null){
//					System.out.println(pares[0][x]+"\t"+pares[1][x]);
//				}
//			}
			System.out.println(c);
			c++;
			
			boolean f1 = true;
			boolean f2;	// Esta en la tabla
			int x = 0;
			while(f1 &&  x < (c*2)){
				f2=false;
				for(int y = 0; y < c; y++){
					if(pares[0][x].equals(t[0][y]) && pares[1][x].equals(t[1][y])){
						f2 = true;
						if(x == (c*2)-1){
							keep=false;
							r=true;
							f1=false;
							break;
						}
					}
				}
				if(!f2){
					if(Compatibles(afd, pares[0][x], pares[1][x])){
						t[0][c] = pares[0][x];
						t[1][c] = pares[1][x];
						f1=false;
					}else{
						keep = false;
						f1 = false;
						r = false;
					}
				}
				x++;
			}
		}
		
		return r;
	}
	
//	public boolean isNull(String[][] pares, int p){
//		boolean f = false;
//		try{
//			char aux = pares[0][p+1].charAt(0);
//		}catch(NullPointerException e){
//			return f = true;
//		}
//		return f;
//	}
//	
	public String Destination(AFD afd, String from, String who){
		String to=null;
		for(int x = 0; x < afd.getDelta()[0].length;x++){
			if(afd.getDelta()[0][x].equals(from) && afd.getDelta()[1][x].equals(who)){
				to = afd.getDelta()[2][x];
			}
		}
		return to;
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
		String[] F = {"q1"};
		a.setF(F);
		String[][] delta = new String[3][6];
		delta[0][0] = "q0";
		delta[0][1] = "q0";
		delta[0][2] = "q1";
		delta[0][3] = "q1";
		delta[0][4] = "q2";
		delta[0][5] = "q2";
		
		delta[1][0] = "a";
		delta[1][1] = "b";
		delta[1][2] = "a";
		delta[1][3] = "b";
		delta[1][4] = "a";
		delta[1][5] = "b";
		
		delta[2][0] = "q1";
		delta[2][1] = "q0";
		delta[2][2] = "q2";
		delta[2][3] = "q1";
		delta[2][4] = "q1";
		delta[2][5] = "q2";
		a.setDelta(delta);
		
		Minimiza m = new Minimiza(a);
	}

}