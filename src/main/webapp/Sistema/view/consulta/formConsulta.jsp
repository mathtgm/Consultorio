<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	include file="/Sistema/includes/jsp/httprequest.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>
			<c:choose>
				<c:when test="${consulta.id_consulta != null }">
					Alterar consulta
				</c:when>
				<c:otherwise>
					Agendar consulta
				</c:otherwise>
			</c:choose>			
		</title>
		<%@include file="/Sistema/includes/html/head.jsp" %>
		<script src="/SistemaConsultorio/Sistema/includes/script/inputmask/dist/jquery.inputmask.js"></script>
	</head>
	<body>
		<%@include file="/Sistema/includes/html/navBar.jsp" %>
		
		<jsp:useBean id="pacienteDAO" class="br.com.paciente.PacienteDAO"></jsp:useBean>
		<jsp:useBean id="funcionarioDAO" class="br.com.funcionario.FuncionarioDAO"></jsp:useBean>
		<jsp:useBean id="statusDAO" class="br.com.status.StatusDAO"></jsp:useBean>
		<c:if test="${consulta != null }">
			<c:set var="paciente" value="${pacienteDAO.consultarPaciente(consulta.id_paciente)}"></c:set>
			<c:set var="doutor" value="${funcionarioDAO.consultarFuncionario(consulta.id_doutor)}"></c:set>
		</c:if>

					<form class="m-3" method="get" action="/SistemaConsultorio/consultaServlet">
						<input type="hidden" value="${consulta.id_consulta}" name="id_consulta">
						<div class="rounded shadow border p-3 mb-5">
							<div class="row">
								<span class="h2 my-2">Dados do paciente</span>
								<div class="col">
									<div class="form-floating">
										<c:choose>
											<c:when test="${consulta.id_paciente != null}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control"value="${paciente.nome}" readonly>
 													<label for="floatingInput">Paciente</label>
												</div>
											</c:when>
											<c:otherwise>
  												<select onchange="this.form.submit()" name="id_paciente" class="form-select">
    												<option value="">Selecione o paciente</option>
    												<c:forEach var="listaPaciente" items="${pacienteDAO.listarPaciente()}">
    													<c:choose>
    														<c:when test="${paciente.id_paciente == listaPaciente.id_paciente}">
    															<option value="${listaPaciente.id_paciente}"selected >${listaPaciente.nome}</option>	
    														</c:when>
    														<c:otherwise>
    															<option value="${listaPaciente.id_paciente}">${listaPaciente.nome}</option>
    														</c:otherwise>
    													</c:choose>
    												</c:forEach>
  												</select>
  												<label for="floatingSelect">Paciente</label>	
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							<div class="row my-4">
								<div class="col">
									<div class="form-floating">
										<input type="text" class="form-control" value="${paciente.nomeConvenio}" readonly>
 										<label for="floatingInput">Convenio</label>
									</div>
								</div>
								<div class="col">
									<div class="form-floating">
										<input type="text" class="form-control" id="data" value="${paciente.getDatanascBR()}" readonly>
 										<label for="floatingInput">Data de nascimento</label>
									</div>
								</div>
								<div class="col">
									<div class="form-floating">
										<input type="text" class="form-control" id="cpf" value="${paciente.cpf}" readonly>
 										<label for="floatingInput">CPF</label>
									</div>
								</div>
							</div>	
						</div>
						
						<div class="rounded shadow border p-3 mb-5">
							<div class="row my-3">
								<span class="h2 my-2">Dados do Doutor(a)</span>
								<div class="col">
									<div class="form-floating">
										<c:choose>
											<c:when test="${consulta.id_doutor != null}">
												<div class="form-floating mb-3">
													<input type="text" class="form-control"value="${doutor.nome}" readonly>
 													<label for="floatingInput">Doutor(a)</label>
												</div>
											</c:when>
											<c:otherwise>
  												<select onchange="this.form.submit()" name="id_doutor" class="form-select">
    												<option value="">Selecione o Doutor(a)</option>
    												<c:forEach var="listaDoutores" items="${funcionarioDAO.listarFuncionarioDoutores()}">
    													<c:choose>
    														<c:when test="${doutor.id_usuario == listaDoutores.id_usuario }">
    															<option value="${listaDoutores.id_usuario}" selected>${listaDoutores.nome}</option>
    														</c:when>
    														<c:otherwise>
    															<option value="${listaDoutores.id_usuario}">${listaDoutores.nome}</option>	
    														</c:otherwise>
    													</c:choose>
    												</c:forEach>
  												</select>
  												<label for="floatingSelect">Doutor(a)</label>	
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="form-floating">
										<input type="text" class="form-control" value="${doutor.especializacao}" readonly>
 										<label for="floatingInput">Especialização</label>
									</div>
								</div>
								<div class="col">
									<div class="form-floating">
										<input type="text" class="form-control" value="${doutor.documento}" readonly>
 										<label for="floatingInput">Documento</label>
									</div>
								</div>
								<div class="col">
									<div class="form-floating">
										<input type="text" class="form-control" value="${doutor.status ? "Ativo" : "Inativo"}" readonly>
 										<label for="floatingInput">Situação</label>
									</div>
								</div>
							</div>
						</div>
						
						<div class="rounded shadow border p-3 mb-5">
							<div class="row">
								<span class="h2 my-2">Dados da Consulta</span>
								<div class="col">
									<div class="form-floating">
										<input type="text" class="form-control" id="data" name="data" value="${consulta.getDataConsultaFormat()}">
 										<label for="floatingInput">Data da consulta</label>
									</div>
								</div>
								<div class="col">
									<div class="form-floating">
										<input type="text" class="form-control" id="hora" name="hora" value="${consulta.getTimeConsultaFormat()}">
 										<label for="floatingInput">Hora da consulta</label>
									</div>
								</div>
								<div class="col">
									<div class="form-floating">
										<select name="status" class="form-select">
											<c:forEach var="status" items="${statusDAO.listaStatus()}">
												<c:choose>
													<c:when test="${status.id_status == consulta.id_status}">
														<option value="${status.id_status}" selected>${status.status}</option>
													</c:when>
													<c:otherwise>
														<option value="${status.id_status}">${status.status}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
										<label for="floatingSelect">Status</label>
									</div>
								</div>
							</div>
							<c:if test="${sessionScope.nivel_acesso == 1 }">
								<div class="row mt-3">
									<div class="col">
										<div class="form-floating">
  											<textarea class="form-control" placeholder="Informações sobre a consulta" maxlength="300" id="anotacao" style="height: 100px" name="anotacao">${consulta.anotacao}</textarea>
  											<label for="anotacao">Anotação</label>
										</div>
									</div>
								</div>
							</c:if>
							<div class="row mt-3">		
								<div class="col">							
								<c:choose>
									<c:when test="${consulta.id_consulta == null}">
										<button type="submit" name="acao" value="gravar" class="btn btn-success">
											Gravar consulta 
											<i class="bi bi-pencil"></i>
										</button>
									</c:when>
									<c:otherwise>
  										<button type="submit" name="acao" value="alterar" class="btn btn-primary">
  											Gravar alteração
  											<i class="bi bi-pencil"></i>
  										</button>
									</c:otherwise>
								</c:choose>
								</div>
							</div>
						</div>
					</form>			
					<%@include file="/Sistema/includes/html/footer.jsp" %>
					<script>
					
						$(document).ready(function(){
							$("#cpf").inputmask("999.999.999-99");
							$('[id="data"]').inputmask("99/99/9999");
							$("#hora").inputmask("99:99");
						});
			</script>
	</body>	
</html>