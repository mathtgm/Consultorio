<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	include file="/Sistema/includes/jsp/httprequest.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
	<html>
		<head>
			<title>Lista de Consultas</title>
			<%@include file="/Sistema/includes/html/head.jsp" %>
		</head>
		<body>
			<%@include file="/Sistema/includes/html/navBar.jsp" %>
			
			<jsp:useBean id="DAOConsulta" class="br.com.consulta.ConsultaDAO"></jsp:useBean>
			<jsp:useBean id="DAOPaciente" class="br.com.paciente.PacienteDAO"></jsp:useBean>
			<jsp:useBean id="DAOFuncionario" class="br.com.funcionario.FuncionarioDAO"></jsp:useBean>
			<jsp:useBean id="DAOStatus" class="br.com.status.StatusDAO"></jsp:useBean>
			
			<form id="formHoje" action="/SistemaConsultorio/consultaServlet" method="post">
				<input type="hidden" id="numbPagina" name="numbPagina" value="0">
				<input type="hidden" name="acao" value="consultasHoje">
			</form>
			
			<table class="footable table table-striped table-hover">
				<thead>
					<tr>
						<th>Nome Paciente</th>
						<th>Convenio</th>
						<th>Data</th>
						<th>Hora</th>
						<th>Doutor(a)</th>
						<th>Situação</th>
					</tr>
				</thead>
				<tbody>
					<%-- Se o usuario que acessar o menu for um doutor(nivel_acesso == 1) o sistema só mostrara as consulta atreladas ao mesmo no dia, caso ao contrario 
					mostrara todas as consulta agendadas no dia.
					 --%>
					<c:choose>
						<c:when test="${sessionScope.nivel_acesso == 1}">
							<c:set var="listaConsulta" value="${DAOConsulta.consultaDiaMedico(sessionScope.id_usuario)}"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="listaConsulta" value="${DAOConsulta.consultaDia()}"></c:set>
						</c:otherwise>
					</c:choose>
				
				
				
					<c:forEach  varStatus="statusLista" begin="${numbPagina * 10}" end="${(numbPagina + 1) * 10}">
						<c:if test="${listaConsulta[statusLista.index].id_consulta != null}">
							<c:set var="paciente" value="${DAOPaciente.consultarPaciente(listaConsulta[statusLista.index].id_paciente)}"></c:set>
							<c:set var="doutor" value="${DAOFuncionario.consultarFuncionario(listaConsulta[statusLista.index].id_doutor)}"></c:set>
							<c:set var="status" value="${DAOStatus.getStatus(listaConsulta[statusLista.index].id_status)}"></c:set>
							<tr>
								<td>${paciente.nome}</td>
								<td>${paciente.nomeConvenio}</td>
								<td>${listaConsulta[statusLista.index].getDataConsultaFormat()}</td>
								<td>${listaConsulta[statusLista.index].getTimeConsultaFormat()}</td>
								<td>${doutor.nome} - ${doutor.especializacao}</td>
								<td><span class="badge bg-${status.color} text-dark">${status.status }</span></td>
							</tr>
						</c:if>
					</c:forEach>
					
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="6">
							<nav>
								<ul class="pagination justify-content-end">
							
									<c:forEach begin="1" end="${(listaConsulta.size() / 10) + 1}" varStatus="paginaStatus">
										<c:choose>
											<c:when test="${(numbPagina + 1) == paginaStatus.index }">
												<li class="page-item active"><a class="page-link">${paginaStatus.index}</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link" onclick="nextPage(${paginaStatus.index})">${paginaStatus.index}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							
								</ul>
							</nav>
						</td>					
					</tr>
				</tfoot>
			</table>
			
			<%@include file="/Sistema/includes/html/footer.jsp" %>
		</body>
</html>