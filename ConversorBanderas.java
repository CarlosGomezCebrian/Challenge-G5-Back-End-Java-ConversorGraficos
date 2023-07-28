//javac -cp .;json-java.jar ConversorBanderas.java
//java -cp .;json-java.jar ConversorBanderas
//jar cfm ConversorBanderas.jar manifest.txt *.class org/json/*.class resources/*
//jar tf ConversorBanderas.jar
//java -jar ConversorBanderas.jar

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.awt.*;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

//Gris claro    rgb(206, 212, 218);
//Gris medio    rgb(173, 181, 189);
//Gris obscuro  rgba(73, 80, 87);
//Azul obscuro  rgb(10, 56, 113);
//Azul medio    rgb(233, 236, 248);
//Azul claro    rgb(243, 245, 252);
//Turq. claro   rgb(225, 245, 254);
//Turq. medio   rgb(128, 211, 249);
//Turq. obscuro rgb(50, 150, 232);


public class ConversorBanderas extends JFrame implements ActionListener, DocumentListener {

	private JTextField ingresaCantidad;

	private JOptionPane panelError; 

	private JPanel panelError1, panelGif;

	private JButton botonMenu, botonMenos, botonSalir,  botonLimpiar, botonCambiar, botonIgual, botonJPanel,
			botonARS, botonBRL, botonCAD, botonCHF, botonCLP,
			botonCNY, botonCOP, botonEUR, botonGBP, botonINR, 
			botonJPY, botonKRW, botonMXN, botonRUB, botonUSD;

	private JLabel 	labelDinero, labelFlecha,  labelGif, labelCantidad, labelResultado, labelNombre, labelFecha,    
			labelMundo_De, labelMundo_A, labelGifFlecha,
			labelARS_De, labelARS_A, labelBRL_De, labelBRL_A, labelCAD_De, labelCAD_A, labelCHF_De, labelCHF_A, labelCLP_De,labelCLP_A,
			labelCNY_De, labelCNY_A, labelCOP_De, labelCOP_A, labelEUR_De, labelEUR_A, labelGBP_De, labelGBP_A, labelINR_De, labelINR_A, 
			labelJPY_De, labelJPY_A, labelKRW_De, labelKRW_A, labelMXN_De, labelMXN_A, labelRUB_De, labelRUB_A, labelUSD_De, labelUSD_A;



	private String generarString_De = " ";
	private String generarString_A = " ";

	private JSONObject cambioMoneda = null;
	private static final String DATA_FILE = "cambioMoneda.dat";

	private long nextUpdateTime = 0;


	
	
	ImageIcon iconERROR = new ImageIcon("resources/FlechaArriva.GIF");
	ImageIcon iconERROR1 = new ImageIcon("resources/ERROR1.PNG");
	
	public static ConversorBanderas frame;

	public ConversorBanderas() {

		setLayout(null);

		getContentPane().setBackground(new java.awt.Color(225, 245, 254));
		UIManager.put("Panel.background", new Color(248,222,126));//amarillo
		UIManager.put("OptionPane.messageForeground", new Color(216, 30, 5));//rojo
		UIManager.put("OptionPane.messageFont", new Font("Malgun Gothic", Font.BOLD, 14));
		UIManager.put("OptionPane.background", new Color(248,222,126));//amarillo
		UIManager.put("Button.background", new Color(225, 245, 254));
		UIManager.put("Label.foreground", new Color(50, 150, 232));
		UIManager.put("Button.foreground", new Color(50, 150, 232));
		UIManager.put("Button.font", new Font("Malgun Gothic", Font.BOLD, 12));		
		UIManager.put("Button.margin", new Insets(2, 2, 2, 2));
		UIManager.put("Button.border", BorderFactory.createLineBorder(new Color(50, 150, 232), 3));
		//UIManager.put("Button.border", BorderFactory.createEmptyBorder());

		
		panelError1 = new JPanel();
		panelError1.setBounds(310,90,100,170);
		panelError1.setVisible(false);
		add(panelError1);
		
		labelGifFlecha = new JLabel(); labelGifFlecha.setIcon(iconERROR); labelGifFlecha.setVisible(true); panelError1.add(labelGifFlecha);
		
		botonJPanel = new JButton("OK");
		botonJPanel.setPreferredSize(new Dimension(30, 25));
		botonJPanel.setBorder(BorderFactory.createEmptyBorder());
		botonJPanel.setAlignmentX(Component.CENTER_ALIGNMENT); botonJPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT); panelError1.add(botonJPanel); botonJPanel.addActionListener(this);

			//Jlabel de texto y TTextField
		ingresaCantidad = new JTextField("1");
		ingresaCantidad.setBounds(270, 40, 185, 50); ingresaCantidad.setForeground(new Color(50, 150, 232)); ingresaCantidad.setCaretColor(new Color(50, 150, 232));ingresaCantidad.setBackground(new Color(128, 211, 249)); 
		ingresaCantidad.setBorder(BorderFactory.createEmptyBorder()); ingresaCantidad.requestFocusInWindow();
		ingresaCantidad.setFont(new Font("Malgun Gothic", Font.BOLD, 22));ingresaCantidad.setHorizontalAlignment(SwingConstants.CENTER); add(ingresaCantidad);
		ingresaCantidad.getDocument().addDocumentListener(this);ingresaCantidad.requestFocusInWindow();

		labelCantidad = new JLabel(ingresaCantidad.getText());
		labelCantidad.setVisible(true); labelCantidad.setHorizontalAlignment(SwingConstants.RIGHT); labelCantidad.setBounds(10, 600, 140, 80); labelCantidad.setFont(new Font("Malgun Gothic", Font.BOLD, 30)); add(labelCantidad);	

		labelResultado = new JLabel("");
		labelResultado.setHorizontalAlignment(SwingConstants.LEFT); labelResultado.setVisible(true); labelResultado.setBounds(470, 600, 200, 80); labelResultado.setFont(new Font("Malgun Gothic", Font.BOLD, 30)); add(labelResultado);

		labelFecha = new JLabel("");
		labelFecha.setHorizontalAlignment(SwingConstants.LEFT); labelFecha.setVisible(true); labelFecha.setBounds(350, 700, 360, 20); labelFecha.setFont(new Font("Malgun Gothic", Font.BOLD, 10)); add(labelFecha);

		labelNombre = new JLabel("Realizado por CarlosOmarGomez.");
		labelNombre.setHorizontalAlignment(SwingConstants.LEFT); labelNombre.setVisible(true); labelNombre.setBounds(10, 700, 360, 20); labelNombre.setFont(new Font("Malgun Gothic", Font.BOLD, 12)); add(labelNombre);

			//Comienzan botones banderas
			//PRIMERA COLUNMA botonARS, botonBRL, botonCAD, botonCHF, botonCLP,

		ImageIcon iconARS = new ImageIcon("resources/ARS.png");		
		botonARS = new JButton(""); botonARS.setIcon(iconARS);    botonARS.setVisible(true);     botonARS.setBounds(160, 100, 85, 85);    add(botonARS); botonARS.addActionListener(this);
		labelARS_De = new JLabel(); labelARS_De.setIcon(iconARS); labelARS_De.setVisible(false); labelARS_De.setBounds(150, 600, 90, 90); add(labelARS_De);		
		labelARS_A = new JLabel();  labelARS_A.setIcon(iconARS);  labelARS_A.setVisible(false);  labelARS_A.setBounds(364, 600, 90, 90);  add(labelARS_A);
		
		ImageIcon iconBRL = new ImageIcon("resources/BRL.png");		
		botonBRL = new JButton(""); botonBRL.setIcon(iconBRL);    botonBRL.setVisible(true);     botonBRL.setBounds(160, 200, 85, 85); add(botonBRL); botonBRL.addActionListener(this);
		labelBRL_De = new JLabel(); labelBRL_De.setIcon(iconBRL); labelBRL_De.setVisible(false); labelBRL_De.setBounds(150, 600, 90, 90); add(labelBRL_De);
		labelBRL_A = new JLabel();  labelBRL_A.setIcon(iconBRL);  labelBRL_A.setVisible(false);  labelBRL_A.setBounds(364, 600, 90, 90); add(labelBRL_A);

		ImageIcon iconCAD = new ImageIcon("resources/CAD.png");		
		botonCAD = new JButton(""); botonCAD.setIcon(iconCAD);    botonCAD.setVisible(true);     botonCAD.setBounds(160, 300, 85, 85);    add(botonCAD); botonCAD.addActionListener(this);
		labelCAD_De = new JLabel(); labelCAD_De.setIcon(iconCAD); labelCAD_De.setVisible(false); labelCAD_De.setBounds(150, 600, 90, 90); add(labelCAD_De);
		labelCAD_A = new JLabel();  labelCAD_A.setIcon(iconCAD);  labelCAD_A.setVisible(false);  labelCAD_A.setBounds(364, 600, 90, 90);  add(labelCAD_A);

		ImageIcon iconCHF = new ImageIcon("resources/CHF.png");
		botonCHF = new JButton(""); botonCHF.setIcon(iconCHF);    botonCHF.setVisible(true);     botonCHF.setBounds(160, 400, 85, 85);    add(botonCHF); botonCHF.addActionListener(this);
		labelCHF_De = new JLabel(); labelCHF_De.setIcon(iconCHF); labelCHF_De.setVisible(false); labelCHF_De.setBounds(150, 600, 90, 90); add(labelCHF_De);
		labelCHF_A = new JLabel();  labelCHF_A.setIcon(iconCHF);  labelCHF_A.setVisible(false);  labelCHF_A.setBounds(364, 600, 90, 90);  add(labelCHF_A);


		ImageIcon iconCLP = new ImageIcon("resources/CLP.png");		
		botonCLP = new JButton(""); botonCLP.setIcon(iconCLP);    botonCLP.setVisible(true);     botonCLP.setBounds(160, 500, 85, 85);    add(botonCLP); botonCLP.addActionListener(this);
		labelCLP_De = new JLabel(); labelCLP_De.setIcon(iconCLP); labelCLP_De.setVisible(false); labelCLP_De.setBounds(150, 600, 90, 90); add(labelCLP_De);
		labelCLP_A = new JLabel();  labelCLP_A.setIcon(iconCLP);  labelCLP_A.setVisible(false);  labelCLP_A.setBounds(364, 600, 90, 90);  add(labelCLP_A);

			//SEGUNDA COLUMNA botonCNY, botonCOP, botonEUR, botonGBP, botonINR,

		ImageIcon iconCNY = new ImageIcon("resources/CNY.png");		
		botonCNY = new JButton("");  botonCNY.setIcon(iconCNY);   botonCNY.setVisible(true);     botonCNY.setBounds(270, 100, 85, 85);    add(botonCNY); botonCNY.addActionListener(this);
		labelCNY_De = new JLabel(); labelCNY_De.setIcon(iconCNY); labelCNY_De.setVisible(false); labelCNY_De.setBounds(147, 600, 90, 90); add(labelCNY_De);
		labelCNY_A = new JLabel();  labelCNY_A.setIcon(iconCNY);  labelCNY_A.setVisible(false);  labelCNY_A.setBounds(372, 600, 90, 90);  add(labelCNY_A);

		ImageIcon iconCOP = new ImageIcon("resources/COP.png");		
		botonCOP = new JButton(""); botonCOP.setIcon(iconCOP);    botonCOP.setVisible(true);     botonCOP.setBounds(270, 200, 85, 85);    add(botonCOP); botonCOP.addActionListener(this);
		labelCOP_De = new JLabel(); labelCOP_De.setIcon(iconCOP); labelCOP_De.setVisible(false); labelCOP_De.setBounds(145, 600, 90, 90); add(labelCOP_De);
		labelCOP_A = new JLabel();  labelCOP_A.setIcon(iconCOP);  labelCOP_A.setVisible(false);  labelCOP_A.setBounds(372, 600, 90, 90);  add(labelCOP_A);

		ImageIcon iconEUR = new ImageIcon("resources/EUR.png");		
		botonEUR = new JButton(""); botonEUR.setIcon(iconEUR);    botonEUR.setVisible(true);     botonEUR.setBounds(270, 300, 85, 85);    add(botonEUR); botonEUR.addActionListener(this);
		labelEUR_De = new JLabel(); labelEUR_De.setIcon(iconEUR); labelEUR_De.setVisible(false); labelEUR_De.setBounds(145, 600, 90, 90); add(labelEUR_De);
		labelEUR_A = new JLabel();  labelEUR_A.setIcon(iconEUR);  labelEUR_A.setVisible(false);  labelEUR_A.setBounds(372, 600, 90, 90);  add(labelEUR_A);

		ImageIcon iconGBP = new ImageIcon("resources/GBP.png");		
		botonGBP = new JButton(""); botonGBP.setIcon(iconGBP);    botonGBP.setVisible(true);     botonGBP.setBounds(270, 400, 85, 85);    add(botonGBP); botonGBP.addActionListener(this);
		labelGBP_De = new JLabel(); labelGBP_De.setIcon(iconGBP); labelGBP_De.setVisible(false); labelGBP_De.setBounds(143, 600, 90, 90); add(labelGBP_De);
		labelGBP_A = new JLabel();  labelGBP_A.setIcon(iconGBP);  labelGBP_A.setVisible(false);  labelGBP_A.setBounds(370, 600, 90, 90);  add(labelGBP_A);

		ImageIcon iconINR = new ImageIcon("resources/INR.png");		
		botonINR = new JButton(""); botonINR.setIcon(iconINR);    botonINR.setVisible(true);     botonINR.setBounds(270, 500, 85, 85);    add(botonINR); botonINR.addActionListener(this);
		labelINR_De = new JLabel(); labelINR_De.setIcon(iconINR); labelINR_De.setVisible(false); labelINR_De.setBounds(142, 600, 90, 90); add(labelINR_De);
		labelINR_A = new JLabel();  labelINR_A.setIcon(iconINR);  labelINR_A.setVisible(false);  labelINR_A.setBounds(365, 600, 90, 90);  add(labelINR_A);

			//TERCERA COLUMNA botonJPY, botonKRW, botonMXN, botonRUB, botonUSD;

		ImageIcon iconJPY = new ImageIcon("resources/JPY.png");		
		botonJPY = new JButton(""); botonJPY.setIcon(iconJPY);    botonJPY.setVisible(true);     botonJPY.setBounds(380, 100, 85, 85);    add(botonJPY); botonJPY.addActionListener(this);
		labelJPY_De = new JLabel(); labelJPY_De.setIcon(iconJPY); labelJPY_De.setVisible(false); labelJPY_De.setBounds(160, 600, 90, 90); add(labelJPY_De);
		labelJPY_A = new JLabel();  labelJPY_A.setIcon(iconJPY);  labelJPY_A.setVisible(false);  labelJPY_A.setBounds(383, 600, 90, 90);  add(labelJPY_A);

		ImageIcon iconKRW = new ImageIcon("resources/KRW.png");		
		botonKRW = new JButton(""); botonKRW.setIcon(iconKRW);    botonKRW.setVisible(true);     botonKRW.setBounds(380, 200, 85, 85);    add(botonKRW); botonKRW.addActionListener(this);
		labelKRW_De = new JLabel(); labelKRW_De.setIcon(iconKRW); labelKRW_De.setVisible(false); labelKRW_De.setBounds(150, 600, 90, 90); add(labelKRW_De);
		labelKRW_A = new JLabel();  labelKRW_A.setIcon(iconKRW);  labelKRW_A.setVisible(false);  labelKRW_A.setBounds(370, 600, 90, 90);  add(labelKRW_A);

		ImageIcon iconMXN = new ImageIcon("resources/MXN.png");		
		botonMXN = new JButton(""); botonMXN.setIcon(iconMXN);    botonMXN.setVisible(true);     botonMXN.setBounds(380, 300, 85, 85);   add(botonMXN); botonMXN.addActionListener(this);
		labelMXN_De = new JLabel(); labelMXN_De.setIcon(iconMXN); labelMXN_De.setVisible(false); labelMXN_De.setBounds(150, 600, 90, 90); add(labelMXN_De);
		labelMXN_A = new JLabel();  labelMXN_A.setIcon(iconMXN);  labelMXN_A.setVisible(false);  labelMXN_A.setBounds(370, 600, 90, 90);  add(labelMXN_A);

		ImageIcon iconRUB = new ImageIcon("resources/RUB.png");		
		botonRUB = new JButton(""); botonRUB.setIcon(iconRUB);    botonRUB.setVisible(true);     botonRUB.setBounds(380, 400, 85, 85);    add(botonRUB); botonRUB.addActionListener(this);
		labelRUB_De = new JLabel(); labelRUB_De.setIcon(iconRUB); labelRUB_De.setVisible(false); labelRUB_De.setBounds(150, 600, 90, 90); add(labelRUB_De);
		labelRUB_A = new JLabel();  labelRUB_A.setIcon(iconRUB);  labelRUB_A.setVisible(false);  labelRUB_A.setBounds(365, 600, 90, 90);  add(labelRUB_A);

		ImageIcon iconUSD = new ImageIcon("resources/USD.png");		
		botonUSD = new JButton(""); botonUSD.setIcon(iconUSD);    botonUSD.setVisible(true);     botonUSD.setBounds(380, 500, 85, 85);    add(botonUSD); botonUSD.addActionListener(this);
		labelUSD_De = new JLabel(); labelUSD_De.setIcon(iconUSD); labelUSD_De.setVisible(false); labelUSD_De.setBounds(150, 600, 90, 90); add(labelUSD_De);
		labelUSD_A = new JLabel();  labelUSD_A.setIcon(iconUSD);  labelUSD_A.setVisible(false);  labelUSD_A.setBounds(370, 600, 90, 90);  add(labelUSD_A);

		//Termina botones banderas

		//Comienzan los botones de acciones

		ImageIcon iconMENU = new ImageIcon("resources/MenuMini.png");
		botonMenu = new JButton(""); botonMenu.setIcon(iconMENU); botonMenu.setVisible(true); botonMenu.setBounds(500, 10, 35, 35); add(botonMenu); botonMenu.addActionListener(this);
		botonMenu.setBorder(BorderFactory.createEmptyBorder());

		ImageIcon iconMENOS = new ImageIcon("resources/MenosMini.png");
		botonMenos = new JButton(""); botonMenos.setIcon(iconMENOS); botonMenos.setVisible(true); botonMenos.setBounds(545, 10, 35, 35); add(botonMenos); botonMenos.addActionListener(this);
		botonMenos.setBorder(BorderFactory.createEmptyBorder());

		ImageIcon iconSAL = new ImageIcon("resources/SalirMini.png");
		botonSalir = new JButton(""); botonSalir.setIcon(iconSAL); botonSalir.setVisible(true); botonSalir.setBounds(585, 10, 35, 35); add(botonSalir); botonSalir.addActionListener(this);
		botonSalir.setBorder(BorderFactory.createEmptyBorder());

		ImageIcon iconLIMP = new ImageIcon("resources/Escoba2.png");
		botonLimpiar = new JButton(""); botonLimpiar.setIcon(iconLIMP); botonLimpiar.setVisible(true); botonLimpiar.setBounds(490, 300, 76, 80); add(botonLimpiar); botonLimpiar.addActionListener(this);
		botonLimpiar.setBorder(BorderFactory.createEmptyBorder());

		ImageIcon iconoCambiar = new ImageIcon("resources/CAMBIAR.png");
		botonCambiar = new JButton(""); botonCambiar.setIcon(iconoCambiar); botonCambiar.setVisible(true); botonCambiar.setBounds(490, 400, 90, 90); add(botonCambiar); botonCambiar.addActionListener(this);
		botonCambiar.setBorder(BorderFactory.createEmptyBorder());

		ImageIcon iconoIGUAL = new ImageIcon("resources/IGUAL1.png");
		botonIgual = new JButton(""); botonIgual.setIcon(iconoIGUAL); botonIgual.setVisible(true); botonIgual.setBounds(270, 600, 90, 90); add(botonIgual); botonIgual.addActionListener(this);


			//Terminan los botones de acciones
			//Jlabels de imagenes

		ImageIcon iconoDINERO = new ImageIcon("resources/DINERO.png");									
		labelDinero = new JLabel(); labelDinero.setIcon(iconoDINERO); labelDinero.setVisible(true); labelDinero.setBounds(45, 15, 90, 90); add(labelDinero);

		ImageIcon iconoFlecha = new ImageIcon("resources/FLECHA.png");									
		labelFlecha = new JLabel(); labelFlecha.setIcon(iconoFlecha); labelFlecha.setVisible(true); labelFlecha.setBounds(140, 20, 100, 90); add(labelFlecha);

		ImageIcon iconoGif = new ImageIcon("resources/GIF1.gif");
		labelGif = new JLabel(); labelGif.setIcon(iconoGif); labelGif.setVisible(true); labelGif.setBounds(35, 300, 85, 85); add(labelGif);
		labelGif.setBorder(BorderFactory.createLineBorder(new Color(50, 150, 232), 3));

		ImageIcon iconoMUNDO = new ImageIcon("resources/MUNDO.png");
		labelMundo_De = new JLabel(); labelMundo_De.setIcon(iconoMUNDO); labelMundo_De.setVisible(true); labelMundo_De.setBounds(160, 600, 80, 80); add(labelMundo_De);


		
		labelMundo_A = new JLabel();
		labelMundo_A.setIcon(iconoMUNDO);labelMundo_A.setVisible(true);labelMundo_A.setBounds(385, 600, 90, 90); add(labelMundo_A);
	}
		//Termina el constructor.

		//El texto de ingresaCantidas se conecte con labelCantidad
	public void insertUpdate(DocumentEvent e) {
		labelCantidad.setText(ingresaCantidad.getText());
	}
	public void removeUpdate(DocumentEvent e) {
		labelCantidad.setText(ingresaCantidad.getText());
	}
	public void changedUpdate(DocumentEvent e) {
		labelCantidad.setText(ingresaCantidad.getText());
	}


		//Método para extraer los datos de la pagina de ExchangeRate

	private JSONObject obtenerCambioMoneda() {		
		 long currentTime = System.currentTimeMillis();

			//Cargar los datos desde el archivo
		if (cambioMoneda == null) {
			cambioMoneda = cargarDatos();
   		}
		

		if (currentTime >= nextUpdateTime || cambioMoneda == null) {
			try {
				URL url = new URL("https://v6.exchangerate-api.com/v6/d6077e3583242934fcbf760f/latest/USD");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder resultAPI = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
				resultAPI.append(line);
				}
				rd.close();
				cambioMoneda = new JSONObject(resultAPI.toString());
				nextUpdateTime = cambioMoneda.getLong("time_next_update_unix") * 1000;
				guardarDatos(cambioMoneda);

				//System.out.println(resultAPI.toString());
				//System.out.println(cambioMoneda);
				//System.out.println("nextUpdateTime: " + nextUpdateTime);
				//System.out.println("currentTime: " + currentTime);

			} catch (Exception e) {
				e.printStackTrace();								
				System.out.println("Se ha producido una excepción: " + e.getMessage());
				
			}
		}
		return cambioMoneda;
	}

		//Guardar datos en memoria del disco
	private void guardarDatos(JSONObject data) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
					out.writeObject(data.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		// Cargar datos guardados en memoria del disco	
	private JSONObject cargarDatos() {
		if (new File(DATA_FILE).exists()) {
			//System.out.println("Cargando datos desde el archivo...");
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
				String dataStr = (String) in.readObject();
				//System.out.println("Datos cargados: " + dataStr);
				return new JSONObject(dataStr);
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
    		}
    		return null;
	}

		//Método para hacer la conversión de monedas
	private void actualizarResultados() {

			try {
				double cantidad = Double.parseDouble(ingresaCantidad.getText());
				JSONObject cambioMoneda = obtenerCambioMoneda();
				if (cambioMoneda != null) {
					JSONObject rates = cambioMoneda.getJSONObject("conversion_rates");
					double usdRate = rates.getDouble("USD");
					String monedaOrigen = generarString_De;
					String monedaDestino = generarString_A;								
					double tasaCambioOrigen = rates.getDouble(monedaOrigen);
					double tasaCambioDestino = rates.getDouble(monedaDestino);
					double resultado = cantidad * tasaCambioDestino / tasaCambioOrigen;
					String fecha = cambioMoneda.getString("time_last_update_utc");
					String fechaConDiaYUTC = fecha;
					int indicePrimerEspacio = fechaConDiaYUTC.indexOf(" ");
					String fechaSinDiaYUTC = fechaConDiaYUTC.substring(indicePrimerEspacio + 1,fechaConDiaYUTC.length() - 6);
					String fechaSiguiente = cambioMoneda.getString("time_next_update_utc");
					String fechaConDiaYUTCSiguiente = fechaSiguiente;
					int indicePrimerEspacioSiguiente = fechaConDiaYUTCSiguiente.indexOf(" ");
					String fechaSinDiaYUTCSiguiente = fechaConDiaYUTCSiguiente.substring(indicePrimerEspacio + 1,fechaConDiaYUTC.length() - 6);
					labelResultado.setVisible(true);
					labelResultado.setText(String.format("%.2f", resultado));
					labelFecha.setVisible(true);
					labelFecha.setText("UTC " + fechaSinDiaYUTC + "=> UTC " + fechaSinDiaYUTCSiguiente);
					

				} else {
					
					JOptionPane.showMessageDialog(botonCambiar, "No hay datos", "Error", JOptionPane.ERROR_MESSAGE, iconERROR1);
					
				}
			} catch (NumberFormatException e) {
				panelError1.setVisible(true);				
				//JOptionPane.showMessageDialog(botonMXN, "<html><br>\"Error\"<br>\"Erreur\"<br>\"Ошибка\"<br>\"Fehler\"<br>\"Errore\"<br>\"エラー\"<br>\"Błąd\"<br>\"Erro\"<br>\"错误\"<br>\"त्रुटि\"<br>\"오류\" </html>", "", JOptionPane.ERROR_MESSAGE, iconERROR);
																
				
			} catch (org.json.JSONException e) {
				JOptionPane.showMessageDialog(botonCambiar, "No hay datos Jsone", "Error", JOptionPane.ERROR_MESSAGE, iconERROR1);
				limpiar();				
			}

	} // aqui se cierra método actualizarResultados 

			// Método para dejar los valores a inicio
		private void limpiar() {
			mundoDe();
			labelMundo_De.setVisible(true);
			generarString_De = " ";
			labelMundo_A.setVisible(true);
			mundoA();
			labelMundo_A.setVisible(true);
			generarString_A = " ";			
			ingresaCantidad.setText("");
			ingresaCantidad.getDocument().addDocumentListener(this);ingresaCantidad.requestFocusInWindow();
			labelResultado.setVisible(false);
			
		}

			//Método para poner todas las banderas del mundo izquierdo no visibles en el convertidor
		private void mundoDe(){
			labelMundo_De.setVisible(false);  
			labelARS_De.setVisible(false); labelBRL_De.setVisible(false); labelCAD_De.setVisible(false); labelCHF_De.setVisible(false); labelCLP_De.setVisible(false); 
			labelCNY_De.setVisible(false); labelCOP_De.setVisible(false); labelEUR_De.setVisible(false); labelGBP_De.setVisible(false); labelINR_De.setVisible(false); 
			labelJPY_De.setVisible(false); labelKRW_De.setVisible(false); labelMXN_De.setVisible(false); labelRUB_De.setVisible(false); labelUSD_De.setVisible(false); 
               }
			//Método para poner todas las banderas del mundo derecho no visibles en el convertidor
		private void mundoA(){
			labelMundo_A.setVisible(false); 
			labelARS_A.setVisible(false); labelBRL_A.setVisible(false); labelCAD_A.setVisible(false); labelCHF_A.setVisible(false); labelCLP_A.setVisible(false);
			labelCNY_A.setVisible(false); labelCOP_A.setVisible(false); labelEUR_A.setVisible(false); labelGBP_A.setVisible(false); labelINR_A.setVisible(false); 
			labelJPY_A.setVisible(false); labelKRW_A.setVisible(false); labelMXN_A.setVisible(false); labelRUB_A.setVisible(false); labelUSD_A.setVisible(false);
		}

	public void actionPerformed(ActionEvent eventoEnMemoria) {
			//De esta línea y al la línea 561 son los botones de las banderas con sus métodos 
		if(eventoEnMemoria.getSource() == botonARS){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelARS_De.setVisible(true); generarString_De = "ARS"; 
			} else {
				mundoA();  labelARS_A.setVisible(true);  generarString_A = "ARS";  
			}
		}

		if(eventoEnMemoria.getSource() == botonBRL){				
		
			if(labelMundo_De.isVisible()){
				mundoDe(); labelBRL_De.setVisible(true); generarString_De = "BRL"; 

			} else {
				mundoA();  labelBRL_A.setVisible(true);  generarString_A = "BRL";  
			}
		}

		if(eventoEnMemoria.getSource() == botonCAD){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelCAD_De.setVisible(true); generarString_De = "CAD"; 

			} else { 
				mundoA();  labelCAD_A.setVisible(true);  generarString_A = "CAD";  
			}
		}

		if(eventoEnMemoria.getSource() == botonCHF){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelCHF_De.setVisible(true); generarString_De = "CHF"; 
			} else { 
				mundoA();  labelCHF_A.setVisible(true); generarString_A = "CHF";  
			}
		}

		if(eventoEnMemoria.getSource() == botonCLP){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelCLP_De.setVisible(true); generarString_De = "CLP"; 

			} else { 
				mundoA();  labelCLP_A.setVisible(true);  generarString_A = "CLP";
			}
		}

		if(eventoEnMemoria.getSource() == botonCNY){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelCNY_De.setVisible(true); generarString_De = "CNY";

			} else { 
				mundoA();  labelCNY_A.setVisible(true);  generarString_A = "CNY";
			}
		}

		if(eventoEnMemoria.getSource() == botonCOP){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelCOP_De.setVisible(true); generarString_De = "COP";

			} else { 
				mundoA();  labelCOP_A.setVisible(true);  generarString_A = "COP";
			}
		}

		if(eventoEnMemoria.getSource() == botonEUR){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelEUR_De.setVisible(true); generarString_De = "EUR";

			} else { 
				mundoA();  labelEUR_A.setVisible(true);  generarString_A = "EUR";
			}
		}

		if(eventoEnMemoria.getSource() == botonGBP){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelGBP_De.setVisible(true); generarString_De = "GBP";

			} else { 
				mundoA();  labelGBP_A.setVisible(true);  generarString_A = "GBP";
			}
		}

		if(eventoEnMemoria.getSource() == botonINR){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelINR_De.setVisible(true); generarString_De = "INR";

			} else { 
				mundoA();  labelINR_A.setVisible(true);  generarString_A = "INR";
			}
		}

		if(eventoEnMemoria.getSource() == botonJPY){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelJPY_De.setVisible(true); generarString_De = "JPY";

			} else { 
				mundoA();  labelJPY_A.setVisible(true);  generarString_A = "JPY";
			}
		}

		if(eventoEnMemoria.getSource() == botonKRW){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelKRW_De.setVisible(true); generarString_De = "KRW";

			} else { 
				mundoA();  labelKRW_A.setVisible(true);  generarString_A = "KRW";
			}
		}

		if(eventoEnMemoria.getSource() == botonMXN){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelMXN_De.setVisible(true); generarString_De = "MXN";

			} else { 
				mundoA();  labelMXN_A.setVisible(true);  generarString_A = "MXN";
			}
		}

		if(eventoEnMemoria.getSource() == botonRUB){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelRUB_De.setVisible(true); generarString_De = "RUB";

			} else { 
				mundoA();  labelRUB_A.setVisible(true);  generarString_A = "RUB";  
			}
		}

		if(eventoEnMemoria.getSource() == botonUSD){

			if(labelMundo_De.isVisible()){
				mundoDe(); labelUSD_De.setVisible(true); generarString_De = "USD";
			} else { 
				mundoA();  labelUSD_A.setVisible(true);  generarString_A = "USD";  
			}
		}

			//Inicia el método de resultados después de que se eligieron las dos monedas
		if (!labelMundo_A.isVisible()) {
			actualizarResultados(); 			
		}
			//Terminan if de botones de banderas

			//Botón que intercambia los valores en pantalla	
		if(eventoEnMemoria.getSource() == botonCambiar){

			String temp = generarString_De;
			generarString_De = generarString_A;
			generarString_A = temp;

				// Actualizar la visibilidad de las etiquetas
			labelARS_De.setVisible(generarString_De.equals("ARS")); labelBRL_De.setVisible(generarString_De.equals("BRL"));
			labelCAD_De.setVisible(generarString_De.equals("CAD")); labelCHF_De.setVisible(generarString_De.equals("CHF")); labelCLP_De.setVisible(generarString_De.equals("CLP"));
			labelCNY_De.setVisible(generarString_De.equals("CNY")); labelCOP_De.setVisible(generarString_De.equals("COP"));
			labelEUR_De.setVisible(generarString_De.equals("EUR")); labelGBP_De.setVisible(generarString_De.equals("GBP")); labelINR_De.setVisible(generarString_De.equals("INR"));
			labelJPY_De.setVisible(generarString_De.equals("JPY")); labelKRW_De.setVisible(generarString_De.equals("KRW"));
			labelMXN_De.setVisible(generarString_De.equals("MXN")); labelRUB_De.setVisible(generarString_De.equals("RUB")); labelUSD_De.setVisible(generarString_De.equals("USD"));

			labelMundo_De.setVisible(generarString_De.equals(" "));

			labelARS_A.setVisible(generarString_A.equals("ARS")); labelBRL_A.setVisible(generarString_A.equals("BRL"));
			labelCAD_A.setVisible(generarString_A.equals("CAD")); labelCHF_A.setVisible(generarString_A.equals("CHF")); labelCLP_A.setVisible(generarString_A.equals("CLP"));
			labelCNY_A.setVisible(generarString_A.equals("CNY")); labelCOP_A.setVisible(generarString_A.equals("COP"));
			labelEUR_A.setVisible(generarString_A.equals("EUR")); labelGBP_A.setVisible(generarString_A.equals("GBP")); labelINR_A.setVisible(generarString_A.equals("INR"));
			labelJPY_A.setVisible(generarString_A.equals("JPY")); labelKRW_A.setVisible(generarString_A.equals("KRW"));
			labelMXN_A.setVisible(generarString_A.equals("MXN")); labelRUB_A.setVisible(generarString_A.equals("RUB")); labelUSD_A.setVisible(generarString_A.equals("USD"));

			labelMundo_A.setVisible(generarString_A.equals(" "));

				//Inicia el método de resultados después de que se eligieron las dos monedas
			if (!labelMundo_A.isVisible()) {
				actualizarResultados(); 
			}
		}

			// Botón que llama al métode para dejar los valores a inicio
		if(eventoEnMemoria.getSource() == botonLimpiar){
			limpiar();
		}
			//Botón para cerrar la aplicación
		if(eventoEnMemoria.getSource() == botonSalir){
			System.exit(0);
		}
			//Botón para minimizar el panel
		if(eventoEnMemoria.getSource() == botonMenos){
			frame.setState(Frame.ICONIFIED);		
		}
			//Da inicio al metodo actualizarResultados
		if(eventoEnMemoria.getSource() == botonIgual){
			actualizarResultados();		
		}
			//Botón es para cerrar el JPanel
		if(eventoEnMemoria.getSource() == botonJPanel){
			panelError1.setVisible(false);
			limpiar();		
		}
	}
		
	public static void main(String args[]) {

		ImageIcon iconFrame = new ImageIcon("resources/ConversorIconA.PNG");
		frame = new ConversorBanderas();
		//frame.pack();
		frame.setIconImage(iconFrame.getImage());
		frame.setUndecorated(true);
		frame.setSize(630, 720);		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Alura Conversor de divisas");
	}
}

