package br.com.casadocodigo.java8.teste;

import br.com.casadocodigo.java8.main.Validador;

public class Capitulo3 {
	
	public static void main(String[] args) {
		
		Validador<String> validadorCEP = valor -> valor.matches("[0-9]{5}-[0-9]{3}");
		
		validadorCEP.valida("04101-300");
		System.out.println(validadorCEP.getClass());
		
		int numero = 5;
		
		new Thread(() -> System.out.println(numero)).start();
		
		
	}

}
