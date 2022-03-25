package br.com.consulta;

import java.util.ArrayList;

public interface ConsultaInterface {
	
	public void gravarConsulta(Consulta consulta);
	
	public void alterarCosulta(Consulta consulta);
	
	public void excluirConsulta(int id_consulta);
	
	public Consulta consultarConsulta(int id_consulta);
	
	public ArrayList<Consulta> listaConsulta();
	
	public ArrayList<Consulta> listaConsultaMedico(int id_usuario);
	
	public ArrayList<Consulta> consultaDiaMedico(int id_usuario);
}
