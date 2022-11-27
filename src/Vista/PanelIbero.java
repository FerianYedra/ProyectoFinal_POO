package Vista;
import javax.swing.*;

/**
 * Clase que contiene el panel donde se coloca la imagen de la institución.
 * @author Fernando Yedra
 * @author Karla Obermeier
 * @date 26 de noviembre de 2022
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PanelIbero extends JPanel {
	public PanelIbero() {
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon("/home/im20fyl/Pictures/Ibero.png"));
		this.add(img);
	}
}
