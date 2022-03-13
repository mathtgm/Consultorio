package br.com.paciente;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/pacienteServlet")
public class PacienteServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		if(req.getParameter("acao") == null) {
			req.getRequestDispatcher("/Sistema/view/paciente/listaPaciente.jsp").forward(req, resp);
		} else if(req.getParameter("acao").equals("consulta")) {
			req.setAttribute("paciente", consultarPaciente(Integer.parseInt(req.getParameter("idPaciente"))));
			req.getRequestDispatcher("/Sistema/view/paciente/formPaciente.jsp").forward(req, resp);
		} else if(req.getParameter("acao").equals("cadastrar")) {
			
			try {
				gravarPaciente(req);
				req.setAttribute("msg", "Paciente cadastrado com sucesso");
			} catch (Exception e) {
				req.setAttribute("erro", "ERRO AO CADASTRAR: " + e.getMessage());				
			} finally {
				req.getRequestDispatcher("/Sistema/view/paciente/listaPaciente.jsp").forward(req, resp);
			}
			
		} else if (req.getParameter("acao").equals("alterar")) {
			
			try {
				alterarPaciente(req);
				req.setAttribute("msg", "Paciente alterado com sucesso");
			} catch (Exception e) {
				req.setAttribute("erro", "ERRO AO ALTERAR: " + e.getMessage());
			} finally {
				req.getRequestDispatcher("/Sistema/view/paciente/listaPaciente.jsp").forward(req, resp);
			}
			
		} else if (req.getParameter("acao").equals("excluir")) {
			
			try {
				excluirPaciente(req);
				req.setAttribute("msg", "Paciente excluido com sucesso");
			} catch (Exception e) {
				req.setAttribute("erro", "ERRO AO EXCLUIR: " + e.getMessage());
			} finally {
				req.getRequestDispatcher("/Sistema/view/paciente/listaPaciente.jsp").forward(req, resp);
			}
			
		}
	}
	
	private void gravarPaciente(HttpServletRequest req) throws Exception {
		Paciente paciente = new Paciente();
		PacienteDAO pDAO = new PacienteDAO();
		
		try {
			paciente.setDatanascDate((String) req.getParameter("data"));
			paciente.setCpf((String) req.getParameter("cpf"));
			paciente.setNome((String) req.getParameter("nome"));
			paciente.setId_convenio(Integer.parseInt(req.getParameter("convenio")));
			pDAO.gravarPaciente(paciente);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void alterarPaciente(HttpServletRequest req) throws Exception {
		Paciente paciente = new Paciente();
		PacienteDAO pDAO = new PacienteDAO();
		
		try {
			paciente.setId_paciente(Integer.parseInt(req.getParameter("id")));
			paciente.setDatanascDate((String) req.getParameter("data"));
			paciente.setCpf(req.getParameter("cpf"));
			paciente.setNome(req.getParameter("nome"));
			paciente.setId_convenio(Integer.parseInt(req.getParameter("convenio")));
			pDAO.alterarPaciente(paciente);
		} catch (ParseException e){
			e.printStackTrace();
		}
	}
	
	private void excluirPaciente(HttpServletRequest req) throws Exception {
		PacienteDAO pDAO = new PacienteDAO();
		
		pDAO.excluirPaciente(Integer.parseInt(req.getParameter("idPaciente")));
	}
	
	private Paciente consultarPaciente(int idPaciente) {
		PacienteDAO pDAO = new PacienteDAO();

		return pDAO.consultarPaciente(idPaciente);
	}
}
