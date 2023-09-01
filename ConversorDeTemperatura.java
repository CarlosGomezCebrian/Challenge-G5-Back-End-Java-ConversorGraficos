import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.lang.reflect.Field;

import java.net.URL;
import java.net.URLConnection;


import org.json.JSONObject;

/*
Dark2 red  rgb(189, 22, 22) #BD1616
Medium2 red  rgb(226, 47, 47) #E22F2F
Light2 red  rgb(234, 92, 92) #EA5C5C
*/

public class ConversorDeTemperatura extends JFrame implements ActionListener, DocumentListener {

	private JButton botonJPanelError1, botonMenu, botonMenos, botonSalir, botonLimpiar, botonCambiar, botonIgual, botonCelsius, botonFahrenheit, 
			botonUbicacion, botonKelvin, botonCodigoPostal, botonCodigoPostalError, botonSinWifi;

	private JLabel  labelGifFlecha, labelResultadoTemp, labelCantidad, labelNombre, labelTemperatura, labelZip, labelIP, labelTiempo, 
			labelUbicacion, labelTemplado, labelFrio, labelCalor, labelCodigoPostal, labelCodigoPostalError, ingresalabelCodigoPostal,
			labelMuestra_De, labelMuestra_A, labelCelsius_De, labelCelsius_A, labelFahrenheit_De, labelFahrenheit_A, labelKelvin_De, labelKelvin_A; 

	private JPanel  panelError1, panelCodigoPostal, panelCodigoPostalError;	


	private JTextField ingresaCantidad, ingresaCodigoPostal;

	public static Conversor formulario1;
	public static ConversorDeTemperatura formulario3;

	private String generarString_De = " ";
	private String generarString_A = " ";
	private String finalDeEtiqueta = " ";


	private String apiKey = "MyapiKey";
	private String apiKeyZipCodeBase = "MyapiKeyZipCodeBase";
	private double lat;
	private double lon;
	private String city;
    	private String countryCode;

	public ConversorDeTemperatura() {

		setLayout(null);			
		new ClaseUtils(this);
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
		panelError1.setBounds(200,50,100,160);
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


		ImageIcon iconCodigoPostal = new ImageIcon("resources/CodigoPostal.png");
		panelCodigoPostal = new JPanel();
		panelCodigoPostal.setLayout(null);
		panelCodigoPostal.setBounds(10,80,210,100);
		panelCodigoPostal.setVisible(false);
		add(panelCodigoPostal);

		labelCodigoPostal = new JLabel(); labelCodigoPostal.setIcon(iconCodigoPostal); labelCodigoPostal.setVisible(true); panelCodigoPostal.add(labelCodigoPostal);
		labelCodigoPostal.setBounds(120, 5, 90, 90);

		botonCodigoPostal = new JButton("OK");		
		botonCodigoPostal.setBounds(40, 60, 35, 30);
		botonCodigoPostal.setBackground(new Color(189, 22, 22));
		botonCodigoPostal.setForeground(new Color(234, 92, 92));
		panelCodigoPostal.add(botonCodigoPostal); 
		botonCodigoPostal.addActionListener(this);


		ingresaCodigoPostal = new JTextField("");
		ingresaCodigoPostal.setBounds(5, 25, 110, 25);
		ingresaCodigoPostal.setBackground(new Color(249, 120, 172));
		ingresaCodigoPostal.setForeground(new Color(189, 22, 22));		
		ingresaCodigoPostal.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		panelCodigoPostal.add(ingresaCodigoPostal);		
		ingresaCodigoPostal.requestFocusInWindow();


		ImageIcon iconCodigoPostalError = new ImageIcon("resources/codogoPostalError.png");
		panelCodigoPostalError = new JPanel();
		panelCodigoPostalError.setLayout(null);
		panelCodigoPostalError.setBounds(10,80,210,100);
		panelCodigoPostalError.setVisible(false);
		add(panelCodigoPostalError);

		labelCodigoPostalError = new JLabel(); labelCodigoPostalError.setIcon(iconCodigoPostalError); labelCodigoPostalError.setVisible(true); panelCodigoPostalError.add(labelCodigoPostalError);
		labelCodigoPostalError.setBounds(120, 5, 90, 90);
 
		botonCodigoPostalError = new JButton("OK");
		botonCodigoPostalError.setBounds(40, 60, 35, 30);
		botonCodigoPostalError.setBackground(new Color(189, 22, 22));
		botonCodigoPostalError.setForeground(new Color(234, 92, 92));
		panelCodigoPostalError.add(botonCodigoPostalError); 
		botonCodigoPostalError.addActionListener(this);


		ingresalabelCodigoPostal = new JLabel("*****");
		ingresalabelCodigoPostal.setBounds(5, 25, 110, 25);
		ingresalabelCodigoPostal.setVisible(true);
		
		ingresalabelCodigoPostal.setHorizontalAlignment(SwingConstants.CENTER);
		ingresalabelCodigoPostal.setBackground(new Color(249, 120, 172));
		ingresalabelCodigoPostal.setForeground(new Color(189, 22, 22));
		ingresalabelCodigoPostal.setBorder(BorderFactory.createEmptyBorder());
		ingresalabelCodigoPostal.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		panelCodigoPostalError.add(ingresalabelCodigoPostal);		
		
 

		ingresaCantidad = new JTextField("0");
		ingresaCantidad.setBounds(175, 20, 150, 30);
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

		ImageIcon iconLimpiar = new ImageIcon("resources/LimpiarTemperatura.png");
		botonLimpiar = new JButton(""); 
		botonLimpiar.setIcon(iconLimpiar); 
		botonLimpiar.setVisible(true); 
		botonLimpiar.setBounds(270, 210, 90, 90); 
		add(botonLimpiar); 
		botonLimpiar.addActionListener(this);

		labelCantidad = new JLabel(ingresaCantidad.getText());
		labelCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		labelCantidad.setVisible(true);
		labelCantidad.setBounds(5, 330, 80, 90);
		labelCantidad.setFont(new Font("Malgun Gothic", Font.BOLD, 30));
		add(labelCantidad);

		ImageIcon iconSol = new ImageIcon("resources/Sol.png");			
		labelMuestra_De = new JLabel("");
		labelMuestra_De.setIcon(iconSol);
		labelMuestra_De.setVisible(true);
		labelMuestra_De.setBounds(90, 330, 90, 90);
		add(labelMuestra_De);

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

		labelMuestra_A = new JLabel("");
		labelMuestra_A.setIcon(iconSol);
		labelMuestra_A.setVisible(true);
		labelMuestra_A.setBounds(290, 330, 90, 90);
		add(labelMuestra_A);

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
		labelNombre.setBounds(5, 430, 220, 20); 
		labelNombre.setFont(new Font("Malgun Gothic", Font.BOLD, 12)); 
		add(labelNombre);

		labelTiempo = new JLabel("api.openweathermap.org");
		labelTiempo.setHorizontalAlignment(SwingConstants.RIGHT); 
		labelTiempo.setVisible(true); 
		labelTiempo.setBounds(255, 432, 240, 15); 
		labelTiempo.setFont(new Font("Malgun Gothic", Font.BOLD, 11)); 
		add(labelTiempo);

		labelZip = new JLabel("app.zipcodebase.com");
		labelZip.setHorizontalAlignment(SwingConstants.RIGHT); 
		labelZip.setVisible(true); 
		labelZip.setBounds(255, 418, 240, 15); 
		labelZip.setFont(new Font("Malgun Gothic", Font.BOLD, 11)); 
		add(labelZip);

		labelIP = new JLabel("ip-api.com");
		labelIP.setHorizontalAlignment(SwingConstants.RIGHT); 
		labelIP.setVisible(true); 
		labelIP.setBounds(255, 404, 240, 15); 
		labelIP.setFont(new Font("Malgun Gothic", Font.BOLD, 11)); 
		add(labelIP);


		ImageIcon iconTemplado = new ImageIcon("resources/Templado.png");
		labelTemplado = new JLabel("");
		labelTemplado.setIcon(iconTemplado);
		labelTemplado.setVisible(false);
		labelTemplado.setBounds(7, 15, 30, 30);
		add(labelTemplado);

		ImageIcon iconFrio = new ImageIcon("resources/Frio.png");
		labelFrio = new JLabel("");
		labelFrio.setIcon(iconFrio);
		labelFrio.setVisible(false);
		labelFrio.setBounds(7, 15, 30, 30);
		add(labelFrio);


		ImageIcon iconCalor = new ImageIcon("resources/Calor.png");
		labelCalor = new JLabel("");
		labelCalor.setIcon(iconCalor);
		labelCalor.setVisible(false);
		labelCalor.setBounds(7, 15, 30, 30);
		add(labelCalor);

		labelTemperatura = new JLabel("");
		labelTemperatura.setHorizontalAlignment(SwingConstants.LEFT);
		labelTemperatura.setVisible(true);
		labelTemperatura.setBounds(42, 10, 100, 35);
		labelTemperatura.setFont(new Font("Malgun Gothic", Font.BOLD, 30));
		add(labelTemperatura);

		
		ImageIcon iconUbicacion = new ImageIcon("resources/UbicacionGif40.gif");
		botonUbicacion = new JButton("");
		botonUbicacion.setIcon(iconUbicacion);
		botonUbicacion.setVisible(true);
		botonUbicacion.setBounds(7, 50, 30, 30);
		add(botonUbicacion);
		botonUbicacion.addActionListener(this);		

		labelUbicacion = new JLabel("");
		labelUbicacion.setHorizontalAlignment(SwingConstants.LEFT);
		labelUbicacion.setVisible(true);
		labelUbicacion.setBounds(42, 50, 250, 30);
		labelUbicacion.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		add(labelUbicacion);


		ImageIcon iconSinWifi = new ImageIcon("resources/sinWifiTemperatura.png");
		botonSinWifi = new JButton("");
		botonSinWifi.setIcon(iconSinWifi);		
		botonSinWifi.setVisible(false);
		botonSinWifi.setBounds(10, 10, 80, 80);		
		add(botonSinWifi);
		botonSinWifi.addActionListener(this);

		//genera la ubicacion por medio de la IP
		ubicacionIp();
		//Da el estado del tiempo
		//updateWeather();


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


	public void updateWeather() { 

		try {	
			// Hacer una llamada a la API de OpenWeatherMap para obtener el estado del tiempo
			String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&APPID=" + apiKey;
			URL openWeatherMap = new URL(url);
			URLConnection connection = openWeatherMap.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				    response.append(line);
			}
			reader.close();

			// Parsear la respuesta JSON
			JSONObject weatherData = new JSONObject(response.toString());
			double temperatureKelvin = weatherData.getJSONObject("main").getDouble("temp");
			double temperatureCelsius = temperatureKelvin - 273.15;
			//System.out.println(weatherData);	

			
			if(temperatureCelsius < 10){
				labelTemplado.setVisible(false);
				labelFrio.setVisible(true);
				labelCalor.setVisible(false);
			} else if(temperatureCelsius > 28){
				labelTemplado.setVisible(false);
				labelFrio.setVisible(false);
				labelCalor.setVisible(true);
			} else {
				labelTemplado.setVisible(true);
				labelFrio.setVisible(false);
				labelCalor.setVisible(false);
			}

			// Formatear la temperatura en grados Celsius para que solo tenga un dígito después del punto
			String temperatureCelsiusFormatted = String.format("%.1f", temperatureCelsius);
			botonUbicacion.setVisible(true);
			botonSinWifi.setVisible(false);
			labelTemperatura.setText(temperatureCelsiusFormatted + "°C");
			labelUbicacion.setText(city + ", " + countryCode + ".");

		} catch  (IOException e) {
			labelTemperatura.setText("");
			labelUbicacion.setText("");
			botonUbicacion.setVisible(false);
			labelTemplado.setVisible(false);
			labelFrio.setVisible(false);
			labelCalor.setVisible(false);
			botonSinWifi.setVisible(true);
			//System.err.println("Se produjo un error al intentar obtener el estado del tiempo: " + e.getMessage());
		}
	}  

	private void ubicacionIp() {
		try {
			URL ipapi = new URL("http://ip-api.com/json");
			URLConnection connection = ipapi.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
			response.append(line);
			}
			reader.close();

				// Parsear la respuesta JSON
			JSONObject locationData = new JSONObject(response.toString());
			lat = locationData.getDouble("lat");
			lon = locationData.getDouble("lon");
			city = locationData.getString("city");
			countryCode = locationData.getString("countryCode");
			//System.out.println(locationData);
			//System.out.println("");


		} catch (IOException e) {
			botonUbicacion.setVisible(false);
			botonSinWifi.setVisible(true);
			//System.err.println("Se produjo un error al intentar obtener la ubicación del usuario: " + e.getMessage());
		}
	}

	private void ubicacionZip(){

		String codigiPostalString = ingresaCodigoPostal.getText();
			
		botonCelsius.setVisible(true);
		botonFahrenheit.setVisible(true);
		panelCodigoPostal.setVisible(false);
						

		String zipCode = codigiPostalString;			

		if (zipCode != null && !zipCode.isEmpty()) {
			try {
				// Obtener la ubicación del usuario utilizando la API de ZipCodeBase
				URL zipapi = new URL("https://app.zipcodebase.com/api/v1/search?apikey=" + apiKeyZipCodeBase + "&codes=" + zipCode + "&country=" + countryCode);
				URLConnection connection = zipapi.openConnection();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
				    response.append(line);
				}
				reader.close();

				// Parsear la respuesta JSON
				JSONObject locationData = new JSONObject(response.toString()).getJSONObject("results").getJSONArray(zipCode).getJSONObject(0);
				lat = locationData.getDouble("latitude");
				lon = locationData.getDouble("longitude");
				String province = locationData.getString("province");
				city = locationData.getString("province");
				if (province.isEmpty()) {
					city = locationData.getString("city");
				}
				//System.out.println(locationData);
				//System.out.println("");
					
			} catch (IOException e) {
				//System.err.println("Se produjo un error al intentar obtener el codigo postal: " + e.getMessage());
				ubicacionIp();					
					
			} catch (org.json.JSONException e){
				//System.err.println("Se produjo un error al intentar obtener los datos json: " + e.getMessage());
				panelCodigoPostalError.setVisible(true);
				botonCelsius.setVisible(false);
				botonFahrenheit.setVisible(false);
				ubicacionIp();					
	   	 	}
		} 
			// Actualizar el estado del tiempo
		updateWeather();
	}
		/*
		 * Para convertir de ºC a ºF use la fórmula: ºF = ºC x 1.8 + 32. Para convertir
		 * de ºF a ºC use la fórmula: ºC = (ºF-32) ÷ 1.8. Para convertir de K a ºC use
		 * la fórmula: ºC = K – 273.15 Para convertir de ºC a K use la fórmula: K = ºC +
		 * 273.15. Para convertir de ºF a K use la fórmula: K = 5/9 (ºF – 32) + 273.15.
		 * Para convertir de K a ºF use la fórmula: ºF = 1.8(K – 273.15) + 32.
		 */
	

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
				botonFahrenheit.setVisible(false);
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

	private void muestraDe(){
		labelMuestra_De.setVisible(false); labelCelsius_De.setVisible(false);labelFahrenheit_De.setVisible(false); labelKelvin_De.setVisible(false);

	}

	private void muestraA(){
		labelMuestra_A.setVisible(false); labelCelsius_A.setVisible(false); labelFahrenheit_A.setVisible(false); labelKelvin_A.setVisible(false);
	}

	private void limpiar() {
		muestraDe();
		labelMuestra_De.setVisible(true);
		generarString_De = " ";
		muestraA();
		labelMuestra_A.setVisible(true);
		generarString_A = " ";			
		ingresaCantidad.setText("0");
		ingresaCantidad.getDocument().addDocumentListener(this);ingresaCantidad.requestFocusInWindow();			
		panelError1.setVisible(false);
		labelResultadoTemp.setVisible(true);
		labelResultadoTemp.setText("0.00");			
	}

	private void cambiar(){

		String temp = generarString_De;
		generarString_De = generarString_A;
		generarString_A = temp;

			// Actualizar la visibilidad de las etiquetas
		labelCelsius_De.setVisible(generarString_De.equals("Celsius")); 
		labelFahrenheit_De.setVisible(generarString_De.equals("Fahrenheit"));
		labelKelvin_De.setVisible(generarString_De.equals("Kelvin")); 

		labelMuestra_De.setVisible(generarString_De.equals(" "));

		labelCelsius_A.setVisible(generarString_A.equals("Celsius")); 
		labelFahrenheit_A.setVisible(generarString_A.equals("Fahrenheit"));
		labelKelvin_A.setVisible(generarString_A.equals("Kelvin")); 

		labelMuestra_A.setVisible(generarString_A.equals(" "));

			//Inicia el método de resultados después de que se eligieron las dos temperaturas
		if (!labelMuestra_A.isVisible()) {
			actualizarResultados(); 			
		}
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


	public void actionPerformed(ActionEvent eventoEnMemoria) {

		ingresalabelCodigoPostal.setText("*" + ingresaCodigoPostal.getText() + "*");

		Object source = eventoEnMemoria.getSource();

		 if (source == botonCelsius){
			finalDeEtiqueta = "Celsius";
			actualizarEtiquetas();
		} else if (source == botonFahrenheit){
			finalDeEtiqueta = "Fahrenheit";
			actualizarEtiquetas();
		} else if (source == botonKelvin){
			finalDeEtiqueta = "Kelvin";
			actualizarEtiquetas();
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
			cambiar();

		}

		if (source == botonLimpiar){
			limpiar();
		}

		if (source == botonIgual){
			actualizarResultados();
		}

		if (source == botonJPanelError1){
			botonFahrenheit.setVisible(true);
			limpiar();
		}

		if (source == botonCodigoPostalError){			
			panelCodigoPostalError.setVisible(false);			
			botonCelsius.setVisible(true);
			botonFahrenheit.setVisible(true);
		}

		if (source == botonUbicacion){
			ingresaCodigoPostal.setText("");
			panelCodigoPostal.setVisible(true);
			botonCelsius.setVisible(false);
			botonFahrenheit.setVisible(false);

		}

		if (source == botonSinWifi) {
			updateWeather();
		}
				
		if(source == botonCodigoPostal){
			ubicacionZip();
			
		}		
	}
	

	public static void main(String args[]) {

		ImageIcon iconFormulario3 = new ImageIcon("resources/ConversorTemperaturaPortada.png");
		formulario3 = new ConversorDeTemperatura();

		//formulario3.updateWeather();


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
