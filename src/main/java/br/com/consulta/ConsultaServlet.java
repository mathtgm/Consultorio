package br.com.consulta;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.funcionario.Funcionario;
import br.com.funcionario.FuncionarioDAO;
import br.com.paciente.Paciente;
import br.com.paciente.PacienteDAO;

@WebServlet("/consultaServlet")
public class ConsultaServlet extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getParameter("acao") != null) {
			
			if(req.getParameter("acao").equals("consultar")) {
				
				try {
					req.setAttribute("consulta", consultarConsulta(Integer.parseInt(req.getParameter("id_consulta"))));
					req.getRequestDispatcher("/Sistema/view/consulta/formConsulta.jsp").forward(req, resp);
				} catch (Exception e) {
					req.setAttribute("erro", "ERRO: " + e);
					req.getRequestDispatcher("/Sistema/view/consulta/listaConsulta.jsp").forward(req, resp);
				}
				
			} else if (req.getParameter("acao").equals("alterar")) {			
				
				try {
					alterarConsulta(setConsulta(req));
					req.setAttribute("msg", "Consulta alterada com sucesso");
				} catch (Exception e) {
					req.setAttribute("erro", "ERRO: " + e);
				} finally {
					req.getRequestDispatcher("/Sistema/view/consulta/listaConsulta.jsp").forward(req, resp);
				}				
				
			} else if (req.getParameter("acao").equals("gravar")) {				
				
				try {
					cadastrarConsulta(setConsulta(req));
					req.setAttribute("msg", "Consulta cadatrada com sucesso");
				} catch (Exception e) {
					req.setAttribute("erro", "ERRO: " + e);
				} finally {
					req.getRequestDispatcher("/Sistema/view/consulta/listaConsulta.jsp").forward(req, resp);
				}
				
			} else if(req.getParameter("acao").equals("excluir")) {
				
				try {
					excluirConsulta(Integer.parseInt(req.getParameter("id_consulta")));
					req.setAttribute("msg", "Consulta excluida com sucesso");
				} catch (Exception e) {
					req.setAttribute("erro", "ERRO: " + e);
				} finally {
					req.getRequestDispatcher("/Sistema/view/consulta/listaConsulta.jsp").forward(req, resp);
				}					
				
			} else {
					
					try {
						alterarConsulta(setConsulta(req));
						req.setAttribute("msg", "Consulta alterada com sucesso");
					} catch (Exception e) {
						req.setAttribute("erro", "ERRO: " + e);
					} finally {
						req.getRequestDispatcher("/Sistema/view/consulta/listaConsulta.jsp").forward(req, resp);
					}
								
			}
			
		} else {
			
			if(req.getParameter("id_paciente") != "") {
				req.setAttribute("paciente", getPaciente(Integer.parseInt(req.getParameter("id_paciente"))));
			}
			
			if(req.getParameter("id_doutor") != "") {
				req.setAttribute("doutor", getFuncionario(Integer.parseInt(req.getParameter("id_doutor"))));
			}
			
			req.getRequestDispatcher("/Sistema/view/consulta/formConsulta.jsp").forward(req, resp);
		}
		
	}
	
	//Funcao para consulta dados do paciente
	public Paciente getPaciente(int id_paciente) {
		PacienteDAO pDAO = new PacienteDAO();
		return pDAO.consultarPaciente(id_paciente);
	}
	
	//Funcao para consulta dados do doutor
	public Funcionario getFuncionario(int id_funcioario) {
		FuncionarioDAO fDAO = new FuncionarioDAO();
		return fDAO.consultarFuncionario(id_funcioario);
	}
	
	//Funcao que extrai os valores dos parametro no request e define um objeto consulta
	public Consulta setConsulta(HttpServletRequest req) {
		Consulta consulta = new Consulta();
		
		consulta.setAnotacao(req.getParameter("anotacao"));
		consulta.converteDataHoraConsulta(req.getParameter("data"), req.getParameter("hora"));
		consulta.setId_status(Integer.parseInt(req.getParameter("status")));
		if(req.getParameter("id_consulta").isEmpty()) {
			consulta.setId_doutor(Integer.parseInt(req.getParameter("id_doutor")));
			consulta.setId_paciente(Integer.parseInt(req.getParameter("id_paciente")));
		} else {
			consulta.setId_consulta(Integer.parseInt(req.getParameter("id_consulta")));
		}
		
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
	
	//Consulta no banco o id da consulta recebida
	public Consulta consultarConsulta(int id_consulta) {
		Consulta consulta = new Consulta();
		ConsultaDAO cDAO = new ConsultaDAO();
		
		consulta = cDAO.consultarConsulta(id_consulta);
		
		return consulta;
	}
}
