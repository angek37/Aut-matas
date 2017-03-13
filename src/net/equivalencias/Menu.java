package net.equivalencias;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener{
	private JLabel titulo, lfi, lse;
	private JButton fi, se, r;
	InputsAFD i1 = new InputsAFD();
	InputsAFD i2 = new InputsAFD();
	
	public Menu(){
		super();
		WindowSetup();
		Form();
	}
	
	public void WindowSetup(){
		this.setBounds(0,0,480,400);
		this.setLocationRelativeTo(getContentPane());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setTitle("Determinar la equivalencia de dos AFD");
	}
	
	public void Form(){
		setLayout(null);
		titulo = new JLabel("Complete la Definición formal de ambos autómatas");
		titulo.setBounds(10, 10, 480, 50);
		titulo.setFont(new Font("Impact", Font.BOLD, 16));
		add(titulo);
		fi = new JButton("Primer autómata");
		fi.setBounds(10, 70, 220, 50);
		add(fi);
		fi.addActionListener(this);
		lfi = new JLabel("No registrado");
		lfi.setBounds(240, 70, 200, 50);
		lfi.setFont(new Font("Impact", Font.BOLD, 13));
		lfi.setForeground(Color.RED);
		add(lfi);
		se = new JButton("Segundo autómata");
		se.setBounds(10, 140, 220, 50);
		add(se);
		se.addActionListener(this);
		lse = new JLabel("No registrado");
		lse.setBounds(240, 140, 200, 50);
		lse.setFont(new Font("Impact", Font.BOLD, 13));
		lse.setForeground(Color.RED);
		add(lse);
		r = new JButton("Comparar Autómatas");
		r.setBounds(120, 210, 200, 80);
		add(r);
		r.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){

		
		if(e.getSource()==fi){
			i1.setVisible(true);
			i1.titulo.setText("Complete la definición formal del AFD (M1):");
			lfi.setText("Registrado");
			lfi.setForeground(Color.GREEN);
		}
		if(e.getSource()==se){
			i2.setVisible(true);
			i2.titulo.setText("Complete la definición formal del AFD (M2):");
			lse.setText("Registrado");
			lse.setForeground(Color.GREEN);
		}
		if(e.getSource()==r){
			Comparison c = new Comparison(i1.a, i2.a);
		}
	}

}
