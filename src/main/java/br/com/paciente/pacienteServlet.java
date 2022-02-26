package br.com.paciente;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pacienteServlet")
public class pacienteServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getAttribute("acao") == null) {
			pacienteDAO pDAO = new pacienteDAO();
			
			req.setAttribute("teste", pDAO);

			req.getRequestDispatcher("/Sistema/view/paciente/listaPaciente.jsp").forward(req, resp);
		} else if(req.getAttribute("acao").equals("gravar")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			
			Paciente paciente = new Paciente();
			pacienteDAO pDAO = new pacienteDAO();
			try {
				paciente.setDatanasc((Date) sdf.parse((String) req.getAttribute("datanasc")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			paciente.setCpf((String) req.getAttribute("cpf"));
			paciente.setNome((String) req.getAttribute("nome"));
			paciente.setId_convenio((Integer) req.getAttribute("convenio"));
			pDAO.gravarPaciente(paciente);
		}
	}
	
}
