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

$(document).ready(function(){
  mostrarCampoFuncionario();
});

function mostrarCampoFuncionario() {
	$(".nivel_acesso").click(function(){
		if (this.value == 1) {
			$(".especializacao").show();
			alert('1');
		} else {
			$(".especializacao").hide();
		}
	});
}