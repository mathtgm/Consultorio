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
		ConsultaDAO cDAO = new ConsultaDAO();
		cDAO.consultaDiaMedico(0);
		super.service(req, resp);
	}
}
