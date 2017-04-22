package net.AFD.minimizacion;

import javax.swing.*;

import net.encapsulados.AFD;
import net.AFD.interfaces.InputsAFD;


public class PruebaEquivalencia {
	
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
	
	public PruebaEquivalencia(AFD a){
		super ();
		InputAFD(a);
		Comparar(a);// este es el que no va comentado
		//InsertarTrans(a);
		//print(a);
		//EliminarNodos(a); //este no va aqui
		//LlenarDelta(a);	
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
				System.out.println("Pasando a Trans ini Ka "+a.getK()[Ka]+" Ke "+a.getK()[Ke]);
				InsertarTrans(a);
				print(a);
				}else{
				System.out.println("Tratando de comparar Ka "+a.getK()[Ka]+" Ke "+a.getK()[Ke]);	
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
		if(Equivalentes=="Son Equivalentes"){
		ObtenerTransIni(a);
		}
	}
	
	public void ObtenerTransIni (AFD a){
		
			while(Trans[ini1][ini2]!=null&&Trans[ini1][(ini2+1)]!=null){
			int j=0;	
			int k=0;
			for(int x=0; x<tamreduc; x++){
				if(Trans[ini1][ini2].equals(a.getK()[x])){//busca posicion en k para automata 1
					System.out.println("Tras es "+a.getK()[x]);
					j=x;
				}	
			}
			ini2++;
			for(int x=0; x<tamreduc; x++){
				if(Trans[ini1][ini2].equals(a.getK()[x])){//busca posicion en K para automata 2
					System.out.println("Tras es "+a.getK()[x]);
					k=x;
				}
			}
			for(int y=0; y<alpha; y++){//obteniendo estados para transiciones
				TransM1=a.getDelta()[j][y];
				System.out.println("Para insertar a M1: "+TransM1);
				TransM2=a.getDelta()[k][y];
				System.out.println("Para insertar a M2: "+TransM2);
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
				System.out.println("entro con "+TransM1);
				sonfinales1=2;
				break;
			}
		}
		for(int x=0; x<a.getF().length; x++){
			if(TransM2.equals(a.getF()[x])){
				System.out.println("entro con "+TransM1);
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
		//ImprimirAutomata(a);
		EliminarNodos(a);	
		}
		Equivalentes="No Son Equivalentes";
		//Comparar(a);
		}
	
		public void salir(){
			//SALIR
		}
	
		public void EliminarNodos(AFD a){
			System.out.println("-------------------Eliminando nodos---------------");
			int kfilaeliminada=0;
			String eliminar="";
			String parchar="";
			parchar=Trans[0][0];// elemento de k (fila) que sera sustituido por el otro estado
			eliminar=Trans[0][1];
					
			for(int x=0; x<tamreduc; x++){
				if(Trans[0][1].equals(a.getK()[x])){//busca posicion de k (fila) que sera eliminada
					kfilaeliminada=x;
				}
			}
			//-----------------------reescribiendo delta-------------------------------

			//kfilaeliminada=0; para una prueba especifica
			//elimina kfila
			//ImprimirAutomata(a);
			Delta = new String[40][10];
			for (int i = 0; i<tamreduc; i++) {//copeando delta completo 
				for(int c=0; c<a.getE().length; c++){
					Delta[i][c]=a.getDelta()[i][c];
				}
			}
			for (int i = 0; i<tamreduc; i++) {//eliminado fila 
				if(i==kfilaeliminada){	
					for(int j=i; j<tamreduc; j++){//problema con el alfabeto
						for(int c=0; c<a.getE().length; c++){	
						Delta[j][c]=a.getDelta()[j+1][c];
						}
					}	
				}
			}
			for (int i = 0; i<tamreduc; i++) {//parcha elimina y sustituye es decir redirigue los estados
				for(int c=0; c<a.getE().length; c++){
					if(a.getDelta()[i][c].equals(eliminar)){
					Delta[i][c]=parchar;
					}
					if(i==tamreduc-1){
					Delta[i][c]=null;
					}
				}
			}
			a.setDelta(Delta);//NO MOVER CRUCIAL QUE ESTE AQUI
			
			//ImprimirAutomata(a);
			System.out.println("<---------------------Segunda-----------------");
			//----------------------eliminando en  k----------------------
			Tamk =new String[20];
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
			a.setK(Tamk);
			tamreduc--;
			//------------------------------------------------------------
			ImprimirAutomata(a);  	
			Equivalentes="No Son Equivalentes";
			System.out.println("------------------pase el automata");
				//ImprimirAutomata(a);
			Comparar(a);
				
		}
		public void ImprimirAutomata(AFD a){
			JOptionPane.showMessageDialog(null,"Por favor dibuje el automata que saldra en consola");
			for(int x=0; x<tamreduc; x++){
				System.out.println("Dibuje el estado "+a.getK()[x]);
				for(int y=0; y<a.getE().length; y++){
					System.out.println("Que valla al estado "+a.getDelta()[x][y]+" con "+a.getE()[y]);
				}
			}
		}	
}
