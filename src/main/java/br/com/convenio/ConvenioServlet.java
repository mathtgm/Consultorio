package br.com.convenio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/convenioServlet")
public class ConvenioServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			if(req.getParameter("acao") == null) {
				req.getRequestDispatcher("/Sistema/view/convenio/listaConvenio.jsp").forward(req, resp);
			} else if (req.getParameter("acao").equals("consultar")) {
				
				try {
					req.setAttribute("convenio", consultarConvenio(Integer.parseInt(req.getParameter("id_convenio"))));
				} catch (Exception e) {
					req.setAttribute("erro", "ERRO AO CONSULTAR O CONVENIO: " + e);
				} finally {
					req.getRequestDispatcher("/Sistema/view/convenio/formConvenio.jsp").forward(req, resp);
				}
				
			} else if (req.getParameter("acao").equals("cadastrar")) {
				
				try {
					cadastrarConvenio(req);
					req.setAttribute("msg", "Convenio cadastrado com sucesso");
				} catch (Exception e) {
					req.setAttribute("erro", "ERRO AO CADASTRAR O CONVENIO: " + e);
				}  finally {
					req.getRequestDispatcher("/Sistema/view/convenio/listaConvenio.jsp").forward(req, resp);
				}
				
			} else if (req.getParameter("acao").equals("alterar")) {
				
				try {
					alterarConvenio(req);
					req.setAttribute("msg", "Convenio alterado com sucesso");
				} catch (Exception e) {
					req.setAttribute("erro", "ERRO AO ALTERAR O CONVENIO: " + e);
				} finally {
					req.getRequestDispatcher("/Sistema/view/convenio/listaConvenio.jsp").forward(req, resp);
				}
				
			} else if (req.getParameter("acao").equals("excluir")) {
				
				try {
					excluirConvenio((Integer) req.getAttribute("id_convenio"));
				} catch (Exception e) {
					req.setAttribute("erro", "ERRO AO EXCLUIR O CONVENIO: " + e);
				} finally {
					req.getRequestDispatcher("/Sistema/view/convenio/listaConvenio.jsp").forward(req, resp);
				}
				
			}
		
	}
	
	public void cadastrarConvenio(HttpServletRequest req) {
		Convenio convenio = new Convenio();
		ConvenioDAO cDAO = new ConvenioDAO();
		
		convenio.setNome(req.getParameter("nome"));
		
		cDAO.cadastrarConvenio(convenio);
	}
	
	public void alterarConvenio(HttpServletRequest req) {
		Convenio convenio = new Convenio();
		ConvenioDAO cDAO = new ConvenioDAO();
		
		convenio.setId_convenio(Integer.parseInt(req.getParameter("id")));
		convenio.setNome(req.getParameter("nome"));
		
		cDAO.alterarConvenio(convenio);
	}
	
	public void excluirConvenio(int id_convenio) {
		ConvenioDAO cDAO = new ConvenioDAO();
		
		cDAO.excluirConvenio(id_convenio);
	}
	
	public Convenio consultarConvenio(int id_convenio) {
		ConvenioDAO cDAO = new ConvenioDAO();
		return  cDAO.consultarConvenio(id_convenio);
	}
	
}
