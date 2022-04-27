package br.com.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.conexaoDB.ConexaoDB;

public class StatusDAO implements StatusInterface{
	
	private Connection connection = new ConexaoDB().getConexao();
	
	//Retorna a lista de status no banco de dados
	@Override
	public ArrayList<Status> listaStatus() {		
		
		ArrayList<Status> listaStatus = new ArrayList<Status>();
		
		try {			
			String sql = "SELECT * FROM status";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Status status = new Status();
				status.setId_status(rs.getInt("id_status"));
				status.setStatus(rs.getString("status"));
				status.setColor(rs.getString("color"));
				
				listaStatus.add(status);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return listaStatus;
	}
	
	@Override
	public Status getStatus(int id_status) {
		Status status = new Status();
		
		try {
			String sql = "SELECT * FROM status WHERE id_status = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id_status);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status.setId_status(rs.getInt("id_status"));
				status.setStatus(rs.getString("status"));
				status.setColor(rs.getString("color"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
}
