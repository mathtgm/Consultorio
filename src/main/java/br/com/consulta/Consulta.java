package br.com.consulta;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Consulta {
	private int id_consulta;
	private Timestamp dataCadastro;
	private Timestamp dataConsulta;
	private String status;
	private int id_paciente;
	private int id_doutor;
	private String anotacao;
	
	public Consulta() {}
	
	public int getId_consulta() {
		return id_consulta;
	}
	
	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}
	
	public Timestamp getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Timestamp getDataConsulta() {
		return dataConsulta;
	}
	
	public String getDataHoraConsultaFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return sdf.format(dataCadastro);
	}
	
	public String getDataConsultaFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataCadastro);
	}
	
	public String getTimeConsultaFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		return sdf.format(dataCadastro);
	}
	
	public void setDataConsulta(Timestamp dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	
	public void converteDataHoraConsulta(String data, String hora) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try {
			this.dataConsulta = (Timestamp) sdf.parse(data + " " + hora);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getId_paciente() {
		return id_paciente;
	}
	
	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}
	
	public int getId_doutor() {
		return id_doutor;
	}
	
	public void setId_doutor(int id_doutor) {
		this.id_doutor = id_doutor;
	}
	
	public String getAnotacao() {
		return anotacao;
	}
	
	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}
	
}
