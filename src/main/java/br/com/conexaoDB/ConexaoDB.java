package br.com.conexaoDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
	private static String url = "jdbc:postgresql://localhost/consultorioDB";
	private static String usuario = "postgres";
	private static String senha = "123";
	private String driver = "org.postgresql.Driver";
	
	public Connection getConexao() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
