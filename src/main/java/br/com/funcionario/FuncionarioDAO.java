package br.com.funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import br.com.conexaoDB.ConexaoDB;

public class FuncionarioDAO implements FuncionarioInterface {
	
	private Connection connection = new ConexaoDB().getConexao();;
	
	@Override
	public void gravarFuncionario(Funcionario funcionario) {
		
	}

	@Override
	public void alterarFuncionario(Funcionario funcionario) {
		
	}

	@Override
	public void excluirFuncionario(int id) {
		try {
			String sql = "DELETE FROM funcionario WHERE id_usuario = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Funcionario consultarFuncionario(int id_usuario) {
		try {
			Funcionario funcionario = new Funcionario();
			String sql = "SELECT * FROM funcionario WHERE id_usuario = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs;
			
			ps.setInt(1, id_usuario);
			rs = ps.executeQuery();			
			
			if(rs.next()) {
				funcionario.setEspecializacao(rs.getString("cargo"));
				funcionario.setDocumento(rs.getString("documento"));
				funcionario.setId_usuario(rs.getInt("id_usuario"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setStatus(rs.getBoolean("status"));
				funcionario.setNivel_acesso(rs.getInt("nivel_acesso"));
				
				funcionario.toString();
			}
			
			ps.close();
			return funcionario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Funcionario autenticaFuncionario(String usuario, String senha) {
		try {
			String sql = "SELECT * FROM funcionario WHERE usuario = ? AND senha = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs;
			Funcionario funcionario = new Funcionario();
			
			ps.setString(1, usuario);
			ps.setString(2, senha);
			
			rs = ps.executeQuery();
			 if(rs.next()) {
				funcionario.setEspecializacao(rs.getString("cargo"));
				funcionario.setDocumento(rs.getString("documento"));
				funcionario.setId_usuario(rs.getInt("id_usuario"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setStatus(rs.getBoolean("status"));
				funcionario.setNivel_acesso(rs.getInt("nivel_acesso"));
				funcionario.setNome(rs.getString("nome"));
			 }
			 
			 ps.close();
			 return funcionario;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
