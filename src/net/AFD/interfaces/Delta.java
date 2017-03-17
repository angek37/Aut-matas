package net.AFD.interfaces;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.encapsulados.AFD;

public class Delta extends JFrame{
	private JLabel titulo;
	private JLabel K, E, d;
	private JLabel[] lK, lE;
	private JComboBox[] cD;
	private JButton set;
	int f = 0;
	String delta[][];

	
	public Delta(AFD a){
		super();
		f=Transiciones(a);
		WindowSetup();
		Form(a);
		set = new JButton("Terminar");
		set.setBounds(230, 30, 150, 30);
		add(set);
        set.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
            	if (e.getSource()==set){
       			 for(int x = 0;x<cD.length;x++){
       				 delta[2][x]=(String)cD[x].getSelectedItem();
       			 }
       			 a.setDelta(delta);
       			 Delta.this.dispose();
       			 JOptionPane.showMessageDialog(null, "Autómata Ingresado Correctamente");
       		 }
            }
          });
	}
	
	public void WindowSetup(){
		this.setBounds(0,0,390,300);
		this.setLocationRelativeTo(getContentPane());
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		this.setTitle("Tabla de transiciones");
	}
	
	public void Form(AFD a){
		setLayout(null);
		titulo = new JLabel("Complete la tabla de transiciones");
		titulo.setBounds(5, 2, 250, 25);
		titulo.setFont(new Font("Impact", Font.BOLD, 12));
		titulo.setForeground(Color.DARK_GRAY);
		add(titulo);
		K = new JLabel("q ∈ Q");
		K.setBounds(10, 30, 100, 15);
		add(K);
		E = new JLabel("σ ∈ Σ");
		E.setBounds(80, 30, 100, 15);
		add(E);
		d = new JLabel("δ(q,σ)");
		d.setBounds(150, 30, 100, 15);
		add(d);
		lK = new JLabel[f];
		lE = new JLabel[f];
		cD = new JComboBox[f];
		for(int g = 0; g<f; g++){	
			cD[g] = new JComboBox();
			cD[g].setBounds(150, (g+1)*45, 50, 20);
			for(int i = 0; i<a.getK().length;i++){
				cD[g].addItem(a.getK()[i]);
			}
			add(cD[g]);
		}
		for(int x = 0; x<f; x++){
			lK[x] = new JLabel(a.getDelta()[0][x]);
			lK[x].setBounds(10, (x+1)*45, 50, 15);
			lK[x].setHorizontalAlignment(JLabel.CENTER);
			add(lK[x]);
			lE[x] = new JLabel(a.getDelta()[1][x]);
			lE[x].setHorizontalAlignment(JLabel.CENTER);
			lE[x].setBounds(80, (x+1)*45, 50, 15);
			add(lE[x]);
		}
	}
	
	public int Transiciones(AFD a){
		int filas = (a.getE().length*a.getK().length);
		delta = new String[3][filas];
		int c=0;
		for(int x = 0; x<filas;){
			for(int b = 0; b< a.getE().length;b++){
				delta[0][x]=a.getK()[c];
				delta[1][x]=a.getE()[b];
				x++;
			}
			c++;
		}
		a.setDelta(delta);
		return filas;
	}
}