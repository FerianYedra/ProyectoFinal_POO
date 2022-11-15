package Vista;
import javax.swing.*;

@SuppressWarnings("serial")
public class FramePrincipal extends JFrame {
	
	public PanelSgm1 panelPrueba;
	public PanelGrafica graf;
	
	public FramePrincipal() {
		//Inciciar Ventana
		super("Análisis de Texto");
		setSize(1250, 700);
		setLocation(250, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Agregar Paneles
		PanelTitulo titulo = new PanelTitulo();
		panelPrueba = new PanelSgm1();
		graf = new PanelGrafica();
		
		this.setLayout(null);
		//Posiciones
		titulo.setBounds(0,0, 10000, 200);
		panelPrueba.setBounds(200, 400, 320, 300);
		graf.setBounds(600, 250, 1000, 1000);
		
		//Agregar paneles
		this.add(titulo);
		this.add(panelPrueba);
		this.add(graf);
		
	}
}
