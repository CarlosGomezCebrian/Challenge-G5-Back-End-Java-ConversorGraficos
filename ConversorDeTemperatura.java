import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.IOException;

/*

Dark2 red  rgb(189, 22, 22) #BD1616
Medium2 red  rgb(226, 47, 47) #E22F2F
Light2 red  rgb(234, 92, 92) #EA5C5C

*/

public class ConversorDeTemperatura extends JFrame implements ActionListener, DocumentListener {

	private JButton botonJPanelError1, botonMenu, botonMenos, botonSalir, botonLimpiar, botonCambiar, botonIgual, botonCelsius, botonFahrenheit, botonKelvin;

	private JLabel labelGifFlecha, labelResultadoTemp, labelCantidad, labelNombre, 
			labelSol_De, labelSol_A, labelCelsius_De, labelCelsius_A, labelFahrenheit_De, labelFahrenheit_A, labelKelvin_De, labelKelvin_A; 

	private JPanel panelError1;	


	private JTextField ingresaCantidad;

	public static Conversor formulario1;
	public static ConversorDeTemperatura formulario3;

	private String generarString_De = " ";
	private String generarString_A = " ";

	public ConversorDeTemperatura() {

		setLayout(null);


		getContentPane().setBackground(new java.awt.Color(234, 92, 92));
		UIManager.put("Panel.background", new Color(248,222,126));//amarillo
		UIManager.put("Button.background", new Color(234, 92, 92));
		UIManager.put("Label.foreground", new Color(189, 22, 22));
		UIManager.put("Button.foreground", new Color(234, 92, 92));
		UIManager.put("Button.font", new Font("Arial", Font.BOLD, 16));
		UIManager.put("Button.margin", new Insets(2, 2, 2, 2));
		//UIManager.put("Button.border", BorderFactory.createLineBorder(new Color(23, 61, 125), 3));
		UIManager.put("Button.border", BorderFactory.createEmptyBorder());

		ImageIcon iconERROR1 = new ImageIcon("resources/FlechaArriva.GIF");
		panelError1 = new JPanel();
		panelError1.setBounds(200,60,100,170);
		panelError1.setVisible(false);
		add(panelError1);
		
		labelGifFlecha = new JLabel(); labelGifFlecha.setIcon(iconERROR1); labelGifFlecha.setVisible(true); panelError1.add(labelGifFlecha);
		
		botonJPanelError1 = new JButton("OK");
		botonJPanelError1.setPreferredSize(new Dimension(50, 25));
		botonJPanelError1.setBackground(new Color(189, 22, 22));
		botonJPanelError1.setForeground(new Color(234, 92, 92));		
		botonJPanelError1.setAlignmentX(Component.CENTER_ALIGNMENT); 
		botonJPanelError1.setAlignmentY(Component.BOTTOM_ALIGNMENT); 
		panelError1.add(botonJPanelError1); 
		botonJPanelError1.addActionListener(this);

		ingresaCantidad = new JTextField("0");
		ingresaCantidad.setBounds(175, 30, 150, 30);
		ingresaCantidad.setBackground(new Color(249, 120, 172));
		ingresaCantidad.setForeground(new Color(189, 22, 22));
		ingresaCantidad.setBorder(BorderFactory.createEmptyBorder());
		ingresaCantidad.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
		ingresaCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		add(ingresaCantidad);
		ingresaCantidad.getDocument().addDocumentListener(this);
		ingresaCantidad.requestFocusInWindow();

		ImageIcon iconMenu = new ImageIcon("resources/MenuTemperatura.png");
		botonMenu = new JButton("");
		botonMenu.setIcon(iconMenu);
		botonMenu.setVisible(true);
		botonMenu.setBounds(370, 10, 35, 35);
		add(botonMenu);
		botonMenu.addActionListener(this);
		
		ImageIcon iconMenos = new ImageIcon("resources/MenosTemperatura.png");
		botonMenos = new JButton("");
		botonMenos.setIcon(iconMenos);
		botonMenos.setVisible(true);
		botonMenos.setBounds(410, 10, 35, 35);		
		add(botonMenos);
		botonMenos.addActionListener(this);

		ImageIcon iconSalir = new ImageIcon("resources/SalirTemperatura.png");
		botonSalir = new JButton("");
		botonSalir.setIcon(iconSalir);
		botonSalir.setVisible(true);
		botonSalir.setBounds(450, 10, 35, 35);
		add(botonSalir);
		botonSalir.addActionListener(this);

		ImageIcon iconCelsius = new ImageIcon("resources/Celsius.png");
		botonCelsius = new JButton("");
		botonCelsius.setIcon(iconCelsius);
		botonCelsius.setVisible(true);
		botonCelsius.setBounds(75, 90, 90, 90);
		add(botonCelsius);
		botonCelsius.addActionListener(this);

		ImageIcon iconFahrenheit = new ImageIcon("resources/Fahrenheit.png");
		botonFahrenheit = new JButton("");
		botonFahrenheit.setIcon(iconFahrenheit);
		botonFahrenheit.setVisible(true);
		botonFahrenheit.setBounds(205, 90, 90, 90);
		add(botonFahrenheit);
		botonFahrenheit.addActionListener(this);

		ImageIcon iconKelvin = new ImageIcon("resources/Kelvin.png");
		botonKelvin = new JButton("");
		botonKelvin.setIcon(iconKelvin);
		botonKelvin.setVisible(true);
		botonKelvin.setBounds(330, 90, 90, 90);
		add(botonKelvin);
		botonKelvin.addActionListener(this);

		ImageIcon iconCambiar = new ImageIcon("resources/CambiarTemperatura.png");
		botonCambiar = new JButton(""); 
		botonCambiar.setIcon(iconCambiar); 
		botonCambiar.setVisible(true); 
		botonCambiar.setBounds(130, 210, 90, 90); 
		add(botonCambiar); 
		botonCambiar.addActionListener(this);

		ImageIcon iconLimpiar = new ImageIcon("resources/Escoba.png");
		botonLimpiar = new JButton(""); 
		botonLimpiar.setIcon(iconLimpiar); 
		botonLimpiar.setVisible(true); 
		botonLimpiar.setBounds(270, 210, 76, 80); 
		add(botonLimpiar); 
		botonLimpiar.addActionListener(this);

		labelCantidad = new JLabel(ingresaCantidad.getText());
		labelCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantidad.setVisible(true);
		labelCantidad.setBounds(5, 330, 80, 90);
		labelCantidad.setFont(new Font("Malgun Gothic", Font.BOLD, 30));
		add(labelCantidad);

		ImageIcon iconSol = new ImageIcon("resources/Sol.png");			
		labelSol_De = new JLabel("");
		labelSol_De.setIcon(iconSol);
		labelSol_De.setVisible(true);
		labelSol_De.setBounds(90, 330, 90, 90);
		add(labelSol_De);

		labelCelsius_De = new JLabel("");
		labelCelsius_De.setIcon(iconCelsius);
		labelCelsius_De.setVisible(false);
		labelCelsius_De.setBounds(90, 330, 90, 90);
		add(labelCelsius_De);

		labelFahrenheit_De = new JLabel("");
		labelFahrenheit_De.setIcon(iconFahrenheit);
		labelFahrenheit_De.setVisible(false);
		labelFahrenheit_De.setBounds(90, 330, 90, 90);
		add(labelFahrenheit_De);

		labelKelvin_De = new JLabel("");
		labelKelvin_De.setIcon(iconKelvin);
		labelKelvin_De.setVisible(false);
		labelKelvin_De.setBounds(90, 330, 90, 90);
		add(labelKelvin_De);

		ImageIcon iconIgual = new ImageIcon("resources/IgualTemperatura.png");
		botonIgual = new JButton("");
		botonIgual.setIcon(iconIgual);
		botonIgual.setVisible(true);
		botonIgual.setBounds(190, 330, 90, 90);
		add(botonIgual);
		botonIgual.addActionListener(this);		

		labelSol_A = new JLabel("");
		labelSol_A.setIcon(iconSol);
		labelSol_A.setVisible(true);
		labelSol_A.setBounds(290, 330, 90, 90);
		add(labelSol_A);

		labelCelsius_A = new JLabel("");
		labelCelsius_A.setIcon(iconCelsius);
		labelCelsius_A.setVisible(false);
		labelCelsius_A.setBounds(290, 330, 90, 90);
		add(labelCelsius_A);

		labelFahrenheit_A = new JLabel("");
		labelFahrenheit_A.setIcon(iconFahrenheit);
		labelFahrenheit_A.setVisible(false);
		labelFahrenheit_A.setBounds(290, 330, 90, 90);
		add(labelFahrenheit_A);

		labelKelvin_A = new JLabel("");
		labelKelvin_A.setIcon(iconKelvin);
		labelKelvin_A.setVisible(false);
		labelKelvin_A.setBounds(290, 330, 90, 90);
		add(labelKelvin_A);

		labelResultadoTemp = new JLabel("0.00");
		labelResultadoTemp.setHorizontalAlignment(SwingConstants.CENTER);
		labelResultadoTemp.setVisible(true);
		labelResultadoTemp.setBounds(385, 330, 115, 90);
		labelResultadoTemp.setFont(new Font("Malgun Gothic", Font.BOLD, 30));
		add(labelResultadoTemp);

		labelNombre = new JLabel("Realizado por CarlosOmarGomez.");
		labelNombre.setHorizontalAlignment(SwingConstants.LEFT); 
		labelNombre.setVisible(true); 
		labelNombre.setBounds(5, 430, 360, 20); 
		labelNombre.setFont(new Font("Malgun Gothic", Font.BOLD, 12)); 
		add(labelNombre);

		/*
		 * Para convertir de ºC a ºF use la fórmula: ºF = ºC x 1.8 + 32. Para convertir
		 * de ºF a ºC use la fórmula: ºC = (ºF-32) ÷ 1.8. Para convertir de K a ºC use
		 * la fórmula: ºC = K – 273.15 Para convertir de ºC a K use la fórmula: K = ºC +
		 * 273.15. Para convertir de ºF a K use la fórmula: K = 5/9 (ºF – 32) + 273.15.
		 * Para convertir de K a ºF use la fórmula: ºF = 1.8(K – 273.15) + 32.
		 */
	}

		
	public void insertUpdate(DocumentEvent e) {
		labelCantidad.setText(ingresaCantidad.getText());
	}
	public void removeUpdate(DocumentEvent e) {
		labelCantidad.setText(ingresaCantidad.getText());
	}
	public void changedUpdate(DocumentEvent e) {
		labelCantidad.setText(ingresaCantidad.getText());
	}	

		private void actualizarResultados() {
			String cantidadString = ingresaCantidad.getText();
			double cantidad = 0;
			String unidadDe = generarString_De;
			String unidadA = generarString_A;
			double resultado = 0;

			try {
				cantidad = Double.parseDouble(cantidadString);
			} catch (NumberFormatException e) {
				panelError1.setVisible(true);
				labelResultadoTemp.setVisible(false);
				return;
			}

			 if (cantidadString.isEmpty()) {				
				panelError1.setVisible(true);
				labelResultadoTemp.setVisible(false);
				return;
			} 

			if (unidadDe.equals("Celsius") && unidadA.equals("Fahrenheit")) {
				labelResultadoTemp.setVisible(true);
				resultado = cantidad * 1.8 + 32;
			} else if (unidadDe.equals("Celsius") && unidadA.equals("Kelvin")) {
				labelResultadoTemp.setVisible(true);
				resultado = cantidad + 273.15;
			} else if (unidadDe.equals("Celsius") && unidadA.equals("Celsius")) {
				labelResultadoTemp.setVisible(true);
				resultado = cantidad;
			} else if (unidadDe.equals("Kelvin") && unidadA.equals("Celsius")) {
				labelResultadoTemp.setVisible(true);
				resultado = cantidad - 273.15;
			} else if (unidadDe.equals("Kelvin") && unidadA.equals("Fahrenheit")) {
				labelResultadoTemp.setVisible(true);
				resultado = (cantidad - 273.15) * 1.8 + 32;
			} else if (unidadDe.equals("Kelvin") && unidadA.equals("Kelvin")) {
				labelResultadoTemp.setVisible(true);
				resultado = cantidad;
			} else if (unidadDe.equals("Fahrenheit") && unidadA.equals("Celsius")) {
				labelResultadoTemp.setVisible(true);
				resultado = (cantidad - 32) / 1.8;
			} else if (unidadDe.equals("Fahrenheit") && unidadA.equals("Kelvin")) {
				labelResultadoTemp.setVisible(true);
				resultado = (cantidad - 32) * 5 / 9 + 273.15;
			} else if (unidadDe.equals("Fahrenheit") && unidadA.equals("Fahrenheit")) {
				labelResultadoTemp.setVisible(true);
				resultado = cantidad;
			}

			labelResultadoTemp.setText(String.format("%.2f", resultado));
			
		}

		private void solDe(){
				labelSol_De.setVisible(false); labelCelsius_De.setVisible(false);labelFahrenheit_De.setVisible(false); labelKelvin_De.setVisible(false);

		}

		private void solA(){
			labelSol_A.setVisible(false); labelCelsius_A.setVisible(false); labelFahrenheit_A.setVisible(false); labelKelvin_A.setVisible(false);
		}

		private void limpiar() {
			solDe();
			labelSol_De.setVisible(true);
			generarString_De = " ";
			solA();
			labelSol_A.setVisible(true);
			generarString_A = " ";			
			ingresaCantidad.setText("0");
			ingresaCantidad.getDocument().addDocumentListener(this);ingresaCantidad.requestFocusInWindow();			
			panelError1.setVisible(false);
			labelResultadoTemp.setVisible(true);
			labelResultadoTemp.setText("0.00");			
		}


	public void actionPerformed(ActionEvent eventoEnMemoria) {

		Object source = eventoEnMemoria.getSource();


		
		if(source == botonCelsius){
			if(labelSol_De.isVisible()){
				solDe();
				labelCelsius_De.setVisible(true);				
				generarString_De = "Celsius" ;			
			} else {
				solA();
				labelCelsius_A.setVisible(true);
				generarString_A = "Celsius" ;
			}
		}


		if(source == botonFahrenheit){
			if(labelSol_De.isVisible()){
				solDe();			
				labelFahrenheit_De.setVisible(true);				
				generarString_De = "Fahrenheit" ;			
			} else {
				solA();
				labelFahrenheit_A.setVisible(true);
				generarString_A = "Fahrenheit" ;
			}
		}

		if(source == botonKelvin){
			if(labelSol_De.isVisible()){
				solDe();
				labelKelvin_De.setVisible(true);
				generarString_De = "Kelvin" ;			
			} else {
				solA();
				labelKelvin_A.setVisible(true);
				generarString_A = "Kelvin" ;
			}
		}
		
		
		if (!labelSol_A.isVisible()) {
			actualizarResultados(); 			
		}



		if (source == botonMenu) {
			limpiar();
			Conversor.formulario1.setVisible(true);
			this.setVisible(false);
		}

		if (source == botonMenos) {
			this.setState(Frame.ICONIFIED);
		}

		if (source == botonSalir) {
			System.exit(0);
		}

		if (source == botonCambiar){

			String temp = generarString_De;
			generarString_De = generarString_A;
			generarString_A = temp;

				// Actualizar la visibilidad de las etiquetas
			labelCelsius_De.setVisible(generarString_De.equals("Celsius")); 
			labelFahrenheit_De.setVisible(generarString_De.equals("Fahrenheit"));
			labelKelvin_De.setVisible(generarString_De.equals("Kelvin")); 

			labelSol_De.setVisible(generarString_De.equals(" "));

			labelCelsius_A.setVisible(generarString_A.equals("Celsius")); 
			labelFahrenheit_A.setVisible(generarString_A.equals("Fahrenheit"));
			labelKelvin_A.setVisible(generarString_A.equals("Kelvin")); 

			labelSol_A.setVisible(generarString_A.equals(" "));

				//Inicia el método de resultados después de que se eligieron las dos temperaturas
			if (!labelSol_A.isVisible()) {
				actualizarResultados(); 			
			}
		}

		if (source == botonLimpiar){
			limpiar();
		}

		if (source == botonIgual){
			actualizarResultados();
		}

		if (source == botonJPanelError1){
			limpiar();
		}

		
	}
	

	public static void main(String args[]) {

		ImageIcon iconFormulario3 = new ImageIcon("resources/ConversorTemperaturaPortada.png");
		formulario3 = new ConversorDeTemperatura();

		formulario3.setIconImage(iconFormulario3.getImage());
		formulario3.setUndecorated(true);
		formulario3.setSize(500, 450);		
		formulario3.setVisible(true);
		formulario3.setResizable(false);
		formulario3.setLocationRelativeTo(null);
		formulario3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario3.setTitle("Conversor de temperatura");

	}

}
