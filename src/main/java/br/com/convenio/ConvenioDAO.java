package br.com.convenio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.conexaoDB.ConexaoDB;

public class ConvenioDAO  implements ConvenioInterface{
	
	private Connection connection = new ConexaoDB().getConexao();

	//Cadastrar convenio no banco de dados
	@Override
	public void cadastrarConvenio(Convenio convenio) {
		try {
			String sql = "INSERT INTO convenio (nome) VALUES(?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, convenio.getNome());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	//Alterar as informações do convenio no banco de dados
	@Override
	public void alterarConvenio(Convenio convenio) {
		try {
			String sql = "UPDATE convenio SET nome = ? WHERE id_convenio = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, convenio.getNome());
			ps.setInt(2, convenio.getId_convenio());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Excluir o convenio do banco de dados pela ID
	@Override
	public void excluirConvenio(int id_convenio) {
		try {
			String sql = "DELETE FROM convenio WHERE id_convenio = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id_convenio);
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
			ps.close();
			return listConvenio;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Convenio consultarConvenio(int id_convenio) {
		try {
			String sql = "SELECT * FROM convenio WHERE id_convenio = ?";
			Convenio convenio = new Convenio();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs;
			ps.setInt(1, id_convenio);
			rs = ps.executeQuery();
			if(rs.next()) {
				convenio.setId_convenio(id_convenio);
				convenio.setNome(rs.getString("nome"));
			}
			
			ps.close();
			return convenio;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
