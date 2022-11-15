package Vista;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PanelTitulo extends JPanel{
	public PanelTitulo() {
		this.setLayout(null);
		JLabel titulo = new JLabel("Análisis de Texto");
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		//Agregar imagen
		PanelIbero img = new PanelIbero();
		
		//Ajustar
		titulo.setBounds(200, 40, 300, 100);
		img.setBounds(700, 0, 500, 150);
		
		this.add(titulo);
		this.add(img);
	}

}
