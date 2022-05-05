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
	
	
	@Override
	public Consulta setConsulta(ResultSet rs) {
		Consulta consulta = new Consulta();
		
		try {
			consulta.setAnotacao(rs.getString("anotacao"));
			consulta.setId_consulta(rs.getInt("id_consulta"));
			consulta.setId_doutor(rs.getInt("id_doutor"));
			consulta.setId_paciente(rs.getInt("id_paciente"));
			consulta.setId_status(rs.getInt("id_status"));
			consulta.setDataCadastro(rs.getTimestamp("datacadastro"));
			consulta.setDataConsulta(rs.getTimestamp("dataconsulta"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return consulta;
	}
	
	//Gravar consulta no banco de dados
	@Override
	public void gravarConsulta(Consulta consulta) {
		try {
			String sql = "INSERT INTO consulta(datacadastro, dataconsulta, id_status, id_paciente, id_doutor) VALUES(now(), ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setTimestamp(1, consulta.getDataConsulta());
			ps.setInt(2, 1);
			ps.setInt(3, consulta.getId_paciente());
			ps.setInt(4, consulta.getId_doutor());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Alterar dados da consulta
	@Override
	public void alterarCosulta(Consulta consulta) {
		try {
			String sql = "UPDATE consulta SET dataconsulta = ?, id_status = ?, anotacao = ? WHERE id_consulta = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setTimestamp(1, consulta.getDataConsulta());
			ps.setInt(2, consulta.getId_status());
			ps.setString(3, consulta.getAnotacao());
			ps.setInt(4, consulta.getId_consulta());

			ps.executeUpdate();
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
				consulta.setId_consulta(rs.getInt("id_consulta"));
				consulta.setId_doutor(rs.getInt("id_doutor"));
				consulta.setId_paciente(rs.getInt("id_paciente"));
				consulta.setId_status(rs.getInt("id_status"));
				consulta.setAnotacao(rs.getString("anotacao"));
				consulta.setDataConsulta(rs.getTimestamp("dataconsulta"));
				consulta.setDataCadastro(rs.getTimestamp("datacadastro"));
			}
			
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
				consulta.setId_consulta(rs.getInt("id_consulta"));
				consulta.setId_doutor(rs.getInt("id_doutor"));
				consulta.setId_paciente(rs.getInt("id_paciente"));
				consulta.setId_status(rs.getInt("id_status"));
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
			
			while(rs.next()) {
				Consulta consulta = new Consulta();
				
				consulta.setId_consulta(rs.getInt("id_consulta"));
				consulta.setId_doutor(rs.getInt("id_doutor"));
				consulta.setId_paciente(rs.getInt("id_paciente"));
				consulta.setAnotacao(rs.getString("anotacao"));
				consulta.setId_status(rs.getInt("id_status"));
				consulta.setDataCadastro(rs.getTimestamp("datacadastro"));
				consulta.setDataConsulta(rs.getTimestamp("dataconsulta"));
				listaConsulta.add(consulta);
			}
			
			return listaConsulta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Extrai todas as consultas do dia
	@Override
	public ArrayList<Consulta> consultaDia() {

		try {
			ArrayList<Consulta> listaConsulta = new ArrayList<Consulta>();
			Date data = new Date(System.currentTimeMillis());
			
			String sql = "SELECT * FROM consulta WHERE dataconsulta BETWEEN '" + data + " 00:00:00.000' AND '" + data + " 23:59:59.000' ORDER BY dataconsulta ASC";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs;

			rs = ps.executeQuery();
			
			while(rs.next()) {
				listaConsulta.add(setConsulta(rs));
			}
			
			ps.close();
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
			
			String sql = "SELECT * FROM consulta WHERE id_doutor = ? AND dataconsulta BETWEEN '" + data + " 00:00:00.000' AND '" + data + " 23:59:59.000' ORDER BY dataconsulta ASC";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs;
			
			ps.setInt(1, id_usuario);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				listaConsulta.add(setConsulta(rs));
				
			}
				return listaConsulta;
		} catch (SQLException e) { 
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
}
