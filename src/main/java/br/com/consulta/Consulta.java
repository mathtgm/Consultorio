package br.com.consulta;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Consulta {
	private int id_consulta;
	private Timestamp dataCadastro;
	private Timestamp dataConsulta;
	private int id_status;
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return sdf.format(dataConsulta);
	}
	
	public String getDataConsultaFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataConsulta);
	}
	
	public String getTimeConsultaFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(dataConsulta);
	}
	
	public void setDataConsulta(Timestamp dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	
	public void converteDataHoraConsulta(String data, String hora) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		try {
			Timestamp ts = new Timestamp(sdf.parse(data + " " + hora).getTime());
			this.dataConsulta = ts;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getId_status() {
		return id_status;
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
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
