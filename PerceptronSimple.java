//-------------------------------------------------------------------
//					NORMALIZADO ENTRE 0 Y 1
//-------------------------------------------------------------------

import java.util.Random;

public class PerceptronSimple {
	private double[] weight;
	private double umbral;
	private double learnRate;
	private final double ERROR_ACEPTABLE;
	private final int MAX_ITERATION;
	
	public PerceptronSimple(int n){
		ERROR_ACEPTABLE = 0.01;
		MAX_ITERATION = 1000;
		weight = new double[n];
		learnRate = 0;
		randomInit(n);
	}
	
	private void randomInit(int n){
		Random r = null;
		double low = 0;
		double high = 1;
		for(int i = 0; i < n; i++){
			r = new Random();
			weight[i] = r.nextDouble() * (high - low) + low;
			//weight[i] = 0.5;
		}
		umbral = r.nextDouble() * (high - low) + low;
		//umbral = 0.5;
	}
	
	public void learn(double[][] samples){
		int control = 0; //muestras que sabe que están bien
		int time = 1;
		double errorPatron = 1000;
		double error = 1000;
		double sumaErrorPatrones = 0;
		double c = 100;
		double alpha = 0.01;
		int countFalsoPos = 0;
		int countFalsoNeg = 0;
		
		while(control < samples.length && time < MAX_ITERATION && error > ERROR_ACEPTABLE){
			learnRate = -1/(1 + Math.pow(Math.E, -alpha*(time-c))) + 1;
			//learnRate = 0.5;
			for (int i = 0; i < samples.length; i++){
				int actualOutput = output(samples[i]);
				int expectedOutput = (int) samples[i][samples[0].length - 1];
				if(actualOutput != expectedOutput){
					errorPatron = expectedOutput - actualOutput;
					for(int x = 0; x < weight.length; x++){
						weight[x] += learnRate * errorPatron * samples[i][x];
					}
					umbral += learnRate * errorPatron * (-1);
					for(int j = 0; j < samples.length; j++){
						expectedOutput = (int) samples[j][samples[0].length - 1];
						actualOutput = output(samples[j]);
						if(actualOutput != expectedOutput){
							if(expectedOutput == 0){
								countFalsoNeg++;
							}else{
								countFalsoPos++;
							}
						}
						errorPatron = expectedOutput - actualOutput;
						sumaErrorPatrones += Math.abs(errorPatron);
					}
					error = sumaErrorPatrones/(samples.length);
					int falsos = countFalsoPos + countFalsoNeg;
					System.out.println("Falsos: " + falsos + " El error después de cambiar los pesos es de: " + error + " instante: " + time);
					control = 0;
					sumaErrorPatrones = 0;
					countFalsoNeg = 0;
					countFalsoPos = 0;
					time++;
				}else{
					control++;
				}
				
			}
		}
	}
	
	private int output(double[] samples){
		double potencial = 0;
		
		for(int i = 0; i < weight.length; i++){
			potencial += weight[i] * samples[i];
		}
		potencial = potencial - umbral;
		if(potencial >= 0){
			return 1;
		}else{
			return 0;
		}
	}
}


/*

//-------------------------------------------------------------------
//					NORMALIZADO ENTRE -1 Y 1
//-------------------------------------------------------------------

import java.util.Random;

public class PerceptronSimple {
	private double[] weight;
	private double umbral;
	private double learnRate;
	private final double ERROR_ACEPTABLE;
	private final int MAX_ITERATION;
	
	public PerceptronSimple(int n){
		ERROR_ACEPTABLE = 0.01;
		MAX_ITERATION = 1000;
		weight = new double[n];
		learnRate = 0;
		randomInit(n);
	}
	
	private void randomInit(int n){
		Random r = null;
		double low = -1;
		double high = 1;
		for(int i = 0; i < n; i++){
			r = new Random();
			weight[i] = r.nextDouble() * (high - low) + low;
			//weight[i] = 0.5;
		}
		umbral = r.nextDouble() * (high - low) + low;
		//umbral = 0.5;
	}
	
	public void learn(double[][] samples){
		int control = 0; //muestras que sabe que están bien
		int time = 1;
		double errorPatron = 1000;
		double error = 1000;
		double sumaErrorPatrones = 0;
		double c = MAX_ITERATION;
		double alpha = 0.1;
		int countFalsoPos = 0;
		int countFalsoNeg = 0;
		
		while(control < samples.length && time < MAX_ITERATION && error > ERROR_ACEPTABLE){
			//learnRate = -1/(1 + Math.pow(Math.E, -alpha*(time-c))) + 1;
			learnRate = 1;
			for (int i = 0; i < samples.length; i++){
				int actualOutput = output(samples[i]);
				int expectedOutput = (int) samples[i][samples[0].length - 1];
				if(actualOutput != expectedOutput){
					errorPatron = expectedOutput - actualOutput;
					for(int x = 0; x < weight.length; x++){
						weight[x] += learnRate * expectedOutput * samples[i][x];
					}
					umbral += learnRate * expectedOutput * (-1);
					for(int j = 0; j < samples.length; j++){
						expectedOutput = (int) samples[j][samples[0].length - 1];
						actualOutput = output(samples[i]);
						if(actualOutput != expectedOutput){
							if(expectedOutput == 0){
								countFalsoNeg++;
							}else{
								countFalsoPos++;
							}
						}
						errorPatron = expectedOutput - actualOutput;
						sumaErrorPatrones += Math.abs(errorPatron);
					}
					error = sumaErrorPatrones/(2*samples.length);
					int falsos = countFalsoPos + countFalsoNeg;
					System.out.println("Falsos: " + falsos + " El error después de cambiar los pesos es de: " + error + " instante: " + time);
					control = 0;
					sumaErrorPatrones = 0;
					countFalsoNeg = 0;
					countFalsoPos = 0;
					time++;
				}else{
					control++;
				}
			}
		}
	}
	
	private int output(double[] samples){
		double potencial = 0;
		
		for(int i = 0; i < weight.length; i++){
			potencial += weight[i] * samples[i];
		}
		potencial = potencial - umbral;
		if(potencial >= 0){
			return 1;
		}else{
			return -1;
		}
	}
}
*/