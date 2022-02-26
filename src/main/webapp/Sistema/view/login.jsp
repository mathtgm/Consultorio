<%@ page language="java" contentType="text/html; charset=utf-8" errorPage="errorPage.jsp" %>
<%@include file="/Sistema/includes/jsp/httprequest.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Página de Acesso</title>
		<%@include file="/Sistema/includes/html/head.jsp"%>
	</head>
	<body>
		<div class="bg-image"></div>
		<div class="cardLogin">
			<h1>Painel do usuário ${name}</h1>
			<img class="logo" src="/SistemaConsultorio/Sistema/view/images/logo-example.png">
			<form method="get" name="loginForm" action="/SistemaConsultorio/Sistema/view/login.jsp">
				<div class="input-group mb-3">
  					<span class="input-group-text" id="inputGroup-sizing-default">Usuário</span>
  					<input name="usuario" type="text" class="form-control">
				</div>
				<br>
				<div class="input-group mb-3">
  					<span class="input-group-text" id="inputGroup-sizing-default">Senha</span>
  					<input name="senha" type="password" class="form-control">
				</div>
				<br>
				<button name="acessar" type="submit" class="btn btn-primary" value="login">Acessar</button>
			</form>
		</div>
	</body>
</html>