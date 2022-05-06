<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*"%>
<%@include file="/Sistema/includes/jsp/valida.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
	<html>
		<head>
			<title>
				<c:choose>
					<c:when test="${paciente.id_paciente != null }">
						Alteração de paciente
					</c:when>
					<c:otherwise>
						Cadastro de paciente
					</c:otherwise>
				</c:choose>	
			</title>		
			<%@include file="/Sistema/includes/html/head.jsp" %>
			<script src="/SistemaConsultorio/Sistema/includes/script/inputmask/dist/jquery.inputmask.js"></script>
		</head>
		<body>
			<%@include file="/Sistema/includes/html/navBar.jsp" %>
				<form method="get" action="/SistemaConsultorio/pacienteServlet">
				 <input type="hidden" name="id" value="${paciente.id_paciente }">
					<div class="row">
						<div class="col">
							<div class="input-group mb-3">
  								<span class="input-group-text" id="inputGroup-sizing-default">Nome Completo</span>
  								<input maxlength="100" name="nome" type="text" class="form-control" aria-describedby="inputGroup-sizing-default" value="${paciente.nome}">
							</div>
						</div>
						<div class="col">
							<div class="input-group mb-3">
  								<select name="convenio" class="form-select">
  									<jsp:useBean id="convenioDAO" class="br.com.convenio.ConvenioDAO"></jsp:useBean>
									<c:set var="listaConvenio" value="${convenioDAO.listarConvenio()}"></c:set>
  									<c:forEach var="convenio" items="${listaConvenio}">
  										<c:choose>
  											<c:when test="${paciente.id_convenio == convenio.id_convenio}">
  												<option value="${convenio.id_convenio}" selected>${convenio.nome}</option>
  											</c:when>
  											<c:otherwise>
  												<option value="${convenio.id_convenio}">${convenio.nome}</option>
  											</c:otherwise>
  										</c:choose>
  									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="input-group mb-3">
  								<span class="input-group-text" id="inputGroup-sizing-default">CPF</span>
  								<input id="cpf" name="cpf" type="text" class="form-control" aria-describedby="inputGroup-sizing-default" value="${paciente.cpf }">
							</div>
						</div>
						<div class="col">
							<div class="input-group mb-3">
  								<span class="input-group-text" id="inputGroup-sizing-default">Nascimento</span>
  								<input id="data" name="data" type="text" class="form-control" aria-describedby="inputGroup-sizing-default" value="${paciente.getDatanascBR() }">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col d-grid gap-2">
							<c:choose>
								<c:when test="${paciente.id_paciente != null }">
									<button class="btn btn-success btn-block" type="submit" name="acao" value="alterar"><i class="bi bi-pencil"></i>Alterar Paciente</button>
								</c:when>
								<c:otherwise>
									<button class="btn btn-success btn-block" type="submit" name="acao" value="cadastrar"><i class="bi bi-pencil"></i>Cadastrar Paciente</button>
								</c:otherwise>
							</c:choose>
						</div>
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
