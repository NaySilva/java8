package br.com.casadocodigo.java8.teste;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import br.com.casadocodigo.java8.main.Usuario;

public class Capitulo7 {
	
	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 150);
		Usuario user3 = new Usuario("Nayara Silva", 90);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		usuarios.sort(comparing(Usuario::getPontos).reversed());
		usuarios.subList(0,3).forEach(Usuario::tornaModerador);
		
		usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.forEach(System.out::println);
		
		usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.collect(toList());
		
		List<Integer> pontos = usuarios.stream()
				.map(Usuario::getPontos)
				.collect(toList());
		
		pontos.forEach(System.out::println);
		
		double pontuacaoMedia = usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.average()
				.getAsDouble();
		
		System.out.println(pontuacaoMedia);
		
		OptionalDouble media = usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.average();
		
		double pontuacaoMedia2 = media.orElse(0.0);
		
		System.out.println(pontuacaoMedia2);
		
		double pontuacaoErro = media
				.orElseThrow(IllegalStateException::new);
		
		System.out.println(pontuacaoErro);
		
		Optional<String> maxNome = usuarios
				.stream()
				.max(comparing(Usuario::getPontos))
				.map(Usuario::getNome);
		
		System.out.println(maxNome);
		
	}

}
