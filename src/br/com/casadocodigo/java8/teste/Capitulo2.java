package br.com.casadocodigo.java8.teste;

import java.util.Arrays;
import java.util.List;

import br.com.casadocodigo.java8.main.Usuario;

public class Capitulo2 {
	
	public static void main(String[] args) {
		Usuario user1 = new Usuario("Paulo Silvera", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silvera", 190);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		
	}

}
