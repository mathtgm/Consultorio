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
			
			if(acao == "gravar") {
				
				try {
					
				} catch (Exception e) {
					
				}
				
			} else if(acao == "alterar") {
				
				try {
					
				} catch (Exception e) {
				
				}
			}
			
		}
		
	}
	
	
	//Funcao que extrai os valores dos parametro no request e define um objeto consulta
	public Consulta setConsulta(HttpServletRequest req) {
		Consulta consulta = new Consulta();
		
		consulta.setAnotacao(req.getParameter("anotacao"));
		consulta.converteDataHoraConsulta(req.getParameter("data"), req.getParameter("hora"));
		consulta.setId_consulta(Integer.parseInt(req.getParameter("id_consulta")));
		consulta.setId_doutor(Integer.parseInt(req.getParameter("id_doutor")));
		consulta.setId_paciente(Integer.parseInt(req.getParameter("id_paciente")));
		consulta.setStatus(req.getParameter("status"));
		
		return consulta;
	}
	
	//Funcao para gravar consulta no banco de dados
	public void alterarConsulta(Consulta consulta) {
		ConsultaDAO cDAO = new ConsultaDAO();

		cDAO.alterarCosulta(consulta);
		
	}
	
	
	//Funcao para gravar consulta no banco de dados
	public void cadastrarConsulta(Consulta consulta) {
		ConsultaDAO cDAO = new ConsultaDAO();
		
		cDAO.gravarConsulta(consulta);
		
	}
	
	//Funcao para excluir uma consulta no banco de dados
	public void excluirConsulta(int id_consulta) {
		ConsultaDAO cDAO = new ConsultaDAO();
		
		cDAO.excluirConsulta(id_consulta);
		
	}
	
}
