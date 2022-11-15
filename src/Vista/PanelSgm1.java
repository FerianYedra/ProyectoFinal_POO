package Vista;
import javax.swing.*;

@SuppressWarnings("serial")
public class PanelSgm1 extends JPanel{
	
	public JButton bLeer;
	public JTextField textfRuta;
	public JButton bBuscar;
	public JTextField textfPal;
	public JButton bArchivo;
	public JButton bClasif;
	public JTextField textfFrase;
	public JButton bFrases;
	
	public PanelSgm1(){
		this.setLayout(null);
		
		//Iniciar elementos
		textfRuta = new JTextField("/home/im20fyl/Documents/final_book_dataset_kaggle.csv");
		textfPal = new JTextField("Ingresa palabra");
		textfFrase = new JTextField("Ingresa frase");
		bLeer = new JButton("Leer");
		bArchivo = new JButton("Archivo palabras a buscar");
		bBuscar = new JButton("Buscar");
		bFrases = new JButton("Archivo frases claseificar");
		bClasif = new JButton("Clasificar");
		
		
		//Colocar
		textfRuta.setBounds(0, 0, 200, 25);
		bLeer.setBounds(210, 0, 100, 25);
		bArchivo.setBounds(0, 40, 220, 25);
		textfPal.setBounds(0, 80, 200, 25);
		bBuscar.setBounds(210, 80, 100, 25);
		bFrases.setBounds(0, 120, 220, 25);
		textfFrase.setBounds(0, 160, 200, 25);
		bClasif.setBounds(210, 160, 100, 25);
		
		//Añadir
		this.add(textfRuta);
		this.add(bLeer);
		this.add(bArchivo);
		this.add(textfPal);
		this.add(bBuscar);
		this.add(bFrases);
		this.add(textfFrase);
		this.add(bClasif);

	}
}
