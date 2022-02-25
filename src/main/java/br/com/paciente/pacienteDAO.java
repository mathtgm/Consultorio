package br.com.paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

public class pacienteDAO implements pacienteInterface {
	
	private Connection connection;

	@Override
	public boolean gravarPaciente(Paciente paciente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterarPaciente(Paciente paciente) {
		try {
			String sql = "UPDATE paciente SET nome = ?, cpf = ?, datanasc = ?, id_convenio = ? WHERE id_paciente = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, paciente.getNome());
			ps.setString(2, paciente.getCpf());
			ps.setDate(3, paciente.getDatanasc());
			ps.setInt(4, paciente.getId_convenio());
			ps.setInt(5, paciente.getId_paciente());
			
			if (ps.executeUpdate() > 0) {
				ps.close();
				return true;
			} else
				return false;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean excluirPaciente(int id) {
		try {
			String sql = "DELETE FROM áciente WHERE id+paciente = ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			if (ps.executeUpdate() > 0) {
				ps.close();
				return true;
			} else
				return false;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Paciente> listarPaciente() {
		try {
			Paciente paciente = new Paciente();
			List<Paciente> listPaciente = new ArrayList<Paciente>();
			String sql = "SELECT * FROM paciente";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			do {
				paciente.setId_paciente(rs.getInt("od_paciente"));
				paciente.setNome(rs.getString("nome"));
				paciente.setCpf(rs.getString("cpf"));
				paciente.setDatanasc(rs.getDate("datanasc"));
				paciente.setId_convenio(rs.getInt("id_convenio"));
				listPaciente.add(paciente);
			} while (rs.next());
			rs.close();
			return listarPaciente();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
