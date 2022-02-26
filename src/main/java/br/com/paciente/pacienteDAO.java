package br.com.paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.conexaoDB.ConexaoDB; 

public class pacienteDAO implements pacienteInterface {
	
	private Connection connection = new ConexaoDB().getConexao();
	
	//Operação para gravar um paciente no banco de dados
	@Override
	public boolean gravarPaciente(Paciente paciente) {
		// TODO Auto-generated method stub
		return false;
	}

	//Operação para alterar dados do paciente no banco de dados
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
	
	
	//Operação para excluir um paciente do banco de dados
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
	
	//Lista todos os pacientes cadastrados no bancco de dados
	public ArrayList<Paciente> listarPaciente() {
		try {
			ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
			String sql = "SELECT p.*, c.nome as nomeConv FROM paciente p INNER JOIN convenio c ON p.id_convenio = c.id_convenio";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Paciente pac = new Paciente();
				pac.setId_paciente(rs.getInt("id_paciente"));
				pac.setNome(rs.getString("nome"));
				pac.setCpf(rs.getString("cpf"));
				pac.setDatanasc(rs.getDate("datanasc"));
				pac.setId_convenio(rs.getInt("id_convenio"));
				pac.setNomeConvenio(rs.getString("nomeConv"));
				listaPacientes.add(pac);
			}
			return listaPacientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
