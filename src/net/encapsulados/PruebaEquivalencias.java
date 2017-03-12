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
	String auxS2 = "z0";
	int c=0;
	int alpha=0;
	int k1=0;
	int k2=0;
	int F1=0;
	int F2=0;
	SepararStrings sx = new SepararStrings();
	
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
		//JOptionPane.showMessageDialog(null,"Estados Aut1 "+a.getK()[x]);	
		}
		k2=sx.contar(auxK2);
		e.setK(sx.Extraer(k2, auxK2));
		System.out.println(k2);
		for (int x=0; x<k2; x++) {
		//JOptionPane.showMessageDialog(null,"Estados Aut2 "+e.getK()[x]);	
		}
		F1=sx.contar(auxF1);
		e.setF(sx.Extraer(F1, auxF1));
		System.out.println(F1);
		for (int x=0; x<c; x++) {
		//JOptionPane.showMessageDialog(null,"Estados Finales1 "+a.getF()[x]);	
		}
		F2=sx.contar(auxF2);
		e.setF(sx.Extraer(F2, auxF2));
		System.out.println(F2);
		for (int x=0; x<c; x++) {
		//JOptionPane.showMessageDialog(null,"Estados Finales2 "+e.getF()[x]);	
		}
		a.setS(auxS1);
		//JOptionPane.showMessageDialog(null,"Estado inicial1 "+a.getS());	
		e.setS(auxS2);
		//JOptionPane.showMessageDialog(null,"Estado inicial2 "+e.getS());
				
	}
	
	
	public void LlenarDelta() {
		//c=sx.contar(auxE);
		c=2;
		String ad [][]= new String [(k1+1)][(alpha+1)];
		String ad2 [][]=new String [k2][alpha];
		System.out.println(k1+" "+alpha);
		for(int x=0; x<k1; x++){
			for(int y=0; y<alpha; y++){
				ad[x][y]=JOptionPane.showInputDialog("Por "+a.getK()[x]+"con "+a.getE()[y]+ " Dime hacia que estado va:");
				a.setDelta(ad);
			}
		}
		for(int x=0; x<k2; x++){
			for(int y=0; y<alpha; y++){
				ad2[x][y]=JOptionPane.showInputDialog("Por "+e.getK()[x]+"con "+a.getE()[y]+ " Dime hacia que estado va:");
				e.setDelta(ad2);
			}
		}	
	}
	
	
	public void Paso1(){//nombre provisional
		String Equivalentes ="";
		boolean sonfinales1=true;
		boolean sonfinales2=true;
		for(int x=0; x<F1; x++){
			if(a.getS()!=a.getF()[x]){
				sonfinales1=false;
				break;
			}
			if(e.getS()!=e.getF()[x]){
				sonfinales2=false;
				break;
			}	
			if(sonfinales1==sonfinales2){
				//continua comparando se va al paso 2
			}else{
				//ya no compara no son equivalentes
			}
			if(a.getS()!=a.getF()[x]&&e.getS()!=e.getF()[x]){
				//continua comparando se va al paso 2
			}else{
				//ya no compara no son equivalentes
			}
			
		}
	}
	public void Paso2(){//nombre provisional
		int tem1=0, tem2=0, M1 = 10, M2 = 10;
		int ini=0, tope=0;
		String Trans[][] = new String [M1][M2];//transiciones
		
		for(int x=0; x<k1; x++){//buscando inicial en K1
			if(a.getS()==a.getK()[x]){
			
			Trans[0][0]=a.getK()[x];
			}
		}
		for(int x=0; x<k2; x++){//buscando inicial para K2
			if(e.getS()==e.getK()[x]){
			tem2=1;	
			Trans[0][1]=e.getK()[x];
			}
		}
		ini=0;
		for(int x=0; x<k1; x++){
			if(Trans[tem1][tem2-1]==a.getK()[x]){
				for(int y=0; y<alpha; y++){
		//			if(a.getDelta()[x][y]==Trans[][]&&e.getDelta()[x][y+1]==){
					
					}
				}		
			}
			
		}
		
	public void busqueda(){
		
	}
	
	public void ComparacionAFD(){
		String Equivalentes ="";
		boolean sonfinales1=true;
		boolean sonfinales2=true;
		for(int x=0; x<F1; x++){
			if(a.getS()!=a.getF()[x]){
				sonfinales1=false;
				break;
			}
			if(e.getS()!=e.getF()[x]){
				sonfinales2=false;
				break;
			}	
			if(sonfinales1==sonfinales2){
				//continua comparando
			}else{
				//ya no compara no son equivalentes
			}
			if(a.getS()!=a.getF()[x]&&e.getS()!=e.getF()[x]){
			//continua comparando
			}else{
			//ya no compara no son equivalentes
			}
		}	
	}
	
	
	public static void main (String args[]){
		PruebaEquivalencias x = new PruebaEquivalencias ();
		x.InputAFD();
		x.LlenarDelta();
	}

}
