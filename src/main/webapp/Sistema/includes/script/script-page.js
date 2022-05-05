function alterarPaciente(idPaciente) {
	 window.location.href = "/SistemaConsultorio/pacienteServlet?idPaciente=" + idPaciente +"&acao=consulta";
}

function excluirPaciente(idPaciente) {
	window.location.href = "/SistemaConsultorio/pacienteServlet?idPaciente=" + idPaciente +"&acao=excluir";
}

function alterarConvenio(idConvenio) {
	window.location.href = "/SistemaConsultorio/convenioServlet?id_convenio=" + idConvenio +"&acao=consultar";
}

function excluirConvenio(idConvenio) {
	window.location.href = "/SistemaConsultorio/convenioServlet?id_convenio=" + idConvenio +"&acao=excluir";
}

function excluirFuncionario(idFuncionario) {
	window.location.href = "/SistemaConsultorio/funcionarioServlet?id_usuario=" + idFuncionario +"&acao=excluir";
}

function alterarFuncionario(idFuncionario) {
	window.location.href = "/SistemaConsultorio/funcionarioServlet?id_usuario=" + idFuncionario +"&acao=consultar";
}

function consulta(idConsulta) {
	window.location.href = "/SistemaConsultorio/consultaServlet?id_consulta=" + idConsulta +"&acao=consultar";
}

$(document).ready(function(){
	
	mostrarCampoFuncionario();
	
	//Ativar PopOvers da pagina
	var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
	var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
  			return new bootstrap.Popover(popoverTriggerEl)
	})
	
})



function mostrarCampoFuncionario() {
	
	if ($("input[name=nivel_acesso]:checked").val() == 1) {
		$(".especializacao").show();	
	} else {
		$(".especializacao").hide();
	}
	
	$(".nivel_acesso").click(function(){
		if (this.value == 1) {
			$(".especializacao").show();
		} else {
			$(".especializacao").hide();
		}
	});
}

function nextPage(pageIndex) {
	$("#numbPagina").val(pageIndex-1);
	$("#formHoje").submit();
}