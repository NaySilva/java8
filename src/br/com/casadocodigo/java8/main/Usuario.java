package br.com.casadocodigo.java8.main;

public class Usuario {
	
	private String nome;
	private int pontos;
	private boolean moderador;
	
	public Usuario(String nome, int pontos) {
		this.pontos = pontos;
		this.nome = nome;
		this.moderador = false;
	}
	
	public Usuario(String nome, int pontos, boolean moderador) {
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = moderador;
	}



	public String getNome() {
		return nome;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public void tornaModerador() {
		this.moderador = true;
	}
	
	public boolean isModerador() {
		return moderador;
	}
	
	@Override
	public String toString() {
		return "Usuario " + nome;
	}

}
