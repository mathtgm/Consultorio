<%@ page language="java" contentType="text/html; UTF-8" import="java.util.*" isErrorPage="true" errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html>
	<%@ include file="../includes/html/head.jsp" %>
	<body>
		<div class="row conteudo">
			<div class="col-3 align-middle">
				<img src="images/error.png" class="img-fluid" alt="Imagem ilustrativa de erro">
			</div>
			<div class="col align-middle">
				<h1> Erro no servidor!</h1>
				<p>Aconteceu um erro no servidor, por favor entre em contato com o administrador do sistema</p>
				<%= exception.getMessage() %>
			</div>
		</div>
	</body>
</html>