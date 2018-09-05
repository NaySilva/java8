package br.com.casadocodigo.java8.teste;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import static java.util.Comparator.comparing;

import br.com.casadocodigo.java8.main.Usuario;

public class Capitulo9 {
	
	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Usuario user1 = new Usuario("Paulo Silveira", 150, true);
		Usuario user2 = new Usuario("Rodrigo Turini", 150, true);
		Usuario user3 = new Usuario("Nayara Silva", 90);
		Usuario user4 = new Usuario("Sergio Lopes", 120);
		Usuario user5 = new Usuario("Adriano Almeida", 100);
		
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);
		
		Map<Path, Long> lines = Files
				.list(Paths.get("./src/br/com/casadocodigo/java8/main"))
				.filter(p -> p.toString().endsWith(".java"))
				.collect(Collectors.toMap(
						Function.identity(),
						p -> lines(p).count()));
		
		System.out.println(lines);
		
		Map<Path, List<String>> content = Files
				.list(Paths.get("./src/br/com/casadocodigo/java8/main"))
				.filter(p -> p.toString().endsWith(".java"))
				.collect(Collectors.toMap(
						Function.identity(), 
						p -> lines(p).collect(Collectors.toList())));
		
		System.out.println(content);
		
		Map<String, Usuario> nomeToUser = usuarios
				.stream()
				.collect(Collectors.toMap(
						Usuario::getNome,
						Function.identity()));
		
		System.out.println(nomeToUser);
		
		Map<Integer, List<Usuario>> pontuacao = usuarios
				.stream()
				.collect(Collectors.groupingBy(Usuario::getPontos));
		
		System.out.println(pontuacao);
		
		Map<Boolean, List<Usuario>> modeladores = usuarios
				.stream()
				.collect(Collectors.partitioningBy(Usuario::isModerador));
		
		System.out.println(modeladores);

		
		Map<Boolean, List<String>> nomePorTipo = usuarios
				.stream()
				.collect(Collectors.partitioningBy(
						Usuario::isModerador,
						Collectors.mapping(
								Usuario::getNome, 
								Collectors.toList())));
		
		System.out.println(nomePorTipo);
	
		Map<Boolean, Integer> pontuacaoPorTipo = usuarios
				.stream()
				.collect(Collectors.partitioningBy(
						Usuario::isModerador,
						Collectors.summingInt(
								Usuario::getPontos)));
		
		System.out.println(pontuacaoPorTipo);
		
		String nomes = usuarios
				.stream()
				.map(Usuario::getNome)
				.collect(Collectors.joining(", "));
		
		System.out.println(nomes);
		
		List<Usuario> filtradosOrdenados = usuarios.parallelStream()
				.filter(u -> u.getPontos() > 100)
				.sorted(comparing(Usuario::getNome))
				.collect(Collectors.toList());
		
		System.out.println(filtradosOrdenados);
		
		long sum = LongStream.range(0, 1_000_000_000)
				.parallel()
				.filter(x -> x % 2 == 0)
				.sum();
		System.out.println(sum);
		
		
		
		
	}

}
