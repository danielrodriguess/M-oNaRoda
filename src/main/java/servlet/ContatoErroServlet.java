package main.java.servlet;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.CadastroDao;
import main.java.dao.ContatoErroDao;
import main.java.dao.EnviaDao;
import main.java.model.Mensagem;
import main.java.model.Usuario;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet({"/contato","/contato/*"})
public class ContatoErroServlet extends HttpServlet {
	ContatoErroDao  erro;
	private static final long serialVersionUID = 1L;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContatoErroServlet() {
        super();
        erro = new ContatoErroDao();
            
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String acao = request.getParameter("acao");
		if(acao == null){
			contato(request,response);
		}else if(acao.equals("erro")){
			erro(request,response);
		}else if(acao.equals("enviacontato")){
			enviacontato(request,response);
		}else if(acao.equals("enviaerro")){
			enviaerro(request,response);
		}
	}

	private void enviaerro(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String merro = request.getParameter("erro");
		erro.enviarerro(email,merro);
		response.sendRedirect("index.jsp");
		
	}

	private void enviacontato(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String mensagem = request.getParameter("mensagem");
		erro.enviarcontato(email,mensagem);
		response.sendRedirect("index.jsp");
	}

	private void contato(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("contato.jsp");
		rd.forward(request, response);
	}
	
	private void erro(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
		rd.forward(request, response);	
	}
}
