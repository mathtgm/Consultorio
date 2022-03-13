package br.com.convenio;

public class Convenio {
	
	 private int id_convenio;
	 private String nome;
	 
	 public Convenio() {}
	 
	 public Convenio(int id_convenio, String nome) {
		super();
		this.id_convenio = id_convenio;
		this.nome = nome;
	}

	public int getId_convenio() {
		return id_convenio;
	}
	
	public void setId_convenio(int id_convenio) {
		this.id_convenio = id_convenio;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
