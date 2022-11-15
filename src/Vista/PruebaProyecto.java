 package Vista;

import Controlador.Controlador;
import Modelo.Modelo;

public class PruebaProyecto {

	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		FramePrincipal frame = new FramePrincipal();
		Controlador controlador = new Controlador(modelo, frame);
		frame.setVisible(true);

	}

}
