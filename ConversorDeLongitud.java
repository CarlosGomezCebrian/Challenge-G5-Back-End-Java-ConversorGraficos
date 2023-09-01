import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.awt.*;
import java.awt.event.*;

import java.lang.reflect.Field;


//Amarillo. claro   rgb(254, 225, 119); #FEE177
//GrisClaro				#4F6D90
//Amarillo. medio   rgb(255, 204, 74); #FFCC4A
//Amarillo. obscuro rgb(255, 145, 77);  #FF914D

public class ConversorDeLongitud extends JFrame implements ActionListener, DocumentListener {
	private JTextField ingresaCantidad;
	private JComboBox<String> comboEntrada;
	private JComboBox<String> comboSalida;
	

	public static Conversor formulario1;
	public static ConversorDeLongitud formulario4;

	private String generarString_De = " ";
	private String generarString_A = " ";
	private String finalDeEtiqueta = " ";


	JPanel panelError1, panelResultado, panelCantidad;

	private JButton botonJPanelError1, botonMenu, botonMenos, botonSalir,  botonLimpiar, botonCambiar, botonIgual,
			botonKm, botonM, botonCm, botonMm, boton_μm, botonNm, botonMi, botonYd, botonFt, botonIn, botonMil;

        private JLabel  labelResultado, labelCantidad, labelGifFlecha,labelNombre,
			labelMuestra_De, labelMuestra_A,
			labelKm_De, labelM_De, labelCm_De, labelMm_De, label_μm_De, labelNm_De, labelMi_De, labelYd_De, labelFt_De, labelIn_De, labelMil_De,
			labelKm_A,  labelM_A, labelCm_A, labelMm_A, label_μm_A, labelNm_A, labelMi_A, labelYd_A, labelFt_A, labelIn_A, labelMil_A;




	public ConversorDeLongitud() {	      


		setLayout(null);

		getContentPane().setBackground(new java.awt.Color(254, 225, 119));
		UIManager.put("Panel.background", new Color(254, 225, 119));//amarillo
		UIManager.put("OptionPane.messageForeground", new Color(216, 30, 5));//rojo
		UIManager.put("OptionPane.messageFont", new Font("Malgun Gothic", Font.BOLD, 14));
		UIManager.put("OptionPane.background", new Color(255, 145, 77));//amarillo
		UIManager.put("Button.background", new Color(254, 225, 119));
		UIManager.put("Label.foreground", new Color(255, 145, 77));
		UIManager.put("Button.foreground", new Color(255, 145, 77));
		UIManager.put("Button.font", new Font("Malgun Gothic", Font.BOLD, 12));		
		UIManager.put("Button.margin", new Insets(2, 2, 2, 2));
		UIManager.put("Button.border", BorderFactory.createLineBorder(new Color(255, 145, 77)));
		UIManager.put("Button.border", BorderFactory.createEmptyBorder());


		panelError1 = new JPanel();
		panelError1.setBounds(240,90,100,170);
		panelError1.setBackground(new Color(255, 145, 77));		
		panelError1.setVisible(false);
		add(panelError1);

		ImageIcon iconERROR1 = new ImageIcon("resources/FlechaArriva.GIF");
		labelGifFlecha = new JLabel(); labelGifFlecha.setIcon(iconERROR1); labelGifFlecha.setVisible(true); panelError1.add(labelGifFlecha);
		
		botonJPanelError1 = new JButton("OK");
		botonJPanelError1.setPreferredSize(new Dimension(50, 25));
		botonJPanelError1.setBorder(BorderFactory.createEmptyBorder());
		botonJPanelError1.setAlignmentX(Component.CENTER_ALIGNMENT); botonJPanelError1.setAlignmentY(Component.BOTTOM_ALIGNMENT); 
		panelError1.add(botonJPanelError1); botonJPanelError1.addActionListener(this);
		
		//ingresaCantidad.setCaretColor(new Color(255, 145, 77));

		ingresaCantidad = new JTextField("1");
		ingresaCantidad.setBounds(200, 40, 180, 50); ingresaCantidad.setForeground(new Color(255, 145, 77)); ingresaCantidad.setBackground(new Color(255, 204, 74)); 
		ingresaCantidad.setBorder(BorderFactory.createEmptyBorder()); 
		ingresaCantidad.setFont(new Font("Malgun Gothic", Font.BOLD, 22));ingresaCantidad.setHorizontalAlignment(SwingConstants.CENTER); add(ingresaCantidad);
		ingresaCantidad.getDocument().addDocumentListener(this);ingresaCantidad.requestFocusInWindow();

		panelCantidad = new JPanel();panelCantidad.setLayout(new BorderLayout()); panelCantidad.setBounds(10, 505, 120, 40); add(panelCantidad);

		labelCantidad = new JLabel(ingresaCantidad.getText()); labelCantidad.setFont(new Font("Malgun Gothic", Font.BOLD, 30)); labelCantidad.setHorizontalAlignment(SwingConstants.RIGHT); 
		labelCantidad.setVisible(true);   panelCantidad.add(labelCantidad);



        	panelResultado = new JPanel();panelResultado.setLayout(new BorderLayout()); panelResultado.setBounds(425, 505, 160, 40); add(panelResultado);

		labelResultado = new JLabel(""); labelResultado.setFont(new Font("Malgun Gothic", Font.BOLD, 30)); labelResultado.setHorizontalAlignment(SwingConstants.CENTER); 
		labelResultado.setVisible(true); panelResultado.add(labelResultado);
		

		labelNombre = new JLabel("Realizado por CarlosOmarGomez."); labelNombre.setHorizontalAlignment(SwingConstants.LEFT); labelNombre.setVisible(true); labelNombre.setBounds(5, 580, 220, 20); labelNombre.setFont(new Font("Malgun Gothic", Font.BOLD, 12)); add(labelNombre);

		

		ImageIcon iconKm = new ImageIcon("resources/km.png");		
		botonKm =    new JButton();            botonKm.setIcon(iconKm);    botonKm.setVisible(true);     botonKm.setBounds(15, 100, 85, 85);     add(botonKm); botonKm.addActionListener(this);
		labelKm_De = new JLabel("labelKm_De"); labelKm_De.setIcon(iconKm); labelKm_De.setVisible(false); labelKm_De.setBounds(135, 480, 90, 90); add(labelKm_De);		
		labelKm_A =  new JLabel("labelKm_A");  labelKm_A.setIcon(iconKm);  labelKm_A.setVisible(false);  labelKm_A.setBounds(340, 480, 90, 90);  add(labelKm_A);

		ImageIcon iconM = new ImageIcon("resources/m.png");		
		botonM =    new JButton();           botonM.setIcon(iconM);    botonM.setVisible(true);     botonM.setBounds(110, 100, 85, 85);    add(botonM); botonM.addActionListener(this);
		labelM_De = new JLabel("labelM_De"); labelM_De.setIcon(iconM); labelM_De.setVisible(false); labelM_De.setBounds(135, 480, 90, 90); add(labelM_De);		
		labelM_A =  new JLabel("labelM_A");  labelM_A.setIcon(iconM);  labelM_A.setVisible(false);  labelM_A.setBounds(340, 480, 90, 90);  add(labelM_A);

		ImageIcon iconCm = new ImageIcon("resources/cm.png");		
		botonCm =    new JButton();            botonCm.setIcon(iconCm);    botonCm.setVisible(true);     botonCm.setBounds(205, 100, 85, 85);    add(botonCm); botonCm.addActionListener(this);
		labelCm_De = new JLabel("labelCm_De"); labelCm_De.setIcon(iconCm); labelCm_De.setVisible(false); labelCm_De.setBounds(135, 480, 90, 90); add(labelCm_De);		
		labelCm_A =  new JLabel("labelCm_A");  labelCm_A.setIcon(iconCm);  labelCm_A.setVisible(false);  labelCm_A.setBounds(340, 480, 90, 90);  add(labelCm_A);

		ImageIcon iconMm = new ImageIcon("resources/mm.png");		
		botonMm =    new JButton();            botonMm.setIcon(iconMm);    botonMm.setVisible(true);     botonMm.setBounds(300, 100, 85, 85);    add(botonMm); botonMm.addActionListener(this);
		labelMm_De = new JLabel("labelMm_De"); labelMm_De.setIcon(iconMm); labelMm_De.setVisible(false); labelMm_De.setBounds(135, 480, 90, 90); add(labelMm_De);		
		labelMm_A =  new JLabel("labelMm_A");  labelMm_A.setIcon(iconMm);  labelMm_A.setVisible(false);  labelMm_A.setBounds(340, 480, 90, 90);  add(labelMm_A);

		ImageIcon icon_μm = new ImageIcon("resources/μm.png");		
		boton_μm =    new JButton();         boton_μm.setIcon(icon_μm);     boton_μm.setVisible(true);     boton_μm.setBounds(395, 100, 85, 85);    add(boton_μm); boton_μm.addActionListener(this);
		label_μm_De = new JLabel("label_μm_De"); label_μm_De.setIcon(icon_μm); label_μm_De.setVisible(false); label_μm_De.setBounds(135, 480, 90, 90); add(label_μm_De);		
		label_μm_A =  new JLabel("label_μm_A");  label_μm_A.setIcon(icon_μm);  label_μm_A.setVisible(false);  label_μm_A.setBounds(340, 480, 90, 90);  add(label_μm_A);


		ImageIcon iconNm = new ImageIcon("resources/nm.png");		
		botonNm =    new JButton();        botonNm.setIcon(iconNm);    botonNm.setVisible(true);     botonNm.setBounds(490, 100, 85, 85);    add(botonNm); botonNm.addActionListener(this);
		labelNm_De = new JLabel("labelNm_De"); labelNm_De.setIcon(iconNm); labelNm_De.setVisible(false); labelNm_De.setBounds(135, 480, 90, 90); add(labelNm_De);		
		labelNm_A =  new JLabel("labelNm_A");  labelNm_A.setIcon(iconNm);  labelNm_A.setVisible(false);  labelNm_A.setBounds(340, 480, 90, 90);  add(labelNm_A);


		ImageIcon iconMi = new ImageIcon("resources/mi.png");		
		botonMi =    new JButton();        botonMi.setIcon(iconMi);    botonMi.setVisible(true);     botonMi.setBounds(30, 215, 85, 85);     add(botonMi); botonMi.addActionListener(this);
		labelMi_De = new JLabel("labelMi_De"); labelMi_De.setIcon(iconMi); labelMi_De.setVisible(false); labelMi_De.setBounds(135, 480, 90, 90); add(labelMi_De);		
		labelMi_A =  new JLabel("labelMi_A");  labelMi_A.setIcon(iconMi);  labelMi_A.setVisible(false);  labelMi_A.setBounds(340, 480, 90, 90);  add(labelMi_A);

		ImageIcon iconYd = new ImageIcon("resources/yd.png");		
		botonYd =    new JButton();        botonYd.setIcon(iconYd);    botonYd.setVisible(true);     botonYd.setBounds(140, 215, 85, 85);    add(botonYd); botonYd.addActionListener(this);
		labelYd_De = new JLabel("labelYd_De"); labelYd_De.setIcon(iconYd); labelYd_De.setVisible(false); labelYd_De.setBounds(135, 480, 90, 90); add(labelYd_De);		
		labelYd_A =  new JLabel("labelYd_A");  labelYd_A.setIcon(iconYd);  labelYd_A.setVisible(false);  labelYd_A.setBounds(340, 480, 90, 90);  add(labelYd_A);


		ImageIcon iconFt = new ImageIcon("resources/ft.png");		
		botonFt =    new JButton();        botonFt.setIcon(iconFt);    botonFt.setVisible(true);     botonFt.setBounds(255, 215, 85, 85);    add(botonFt); botonFt.addActionListener(this);
		labelFt_De = new JLabel("labelFt_De"); labelFt_De.setIcon(iconFt); labelFt_De.setVisible(false); labelFt_De.setBounds(135, 480, 90, 90); add(labelFt_De);		
		labelFt_A =  new JLabel("labelFt_A");  labelFt_A.setIcon(iconFt);  labelFt_A.setVisible(false);  labelFt_A.setBounds(340, 480, 90, 90);  add(labelFt_A);


		ImageIcon iconIn = new ImageIcon("resources/in.png");		
		botonIn =    new JButton();        botonIn.setIcon(iconIn);    botonIn.setVisible(true);     botonIn.setBounds(365, 215, 85, 85);    add(botonIn); botonIn.addActionListener(this);
		labelIn_De = new JLabel("labelIn_De"); labelIn_De.setIcon(iconIn); labelIn_De.setVisible(false); labelIn_De.setBounds(135, 480, 90, 90); add(labelIn_De);		
		labelIn_A =  new JLabel("labelIn_A");  labelIn_A.setIcon(iconIn);  labelIn_A.setVisible(false);  labelIn_A.setBounds(340, 480, 90, 90);  add(labelIn_A);

		ImageIcon iconMil = new ImageIcon("resources/mil.png");		
		botonMil =    new JButton();        botonMil.setIcon(iconMil);    botonMil.setVisible(true);     botonMil.setBounds(475, 215, 85, 85);    add(botonMil); botonMil.addActionListener(this);
		labelMil_De = new JLabel("labelMil_De"); labelMil_De.setIcon(iconMil); labelMil_De.setVisible(false); labelMil_De.setBounds(135, 480, 90, 90); add(labelMil_De);		
		labelMil_A =  new JLabel("labelMil_A");  labelMil_A.setIcon(iconMil);  labelMil_A.setVisible(false);  labelMil_A.setBounds(340, 480, 90, 90);  add(labelMil_A);


		ImageIcon iconMENU = new ImageIcon("resources/MenuDistancia.png");
		botonMenu = new JButton(); botonMenu.setIcon(iconMENU); botonMenu.setVisible(true); botonMenu.setBounds(450, 10, 35, 35); add(botonMenu); botonMenu.addActionListener(this);
		

		ImageIcon iconMENOS = new ImageIcon("resources/MenosDistancia.png");
		botonMenos = new JButton(); botonMenos.setIcon(iconMENOS); botonMenos.setVisible(true); botonMenos.setBounds(495, 10, 35, 35); add(botonMenos); botonMenos.addActionListener(this);
		

		ImageIcon iconSAL = new ImageIcon("resources/SalirDistancia.png");
		botonSalir = new JButton(); botonSalir.setIcon(iconSAL); botonSalir.setVisible(true); botonSalir.setBounds(540, 10, 35, 35); add(botonSalir); botonSalir.addActionListener(this);
		

		ImageIcon iconCambiar = new ImageIcon("resources/CambiarDistancia.png");
		botonCambiar = new JButton(); botonCambiar.setIcon(iconCambiar); botonCambiar.setVisible(true); botonCambiar.setBounds(145, 350, 90, 90); add(botonCambiar); botonCambiar.addActionListener(this);

		ImageIcon iconLimpiar = new ImageIcon("resources/LimpiarDistancia.png");
		botonLimpiar = new JButton(); botonLimpiar.setIcon(iconLimpiar); botonLimpiar.setVisible(true); botonLimpiar.setBounds(330, 350, 90, 90); add(botonLimpiar); botonLimpiar.addActionListener(this);

		
		ImageIcon iconoMetro = new ImageIcon("resources/Metro.png");

		labelMuestra_De = new JLabel(); 
		labelMuestra_De.setIcon(iconoMetro); labelMuestra_De.setVisible(true); labelMuestra_De.setBounds(135, 480, 85, 85); add(labelMuestra_De);

		ImageIcon iconIgual = new ImageIcon("resources/IgualDistancia.png");
		botonIgual = new JButton(); botonIgual.setIcon(iconIgual); botonIgual.setVisible(true);botonIgual.setBounds(235, 480, 90, 90); add(botonIgual); botonIgual.addActionListener(this);
		
		labelMuestra_A = new JLabel();
		labelMuestra_A.setIcon(iconoMetro);  labelMuestra_A.setVisible(true);  labelMuestra_A.setBounds(340, 480, 85, 85);  add(labelMuestra_A);
		//Para que inicie el cursor en el igresa cantidad

		labelCantidad.addPropertyChangeListener("text", evt -> { ClaseUtils.autoResizeFont(labelCantidad);});
		labelResultado.addPropertyChangeListener("text", evt -> { ClaseUtils.autoResizeFont(labelResultado);});
		new ClaseUtils(this);
		
		

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

	public void actualizarResultados() {

		try{
			double valor = Double.parseDouble(ingresaCantidad.getText());
        		double metros = 0;
 

		
			switch (generarString_De) {
				case "Km":
					metros = valor * 1000;
					break;
	            		case "M":
					metros = valor;
					break;
				case "Cm":
					metros = valor / 100;
					break;
				case "Mm":
					metros = valor / 1000;
					break;
				case "_μm":
					metros = valor / 1e6;
					break;
				case "Nm":
					metros = valor / 1e9;
					break;
				case "Mi":
					metros = valor * 1609.34;
					break;
				case "Yd":
					metros = valor * 0.9144;
					break;
				case "Ft":
					metros = valor * 0.3048;
					break;
				case "In":
					metros = valor * 0.0254;
					break;
				case "Mil":
					metros = valor * 0.0000254; 
					break; 
			} 
		double resultado = 0;   
			switch (generarString_A) { 
				case "Km": 
					resultado = metros / 1000; 
					break; 
				case "M": 
					resultado = metros; 
					break; 
				case "Cm": 
					resultado = metros * 100; 
					break; 
				case "Mm": 
					resultado = metros * 1000; 
					break; 
				case "_μm": 
					resultado = metros * 1e6; 
					break; 
				case "Nm": 
					resultado = metros * 1e9; 
					break; 
				case "Mi": 
					resultado = metros / 1609.34; 
					break; 
				case "Yd": 
					resultado = metros / 0.9144; 
					break; 
				case "Ft": 
					resultado = metros / 0.3048; 
					break;  
				case "In":  
					resultado = metros / 0.0254;  
					break;  
				case "Mil":  
					resultado = metros / 0.0000254;  
					break;  
			}  


		String resultadoNumeroFormateado = ClaseUtils.formatearNumero(resultado);
		
		
		labelResultado.setText(resultadoNumeroFormateado);

		
		} catch (NumberFormatException e) {
			botonCm.setVisible(false);
			botonMm.setVisible(false);	
			botonFt.setVisible(false);		
			panelError1.setVisible(true);
		}
	}

	private void cambiar() {

		String temp = generarString_De;
		generarString_De = generarString_A;
		generarString_A = temp;

			// Actualizar la visibilidad de las etiquetas
		labelKm_De.setVisible(generarString_De.equals("Km")); labelM_De.setVisible(generarString_De.equals("M"));
		labelCm_De.setVisible(generarString_De.equals("Cm")); labelMm_De.setVisible(generarString_De.equals("Mm")); label_μm_De.setVisible(generarString_De.equals("_μm"));
		labelNm_De.setVisible(generarString_De.equals("Nm")); labelMi_De.setVisible(generarString_De.equals("Mi"));
		labelYd_De.setVisible(generarString_De.equals("Yd")); labelFt_De.setVisible(generarString_De.equals("Ft")); labelIn_De.setVisible(generarString_De.equals("In"));
		labelMil_De.setVisible(generarString_De.equals("Mil")); 

		labelMuestra_De.setVisible(generarString_De.equals(" "));

		labelKm_A.setVisible(generarString_A.equals("Km")); labelM_A.setVisible(generarString_A.equals("M"));
		labelCm_A.setVisible(generarString_A.equals("Cm")); labelMm_A.setVisible(generarString_A.equals("Mm")); label_μm_A.setVisible(generarString_A.equals("_μm"));
		labelNm_A.setVisible(generarString_A.equals("Nm")); labelMi_A.setVisible(generarString_A.equals("Mi"));
		labelYd_A.setVisible(generarString_A.equals("Yd")); labelFt_A.setVisible(generarString_A.equals("Ft")); labelIn_A.setVisible(generarString_A.equals("In"));
		labelMil_A.setVisible(generarString_A.equals("Mil")); 

		labelMuestra_A.setVisible(generarString_A.equals(" "));

			//Inicia el método de resultados después de que se eligieron las dos monedas
		if (!labelMuestra_A.isVisible()) {
			actualizarResultados(); 
		}
	}
 	

	private void muestraDe(){
		labelMuestra_De.setVisible(false);  
		labelKm_De.setVisible(false); labelM_De.setVisible(false);  labelCm_De.setVisible(false); labelMm_De.setVisible(false); label_μm_De.setVisible(false); 
		labelNm_De.setVisible(false); labelMi_De.setVisible(false); labelYd_De.setVisible(false); labelFt_De.setVisible(false); labelIn_De.setVisible(false);
		labelMil_De.setVisible(false); 
		
	}
		//Método para poner todas las banderas del mundo derecho no visibles en el convertidor
	private void muestraA(){
		labelMuestra_A.setVisible(false); 
		labelKm_A.setVisible(false); labelM_A.setVisible(false);  labelCm_A.setVisible(false); labelMm_A.setVisible(false); label_μm_A.setVisible(false);
		labelNm_A.setVisible(false); labelMi_A.setVisible(false); labelYd_A.setVisible(false); labelFt_A.setVisible(false); labelIn_A.setVisible(false); 
		labelMil_A.setVisible(false);
		
		
	}



	private void actualizarEtiquetas(){		
		
		if (!finalDeEtiqueta.isEmpty()) {
			if (labelMuestra_De.isVisible()) {
				muestraDe();
				try {
					Field labelUnida = getClass().getDeclaredField("label" + finalDeEtiqueta + "_De");
					JLabel label = (JLabel) labelUnida.get(this);
					label.setVisible(true);
					generarString_De = finalDeEtiqueta;
				} catch (NoSuchFieldException | IllegalAccessException e) {
					//System.out.println("Se ha producido una excepción Etiqueta _De vacía: " + e.getMessage());
					e.printStackTrace();
				}
			} else {
				muestraA();
				try {
					Field labelUnida = getClass().getDeclaredField("label" + finalDeEtiqueta + "_A");
					JLabel label = (JLabel) labelUnida.get(this);
					label.setVisible(true);
					generarString_A = finalDeEtiqueta;

				} catch (NoSuchFieldException | IllegalAccessException e) {
					//System.out.println("Se ha producido una excepción Etiqueta _A vacía: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}


			//Inicia el método de resultados después de que se eligieron las dos monedas
		if (!labelMuestra_A.isVisible()) {
			actualizarResultados(); 			
		}

	}

	private void limpiar() {
		muestraDe();
		labelMuestra_De.setVisible(true);
		generarString_De = " ";
		muestraA();
		labelMuestra_A.setVisible(true);
		generarString_A = " ";			
		ingresaCantidad.setText("1");
		labelResultado.setText("");
		panelError1.setVisible(false);
		ingresaCantidad.getDocument().addDocumentListener(this);ingresaCantidad.requestFocusInWindow();	
			
	}


	public void actionPerformed(ActionEvent eventoEnMemoria) {

		Object source = eventoEnMemoria.getSource();


		if (source == botonKm) {
			finalDeEtiqueta = "Km";
			actualizarEtiquetas();		
		} else if (source == botonM) {
			finalDeEtiqueta = "M";
			actualizarEtiquetas();
		} else if (source == botonCm) {
			finalDeEtiqueta = "Cm";
			actualizarEtiquetas();
		} else if (source == botonMm) {
			finalDeEtiqueta = "Mm";
			actualizarEtiquetas();
		} else if (source == boton_μm) {
			finalDeEtiqueta = "_μm";
			actualizarEtiquetas();
		} else if (source == botonNm) {
			finalDeEtiqueta = "Nm";
			actualizarEtiquetas();
		} else if (source == botonMi) {
			finalDeEtiqueta = "Mi";
			actualizarEtiquetas();
		} else if (source == botonYd) {
			finalDeEtiqueta = "Yd";
			actualizarEtiquetas();
		} else if (source == botonFt) {
			finalDeEtiqueta = "Ft";
			actualizarEtiquetas();
		} else if (source == botonIn) {
			finalDeEtiqueta = "In";
			actualizarEtiquetas();
		} else if (source == botonMil) {
			finalDeEtiqueta = "Mil";
			actualizarEtiquetas();
		}


		if(source == botonLimpiar){
			limpiar();
		}

		if(source == botonCambiar){
			cambiar();
		}

		if(source == botonIgual){
			actualizarResultados();
		}

		if(source == botonMenu){
			limpiar();
			Conversor.formulario1.setVisible(true);
			this.setVisible(false);	
		}
			//Botón para minimizar el panel
		if(source == botonMenos){
			this.setState(Frame.ICONIFIED);	
		}

		if(source == botonSalir){
			System.exit(0);
		}

		if(source == botonJPanelError1){
			limpiar();
			botonCm.setVisible(true);
			botonMm.setVisible(true);	
			botonFt.setVisible(true);	
		}

	
	}
   
      public static void main(String[] args) { 

 
         	ImageIcon iconFormulario4 = new ImageIcon("resources/ConversorDis.png");		
		formulario4 = new ConversorDeLongitud();
		formulario4.setIconImage(iconFormulario4.getImage());
		formulario4.setUndecorated(true);
		formulario4.setSize(590, 600);		
		formulario4.setVisible(true);
		formulario4.setResizable(false);
		formulario4.setLocationRelativeTo(null);		
		formulario4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formulario4.setTitle("Conversor de longitud");  
      }  
}  
