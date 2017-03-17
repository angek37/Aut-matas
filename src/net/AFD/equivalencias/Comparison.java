package net.AFD.equivalencias;


import javax.swing.*;
import net.encapsulados.*;

public class Comparison {
	
	public Comparison(AFD a,AFD e){
		Patch(a,e);
		InputAFD(a,e);
		ComparacionAFD(a,e);
		InsertarTrans(a,e);
		ObtenerTransIni(a,e);
	}
	
	//Captura
	/*
	String auxK1 = "q0,q1,q2";
	String auxK2 = "r0,r1";
	String auxE = "a,b";
	String auxF1 = "q0,q2";
	String auxF2 = "r0";
	String auxS1 = "q0";
	String auxS2 = "r0";
	*/
	
	
	
	int alpha=0;
	int k1=0;
	int k2=0;
	int F1=0;
	int F2=0;
	SepararStrings sx = new SepararStrings();
	int tem1=0, tem2=0, M1 = 150, M2 = 30;
	String Trans[][] = new String [M1][M2];//transiciones
	String Equivalentes ="";
	int n=0, m=0, actual=0;//Apuntadores donde n es el numero de pareja de transiciones, m es la transicion para M1 y M2 para los estados
	//y actual es el par para n de comparaciones actualmente; sugerencia utilizar arreglo dinamico 
	String TransM1="", TransM2="";
	int tope=0;
	int ini1=0;
	int ini2=0;
	boolean insertar = true;
	int parar=0;
	
	public void Patch(AFD a, AFD e){
		int l = (a.getE().length*a.getK().length);
		String aux[][] = new String[2][l];
		for(int x = 0; x<l; x++){
			aux[0][x] = a.getDelta()[0][x];
			aux[1][x] = a.getDelta()[2][x];
		}
		a.setDelta(aux);
		l = (e.getE().length*e.getK().length);
		String aux2[][] = new String[2][l];
		for(int x = 0; x<l; x++){
			aux2[0][x] = e.getDelta()[0][x];
			aux2[1][x] = e.getDelta()[2][x];
		}
		e.setDelta(aux2);
	}
	
	public void InputAFD(AFD a, AFD e){
		alpha=a.getE().length;
		k1=a.getK().length;
		k2=e.getK().length;
		F1=a.getF().length;
		F2=e.getF().length;
	}
	
	
	public void ComparacionAFD (AFD a, AFD e){// en este metodo se comprueban que sean finales o no finales los estados de momento funciona solo con los iniciales

		int sonfinales1=1;
		int sonfinales2=1;
		for(int x=0; x<F1; x++){
			if(a.getS().equals(a.getF()[x])){
				sonfinales1=2;
				break;
			}
		}
		for(int x=0; x<F2; x++){
			if(e.getS().equals(e.getF()[x])){
				sonfinales2=2;
				break;
			}
		}
		if(sonfinales1==sonfinales2){
			Equivalentes="Son Equivalentes";
			
		}else{
			Equivalentes="No Son Equivalentes";
			//System.out.println("son finales"+sonfinales1+sonfinales2);
			print();
		}
	}
	
	public void InsertarTrans (AFD a, AFD e){// en este metodo se insertan las transiciones para los estados iniciales
		for(int x=0; x<k1; x++){//buscando inicial en estados K1
			if(a.getS().equals(a.getK()[x])){
				Trans[n][m]=a.getK()[x];
				m++;
			}
		}
		for(int x=0; x<k2; x++){//buscando inicial para estados K2
			if(e.getS().equals(e.getK()[x])){
				Trans[n][m]=e.getK()[x];
			}
		}
		m=0;
		tope++;
		n++;
	}
	
	public void ObtenerTransIni (AFD a, AFD e){
		
			while(insertar==true){
			int j=0;	
			int k=0;
			for(int x=0; x<k1; x++){
				if(Trans[ini1][ini2].equals(a.getK()[x])){//busca posicion en k para automata a
					j=x;
				}	
			}
			ini2++;
			for(int x=0; x<k2; x++){
				if(Trans[ini1][ini2].equals(e.getK()[x])){//busca posicion en Ke para automata 2
					k=x;
				}
			}
			for(int y=0; y<alpha; y++){//obteniendo estado en trans
				TransM1=a.getDelta()[j][y];
				System.out.println("Para insertar a M1: "+TransM1);
				System.out.println("Para insertar a M2: "+TransM2);
				BusquedaInsertar(a, e);
			}
			ini1++;
			ini2=0;
		}	
	}
	
	public void BusquedaInsertar(AFD a, AFD e){//Busqueda para insertar 
		for(int x=0; x<tope; x++){//Trans.length
			for(int y=0; y<(alpha+1); y++){
				if(TransM1.equals(Trans[x][y])){//mientras los estados sean diferentes a los que estan en la tabla insertar
					if(TransM2.equals(Trans[x][y+1])){
					parar++;
					System.out.println("Entro a falso con "+TransM1+" "+TransM2);
						if(parar==3){
							print();
						}
					insertar = false;
					break;
					}
				}
			}
		}
		if(insertar == true){
			ComparacionAFD2(a, e);
			Trans[n][m]=TransM1;
			m++;
			Trans[n][m]=TransM2;
			if(parar>0){
			parar=0;
			}
			n++;//aumenta el numero de transiciones en 1
		}
		insertar=true;
		m=0;//vuelve al estado inicial en el alpha para insertar
		tope++;//imprimir tabala de estados
		for(int x=0; x<n; x++){
			for(int y=0; y<alpha; y++){
				System.out.println("Tabla: "+Trans[x][y]);
			}
		}
		System.out.println("parar= "+parar);

	}
	public void ComparacionAFD2 (AFD a, AFD e){// en este metodo se comprueban que sean finales o no finales los estados de momento funciona solo con los iniciales

		int sonfinales1=1;
		int sonfinales2=1;
		
		for(int x=0; x<F1; x++){
			if(TransM1.equals(a.getF()[x])){
				sonfinales1=2;
				break;
			}
		}
		for(int x=0; x<F2; x++){
			if(TransM2.equals(e.getF()[x])){
				sonfinales2=2;
				break;
			}
		}
			if(sonfinales1==sonfinales2){
				Equivalentes="Son Equivalentes";
				
			}else{
				Equivalentes="No Son Equivalentes";
				print();
			}
	}
	public void print(){
		JOptionPane.showMessageDialog(null, "Los automatas: "+Equivalentes);
		System.exit(0);
	}
	

}