package br.com.casadocodigo.java8.main;

@FunctionalInterface
public interface Validador<T> {
	boolean valida(T t);

}
