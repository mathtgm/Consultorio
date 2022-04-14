<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	include file="/Sistema/includes/jsp/httprequest.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
	<html>
		<head>
			<title>Lista de Convenios</title>
			<%@include file="/Sistema/includes/html/head.jsp" %>
		</head>
		<body>
			<%@include file="/Sistema/includes/html/navBar.jsp" %>
			<a class="btn btn-success mb-3" href="/SistemaConsultorio/Sistema/view/convenio/formConvenio.jsp"><i class="bi bi-person-plus-fill pe-1"></i>Cadastrar convênio</a>
			<c:choose>
				<c:when test="${msg != null || erro != null }">
					<c:choose>
						<c:when test="${msg != null}">
							<div class="alert alert-success">${msg}</div>
						</c:when>
						<c:otherwise>
							<div class="alert alert-danger">${erro}</div>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<table class="table table-striped table-hover">
				<tr>
					<th>Nome</th>
					<th>Ação</th>
				</tr>
				<jsp:useBean id="convenioDAO" class="br.com.convenio.ConvenioDAO"></jsp:useBean>
				<c:choose>
					<c:when test="${convenioDAO.listarConvenio().isEmpty()}">
						<tr>
							<td class="align-middle">Nenhum convenio cadastrado</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${convenioDAO.listarConvenio()}" var="convenio">	
						<tr>
							<td class="align-middle">${convenio.nome}</td>
							<td>
								<div class="dropdown">
  									<button class="btn dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">Ação</button>
  									<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
    									<li>
    										<a class="dropdown-item link-primary" onclick="alterarConvenio(${convenio.id_convenio})"><i class="bi bi-pencil"></i>Editar</a>
										</li> 	
    									<li>
    										<a class="dropdown-item link-danger" onclick="excluirConvenio(${paciente.id_convenio})"><i class="bi bi-trash-fill"></i>Excluir</a>
    									</li>
  									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>			
				</c:otherwise>
				
				</c:choose>					
						
			</table>

			<%@include file="/Sistema/includes/html/footer.jsp" %>
		</body>
</html>