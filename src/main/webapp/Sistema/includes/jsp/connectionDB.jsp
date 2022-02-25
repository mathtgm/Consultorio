<%!
	Connection connection = null;
	Statement statement = null;
	ResultSet resultset = null;
	String acesso;
	
	String driver = "org.postgresql.Driver";
	String url = "jdbc:postgresql://localhost/consultorioDB";
	String usuario = "postgres";
	String senha = "123";
	
	public void jspInit(){ 
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, usuario, senha);
			statement = connection.createStatement();
		} catch (Exception e){
			System.out.println("Falha na conexão!" + e);
		}
	}
	 public void jspDestroy() {
	 	try {
	 		statement.close();
	 		connection.close();
	 	} catch (Exception e) {
	 		System.out.println("Servidor não fechou erro: " + e);
	 	}
	 }
%>
<%
	acesso = (String) session.getAttribute("userName");
%>