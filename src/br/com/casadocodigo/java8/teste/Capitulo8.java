package br.com.casadocodigo.java8.teste;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.casadocodigo.java8.main.Fibonacci;
import br.com.casadocodigo.java8.main.Grupo;
import br.com.casadocodigo.java8.main.Usuario;

public class Capitulo8 {
	
	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 150);
		Usuario user3 = new Usuario("Nayara Silva", 90);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.sorted(comparing(Usuario::getNome))
				.peek(System.out::println)
				.findAny();
		
		
		int somaPontos = usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.reduce(0, Integer::sum);
		
		System.out.println(somaPontos);
	
		Random random = new Random(0);
		
		IntStream
			.generate(() -> random.nextInt())
			.limit(100)
			.boxed()
			.collect(Collectors.toList());
		
		IntStream.generate(new Fibonacci())
			.limit(10)
			.forEach(System.out::println);
		
		
		int maiorQue100 = IntStream
				.generate(new Fibonacci())
				.filter(f -> f > 100)
				.findFirst()
				.getAsInt();
		
		System.out.println(maiorQue100);
		
		
		Files.list(Paths.get("./src/br/com/casadocodigo/java8/main"))
			.filter(p -> p.toString().endsWith(".java"))
			.flatMap(p -> lines(p))
			.flatMapToInt((String s) -> s.chars());
		
		Grupo englishSpeakers = new Grupo();
		englishSpeakers.add(user1);
		englishSpeakers.add(user2);
		
		Grupo spanishSpeakers = new Grupo();
		
		spanishSpeakers.add(user2);
		spanishSpeakers.add(user3);
		
		List<Grupo> groups = Arrays.asList(englishSpeakers, spanishSpeakers);
		
		groups.stream()
			.flatMap(g -> g.getUsuarios().stream())
			.distinct()
			.forEach(System.out::println);
		
	}

}
