<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*" %>
<%@include file="/Sistema/includes/jsp/valida.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Home | Dashboard</title>
  <%@include file="/Sistema/includes/html/head.jsp" %>
</head>

<body>
	<%@include file="/Sistema/includes/html/navBar.jsp" %>
		
		<jsp:useBean id="DAOConsulta" class="br.com.consulta.ConsultaDAO"></jsp:useBean>
		<jsp:useBean id="DAOPaciente" class="br.com.paciente.PacienteDAO"></jsp:useBean>
		<jsp:useBean id="DAOFuncionario" class="br.com.funcionario.FuncionarioDAO"></jsp:useBean>
		<jsp:useBean id="DAOStatus" class="br.com.status.StatusDAO"></jsp:useBean>
		
    	<div class="row">
    		<div class="col text-light bg-info rounded shadow m-3 p-3">
    			<span class="h5">Consultas agendadas (Hoje)</span><br><br>
    			<span class="h4">${DAOConsulta.totalConsultaStatus(1)} <i class="bi bi-person-lines-fill"></i></span>
    		</div>
    		<div class="col bg-warning rounded shadow m-3 p-3">
    			<span class="h5">Espera de consulta (Hoje)</span><br><br>
    			<span class="h4">${DAOConsulta.totalConsultaStatus(3)} <i class="bi bi-person-check-fill"></i></span>
    		</div>
    		<div class="col text-light bg-danger shadow rounded m-3 p-3">
    			<span class="h5">Consulta canceladas (Hoje)</span><br><br>
    			<span class="h4">${DAOConsulta.totalConsultaStatus(2)} <i class="bi bi-person-x-fill"></i></span>
    		</div>
    	</div>
    	
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
					<c:set var="listaConsulta" value="${DAOConsulta.consultaDia()}"></c:set>

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