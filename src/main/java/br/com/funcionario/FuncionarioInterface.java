package br.com.funcionario;

public interface FuncionarioInterface {
	
	public boolean autenticarFuncionario(Funcionario funcionario);
	
	public boolean gravarFuncionario(Funcionario funcionario);
	
	public boolean alterarFuncionario(Funcionario funcionario);
	
	public boolean excluirFUncionario(int id);
}
