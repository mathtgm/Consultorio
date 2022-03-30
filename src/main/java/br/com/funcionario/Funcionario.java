package br.com.funcionario;

import java.io.Serializable;

public class Funcionario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id_usuario;
	private String nome;
	private String usuario;
	private String senha;
	private String especializacao;
	private boolean status;
	private String documento;
	private int nivel_acesso;
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

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

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getNivel_acesso() {
		return nivel_acesso;
	}

	public void setNivel_acesso(int nivel_acesso) {
		this.nivel_acesso = nivel_acesso;
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}
	
}
