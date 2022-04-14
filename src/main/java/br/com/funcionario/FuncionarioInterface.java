package br.com.funcionario;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface FuncionarioInterface {
	
	public Funcionario setFuncionario(ResultSet rs);
	
	public void gravarFuncionario(Funcionario funcionario);
	
	public void alterarFuncionario(Funcionario funcionario);
	
	public void excluirFuncionario(int id);
	
	public Funcionario consultarFuncionario(int id_funcionario);
	
	public Funcionario autenticaFuncionario(String usuario, String senha);
	
	public ArrayList<Funcionario> listarFuncionario();
	
	public ArrayList<Funcionario> listarFuncionarioDoutores();
}
