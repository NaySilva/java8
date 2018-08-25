package br.com.casadocodigo.java8.main;

import java.util.function.Consumer;

public class Mostrador implements Consumer<Usuario>{

	@Override
	public void accept(Usuario t) {
		System.out.println(t.getNome());
		
	}

}
