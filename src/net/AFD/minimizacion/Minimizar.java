package net.AFD.minimizacion;

import javax.swing.*;

import net.encapsulados.AFD;
import net.AFD.interfaces.InputsAFD;


public class Minimizar {
	
	int M1 = 150, M2 = 30;
	String Trans[][] = new String [M1][M2];
	String Equivalentes ="";
	int n=0, m=0, actual=0; 
	String TransM1="", TransM2="";
	int tope=0, ini1=0, ini2=0, parar=0;
	boolean insertar = true;
	int eliminar=0;
	int alpha=0, k1=0, f1=0;
	int Ka=0,Ke=0;
	String Delta [][];
	String Tamk[];
	int tamreduc=0;
	
	public Minimizar(AFD a){
		super ();
		InputAFD(a);
		Comparar(a);// este es el que no va comentado
			
	}
	public void InputAFD(AFD a){
		alpha=a.getE().length;
		k1=tamreduc;
		f1=a.getF().length;
	}
	
	public void BorrarTrans() {
		for(int x=0; x<Trans.length; x++){
			for(int y=0; y<Trans[x].length; y++){
				Trans[x][y]=null;
			}
		}
	}
	public void Comparar(AFD a){
		tamreduc=a.getK().length;
		for(int x=0; x<tamreduc; x++){
			for(int y=0; y<tamreduc; y++){	
			Ka=x;
			Ke=y;
			if(Ka!=Ke){
				System.out.println("Comparando estado "+a.getK()[Ka]+" con "+a.getK()[Ke]);
				InsertarTrans(a);
				print(a);
				}else{	
				}
			}
		}
	}

	public void InsertarTrans (AFD a){// en este metodo se insertan las transiciones para los estados iniciales
		BorrarTrans();
		tope=0;
		n=0;
		ini1=0;
		ini2=0;
		Trans[n][m]=a.getK()[Ka];
		TransM1=a.getK()[Ka];
		m++;
		Trans[n][m]=a.getK()[Ke];
		TransM2=a.getK()[Ke];
		ComparacionAFD(a);
		m=0;
		tope++;
		n++;
		ObtenerTransIni(a);
	}
	
	public void ObtenerTransIni (AFD a){
		
			while(Trans[ini1][ini2]!=null&&Trans[ini1][(ini2+1)]!=null){
			int j=0;	
			int k=0;
			for(int x=0; x<tamreduc; x++){
				if(Trans[ini1][ini2].equals(a.getK()[x])){//busca posicion en k para automata 1
					j=x;
				}	
			}
			ini2++;
			for(int x=0; x<tamreduc; x++){
				if(Trans[ini1][ini2].equals(a.getK()[x])){//busca posicion en K para automata 2
					k=x;
				}
			}
			for(int y=0; y<alpha; y++){//obteniendo estados para transiciones
				TransM1=a.getDelta()[j][y];
				TransM2=a.getDelta()[k][y];
				BusquedaInsertar(a);
			}
			ini1++;
			ini2=0;
		}
			//print(a);
	}
	
	public void BusquedaInsertar(AFD a){//Busqueda para insertar 
		insertar=true;
		for(int x=0; x<tope; x++){
			for(int y=0; y<(alpha+1); y++){
				if(TransM1.equals(Trans[x][y])){//mientras los estados sean diferentes a los que estan en la tabla insertar
					if(TransM2.equals(Trans[x][y+1])){
					insertar = false;
					break;
					}
				}
			}
		}
		if(insertar == true){
			ComparacionAFD(a);
			Trans[n][m]=TransM1;
			m++;
			Trans[n][m]=TransM2;
			n++;
		}
		insertar=true;
		m=0;
		tope++;
	}
	
	public void ComparacionAFD (AFD a){// en este metodo se comprueban que sean finales o no finales 

		int sonfinales1=1;
		int sonfinales2=1;
		
		for(int x=0; x<a.getF().length; x++){
			if(TransM1.equals(a.getF()[x])){
				sonfinales1=2;
				break;
			}
		}
		for(int x=0; x<a.getF().length; x++){
			if(TransM2.equals(a.getF()[x])){
				sonfinales2=2;
				break;
			}
		}
			if(sonfinales1==sonfinales2){
				Equivalentes="Son Equivalentes";
			}else{
				Equivalentes="No Son Equivalentes";
				print(a);
			}
	}
	public void print(AFD a){
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
				for(int z=0; z<tamreduc; z++){
					if(Trans[x][l].equals(a.getK()[z])){//busca posicion en k para automata 1
						j=z;
					}	
				}
				l++;
				for(int z=0; z<tamreduc; z++){
					if(Trans[x][l].equals(a.getK()[z])){//busca posicion en K para automata 2
						k=z;
					}
				}
				for(int z=0; z<alpha; z++){//obteniendo estados para transiciones
					TransM1=a.getDelta()[j][z];
					System.out.print(" "+TransM1);
					TransM2=a.getDelta()[k][z];
					System.out.print(" "+TransM2);
					System.out.print(" | ");
				}
				
			System.out.println("");
		}
		JOptionPane.showMessageDialog(null, "Los estados "+ Equivalentes);
		if(Equivalentes.equals("Son Equivalentes")){
		EliminarNodos(a);	
		}
		}
		public void EliminarNodos(AFD a){
			System.out.println("-------------------Eliminando nodos---------------");
			int kfilaeliminada=0;
			String eliminar="";
			String parchar="";
			parchar=Trans[0][0];// elemento de k (fila) que sera sustituido por el otro estado
			eliminar=Trans[0][1];
			tamreduc--;
					
			for(int x=0; x<tamreduc; x++){
				if(Trans[0][1].equals(a.getK()[x])){//busca posicion de k (fila) que sera eliminada
					kfilaeliminada=x;
				}
			}
			//-----------------------reescribiendo delta-------------------------------

			Delta = new String[100][100];
			for (int i = 0; i<tamreduc; i++) { 
				for(int c=0; c<a.getE().length; c++){
					Delta[i][c]=a.getDelta()[i][c];
				}
			}
			for (int i = 0; i<tamreduc; i++) {//eliminado fila 
				for(int c=0; c<a.getE().length; c++){
					if(i==kfilaeliminada){	
						for(int j=i; j<tamreduc; j++){
						Delta[j][c]=a.getDelta()[j+1][c];
						Delta[j][c+1]=a.getDelta()[j+1][c+1];
						}
					}
				}
			}
			for (int i = 0; i<tamreduc; i++) {
				for(int c=0; c<a.getE().length; c++){
					if(a.getDelta()[i][c].equals(eliminar)){
					Delta[i][c]=parchar;
					}
				}
			}
			a.setDelta(Delta);
			
			//----------------------eliminando en  k----------------------
			/*Tamk =new String[tamreduc];
			for(int j=0; j<tamreduc; j++){
				Tamk[j]=a.getK()[j];
			}
			for(int x =0; x<tamreduc; x++){
				if(x==kfilaeliminada){	
					for(int j=x; j<(tamreduc-1); j++){
						Tamk[j]=a.getK()[j+1];
					}
				}
			}
			a.setK(Tamk);*/
			//------------------------------------------------------------
			ImprimirAutomata(a);  	
	      
			
				
		}
		public void ImprimirAutomata(AFD a){
			JOptionPane.showMessageDialog(null,"Por favor dibuje el automata que saldra en consola");
			for(int x=0; x<tamreduc; x++){
				System.out.println("Dibuje el estado "+a.getK()[x]);
				for(int y=0; y<a.getE().length; y++){
					System.out.println("Que vaya al estado "+a.getDelta()[x][y]+" con "+a.getE()[y]);
				}
			}
		}	
}


