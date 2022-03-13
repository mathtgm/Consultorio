package br.com.paciente;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.Serializable;

public class Paciente implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_paciente;
	private String nome;
	private String cpf;
	private Date datanasc;
	private int id_convenio;
	private String nomeConvenio;
	
	
	public Paciente() {
	}
	
	public Paciente(int id_paciente, String nome, String cpf, Date datanasc, int id_convenio) {
		this.id_paciente = id_paciente;
		this.nome = nome;
		this.cpf = cpf;
		this.datanasc = datanasc;
		this.id_convenio = id_convenio;
	}

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDatanasc() {
		return datanasc;
	}
	
	public String getDatanascBR() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(datanasc);
	}
	
	public void setDatanascDate(String datanasc) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.datanasc = (Date) sdf.parse(datanasc);
		} catch (ParseException e) {
			throw e;
		}
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}

	public int getId_convenio() {
		return id_convenio;
	}

	public void setId_convenio(int id_convenio) {
		this.id_convenio = id_convenio;
	}

	public String getNomeConvenio() {
		return nomeConvenio;
	}

	public void setNomeConvenio(String nomeConvenio) {
		this.nomeConvenio = nomeConvenio;
	}
	
	
}
