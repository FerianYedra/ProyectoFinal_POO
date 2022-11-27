 package Vista;

import Controlador.Controlador;
import Modelo.Modelo;

/**
 * Clase que contiene el main del programa.
 * @author Fernando Yedra
 * @author Karla Obermeier
 * @date 26 de noviembre de 2022
 * @version 1.0
 */
public class PruebaProyecto {

	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		FramePrincipal frame = new FramePrincipal();
		Controlador controlador = new Controlador(modelo, frame);
		frame.setVisible(true);

	}

}
