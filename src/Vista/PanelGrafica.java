package Vista;
import javax.swing.*;

@SuppressWarnings("serial")
public class PanelGrafica extends JPanel{
	
	public JLabel graf;
	public JPanel grafica = new JPanel();
	
	public PanelGrafica() {
		this.setLayout(null);
		graf = new JLabel("Esperando Ruta");
		//graf.setIcon(new ImageIcon("/home/im20fyl/Documents/grafico.png"));
		
		
		graf.setBounds(50, 0, 500, 20);
		grafica.setBounds(0, 25, 650, 500);
		
		this.add(graf);
		this.add(grafica);
	}
}
