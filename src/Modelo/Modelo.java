package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import opennlp.tools.doccat.BagOfWordsFeatureGenerator;
import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.doccat.FeatureGenerator;
import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerFactory;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.ModelUtil;


/**
 * Clase del modelo del proyecto final, en esta clase se encuentran todos métodos que tuiliza el controlador, puede guardar la ruta de un archivo.
 * @author Fernando Yedra
 * @author Karla Obermeier
 * @date 26 de noviembre del 2022
 * @version 1.0
 */
public class Modelo {
	public static DocumentCategorizerME myCategorizer;
	
	private String ruta;
	
	/**
	 * Constructor del modelo que no recibe nada. 
	 */
	public Modelo() {
		
	}

	/**
	 * Constructor del modelo el cual recibe la ruta y la guarda.
	 */	
	public Modelo(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Método que regresa la ruta del modelo
	 * @return ruta Contiene la ruta guardada.
	 */
	public String getRuta () {
		return ruta;
	}
	
	/**
	 * Método que lee las lineas de un archivo csv y las guarda en un Array List.
	 * @param ruta Contiene la ruta donde se encuentra el archivo a leer.
	 * @return list Array List con las lineas leaidas del archivo.
	 */
	public ArrayList<String> leerArch(String ruta){
		ArrayList<String> list = new ArrayList<String>();
		try {
            @SuppressWarnings("resource")
			Scanner csvData = new Scanner(new File(ruta));
            while(csvData.hasNext()) {
                list.add(csvData.nextLine());
            }
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
		return list;
	}
	
	/**
	 * Método que separa un String en palabras (Separadas por espacios) y las regresa como un Array List.
	 * @param palabrasBusc String a separar en palabras.
	 * @return palabras Palabras del string separadas.
	 */
	public ArrayList<String> obtenerPalabras(String palabrasBusc){
		ArrayList<String> palabras = new ArrayList<String>();
		
		String[] partes = palabrasBusc.split(" ");
		for(int i = 0; i < partes.length; i++) {
			palabras.add(partes[i]);
		}
		
		return palabras;
	}
	
	/**
	 * Método que cuenta la cantidad de veces que encuentra las palabras de un Array List de palabras en un Array List de frases.
	 * @param palabras ArrayList de palabras a buscar.
	 * @param texto ArrayList con las frases en las que se van a buscar las palabras.
	 * @return coincidencias ArrayList de enteros que contiene las veces que se encontrarón las palabras en el mismo orden que las palabras que se le mandan.
	 */
	public ArrayList<Integer> contarPalabras(ArrayList<String> palabras, ArrayList<String> texto){
		ArrayList<Integer> coincidencias = new ArrayList<Integer>();
		int contador = 0;
		
		for(int i = 0; i < palabras.size(); i++) {
			contador = 0;
			for(int j = 0; j < texto.size(); j++) {
				if(texto.get(j).contains(palabras.get(i))) {
					//System.out.println("coincidencia en pal " + i + " linea " + j);
					contador++;
				}
				
			}
			coincidencias.add(contador);
		} 
		return coincidencias;
	}
	
	/**
	 * Método que separa en palabras el texto de un Array List.
	 * @param texto ArraList con las frases que se van a separar.
	 * @return partes ArrayList con las palabras separadas.
	 */
	public ArrayList<String> separarTexto (ArrayList<String> texto){
		ArrayList<String> partes = new ArrayList<String>();
		
		for(int i = 0; i < texto.size(); i++) {
			String[] part = texto.get(i).split(" ");
			
			for(int j = 0; j < part.length; j++) {
				partes.add(part[j]);
			}
		}
		
		return partes;
	}
	
	/**
	 * Método que tokeniza una frase.
	 * @param frase Frase a ser tokenizada.
	 * @throws Exception Error al tokenizar.
	 */
	public void tokenizer(String frase) throws Exception{
		/**-----------------------------------------------------------------------------------------------------_**/
		// Archivo con ejemplo de tokenization. Se carga un modelo. 
				// formato OpenNLP contiene una oración por línea.
				//Los tokens están separados por un espacio en blanco o por una etiqueta especial <SPLIT>
				InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File("/home/im20fyl/Documents/TokenizerFiles/tokenizerdata.txt"));
				// Se genera un objeto 
				ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
				ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);
		 
				
				// TokenizerFactory Divide una frase en palabras
				// 1.- Se identifican los límites de las oraciones
				// 2.- tokens dentro de una oración. 
				TokenizerFactory factory = new TokenizerFactory("en", null, false, null);
				// Este es el método que entrena 
				TokenizerModel model = TokenizerME.train(sampleStream, factory, TrainingParameters.defaultParams());
		 
			   // guarda el modelo entrenado. 
				model.serialize(new File("/home/im20fyl/Documents/TokenizerFiles/tokenizermodel.bin"));
		 
				/**
				 * Lets tokenize
				 */
				try (Scanner scanner = new Scanner(System.in)) {
		 
					//while (true) {
						// Get inputs in loop
						//System.out.println("Enter a sentence to tokenize:");
		             String texto = frase;
						// Initialize tokenizer tool
						TokenizerME myCategorizer = new TokenizerME(model);
		 
						// Tokenize sentence.
						String[] tokens = myCategorizer.tokenize(texto);
						for (String t : tokens) {
							System.out.println("Tokens: " + t);
						}
		 
					//}
		 
				} catch (Exception e) {
					//e.printStackTrace();
				}
		 
	}
	
	/**
	 * Método que limpia un texto.
	 * @param texto ArrayList con las frases a limpiar.
	 * @return tLimpio ArrayList con el texto limpio.
	 */
	public ArrayList<String> limpiarTexto(ArrayList<String> texto){
		ArrayList<String> tLimpio = new ArrayList<String>();
		
		String linea;
		
		for(int i = 0; i < texto.size(); i++) {
			linea = texto.get(i);
			
			linea = linea.replaceAll("\\d", "");
			linea = linea.replaceAll("[\t]", "");
			linea = linea.replaceAll("é", "e");
			linea = linea.replaceAll("ó", "o");
			linea = linea.replaceAll("í", "i");
			linea = linea.replaceAll("[^a-zA-Z0-9\\s]", "");
			linea = linea.replaceAll("([A-Z])", "$1").toLowerCase();
			
			tLimpio.add(linea);
		}
		
		return tLimpio;
	}
	
	/**
	 * Método que se encarga de haccer la categorización de la frase.
	 */
	public void openNLP() throws Exception {
		 
		// Calsifica texto en categorías predefinidas. Basado en la máxima entropía. 
		 
		// Archivo con los ejemplo de clasificación. Este es el modelo
		InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File("/home/im20fyl/Documents/TokenizerFiles/documentcategorizer.txt"));
		ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
		ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);
 
		// CUT_OFF, como 0, se utilizan pocas muestras. 
		// BagOfWordsFeatureGenerator Utiliza las palabras del model

		TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
		params.put(TrainingParameters.CUTOFF_PARAM, 0);
		DoccatFactory factory = new DoccatFactory(new FeatureGenerator[] { new BagOfWordsFeatureGenerator() });
 
		// Aquí comienza el entrenamiento del modelo. 
		DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, factory);
 
		
		// Archivo que se carga directamente al modelo, se reutiliza. 
		model.serialize(new File("/home/im20fyl/Documents/TokenizerFiles/documentcategorizer.bin"));
 
		
		// Se carga el modelo entrenado
		try (InputStream modelIn = new FileInputStream("/home/im20fyl/Documents/TokenizerFiles/documentcategorizer.bin");
				Scanner scanner = new Scanner(System.in);) {
 
				// categorización
				myCategorizer = new DocumentCategorizerME(model);
 
				// Frase a categorizar.
				
 
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que termina la categorización de la frase.
	 * @param frase Frase que se va a categorizar.
	 * @return category La categoría determinada de la frase.
	 */
	public String categorizar(String frase) {
		System.out.println("Entro a categorizar\n" + frase);
		double[] probabilitiesOfOutcomes = myCategorizer.categorize(getTokens(frase));
		 
		System.out.println("obtener probabilidades");
		// Se obtiene la categoría de la frase ingresada.
		String category = myCategorizer.getBestCategory(probabilitiesOfOutcomes);
		System.out.println("Category: " + category);
		
		return category;
	}
	
	/**
	 * Método que tokeniza la frase para el categorizador.
	 * @param frase Es la frase que se va a tokenizar.
	 * @return tokens Son los tokens generados.
	 */
	public String[] getTokens (String frase) {
		// Use model that was created in earlier tokenizer tutorial
				try (InputStream modelIn = new FileInputStream("/home/im20fyl/Documents/TokenizerFiles/tokenizermodel.bin")) {
					
					TokenizerME myCategorizer = new TokenizerME(new TokenizerModel(modelIn));
		 
					String[] tokens = myCategorizer.tokenize(frase);
		 
					for (String t : tokens) {
						System.out.println("Tokens: " + t);
					}
					System.out.println("Regresar tokens");
					return tokens;
		 
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		return null;
	}
}
