package net.equivalencias;

import net.encapsulados.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InputsAFD extends JFrame implements ActionListener{
	AFD a = new AFD();
	private JLabel lE, lK, ls, lF;
	public JLabel titulo;
	private JTextField tE, tK, ts, tF;
	public JButton boton;
	Delta d;
	
	public InputsAFD(){
		super();
		WindowSetup();
		Form();
	}
	
	
	public void WindowSetup(){
		this.setBounds(0,0,520,300);
		this.setLocationRelativeTo(getContentPane());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		this.setTitle("Complete el Autómata Finito Determinista");
	}
	
	public void Form(){
		setLayout(null);
		titulo = new JLabel("");
		titulo.setBounds(10, 2, 500, 25);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setFont(new Font("Impact", Font.BOLD, 20));
		titulo.setForeground(Color.DARK_GRAY);
		add(titulo);
		
		lE = new JLabel("Ingrese los elementos del alfabeto (separado por coma sin espacios):");
		lE.setBounds(10, 30, 500, 20);
		add(lE);
		tE = new JTextField();
		tE.setBounds(10, 55, 500, 20);
		add(tE);
		
		lK = new JLabel("Ingrese el nombre de los estados (separado por coma sin espacios):");
		lK.setBounds(10, 80, 500, 20);
		add(lK);
		tK = new JTextField();
		tK.setBounds(10, 105, 500, 20);
		add(tK);
		
		ls = new JLabel("Ingrese el nombre del estado inicial:");
		ls.setBounds(10, 130, 500, 20);
		add(ls);
		ts = new JTextField();
		ts.setBounds(10, 155, 200, 20);
		add(ts);
		
		lF = new JLabel("Ingrese los estados finales (separado por coma sin espacios):");
		lF.setBounds(10, 180, 500, 20);
		add(lF);
		tF = new JTextField();
		tF.setBounds(10, 205, 500, 20);
		add(tF);
		
		boton = new JButton("Siguiente");
		boton.setBounds(10, 245, 250, 20);
		add(boton);
		boton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
       	 String aux = "";
    		int c=0;
    		SepararStrings sx = new SepararStrings();
    		if(e.getSource()==boton){
    			aux = tE.getText();
    			c=sx.contar(aux);
    			a.setE(sx.Extraer(c, aux));
    			
    			aux = tK.getText();
    			c=sx.contar(aux);
    			a.setK(sx.Extraer(c,aux));
    			
    			aux = tF.getText();
    			c=sx.contar(aux);
    			a.setF(sx.Extraer(c,aux));
    			
    			a.setS(ts.getText());
    			this.dispose();
    			d = new Delta(a);
				d.setVisible(true);
    		}
	}
}