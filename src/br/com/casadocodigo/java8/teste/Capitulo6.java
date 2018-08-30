package br.com.casadocodigo.java8.teste;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparing;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

import br.com.casadocodigo.java8.main.Usuario;

public class Capitulo6 {
	
	public static void main(String[] args) {
		

		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 150);
		Usuario user3 = new Usuario("Nayara Silva", 190);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		usuarios.forEach(Usuario::tornaModerador);
		
		usuarios.sort(Comparator.nullsLast(
				comparing(Usuario::getPontos)
				.thenComparing(Usuario::getNome))
				.reversed());
		
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		Runnable bloco1 = user1::tornaModerador;
		bloco1.run();
		
		Consumer<Usuario> consumer = Usuario::tornaModerador;
		consumer.accept(user1);
		
		usuarios.forEach(System.out::println);
		
		BiFunction<String, Integer, Usuario> criadorDeUsuarios = Usuario::new;
		Usuario user4 = criadorDeUsuarios.apply("Novo user", 50);
		
		System.out.println(user4);
		
	}

}
