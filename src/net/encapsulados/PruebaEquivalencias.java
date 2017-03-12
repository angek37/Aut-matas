package net.encapsulados;


import javax.swing.*; 

public class PruebaEquivalencias {
	
	AFD2 a = new AFD2();
	AFD2 e = new AFD2();
	
	//Captura
	String auxK1 = "q0,q1,q2";
	String auxK2 = "r0,r1";
	String auxE = "a,b";
	String auxF1 = "q0";
	String auxF2 = "r0";
	String auxS1 = "q0";
	String auxS2 = "r0";
	int c=0;
	int alpha=0;
	int k1=0;
	int k2=0;
	int F1=0;
	int F2=0;
	SepararStrings sx = new SepararStrings();
	int tem1=0, tem2=0, M1 = 10, M2 = 10;
	String Trans[][] = new String [M1][M2];//transiciones
	String Equivalentes ="";
	int n=0, m=0, actual=0;//Apuntadores donde n es el numero de pareja de transiciones, m es la transicion para M1 y M2 para los estados
	//y actual es el par para n de comparaciones actualmente; sugerencia utilizar arreglo dinamico 
	int posiak=0;
	
	
	public void InputAFD(){
		alpha=sx.contar(auxE);
		a.setE(sx.Extraer(alpha, auxE));
		System.out.println(alpha);
		for (int x=0; x<alpha; x++) {
		System.out.println("Elemetos de sigma "+a.getE()[x]);	
		}
		k1=sx.contar(auxK1);
		a.setK(sx.Extraer(k1, auxK1));
		System.out.println(k1);
		for (int x=0; x<k1; x++) {
		JOptionPane.showMessageDialog(null,"Estados Aut1 "+a.getK()[x]);	
		}
		k2=sx.contar(auxK2);
		e.setK(sx.Extraer(k2, auxK2));
		System.out.println(k2);
		for (int x=0; x<k2; x++) {
		JOptionPane.showMessageDialog(null,"Estados Aut2 "+e.getK()[x]);	
		}
		F1=sx.contar(auxF1);
		a.setF(sx.Extraer(F1, auxF1));
		System.out.println(F1);
		for (int x=0; x<F1; x++) {
		JOptionPane.showMessageDialog(null,"Estados Finales1 "+a.getF()[x]);	
		}
		F2=sx.contar(auxF2);
		e.setF(sx.Extraer(F2, auxF2));
		System.out.println(F2);
		for (int x=0; x<F2; x++) {
		JOptionPane.showMessageDialog(null,"Estados Finales2 "+e.getF()[x]);	
		}
		a.setS(auxS1);
		JOptionPane.showMessageDialog(null,"Estado inicial1 "+a.getS());	
		e.setS(auxS2);
		JOptionPane.showMessageDialog(null,"Estado inicial2 "+e.getS());
				
	}
	
	public void LlenarDelta() {
		c=2;
		String ad [][]= new String [(k1)][(alpha)];//+1+1
		String ad2 [][]=new String [k2][alpha];
		System.out.println(k1+" "+alpha);
		for(int x=0; x<k1; x++){
			for(int y=0; y<alpha; y++){
				ad[x][y]=JOptionPane.showInputDialog("Por "+a.getK()[x]+" con "+a.getE()[y]+ " Dime hacia que estado va:");
				a.setDelta(ad);
			}
		}
		for(int x=0; x<k2; x++){
			for(int y=0; y<alpha; y++){
				ad2[x][y]=JOptionPane.showInputDialog("Por "+e.getK()[x]+" con "+a.getE()[y]+ " Dime hacia que estado va:");
				e.setDelta(ad2);
			}
		}	
	}
	
	public void ComparacionAFD (){// en este metodo se comprueban que sean finales o no finales los estados de momento funciona solo con los iniciales
		int sonfinales1=1;
		int sonfinales2=1;
		for(int x=0; x<F1; x++){
			System.out.println("llega al paso 1"+a.getS()+a.getF()[x]);
			if(a.getS().equals(a.getF()[x])){
				sonfinales1=2;
				break;
			}
		}
		for(int x=0; x<F2; x++){
			System.out.println("llega al paso 2"+e.getS()+e.getF()[x]);
			if(e.getS().equals(e.getF()[x])){
				sonfinales2=2;
				break;
			}
		}
			if(sonfinales1==sonfinales2){
				
				System.out.println("ambos son finales"+sonfinales1+sonfinales2);
			}else{
				System.out.println("no son finales"+sonfinales1+sonfinales2);
			}
			//if(a.getS()!=a.getF()[x]&&e.getS()!=e.getF()[x]){
				//continua comparando se va al paso 2
			//}else{
				//ya no compara no son equivalentes
			//}
	}
	public void InsertarTrans (){// en este metodo se insertan las transiciones 
		for(int x=0; x<k1; x++){//buscando inicial en K1
			if(a.getS().equals(a.getK()[x])){
				Trans[n][m]=a.getK()[x];
				m++;
			}
		}
		for(int x=0; x<k2; x++){//buscando inicial para K2
			if(e.getS().equals(e.getK()[x])){
				Trans[n][m]=e.getK()[x];
			}
		}
		System.out.println("Para trans 1: "+Trans[0][0]+" Para trans2 :"+Trans[0][1]);
		m=0;
	}
	public void ObtenerTrans (){// En este metodo se busca obtener las transiciones para los estados esta incompleto actualmente
		
		for(int x=0; x<k1; x++){
			if(Trans[actual][m].equals(a.getK()[x])){
				posiak=x;//ahora tenemos posicion de k en m y ahora podemos usar delta
				for(int y=0; y<alpha; y++){//en cada Transicion agrega los estados nuevos al arreglo Transiciones
					//if(Trans[n][y].equals(a.getDelta()[posiak][y])||Trans[n][y].equals(a.getDelta()[posiak][y])){
						//=a.getDelta()[posiak][y];
					//}
				}		
			}
		}
		for(int x=0; x<k2; x++){
			if(Trans[actual][m].equals(e.getK()[x])){
				for(int y=0; y<alpha; y++){//en cada Transicion agrega los estados nuevos al arreglo Transiciones
					n++;
					if(e.getF()[0].equals(e.getDelta()[posiak][y])){
						Trans[n][y]=e.getDelta()[posiak][y];
					}
				}
			}
		}
		for(int x=0; x<3; x++){
			for(int y=0; y<2; y++){
				System.out.println("Para M"+y+": "+Trans[x][y]);
			}	
		}
	}	
	
	public void busqueda(){
		
	}
	
	public static void main (String args[]){
		PruebaEquivalencias x = new PruebaEquivalencias ();
		x.InputAFD();
		x.LlenarDelta();
		x.ComparacionAFD();
		x.InsertarTrans();
		x.ObtenerTrans();
	}

}
