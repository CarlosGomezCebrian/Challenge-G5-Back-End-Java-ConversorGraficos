//javac -cp .;json-java.jar ConversorBanderas.java
//java -cp .;json-java.jar ConversorBanderas
//jar cfm Conversor.jar manifest.txt *.class org/json/*.class resources/*
//jar tf Conversor.jar
//java -jar Conversor.jar

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



import org.json.JSONObject;

/*
Dark blue rgb(10, 56, 113);  #0A3871
Azul medio     rgb(233, 236, 248);
Light blue rgba(243, 245, 252); #F3F5FC
Dark gray rgba(73, 80, 87);
Light gray rgb(216, 223, 232);*/

public class Conversor extends JFrame implements ActionListener {

	private JButton botonTemperatura, botonDivisas, botonSalir, botonMenos;
	private	JLabel labelNombre;  


	public static Conversor formulario1;
	public static ConversorBanderas formulario2;
	public static ConversorDeTemperatura formulario3;

	public Conversor() {
		setLayout(null);

		getContentPane().setBackground(new java.awt.Color(243, 245, 252));
		UIManager.put("Panel.background", new Color(243, 245, 252));
		UIManager.put("OptionPane.background", new Color(243, 245, 252));
		UIManager.put("Button.background", new Color(10, 56, 113));
		UIManager.put("Label.foreground", new Color(10, 56, 113));
		UIManager.put("Button.foreground", new Color(243, 245, 252));
		UIManager.put("Button.font", new Font("Malgun Gothic", Font.BOLD, 12));		
		UIManager.put("Button.margin", new Insets(2, 2, 2, 2));
		//UIManager.put("Button.border", BorderFactory.createLineBorder(new Color(23, 61, 125), 3));
		UIManager.put("Button.border", BorderFactory.createEmptyBorder());;

		
		ImageIcon iconTmp = new ImageIcon("resources/ConversorTemperaturaPortada.png");
		botonTemperatura = new JButton("");
		botonTemperatura.setIcon(iconTmp);
		botonTemperatura.setVisible(true);
		botonTemperatura.setBounds(90, 70, 120, 120);
		add(botonTemperatura);
		botonTemperatura.addActionListener(this);
		
		ImageIcon iconDiv = new ImageIcon("resources/ConversorDiv.png");
		botonDivisas = new JButton("");
		botonDivisas.setIcon(iconDiv);
		botonDivisas.setVisible(true);
		botonDivisas.setBounds(230, 70, 120, 120);
		add(botonDivisas);
		botonDivisas.addActionListener(this);

		ImageIcon iconSalir = new ImageIcon("resources/SalirPortada.png");
		botonSalir = new JButton("");
		botonSalir.setIcon(iconSalir);
		botonSalir.setVisible(true);		
		botonSalir.setBounds(400, 10, 35, 35);
		add(botonSalir);
		botonSalir.addActionListener(this);

		ImageIcon iconMenos = new ImageIcon("resources/MenosPortada.png");
		botonMenos = new JButton("");
		botonMenos.setIcon(iconMenos);
		botonMenos.setVisible(true);		
		botonMenos.setBounds(360, 10, 35, 35);
		add(botonMenos);
		botonMenos.addActionListener(this);

		labelNombre = new JLabel("Realizado por CarlosOmarGomez.");
		labelNombre.setHorizontalAlignment(SwingConstants.LEFT); 
		labelNombre.setVisible(true); 
		labelNombre.setBounds(5, 210, 360, 20); 
		labelNombre.setFont(new Font("Malgun Gothic", Font.BOLD, 10)); 
		add(labelNombre);

	}

	public void actionPerformed(ActionEvent eventoEnMemoria) {

		Object source = eventoEnMemoria.getSource();

		if (source == botonTemperatura) {
			formulario3.setVisible(true);
			this.setVisible(false);

		}

		if (source == botonDivisas) {
			formulario2.setVisible(true);
			this.setVisible(false);

		}

		if (source == botonSalir) {
			System.exit(0);

		}

		if(source == botonMenos){
			this.setState(Frame.ICONIFIED);		
		}

	}

	public static void main(String args[]) {

		
		ImageIcon iconFormulario1 = new ImageIcon("resources/ConversorPortada.png");
		formulario1 = new Conversor();
		formulario1.setIconImage(iconFormulario1.getImage());
		formulario1.setUndecorated(true);
		formulario1.setSize(450, 230);
		formulario1.setVisible(true);
		formulario1.setResizable(false);
		formulario1.setLocationRelativeTo(null);
		formulario1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario1.setTitle("Conversor");

		ImageIcon iconFormulario2 = new ImageIcon("resources/ConversorDiv.png");
		formulario2 = new ConversorBanderas();
		formulario2.setIconImage(iconFormulario2.getImage());
		formulario2.setUndecorated(true);
		formulario2.setSize(590, 720);		
		formulario2.setVisible(false);
		formulario2.setResizable(false);
		formulario2.setLocationRelativeTo(null);		
		formulario2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario2.setTitle("Conversor de divisas");


		ImageIcon iconFormulario3 = new ImageIcon("resources/ConversorTemperaturaPortada.png");
		formulario3 = new ConversorDeTemperatura();
		formulario3.setIconImage(iconFormulario3.getImage());
		formulario3.setUndecorated(true);
		formulario3.setSize(500, 450);		
		formulario3.setVisible(false);
		formulario3.setResizable(false);
		formulario3.setLocationRelativeTo(null);
		formulario3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario3.setTitle("Conversor de temperatura");

	}

}
