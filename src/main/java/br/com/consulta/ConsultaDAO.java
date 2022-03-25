package br.com.consulta;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import br.com.conexaoDB.ConexaoDB;

public class ConsultaDAO implements ConsultaInterface {
	
	private Connection connection = new ConexaoDB().getConexao();
	
	//Gravar consulta no banco de dados
	@Override
	public void gravarConsulta(Consulta consulta) {
		try {
			String sql = "INSERT INTO consulta(datacadastro, dataconsulta, status, id_paciente, id_doutor, anotacao) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setTimestamp(1, consulta.getDataCadastro());
			ps.setTimestamp(2, consulta.getDataConsulta());
			ps.setString(3, consulta.getStatus());
			ps.setInt(4, consulta.getId_paciente());
			ps.setInt(5, consulta.getId_doutor());
			ps.setString(6, consulta.getAnotacao());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Alterar dados da consulta
	@Override
	public void alterarCosulta(Consulta consulta) {
		try {
			String sql = "UPDATE consulta SET (datacadastro = ?, dataconsulta = ?, status = ?, id_paciente = ?, id_doutor = ?, anotacao = ? WHERE id_consulta = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setTimestamp(1, consulta.getDataCadastro());
			ps.setTimestamp(2, consulta.getDataConsulta());
			ps.setString(3, consulta.getStatus());
			ps.setInt(4, consulta.getId_paciente());
			ps.setInt(5, consulta.getId_doutor());
			ps.setString(6, consulta.getAnotacao());
			ps.setInt(7, consulta.getId_consulta());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	//Excluir a consulta do banco de dados
	@Override
	public void excluirConsulta(int id_consulta) {
		try {
			String sql = "DELETE FROM consulta WHERE id_consulta = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id_consulta);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
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
			
			ps.close();
			return consulta;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Retorna a lista de consultas
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
			
			ps.close();
			return listaConsulta;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Retorna a lista de consulta do medico
	@Override
	public ArrayList<Consulta> listaConsultaMedico(int id_usuario) {
		try {
			ArrayList<Consulta> listaConsulta = new ArrayList<Consulta>();
			String sql = "SELECT * FROM consulta WHERE id_doutor = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs;
			
			ps.setInt(1, id_usuario);
			rs = ps.executeQuery();
			ps.close();
			
			while(rs.next()) {
				Consulta consulta = new Consulta();
				
				consulta.setId_consulta(rs.getInt("id_consulta"));
				consulta.setId_doutor(rs.getInt("id_doutor"));
				consulta.setId_paciente(rs.getInt("id_paciente"));
				consulta.setAnotacao(rs.getString("anotacao"));
				consulta.setStatus(rs.getString("status"));
				consulta.setDataCadastro(rs.getTimestamp("datacadastro"));
				consulta.setDataConsulta(rs.getTimestamp("dataconsulta"));
				listaConsulta.add(consulta);
			}
			
			return listaConsulta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Retorna a lista de consulta do medico no dia
	@Override
	public ArrayList<Consulta> consultaDiaMedico(int id_usuario) {
		try {
			ArrayList<Consulta> listaConsulta = new ArrayList<Consulta>();
			Date data = new Date(System.currentTimeMillis());
			
			String sql = "SELECT * FROM consulta WHERE id_doutor = ? AND dataconsulta BETWEEN '" + data + " 00:00:00.000' AND '" + data + " 23:59:59.000'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Consulta consulta = new Consulta();
				consulta.setAnotacao(rs.getString("anotacao"));
				consulta.setId_consulta(rs.getInt("id_consulta"));
				consulta.setId_doutor(rs.getInt("id_doutor"));
				consulta.setId_paciente(rs.getInt("id_paciente"));
				consulta.setStatus(rs.getString("status"));
				consulta.setDataCadastro(rs.getTimestamp("datacadastro"));
				consulta.setDataConsulta(rs.getTimestamp("dataconsulta"));
				
				listaConsulta.add(consulta);
			}
				return listaConsulta;
		} catch (SQLException e) { 
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
}
