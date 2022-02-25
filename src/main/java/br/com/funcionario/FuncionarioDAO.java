package br.com.funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.conexaoDB.ConexaoDB;

public class FuncionarioDAO implements FuncionarioInterface {
	
	private Connection connection;
	
	
	public FuncionarioDAO() {
		this.connection = new ConexaoDB().getConexao();
	}
	
	@Override
	public boolean gravarFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterarFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluirFUncionario(int id) {
		String sql = "DELETE FROM funcionario WHERE id_usuario = ?";
		try {
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

	@Override
	public boolean autenticarFuncionario(Funcionario funcionario) {
		String sql = "SELECT * FROM funcionario WHERE usuario = ? AND senha = ?";
		ResultSet rs;
		try { 
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getSenha());
			rs = ps.executeQuery();
			ps.close();
			if(rs.next()) {
				rs.close();
				return true;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return false;
	}

}
