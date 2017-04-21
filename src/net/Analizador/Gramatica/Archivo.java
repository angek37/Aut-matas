package net.Analizador.Gramatica;

/**
 * <p><code>Archivo</code> lee un archivo de texto y regresa cada una de las líneas
 * en un arreglo.
 * @author      Miguel Angel Rodríguez
 * </p> 
 **/

import java.io.*;
import javax.swing.JOptionPane;

public class Archivo {
	private String gramar[];
	
	public Archivo(){
		String name = "gramatica.txt";
//		name = JOptionPane.showInputDialog("Ingrese el nombre del archivo: ");
		leer(name);
	}
	
	public void leer(String name){
		File f = new File(name);
		FileReader fr = null;
		FileReader f2 = null;
		BufferedReader count = null;
		BufferedReader read = null;
		System.out.println("Name: "+f.getName());
		System.out.println("Exists: "+f.exists());
		try {
			fr = new FileReader(f);
			f2 = new FileReader(f);
			count = new BufferedReader(fr);
			read = new BufferedReader(f2);
			String aux = "";
			int c =0;
			while((count.readLine()) != null){
				c++;
			}
			System.out.println("Lines:" +c);
			gramar = new String[c];
			for(int x = 0; x < c; x++){
				if((aux=read.readLine()) != null){
					gramar[x] = aux;
				}
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrió un error");
			e.printStackTrace();
		}finally{
			try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
		}
	}
	
	public void Imprimir(){
		/**
		 * Imprime el arreglo que almacena las lineas del archivo
		 */
		for(int x = 0; x < gramar.length; x++){
			System.out.println(gramar[x]);
		}
	}
	
	public String[] getGramar(){
		/**
		 * Regresa las lineas del <tt>archivo en un arreglo</tt>
		 */
		return gramar;
	}
	
	public static void main(String mr[]){
		Archivo a = new Archivo();
	}
}
