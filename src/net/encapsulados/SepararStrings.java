package net.encapsulados;

public class SepararStrings {
	public String[] Extraer(int c, String aux){
		String arreglo[] = new String[c];
		char car;
		boolean ban;
		int y=0;
		for(int x = 0; x < c; x++){
			ban=true;
			car=aux.charAt(y);
			while(car!=',' && ban){
				if(arreglo[x]!=null){arreglo[x]=arreglo[x]+car;}else{arreglo[x]=String.valueOf(car);}
				y++;
				if(y<aux.length()){car=aux.charAt(y);}else{ban=false;}
			}
			y++;
		}
		return arreglo;
	}
	
	public int contar(String au){
		int c=0;
		for(int x = 0; x<au.length(); x++){
			String car = au.substring(x, x+1);
			if(car.equals(",")){
				c++;
			}
		}
		if(c!=0) c++;
		if(c==0 && au.length()>0){
			c++;
		}
		return c;
	}
}
