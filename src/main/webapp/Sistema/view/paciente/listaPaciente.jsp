<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*"%>
<%@include file="/Sistema/includes/jsp/connectionDB.jsp" %>
<%@include file="/Sistema/includes/jsp/httprequest.jsp"%>
<%@include file="/Sistema/includes/jsp/valida.jsp" %>
<%
	try {
		String sql;
		if(request("acao") != null){
			sql = "DELETE FROM paciente WHERE cpf = '" + request("cpf") + "'";
			statement.executeUpdate(sql);
		}
		sql = "SELECT p.id_paciente, p.nome, p.cpf, c.nome as convenio FROM paciente p INNER JOIN convenio c ON p.id_convenio = c.id_convenio";
		resultset = statement.executeQuery(sql);
	} catch (Exception e) {
		System.out.println(e);
	}
%>
<!DOCTYPE html>
	<html>
		<head>
			<title>Lista de Pacientes</title>
			<%@include file="/Sistema/includes/html/head.jsp" %>
		</head>
		<body>
			<%@include file="/Sistema/includes/html/navBar.jsp" %>
			<a class="btn btn-success mb-3" href="/aulas-neri2/Sistema/view/paciente/formPaciente.jsp"><i class="bi bi-person-plus-fill pe-1"></i>Cadastrar paciente</a>
			<table class="table table-striped table-hover">
				<tr>
					<th>CPF</th>
					<th>Nome</th>
					<th>Convênio</th>
					<th>Ação</th>
				</tr>
				<%	
					if(resultset.next())
						do {
				%>
						<tr>
							<td class="align-middle"><%= resultset.getString("cpf") %></td>
							<td class="align-middle"><%= resultset.getString("nome") %></td>
							<td class="align-middle"><%= resultset.getString("convenio") %></td>
							<td>
								<div class="dropdown">
  									<button class="btn dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">Ação</button>
  									<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
    									<li>
    										<a class="dropdown-item link-primary" href="/aulas-neri2/Sistema/view/paciente/formPaciente.jsp?id=<%= resultset.getInt("id_paciente") %>&acao=alterar"><i class="bi bi-pencil"></i>Editar</a>
										</li> 	
    									<li>
    										<a class="dropdown-item link-danger" href="listaPaciente.jsp?acao=excluir&cpf=<%= resultset.getString("cpf") %>"><i class="bi bi-trash"></i>Excluir</a>
    									</li>
  									</ul>
								</div>
							</td>
						</tr>							
				<%
						} while(resultset.next());
					else {
				%> 
						<tr>
							<td colspan="4" class="align-middle">Nenhum paciente cadastrado</td>
						</tr>
				<%
					}
				%>
			</table>

			<%@include file="/Sistema/includes/html/footer.jsp" %>
		</body>
</html>