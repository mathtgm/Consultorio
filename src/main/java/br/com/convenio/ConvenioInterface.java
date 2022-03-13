package br.com.convenio;

import java.util.ArrayList;

public interface ConvenioInterface {

	public void cadastrarConvenio(Convenio convenio);
	
	public void alterarConvenio(Convenio convenio);
	
	public void excluirConvenio(int id_convenio);
	
	public ArrayList<Convenio> listarConvenio();
	
	public Convenio consultarConvenio(int id_convenio);
}
