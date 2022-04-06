package br.com.consulta;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/consultaServlet")
public class ConsultaServlet extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getParameter("acao") != null) {
			
			String acao = req.getParameter("acao");
			
			if(acao == "alterar") {
				
			}
			
		}
		
	}
	
	public void alterarConsulta(HttpServletRequest req) {
		ConsultaDAO cDAO = new ConsultaDAO();
		Consulta consulta = new Consulta();
		
		consulta.setAnotacao(req.getParameter("anotacao"));
		consulta.converteDataHoraConsulta(req.getParameter("data"), req.getParameter("hora"));
		consulta.setId_consulta(Integer.parseInt(req.getParameter("id_consulta")));
		consulta.setId_doutor(Integer.parseInt(req.getParameter("id_doutor")));
		consulta.setId_paciente(Integer.parseInt(req.getParameter("id_paciente")));
		consulta.setStatus(req.getParameter("status"));
		
		cDAO.alterarCosulta(consulta);
		
	}
	
	public void cadastrarConsulta(HttpServletRequest req) {
		
	}	
	
}
