package com.services.chambitas.prueba;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
		
		// Operación de suma
		OperacionesAritmeticas a = new OperacionesAritmeticas(50, 10);
		System.out.println(a.sum());
			
		// Operación de resta
		OperacionesAritmeticas b = new OperacionesAritmeticas(50, 10);
		System.out.println(b.rest());	
		
		// Operación de multiplicación
		OperacionesAritmeticas c = new OperacionesAritmeticas(50, 10);
		System.out.println(c.divide());
		
		// Operación de división
		OperacionesAritmeticas d = new OperacionesAritmeticas(50, 10);
		System.out.println(d.multiplication());
		
		// Operación de modulo
		OperacionesAritmeticas e = new OperacionesAritmeticas(50, 80);
		System.out.println(e.module());
		
		// Operación de suma sobre cargada:
		System.out.println(e.sum(new double[]{10.0,20.0,30.2,40.2}));
		
	}

}
