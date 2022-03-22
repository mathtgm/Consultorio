<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*"%>
<%@include file="/Sistema/includes/jsp/httprequest.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
	<html>
		<head>
			<title>
				<c:choose>
					<c:when test="${convenio.id_convenio != null }">
						Alteração de convênio
					</c:when>
					<c:otherwise>
						Cadastro de convênio
					</c:otherwise>
				</c:choose>	
			</title>		
			<%@include file="/Sistema/includes/html/head.jsp" %>
			<script src="/SistemaConsultorio/Sistema/includes/script/inputmask/dist/jquery.inputmask.js"></script>
		</head>
		<body>
			<%@include file="/Sistema/includes/html/navBar.jsp" %>
				<form method="get" action="/SistemaConsultorio/convenioServlet">
				 <input type="hidden" name="id" value="${convenio.id_convenio }">
					<div class="row">
						<div class="col">
							<div class="input-group mb-3">
  								<span class="input-group-text" id="inputGroup-sizing-default">Convênio</span>
  								<input maxlength="50" name="nome" type="text" class="form-control" aria-describedby="inputGroup-sizing-default" value="${convenio.nome}">
							</div>
						</div>
						<div class="row">
							<div class="col d-grid gap-2">
								<c:choose>
									<c:when test="${convenio.id_convenio != null }">
										<button class="btn btn-success btn-block" type="submit" name="acao" value="alterar"><i class="bi bi-pencil"></i>Alterar Convênio</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-success btn-block" type="submit" name="acao" value="cadastrar"><i class="bi bi-pencil"></i>Cadastrar Convênio</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</form>
			<%@include file="/Sistema/includes/html/footer.jsp" %>
		</body>
	</html>
