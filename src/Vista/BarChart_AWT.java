package Vista;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart_AWT extends ApplicationFrame {
   static ArrayList<String> datosLoc;
   static ArrayList<Integer> contadorLoc;
	
   public BarChart_AWT( String applicationTitle, ArrayList<String> datos, ArrayList<Integer> contar) {
      super( applicationTitle );     //Título  
      datosLoc = datos;
      contadorLoc = contar;
      JPanel content = createDemoPanel();
      content.setPreferredSize(new java.awt.Dimension(700, 700));
      getContentPane().add(content);  
      
   }
   
   public static JPanel createDemoPanel() {
   
	   JFreeChart barChart = ChartFactory.createBarChart(
			   "Frecuencia de las Palabras",           
		         "N° de Veces Encontrada",            
		         "Número",            
		         createDataset(),          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
		         
		      ChartPanel chartPanel = new ChartPanel( barChart );        
		      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );   
		      return chartPanel;
		      
   }
   
   
   
   
   private static CategoryDataset createDataset() {
      final String numPalabras = "Numero Palabras";        
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      
      for(int i = 0; i < datosLoc.size(); i++) {
    	  dataset.addValue(contadorLoc.get(i), numPalabras, datosLoc.get(i));
      }   

      return dataset; 
   }
   
   public static void main( String[ ] args ) {
      BarChart_AWT chart = new BarChart_AWT("Título de la ventana", datosLoc, contadorLoc);        ;
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   } 
}