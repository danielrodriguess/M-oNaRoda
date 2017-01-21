package main.java.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.EnviaDao;
import main.java.model.Mensagem;
import main.java.model.Usuario;

/**
 * Servlet implementation class EnviaServlet
 */
@WebServlet("/mensagem")
public class EnviaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnviaDao envia;
    public static Integer idremetente = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviaServlet() {
        super();
        envia = new EnviaDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if(acao == null){
			response.sendRedirect("/MaoNaRoda/index.jsp");
		}else if(acao.equals("envia")){
			try {
				envia(request,response);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}else if(acao.equals("chat")){
			chat(request,response);
		}else if(acao.equals("enviamensagem")){
			enviamensagem(request,response);
		}
	}
	
	private void enviamensagem(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String mensagem = request.getParameter("mensagem");
		Object iduser = request.getSession(false).getAttribute("idusuario");
		Integer idduser = Integer.parseInt(iduser.toString());
		envia.enviamensagem(idduser,idremetente,mensagem);
		response.sendRedirect("/MaoNaRoda/mensagem?acao=chat&id="+idremetente);
	}

	private void chat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		idremetente = Integer.parseInt(request.getParameter("id"));
		Object iduser = request.getSession(false).getAttribute("idusuario");
		if(iduser != null){
			Integer idduser = Integer.parseInt(iduser.toString());
			ArrayList<Mensagem> listademensagem = new ArrayList<Mensagem>();
			listademensagem = envia.chatt(idduser, idremetente);
			ArrayList<Usuario> user = new ArrayList<Usuario>();
			user = envia.pegarRemetente(idremetente);
			envia.atualizaStatus(idduser,idremetente);
			request.setAttribute("userremetenteee", user);
			request.setAttribute("listmensagemmm", listademensagem);
			RequestDispatcher rd = request.getRequestDispatcher("mensagem.jsp");
			rd.forward(request, response);
		}else{
			response.sendRedirect("index.jsp");
		}
	}

	private void envia(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
		Mensagem mensagem = new Mensagem();
		mensagem = pegarMensagem(request, response);
		String idoferta = request.getParameter("idoferta");
		envia.enviamensagem(mensagem);
		response.sendRedirect("/MaoNaRoda/pesquisa/"+idoferta);
	}
	
	private Mensagem pegarMensagem(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
		Mensagem mensagem = new Mensagem();
		String idofertante = request.getParameter("idofertante");
		Integer idofer = Integer.parseInt(idofertante);
		mensagem.setIddestinatario(idofer);
		
		String id = request.getParameter("id");
		Integer iduser = Integer.parseInt(id);
		mensagem.setIdremetente(iduser);
		
		mensagem.setMensagem(request.getParameter("mensagem"));
		return mensagem;
	}

}
