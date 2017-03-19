package net.AFD.equivalencias;


import javax.swing.*;

import net.encapsulados.AFD; 

public class Equivalencia {
	
		
	int alpha=0, k1=0, k2=0, F1=0, F2=0;
	int M1 = 150, M2 = 30;
	String Trans[][] = new String [M1][M2];
	String Equivalentes ="";
	int n=0, m=0, actual=0; 
	String TransM1="", TransM2="";
	int tope=0, ini1=0, ini2=0, parar=0;
	boolean insertar = true;
	
	
	public Equivalencia(AFD a, AFD e){
		super();
		InputAFD(a,e);
		LlenarDelta(a,e);
		ComparacionAFD(a, e);
		InsertarTrans(a, e);
		ObtenerTransIni(a, e);
		print(a,e);
	}
	
	public void InputAFD(AFD a, AFD e){
		alpha=a.getE().length;
		k1=a.getK().length;
		k2=e.getK().length;
		F1=a.getF().length;
		F2=e.getF().length;				
	}
	
	public void LlenarDelta(AFD a, AFD e) {
		for(int x=0; x<k1; x++){
			for(int y=0; y<alpha; y++){
				System.out.println("Para el automata 1 el estado "+a.getK()[x]+" con "+a.getE()[y]+ " delta "+a.getDelta()[x][y]);
			}
		}
		for(int x=0; x<k2; x++){
			for(int y=0; y<alpha; y++){
				System.out.println("Para el automata 2 el estado "+e.getK()[x]+" con "+a.getE()[y]+" delta "+e.getDelta()[x][y]);
			}
		}	
	}
	public void ComparacionAFD (AFD a, AFD e){// en este metodo se comprueban que sean finales o no finales los estados iniciales

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
			print(a,e);
		}
	}
	
	public void InsertarTrans (AFD a, AFD e){// en este metodo se insertan las transiciones para los estados iniciales
		for(int x=0; x<k1; x++){//buscando inicial en estados K1
			//System.out.println("comparo"+a.getS()+" con "+a.getK()[x]);
			if(a.getS().equals(a.getK()[x])){
				Trans[n][m]=a.getK()[x];
				m++;
			}
		}
		for(int x=0; x<k2; x++){//buscando inicial para estados K2
			//System.out.println("comparo"+e.getS()+" con "+e.getK()[x]);
			if(e.getS().equals(e.getK()[x])){
				Trans[n][m]=e.getK()[x];
			}
		}
		m=0;
		tope++;
		n++;
	}
	
	public void ObtenerTransIni (AFD a, AFD e){
		
			while(Trans[ini1][ini2]!=null&&Trans[ini1][(ini2+1)]!=null){
			int j=0;	
			int k=0;
			for(int x=0; x<k1; x++){
				if(Trans[ini1][ini2].equals(a.getK()[x])){//busca posicion en k para automata 1
					j=x;
				}	
			}
			ini2++;
			for(int x=0; x<k2; x++){
				if(Trans[ini1][ini2].equals(e.getK()[x])){//busca posicion en K para automata 2
					k=x;
				}
			}
			for(int y=0; y<alpha; y++){//obteniendo estados para transiciones
				TransM1=a.getDelta()[j][y];
				//System.out.println("Para insertar a M1: "+TransM1);
				TransM2=e.getDelta()[k][y];
				//System.out.println("Para insertar a M2: "+TransM2);
				BusquedaInsertar(a,e);
			}
			ini1++;
			ini2=0;
		}	
	}
	
	public void BusquedaInsertar(AFD a, AFD e){//Busqueda para insertar 
		insertar=true;
		for(int x=0; x<tope; x++){
			for(int y=0; y<(alpha+1); y++){
				if(TransM1.equals(Trans[x][y])){//mientras los estados sean diferentes a los que estan en la tabla insertar
					if(TransM2.equals(Trans[x][y+1])){
					//System.out.println("Entro a falso con "+TransM1+" "+TransM2);
					insertar = false;
					break;
					}
				}
			}
		}
		if(insertar == true){
			ComparacionAFD2(a,e);
			Trans[n][m]=TransM1;
			m++;
			Trans[n][m]=TransM2;
			n++;
		}
		insertar=true;
		m=0;
		tope++;
		//System.out.println("Tabla:");
		for(int x=0; x<n; x++){
			for(int y=0; y<alpha; y++){
				//System.out.print(Trans[x][y]+" ");
			}
			//System.out.println("");
		}
	}
	public void ComparacionAFD2 (AFD a, AFD e){// en este metodo se comprueban que sean finales o no finales 

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
				print(a,e);
			}
	}
	public void print(AFD a, AFD e){
		int j=0;
		int k=0;
		int l=0;
		System.out.println("M1 M2 |  "+a.getE()[0]+"    |   "+a.getE()[1] );
		for(int x=0; x<n; x++){
			for(int y=0; y<alpha; y++){
				System.out.print(Trans[x][y]+" ");
			}
				System.out.print("|");
				l=0;
				for(int z=0; z<k1; z++){
					if(Trans[x][l].equals(a.getK()[z])){//busca posicion en k para automata 1
						j=z;
					}	
				}
				l++;
				for(int z=0; z<k2; z++){
					if(Trans[x][l].equals(e.getK()[z])){//busca posicion en K para automata 2
						k=z;
					}
				}
				for(int z=0; z<alpha; z++){//obteniendo estados para transiciones
					TransM1=a.getDelta()[j][z];
					System.out.print(" "+TransM1);
					TransM2=e.getDelta()[k][z];
					System.out.print(" "+TransM2);
					System.out.print(" | ");
				}
				
			System.out.println("");
		}
		JOptionPane.showMessageDialog(null, "Los automatas "+ Equivalentes);
		System.exit(0);
		}
	}
