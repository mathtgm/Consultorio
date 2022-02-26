<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@include file="/Sistema/includes/jsp/connectionDB.jsp" %>
<%@include file="/Sistema/includes/jsp/httprequest.jsp"%>

<%	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
	String nome = "";
	String cpf = "";
	Date data = new Date();
	String convenio= "";
	int idConv = -1;
	int idPaci = -1;
	
	try {
		String sql;
		if(request("acao").equals("alterar")) {
			if(request("nome") != null) {
				sql = "UPDATE paciente SET nome = '" + request("nome") + "', cpf = '" + request("cpf") + "', datanasc = '" + formato2.format(formato.parse(request("data"))) + "', id_convenio = " + request("convenio") + " WHERE id_paciente = " + request("id");
				statement.executeUpdate(sql);
			}
			sql = "SELECT p.nome as pnome, p.cpf, p.datanasc, p.id_paciente, c.* FROM paciente p INNER JOIN convenio c ON p.id_convenio = c.id_convenio WHERE p.id_paciente = "+ request("id") +" LIMIT 1";
			resultset = statement.executeQuery(sql);
			resultset.next();
			nome = resultset.getString("pnome");
			cpf = resultset.getString("cpf");
			data = resultset.getDate("datanasc");
			idConv = resultset.getInt("id_convenio");
			idPaci = resultset.getInt("id_paciente");
			convenio = resultset.getString("nome");
		} else if(request("acao").equals("cadastrar")) {
			data = formato.parse(request("data"));
			sql = "INSERT INTO paciente(nome, cpf, datanasc, id_convenio) VALUES('" + request("nome") + "', '" + request("cpf") +"', '"+ formato2.format(data) + "', " + request("convenio") + ")";
			statement.executeUpdate(sql);
		}
	} catch (Exception e) {
		System.out.println(e);
	}
	
%>

<!DOCTYPE html>
	<html>
		<head>
			<title><%= request("acao") == null ? "Cadastro de paciente" : "Alterar paciente" %></title>		
			<%@include file="/Sistema/includes/html/head.jsp" %>
			<script src="/aulas-neri2/Sistema/includes/script/inputmask/dist/jquery.inputmask.js"></script>
		</head>
		<body>
			<%@include file="/Sistema/includes/html/navBar.jsp" %>
				<form method="get" action="formPaciente.jsp">
				 <input type="hidden" name="id" value="<%= idPaci %>">
					<div class="row">
						<div class="col">
							<div class="input-group mb-3">
  								<span class="input-group-text" id="inputGroup-sizing-default">Nome Completo</span>
  								<input name="nome" type="text" class="form-control" aria-describedby="inputGroup-sizing-default" value="<%= request("acao") == null ? "" : nome %>">
							</div>
						</div>
						<div class="col">
							<div class="input-group mb-3">
  								<select name="convenio" class="form-select">
  									<%
  										try {
  											String sql;
  											sql = "SELECT * FROM convenio";
  											resultset = statement.executeQuery(sql);
  											while(resultset.next()) {
  												if(resultset.getInt("id_convenio") == idConv) {
  									%>
  													<option selected value="<%= resultset.getInt("id_convenio") %>"><%= resultset.getString("nome") %></option>
  									<%
  												} else {
  									%>
  													<option value="<%= resultset.getInt("id_convenio") %>"><%= resultset.getString("nome") %></option>
  									<%
  												}
  											}
  										} catch (Exception e) {
  											System.out.println(e);
  										}
  									%>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="input-group mb-3">
  								<span class="input-group-text" id="inputGroup-sizing-default">CPF</span>
  								<input id="cpf" name="cpf" maxlength="14" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%= request("acao") == null ? "" : cpf %>">
							</div>
						</div>
						<div class="col">
							<div class="input-group mb-3">
  								<span class="input-group-text" id="inputGroup-sizing-default">Nascimento</span>
  								<input id="data" name="data" type="text" class="form-control" aria-describedby="inputGroup-sizing-default" value="<%= request("acao") == null ? "" : formato.format(data) %>">
							</div>
						</div>
					</div>
					<div class="row">
						<button class="btn btn-success" type="submit" name="acao" value="<%= request("acao") == null ? "cadastrar" : "alterar"%>"><i class="bi bi-pencil"></i><%= request("acao") == null ? "Cadastar" : "Alterar" %></button>
					</div>
				</form>
			<%@include file="/Sistema/includes/html/footer.jsp" %>
			<script>
				$(document).ready(function(){
					$("#cpf").inputmask("999.999.999-99");
				});
				$(document).ready(function(){
					$("#data").inputmask("99/99/9999");
				});
			</script>
		</body>
	</html>
