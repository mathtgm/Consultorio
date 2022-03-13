package br.com.paciente;

import java.util.List;

public interface PacienteInterface {
	
	public boolean gravarPaciente(Paciente paciente);
	
	public boolean alterarPaciente(Paciente paciente);
	
	public boolean excluirPaciente(int id);
	
	public List<Paciente> listarPaciente();
}
