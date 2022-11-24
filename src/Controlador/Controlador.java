package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import Modelo.Modelo;
import Vista.FramePrincipal;
import Vista.BarChart_AWT;

public class Controlador implements ActionListener{
	//Vista y modelo
	private Modelo modelo;
	private FramePrincipal vista;
	ArrayList<String> texto = new ArrayList<String>();
	ArrayList<String> palabras = new ArrayList<String>();
	
	//Constructor recibe objetos de tipo vista y controlador
	public Controlador(Modelo modelo, FramePrincipal vista) {
		this.modelo = modelo;
		this.vista = vista;
		vista.panelPrueba.bLeer.addActionListener(this);
		vista.panelPrueba.bBuscar.addActionListener(this);
		vista.panelPrueba.bArchivo.addActionListener(this);
		vista.panelPrueba.bClasif.addActionListener(this);
		vista.panelPrueba.bFrases.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String ruta = vista.panelPrueba.textfRuta.getText();
		Modelo mod = new Modelo(ruta);
		
		
		if(e.getSource() == vista.panelPrueba.bLeer) {
			//Aqui esta la parte MVC
			//Gestiona interacción modelo/vista
			try {
				
				if(ruta.isEmpty() || ruta == null) {
					JOptionPane.showMessageDialog(null, "Ruta vacía: Ingresar una ruta correcta","Error", JOptionPane.ERROR_MESSAGE);
				}else {
					//Leer Archivo
					texto = mod.leerArch(ruta);
					texto = mod.limpiarTexto(texto);
					vista.graf.graf.setText("Ruta: " + mod.getRuta());
					if(texto.isEmpty() != true) {
						JOptionPane.showMessageDialog(null, "Archivo Leido Correctamente","Archivo Leido", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Ruta vacía: Ingresar una ruta correcta","Error", JOptionPane.ERROR_MESSAGE);
					}
					//System.out.println(texto);
					//------------------------------------------------------------------------------------
				}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Ruta incorrecta","Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if (e.getSource() == vista.panelPrueba.bArchivo) {
			try {
				//Actualizar interfaz gráfica (gráfica
					palabras = mod.leerArch("/home/im20fyl/Documents/palabras.csv");
					System.out.println(palabras);
					for(int i = 0; i < palabras.size(); i++) {
						if(palabras.get(i) == "" || palabras.get(i).isEmpty()) {
							palabras.remove(i);
						}
					}
					
					ArrayList<String> palabrasTexto = new ArrayList<String>();
					palabrasTexto = mod.separarTexto(texto);
					System.out.println(palabras);
					
					ArrayList<Integer> contar = new ArrayList<Integer>();
					contar = mod.contarPalabras(palabras, palabrasTexto);
					//System.out.println(contar);
					
					BarChart_AWT graficador = new BarChart_AWT("Título", palabras, contar);
					vista.graf.grafica.removeAll();
					vista.graf.grafica.add(graficador.createDemoPanel());
					vista.graf.grafica.updateUI();
				//}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error cargando gráfica","Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if (e.getSource() == vista.panelPrueba.bBuscar) {
			//Aqui tiene que buscar una sola palabra
			try {
				String buscar = vista.panelPrueba.textfPal.getText();
				System.out.println("Bucsar");
				if(buscar.isEmpty() || buscar == null) {
					JOptionPane.showMessageDialog(null, "Error: campo vacío","Error", JOptionPane.ERROR_MESSAGE);
				}else {
					ArrayList<String> palabras = new ArrayList<String>();
					palabras.add(buscar);
					
					ArrayList<String> palabrasTexto = new ArrayList<String>();
					palabrasTexto = mod.separarTexto(texto);
					
					ArrayList<Integer> contar = new ArrayList<Integer>();
					contar = mod.contarPalabras(palabras, palabrasTexto);
					//System.out.println(contar);
					
					BarChart_AWT graficador = new BarChart_AWT("Título", palabras, contar);
					vista.graf.grafica.removeAll();
					vista.graf.grafica.add(graficador.createDemoPanel());
					vista.graf.grafica.updateUI();
				}
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error cargando gráfica","Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == vista.panelPrueba.bClasif) {
			//Accion tras apretar botón de clasificar
			try {
				String frase = vista.panelPrueba.textfFrase.getText();
				String category = ""; 
				System.out.println("---------------------Frase recuperada---------------------");
				if(frase.isEmpty() || frase == null) {
					JOptionPane.showMessageDialog(null, "Error: campo vacío","Error", JOptionPane.ERROR_MESSAGE);
				}else {
					System.out.println("---------------------Categorizando frase--------------------");
					category = mod.categorizar(frase);
					System.out.println("---------------------Frase categorizada---------------------");
					vista.graf.grafica.removeAll();
					vista.graf.graf.setText("Categoria: " + category);
					vista.graf.grafica.updateUI();
				}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al clasificar","Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if (e.getSource() == vista.panelPrueba.bFrases) {
			//Accion tras apretar un botón de clasificar
			try {
				System.out.println("---------------------Preparando OpenNLP...---------------------");
				mod.openNLP();
				System.out.println("---------------------OpenNLP Listo---------------------");
				JOptionPane.showMessageDialog(null, "Listo para claseificar la frase","Clasificar", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al leer el archivo para clasificar", "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
	}
}
