package br.com.convenio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.conexaoDB.ConexaoDB;
import br.com.paciente.Paciente;

public class ConvenioDAO  implements ConvenioInterface{
	
	private Connection connection = new ConexaoDB().getConexao();

	@Override
	public void cadastrarConvenio(Convenio convenio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarConvenio(Convenio convenio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirConvenio(int id_convenio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Convenio> listarConvenio() {
		try {
			String sql = "SELECT * FROM convenio";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs;
			ArrayList<Convenio> listConvenio = new ArrayList<Convenio>();
			rs = ps.executeQuery();
			while(rs.next()) {
				Convenio convenio =  new Convenio();
				convenio.setNome(rs.getString("nome"));
				convenio.setId_convenio(rs.getInt("id_convenio"));
				listConvenio.add(convenio);
			}
			return listConvenio;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Convenio consultarConvenio(int id_convenio) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
