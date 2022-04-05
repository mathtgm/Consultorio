<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	include file="/Sistema/includes/jsp/httprequest.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
	<html>
		<head>
			<title>Lista de Funcinários</title>
			<%@include file="/Sistema/includes/html/head.jsp" %>
		</head>
		<body>
			<%@include file="/Sistema/includes/html/navBar.jsp" %>
			<a class="btn btn-success mb-3" href="/SistemaConsultorio/Sistema/view/funcionario/formFuncionario.jsp"><i class="bi bi-person-plus-fill pe-1"></i>Cadastrar funcionário</a>
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
					<th>Cargo</th>
					<th>Especilização</th>
					<th>Usuário</th>
					<th>Status</th>					
					<th>Ação</th>
				</tr>
				<jsp:useBean id="funcionarioDAO" class="br.com.funcionario.FuncionarioDAO"></jsp:useBean>
				<c:set var="listaFuncionario" value="${funcionarioDAO.listarFuncionario()}"></c:set>
				<c:choose>
					<c:when test="${listaFuncionario.isEmpty()}">
						<tr>
							<td class="align-middle">Nenhum funcionário cadastrado</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${listaFuncionario}" var="funcionario">	
						<tr>
							<td class="align-middle">
								${funcionario.nome}
							</td>
							<td class="align-middle">
								${funcionario.nivel_acesso == 1 ? "Doutor(a)" : "Secretaria(o)"}
							</td>
							<td class="align-middle">
								${funcionario.especializacao == "" ? "Sem Especilização" : funcionario.especializacao}
							</td>
							<td class="align-middle">
								${funcionario.usuario}
							</td>
							<td class="align-middle">
								${funcionario.status ? "Ativo" : "Inativo"}
							</td>
							
							<c:choose>
								<c:when test="${sessionScope.nivel_acesso == 1 }" >
									<td>
										<div class="dropdown">
  											<button class="btn disable dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">Ação</button>
  											<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
    											<li>
    												<a class="dropdown-item link-primary" onclick="alterarFuncionario(${funcionario.id_usuario})"><i class="bi bi-pencil"></i>Editar</a>
												</li> 	
    											<li>
    												<a class="dropdown-item link-danger" onclick="excluirFuncionario(${funcionario.id_usuario})"><i class="bi bi-trash-fill"></i>Excluir</a>
    											</li>
  											</ul>
										</div>
									</td>
								</c:when>
								<c:otherwise>
									<td>
										<span class="d-inline-block" tabindex="0" data-bs-toggle="popover" data-bs-trigger="hover focus" data-bs-content="Sem permissão">
  											<button class="btn disable dropdown-toggle" type="button" disabled>Ação</button>
										</span>
									</td> 
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>			
				</c:otherwise>
				
				</c:choose>					
						
			</table>

			<%@include file="/Sistema/includes/html/footer.jsp" %>
		</body>
</html>