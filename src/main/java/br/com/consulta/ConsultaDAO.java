package br.com.consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.conexaoDB.ConexaoDB;

public class ConsultaDAO implements ConsultaInterface {
	
	private Connection connection = new ConexaoDB().getConexao();
	
	@Override
	public void gravarConsulta(Consulta consulta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarCosulta(Consulta consulta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirConsulta(int id_consulta) {
		// TODO Auto-generated method stub
		
	}
	
	
	//Funcao que retorna uma consulta
	@Override
	public Consulta consultarConsulta(int id_consulta) {
		try {
			Consulta consulta = new Consulta();
			String sql = "SELECT * FROM consulta WHERE id_consulta = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs;
			
			ps.setInt(1, id_consulta);
			rs = ps.executeQuery();
			if (rs.next()) {
				consulta.setId_consulta(rs.getInt("id_cansulta"));
				consulta.setId_doutor(rs.getInt("id_doutor"));
				consulta.setId_paciente(rs.getInt("id_paciente"));
				consulta.setStatus(rs.getString("status"));
				consulta.setAnotacao(rs.getString("anotacao"));
				consulta.setDataConsulta(rs.getTimestamp("dataconsulta"));
				consulta.setDataCadastro(rs.getTimestamp("datacadastro"));
			}
			
			return consulta;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Consulta> listaConsulta() {
		try {
			ArrayList<Consulta> listaConsulta = new ArrayList<Consulta>();
			String sql = "SELECT * FROM consulta";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Consulta consulta = new Consulta();
				consulta.setId_consulta(rs.getInt("id_cansulta"));
				consulta.setId_doutor(rs.getInt("id_doutor"));
				consulta.setId_paciente(rs.getInt("id_paciente"));
				consulta.setStatus(rs.getString("status"));
				consulta.setAnotacao(rs.getString("anotacao"));
				consulta.setDataConsulta(rs.getTimestamp("dataconsulta"));
				consulta.setDataCadastro(rs.getTimestamp("datacadastro"));
				listaConsulta.add(consulta);
			}
			
			return listaConsulta;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
