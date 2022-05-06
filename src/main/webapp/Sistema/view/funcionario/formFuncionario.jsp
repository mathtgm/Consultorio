<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/Sistema/includes/jsp/valida.jsp" %>
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
		<form method="get" action="/SistemaConsultorio/funcionarioServlet">
			<div class="row">
				<input name="id_usuario" type="hidden" value="${funcionario.id_usuario}">
				<div class="col">
					<div class="input-group mb-3">
  						<span class="input-group-text">Nome</span>
  						<input name="nome" type="text" class="form-control" value="${funcionario.nome}">
					</div>
				</div>
				<div class="col">
					<div class="input-group mb-3">
						<label class="form-check-label me-2">Ativo?</label>
  						<div class="form-check form-check-inline">
  							<input class="form-check-input nivel_acesso" type="radio" name="status" value="true" ${funcionario.status ? "checked" : "" }>
  							<label class="form-check-label">
    							Sim
  							</label>
						</div>
						<div class="form-check form-check-inline">
  							<input class="form-check-input nivel_acesso" type="radio" name="status" value="false" ${funcionario.status ? "" : "checked" }>
  							<label class="form-check-label">
    							Não
  							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="row align-items-center">
				<div class="col">
					<div class="input-group mb-3">
  						<span class="input-group-text">Usuário</span>
  						<input name="usuario" type="text" class="form-control" value="${funcionario.usuario}">
					</div>
				</div>
				<div class="col">
					<div class="input-group mb-3">
  						<span class="input-group-text">Senha</span>
  						<input name="senha" type="password" class="form-control" value="${funcionario.senha }">
					</div>
				</div>
				<div class="col">
					<div class="form-group mb-3">
						<label class="form-check-label me-2">Nível de acesso</label>
  						<div class="form-check form-check-inline">
  							<input class="form-check-input nivel_acesso" type="radio" name="nivel_acesso" value="1" ${funcionario.nivel_acesso == 1 ? "checked" : "" }>
  							<label class="form-check-label">
    							Doutor(a)
  							</label>
						</div>
						<div class="form-check form-check-inline">
  							<input class="form-check-input nivel_acesso" type="radio" name="nivel_acesso" value="2" ${funcionario.nivel_acesso != 1 ? "checked" : "" }>
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
  						<span class="input-group-text">Especialização</span>
  						<input name="especializacao" type="text" class="form-control cargo" value="${funcionario.especializacao}">
					</div>
				</div>
				<div class="col especializacao">
					<div class="input-group mb-3">
  						<span class="input-group-text">Documento</span>
  						<input name="documento" type="text" class="form-control cargo" value="${funcionario.documento}">
					</div>
				</div>
			</div>
			<div class="row mx-1">
				<c:choose>
					<c:when test="${funcionario.id_usuario != null}">
						<button type="submit" class="btn btn-success" name="acao" value="alterar">
							<i class="bi bi-pencil"></i>
							Alterar dados							
						</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn btn-success" name="acao" value="cadastrar">
							<i class="bi bi-pencil"></i>
							Cadastrar dados
						</button>
					</c:otherwise>
				</c:choose>
			</div>
		</form>
		<%@include file="/Sistema/includes/html/footer.jsp" %>
	</body>
</html>