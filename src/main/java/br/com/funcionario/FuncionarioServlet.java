package br.com.funcionario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/funcionarioServlet")
public class FuncionarioServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("acao") != null) {
			if(req.getParameter("acao").equals("autenticar")) {
				
				try {
					
					if(autenticarUsuario(req)) {
						req.getRequestDispatcher("/Sistema/view/index.jsp").forward(req, resp);
					} else {
						req.setAttribute("erro", "Usuario/Senha incorretos");
						req.getRequestDispatcher("/Sistema/view/login.jsp").forward(req, resp);
					}
					
				} catch (Exception e) {
					req.setAttribute("erro", "ERRO AO AUTENTICAR: " + e);
					req.getRequestDispatcher("/Sistema/view/login.jsp").forward(req, resp);
				}
			}
		} else {
			req.getRequestDispatcher("/Sistema/view/login.jsp").forward(req, resp);
		}
		
	}
	
	//Coloca atributos na sessao do usuario
	public boolean autenticarUsuario(HttpServletRequest req) {
		FuncionarioDAO fDAO = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		funcionario = fDAO.autenticaFuncionario(req.getParameter("usuario"), req.getParameter("senha"));
		if(funcionario.getId_usuario() != 0) {
			req.getSession().setAttribute("nome", funcionario.getNome());
			req.getSession().setAttribute("nivel_acesso", funcionario.getNivel_acesso());
			req.getSession().setAttribute("id_usuario", funcionario.getId_usuario());
			return true;
		} else {
			return false;
		}
		
	}
	
	//Cadastra um novo funcionario
	public void cadastrarFuncionario(HttpServletRequest req) {
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO fDAO = new FuncionarioDAO();
		
		funcionario.setEspecializacao(req.getParameter("cargo"));
		funcionario.setDocumento(req.getParameter("documento"));
		funcionario.setId_usuario(Integer.parseInt(req.getParameter("id_usuario")));
		funcionario.setNivel_acesso(Integer.parseInt(req.getParameter("nivel_acesso")));
		funcionario.setNome(req.getParameter("nome"));
		funcionario.setSenha(req.getParameter("senha"));
		funcionario.setStatus(Boolean.parseBoolean(req.getParameter("staus")));
		funcionario.setUsuario(req.getParameter("usuario"));

		fDAO.gravarFuncionario(funcionario);
	}
	
	//Altera as informacoes do funcionario
	public void alterarFuncionario(HttpServletRequest req) {
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO fDAO = new FuncionarioDAO();
		
		funcionario.setEspecializacao(req.getParameter("cargo"));
		funcionario.setDocumento(req.getParameter("documento"));
		funcionario.setId_usuario(Integer.parseInt(req.getParameter("id_usuario")));
		funcionario.setNivel_acesso(Integer.parseInt(req.getParameter("nivel_acesso")));
		funcionario.setNome(req.getParameter("nome"));
		funcionario.setSenha(req.getParameter("senha"));
		funcionario.setStatus(Boolean.parseBoolean(req.getParameter("staus")));
		funcionario.setUsuario(req.getParameter("usuario"));

		fDAO.alterarFuncionario(funcionario);
	}
	
	//Excluir um funcionario
	public void excluirFuncionario(int id_usuario) {
		FuncionarioDAO fDAO = new FuncionarioDAO();
		
		fDAO.excluirFuncionario(id_usuario);
	}
	
	
}
