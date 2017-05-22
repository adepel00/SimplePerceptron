import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//-------------------------------------------------------------------
		//					DATOS INICIALES DE AND
		//-------------------------------------------------------------------
		//double[][] samples = {{0,0,0}, {0,1,0}, {1,0,0}, {1,1,1}};
		//double[][] samples = {{-1,-1,-1}, {-1,1,-1}, {1,-1,-1}, {1,1,1}};
		//-------------------------------------------------------------------

		//-------------------------------------------------------------------
		//				LECTURA DE MUESTRAS DE APENDICITIS
		//-------------------------------------------------------------------
		
		File file = new File("appendicitis.dat");
        Scanner inFile = null;
		try {
			inFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        ArrayList<String> appendicitisLines = new ArrayList<String>();
        int countLines = 0;
        while (inFile.hasNextLine()){
        	appendicitisLines.add(inFile.nextLine());
        	countLines++;
        }
        String[] parts = appendicitisLines.get(0).split(",");
        double[][] appendicitisSamples = new double[countLines][parts.length];
        for(int i = 0; i < countLines; i++){
        	parts = appendicitisLines.get(i).split(",");
        	for(int j = 0; j < parts.length; j++){
        		appendicitisSamples[i][j] = Double.parseDouble(parts[j]);
        	}
        }
        
        //-------------------------------------------------------------------
        //					ENTRENAMIENTO CON AND
		//-------------------------------------------------------------------
        //PerceptronSimple perceptron = new PerceptronSimple(2);
        //perceptron.learn(samples);
        
        //-------------------------------------------------------------------
        //					ENTRENAMIENTO CON APENDICITIS
		//-------------------------------------------------------------------
        PerceptronSimple perceptron = new PerceptronSimple(parts.length - 1);
		perceptron.learn(appendicitisSamples);
	}

}
