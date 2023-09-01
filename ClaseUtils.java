import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.text.DecimalFormat;


public class ClaseUtils{

	private int mouseX, mouseY;
	private boolean mousePressed;
    
	public ClaseUtils(JFrame frame) {
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				mousePressed = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			mousePressed = false;
			}
		});

		frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (mousePressed) {
					int deltaX = e.getX() - mouseX;
					int deltaY = e.getY() - mouseY;
					frame.setLocation(frame.getX() + deltaX, frame.getY() + deltaY);
				}
			}
		});
	}




		//Metodo para hacer que el el texto se adapte al temaño del resultado
	public static void autoResizeFont(JLabel label) {
    		Font labelFont = label.getFont();
    		String labelText = label.getText();
    
    		// Obtener el tamaño actual de la fuente
    		int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);

    		// Calcular el factor de escala necesario para que quepa en el tamaño máximo
    		double widthRatio = (double) label.getWidth() / (double) stringWidth;
    		int newFontSize = (int) (labelFont.getSize() * widthRatio);

   		 // Establecer el tamaño mínimo y máximo para la fuente (ajusta según tus necesidades)
    		int fontSizeMin = 12; // Tamaño mínimo de fuente en puntos
    		int fontSizeMax = 31; // Tamaño máximo de fuente en puntos

    			// Verificar que el nuevo tamaño de fuente no sea menor que el tamaño mínimo
		if (newFontSize < fontSizeMin) {
			newFontSize = fontSizeMin;
		}

		// Verificar que el nuevo tamaño de fuente no supere el tamaño máximo
		if (newFontSize > fontSizeMax) {
			newFontSize = fontSizeMax;
		}

		// Establecer la nueva fuente con el tamaño ajustado
		Font newFont = labelFont.deriveFont((float) newFontSize);
		label.setFont(newFont);
	}


	 public static String formatearNumero(Double numero) {
		// Creamos un formato para mostrar siempre 1 decimal (forzando el 0) si es entero y 4 decimales si no es entero
		DecimalFormat formato = numero % 1 == 0
		? new DecimalFormat("0.00")
		: new DecimalFormat("0.####"); // Usamos "0.####" para mostrar hasta 4 decimales
        
		// Formateamos el número
		String resultadoFormateado = formato.format(numero);
        
		 // Reemplazamos ".0" con "" si es entero
		if (resultadoFormateado.endsWith(".00")) {
		resultadoFormateado = resultadoFormateado.replace(".00", ".0");
		}
        
		// Reemplazamos ".####" con "" si los decimales son 0
		resultadoFormateado = resultadoFormateado.replace(".####", "");
        

        
		return resultadoFormateado;
	}





}
