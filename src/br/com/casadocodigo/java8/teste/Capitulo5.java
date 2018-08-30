package br.com.casadocodigo.java8.teste;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparing;
import java.util.List;

import br.com.casadocodigo.java8.main.Usuario;

public class Capitulo5 {
	
	public static void main(String[] args) {
		

		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Nayara Silva", 190);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		usuarios.sort(comparing(u -> u.getNome()));
		
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		
		List<String> palavras = Arrays.asList("Casa do Código","Alura","caelum");
		
		palavras.sort(Comparator.naturalOrder());
		
		palavras.forEach(s -> System.out.println(s));
		
		
		usuarios.sort(comparing(u -> u.getPontos()));
		
	}
}
