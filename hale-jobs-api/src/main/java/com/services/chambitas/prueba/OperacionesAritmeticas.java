package com.services.chambitas.prueba;

public class OperacionesAritmeticas {

	double val1;
	double val2;
	
	public OperacionesAritmeticas(double val1, double val2) {
	   this.val1 = val1;
	   this.val2  = val2;
	}
	
	// Methods
	
	// Suma
	public double sum() throws Exception {
		
		if(val1 == 0 || val2 == 0) {
			throw new Exception("Los valores no pueden ser menores a 1");
		}
		
		return val1 + val2;
	}
	
	
	public double sum(double[] arrayNumbers) throws Exception {
		
		double sum = 0.0;

		for(double e : arrayNumbers) {
			sum += e;
		}
		
		return sum;
	}
	
	// Resta
	public double rest() throws Exception {

		if(val1 == 0 || val2 == 0) {
			throw new Exception("Los valores no pueden ser menores a 1");
		}
		
		return val2 - val1;
	}
	
	// Multiplicación
	public double multiplication() {
		return val1 * val2;
	}
	
	// División
	public double divide() {
		return val1 / val2;
	}
	
	// Modulo 
	public double module() {
		return val1 % val2;
	}
	
	
	
	
	
	
	
	

}
