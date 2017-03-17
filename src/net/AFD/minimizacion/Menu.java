package net.AFD.minimizacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.AFD.interfaces.*;

public class Menu extends JFrame implements ActionListener{
	private JLabel titulo, l;
	private JButton in, r;
	InputsAFD afd = new InputsAFD();
	
	public Menu(){
		WindowSetup();
		Form();
	}
	
	public void WindowSetup(){
		this.setBounds(0,0,400,300);
		this.setLocationRelativeTo(getContentPane());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setTitle("Minimizar un AFD");
	}
	
	public void Form(){
		setLayout(null);
		titulo = new JLabel("Complete la Definición formal del autómata");
		titulo.setBounds(10, 10, 480, 50);
		titulo.setFont(new Font("Impact", Font.BOLD, 16));
		add(titulo);
		in = new JButton("Primer autómata");
		in.setBounds(10, 70, 220, 50);
		add(in);
		in.addActionListener(this);
		l = new JLabel("No registrado");
		l.setBounds(240, 70, 200, 50);
		l.setFont(new Font("Impact", Font.BOLD, 13));
		l.setForeground(Color.RED);
		add(l);
		r = new JButton("Minimizar Autómata");
		r.setBounds(80, 140, 200, 80);
		add(r);
		r.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==in){
			afd.setVisible(true);
			afd.titulo.setText("Complete la Definición Formal del Autómata");
			l.setText("Registrado");
			l.setForeground(Color.GREEN);
		}
		if(e.getSource()==r){
			System.out.print("E: "+afd.getAFD().getE()[0]+"\nDelta: "+afd.getAFD().getDelta()[2][0]);
		}
	}

}

