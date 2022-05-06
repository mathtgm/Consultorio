<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@include file="/Sistema/includes/jsp/valida.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
	<html>
		<head>
			<title>Lista de Consultas</title>
			<%@include file="/Sistema/includes/html/head.jsp" %>
		</head>
		<body>
			<%@include file="/Sistema/includes/html/navBar.jsp" %>
			
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
			
			<jsp:useBean id="DAOfuncionario" class="br.com.funcionario.FuncionarioDAO"></jsp:useBean>
			<jsp:useBean id="DAOconsulta" class="br.com.consulta.ConsultaDAO"></jsp:useBean>
			<jsp:useBean id="DAOpaciente" class="br.com.paciente.PacienteDAO"></jsp:useBean>
			<jsp:useBean id="DAOstatus" class="br.com.status.StatusDAO"></jsp:useBean>
			
			<%--
			Se o usuario for um nivel de acesso igual a 1(um) mostrara so a lista de consulta que pertende ao usuario, caso ao contrario deverar
			mostrar a lista de todas as consultas separado por doutor(a).
			 --%>
			<c:choose>
				<c:when test="${sessionScope.nivel_acesso == 1}">
					<c:set var="listaConsulta" value="${DAOconsulta.listaConsultaMedico(sessionScope.id_usuario)}"></c:set>
					<c:forEach varStatus="indexRow" begin="0" step="3" end="${listaConsulta.size()}">
						<div class="row mb-3">
							<c:forEach varStatus="indexCol" begin="0" end="2">
								<c:set value="${indexRow.index+indexCol.index}" var="index"></c:set>
								<c:set var="consulta" value="${listaConsulta[index]}"></c:set>
								<c:set var="status" value="${DAOstatus.getStatus(consulta.id_status)}"></c:set>
								<c:if test="${index < listaConsulta.size()}">
									<c:set var="paciente" value="${DAOpaciente.consultarPaciente(consulta.id_paciente)}"></c:set>
									<div class="col-4">
										<div class="card">
  											<div class="card-header">
  												${paciente.nome} - Situação: <span class="badge bg-${status.color} text-dark">${status.status}</span>
  											</div>
  											<div class="card-body">
    											<h5 class="card-title">Informações</h5>
    											<p class="card-text">Data da Consulta: ${listaConsulta[index].getDataHoraConsultaFormat()}</p>
    											<p class="card-text">Convenio do paciente: ${paciente.nomeConvenio}</p>
    											<a onclick="consulta(${consulta.id_consulta})" class="btn btn-primary"><i class="bi bi-folder2-open"></i> Acessar</a>
  											</div>
  										</div>
									</div>
								</c:if>	
							</c:forEach>
									</div>
						</c:forEach>
				</c:when>
				<c:otherwise>
					<a class="btn btn-success mb-3" href="/SistemaConsultorio/Sistema/view/consulta/formConsulta.jsp"><i class="bi bi-person-plus-fill pe-1"></i>Cadastrar consulta</a>
					
					<c:forEach var="doutor" begin="0" varStatus="item" items="${DAOfuncionario.listarFuncionarioDoutores()}">
				
						<div class="accordion mb-3">
  							<div class="accordion-item">
    							<h2 class="accordion-header">
      								<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#item-${item.index}" aria-expanded="false" aria-controls="item-${item.index}">
       									${doutor.nome} - ${doutor.especializacao}
      								</button>
    							</h2>
    							<div id="item-${item.index}" class="accordion-collapse collapse">
									<div class="accordion-body">
								
									<c:set var="listaConsulta" value="${DAOconsulta.listaConsultaMedico(doutor.id_usuario)}"></c:set>
									<c:forEach varStatus="indexRow" begin="0" step="3" end="${listaConsulta.size()}">
										
										<div class="row mb-3">
									
											<c:forEach varStatus="indexCol" begin="0" end="2">
												<c:set value="${indexRow.index+indexCol.index}" var="index"></c:set>
												<c:set var="consulta" value="${listaConsulta[index]}"></c:set>
												<c:set var="status" value="${DAOstatus.getStatus(consulta.id_status)}"></c:set>
										
												<c:if test="${index < listaConsulta.size()}">
											
													<c:set var="paciente" value="${DAOpaciente.consultarPaciente(consulta.id_paciente)}"></c:set>
												
														<div class="col-4">
															<div class="card">
  																<div class="card-header">
  																	${paciente.nome} - Situação: <span class="badge bg-${status.color} text-dark">${status.status}</span>
  																</div>
  																<div class="card-body">
    																<h5 class="card-title">Informações</h5>
    																<p class="card-text">Data da Consulta: ${listaConsulta[index].getDataHoraConsultaFormat()}</p>
    																<p class="card-text">Convenio do paciente: ${paciente.nomeConvenio}</p>
    																<a onclick="consulta(${consulta.id_consulta})" class="btn btn-primary"><i class="bi bi-folder2-open"></i> Acessar</a>
  																</div>
  															</div>
														</div>
												
												</c:if>	
											</c:forEach>
										</div>
									</c:forEach>					
									</div>
    							</div>
  							</div>
						</div>
			
					</c:forEach>				
				</c:otherwise>
			</c:choose>
			<%-- FIM DA LISTA DE CONSULTA --%>
			
			<%@include file="/Sistema/includes/html/footer.jsp" %>
		</body>
</html>