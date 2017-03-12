package net.encapsulados;

public class AFD2 {
	private String E[], K[], F[];
	private String S;
	private String Delta[][]; //Transiciones
	
	
	public String[] getE() {
		return E;
	}
	public void setE(String[] e) {
		E = e;
	}
	public String[] getK() {
		return K;
	}
	
	public void setK(String[] k) {
		K = k;
	}
	public String[] getF() {
		return F;
	}
	
	public void setF(String[] f) {
		F = f;
	}
	public String getS() {
		return S;
	}
	public void setS(String s) {
		S = s;
	}
	public String[][] getDelta() {
		return Delta;
	}
	public void setDelta(String[][] delta) {
		Delta = delta;
	}
		
}