<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>
			<c:choose>
				<c:when test="${funcionario.id_usuario != null }">
					Alteração de funcionario
				</c:when>
				<c:otherwise>
					Cadastro de funcionario
				</c:otherwise>
			</c:choose>	
		</title>
		<%@include file="/Sistema/includes/html/head.jsp" %>
		<script src="/SistemaConsultorio/Sistema/includes/script/inputmask/dist/jquery.inputmask.js"></script>
	</head>
	<body>
		<%@include file="/Sistema/includes/html/navBar.jsp" %>
		<form>
			<div class="row">
				<div class="col">
					<div class="input-group mb-3">
  						<span class="input-group-text">Nome</span>
  						<input name="nome" type="text" class="form-control" value="">
					</div>
				</div>
			</div>
			<div class="row align-items-center">
				<div class="col">
					<div class="input-group mb-3">
  						<span class="input-group-text">Usuário</span>
  						<input name="usuario" type="text" class="form-control" value="">
					</div>
				</div>
				<div class="col">
					<div class="input-group mb-3">
  						<span class="input-group-text">Senha</span>
  						<input name="senha" type="password" class="form-control" value="">
					</div>
				</div>
				<div class="col">
					<div class="form-group mb-3">
						<label class="form-check-label me-2">Nível de acesso</label>
  						<div class="form-check form-check-inline">
  							<input class="form-check-input nivel_acesso" type="radio" name="nivel_acesso" value="1" checked>
  							<label class="form-check-label">
    							Doutor(a)
  							</label>
						</div>
						<div class="form-check form-check-inline">
  							<input class="form-check-input nivel_acesso" type="radio" name="nivel_acesso" value="2">
  							<label class="form-check-label">
    							Secretaria
  							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col especializacao">
					<div class="input-group mb-3">
  						<span class="input-group-text">Especilização</span>
  						<input name="especilizacao" type="text" class="form-control cargo" value="">
					</div>
				</div>
				<div class="col especializacao">
					<div class="input-group mb-3">
  						<span class="input-group-text">Documento</span>
  						<input name="documento" type="text" class="form-control cargo" value="">
					</div>
				</div>
			</div>
		</form>
		<%@include file="/Sistema/includes/html/footer.jsp" %>
	</body>
</html>