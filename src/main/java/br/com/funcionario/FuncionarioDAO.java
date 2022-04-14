package br.com.funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import br.com.conexaoDB.ConexaoDB;

public class FuncionarioDAO implements FuncionarioInterface {
	
	private Connection connection = new ConexaoDB().getConexao();
	
	
	@Override
	public Funcionario setFuncionario(ResultSet rs) {
		Funcionario funcionario = new Funcionario();
		
		try {
		
			funcionario.setEspecializacao(rs.getString("especializacao"));
			funcionario.setDocumento(rs.getString("documento"));
			funcionario.setId_usuario(rs.getInt("id_usuario"));
			funcionario.setUsuario(rs.getString("usuario"));
			funcionario.setStatus(rs.getBoolean("status"));
			funcionario.setNivel_acesso(rs.getInt("nivel_acesso"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setSenha(rs.getString("senha"));
			
			return funcionario;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void gravarFuncionario(Funcionario funcionario) {
		try {
			String sql = "INSERT INTO funcionario (nome, usuario, senha, nivel_acesso, especializacao, documento, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getUsuario());
			ps.setString(3, funcionario.getSenha());
			ps.setInt(4, funcionario.getNivel_acesso());
			ps.setString(5, funcionario.getEspecializacao());
			ps.setString(6, funcionario.getDocumento());
			ps.setBoolean(7, funcionario.isStatus());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void alterarFuncionario(Funcionario funcionario) {
		try {
			String sql = "UPDATE funcionario SET nome = ?, usuario = ?, senha = ?, nivel_acesso = ?, especializacao = ?, documento = ?, status = ? WHERE id_usuario = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getUsuario());
			ps.setString(3, funcionario.getSenha());
			ps.setInt(4, funcionario.getNivel_acesso());
			ps.setString(5, funcionario.getEspecializacao());
			ps.setString(6, funcionario.getDocumento());
			ps.setBoolean(7, funcionario.isStatus());
			ps.setInt(8, funcionario.getId_usuario());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
				funcionario = setFuncionario(rs);
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
				funcionario = setFuncionario(rs);
			 }
			 
			 ps.close();
			 return funcionario;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ArrayList<Funcionario> listarFuncionario() {
		try {
			ArrayList<Funcionario> listaFuncionaro = new ArrayList<Funcionario>();
			String sql = "SELECT * FROM funcionario ORDER BY status DESC";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario = setFuncionario(rs);
				
				listaFuncionaro.add(funcionario);
			}
			
			ps.close();
			return listaFuncionaro;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ArrayList<Funcionario> listarFuncionarioDoutores() {
		String sql = "SELECT * FROM funcionario WHERE nivel_acesso = 1";
		ArrayList<Funcionario> listaDoutores = new ArrayList<Funcionario>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario = setFuncionario(rs);
				
				listaDoutores.add(funcionario);
			}
			
			ps.close();
			return listaDoutores;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
