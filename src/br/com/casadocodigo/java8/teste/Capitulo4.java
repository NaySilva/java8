package br.com.casadocodigo.java8.teste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import br.com.casadocodigo.java8.main.Usuario;

public class Capitulo4 {

	public static void main(String[] args) {
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Nayara Silva", 190);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");
		
		Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());
	
		usuarios.forEach(mostraMensagem.andThen(imprimeNome));
		
		List<Usuario> userArray = new ArrayList<>();
		userArray.add(user1);
		userArray.add(user2);
		userArray.add(user3);
		
		System.out.println("\nTestando removeIf\n");
		
		userArray.removeIf(u -> u.getPontos() > 160);
		
		userArray.forEach(imprimeNome);
		
	}
	
}
