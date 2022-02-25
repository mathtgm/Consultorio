package br.com.funcionario;

import java.io.Serializable;

public class Funcionario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private String usuario;
	private String senha;	
	
	public String getSenha() {
		return senha;
	}

	public Funcionario() {
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
