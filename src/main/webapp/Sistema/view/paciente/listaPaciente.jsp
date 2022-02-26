<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*, br.com.paciente.Paciente, java.util.*"%>
<%@include file="/Sistema/includes/jsp/connectionDB.jsp" %>
<%@include file="/Sistema/includes/jsp/httprequest.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				<jsp:useBean id="pacienteDAO" class="br.com.paciente.pacienteDAO"></jsp:useBean>
				<c:set var="listaPaciente" value="${pacienteDAO.listarPaciente()}"></c:set>
				<c:choose>
					<c:when test="${listaPaciente.isEmpty()}">
						<tr>
							<td colspan="4" class="align-middle">Nenhum paciente cadastrado</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${pacienteDAO.listarPaciente()}" var="paciente">	
						<tr>
							<td class="align-middle">${paciente.cpf}</td>
							<td class="align-middle">${paciente.nome}</td>
							<td class="align-middle">${paciente.nomeConvenio}</td>
							<td>
								<div class="dropdown">
  									<button class="btn dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">Ação</button>
  									<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
    									<li>
    										<a class="dropdown-item link-primary" href="#"><i class="bi bi-pencil"></i>Editar</a>
										</li> 	
    									<li>
    										<a class="dropdown-item link-danger" href="#"></i>Excluir</a>
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