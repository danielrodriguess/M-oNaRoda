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
import main.java.dao.GerenciamentoDao;
import main.java.model.Oferta;
import main.java.model.Recomendacao;
import main.java.model.Usuario;

/**
 * Servlet implementation class GerenciamentoSERVLET
 */
@WebServlet("/gerenciamento")
public class GerenciamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    GerenciamentoDao gerenciar;  
    CadastroDao cadastro;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciamentoServlet() {
        super();
        gerenciar = new GerenciamentoDao();
        cadastro = new CadastroDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String idd = request.getParameter("idd");
		if(acao == null){
			response.sendRedirect("/MaoNaRoda/index.jsp");
		}else if(acao.equals("meuperfil") && idd.equals("perfil")){
			meuperfil(request,response);
		}else if(acao.equals("oferta")){
			verOferta(request,response);
		}else if(acao.equals("recomendacao")){
			verRecomendacao(request,response);
		}
	}

	private void verRecomendacao(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String idd = request.getParameter("idd");
		Integer id = Integer.parseInt(idd);
		ArrayList<Recomendacao> listarecomendacao = new ArrayList<Recomendacao>();
		listarecomendacao = gerenciar.buscarRecomendacao(id);
		ArrayList<Usuario> listausuario = new ArrayList<Usuario>();
		double resultado = 0.00;
		Integer valor = 0;
		for(int x = 0; x< listarecomendacao.size(); x++){
			listausuario = gerenciar.pegarUsuario(listarecomendacao.get(x).getIdavaliador());
			valor = listarecomendacao.get(x).getNota() + valor;
		}
		if(listarecomendacao.size() == 0){
			Object idof = request.getSession(false).getAttribute("ofertaatual");
			if(idof != null){
				Oferta oferta = new Oferta();
				Usuario usuarioo = new Usuario();
				Integer idoferta = Integer.parseInt(idof.toString());
				oferta = gerenciar.buscarOferta(idoferta);
				usuarioo = gerenciar.buscarUsuario(oferta);
				request.setAttribute("oferta", oferta);
				request.setAttribute("ofertante", usuarioo);
				RequestDispatcher rd = request.getRequestDispatcher("avaliacoes.jsp");
				rd.forward(request, response);
			}else{
				response.sendRedirect("/index.jsp");
			}
		}else{
			resultado = valor / listarecomendacao.size();
			Object idof = request.getSession(false).getAttribute("ofertaatual");
			if(idof != null){
				Oferta oferta = new Oferta();
				Usuario usuarioo = new Usuario();
				Integer idoferta = Integer.parseInt(idof.toString());
				oferta = gerenciar.buscarOferta(idoferta);
				usuarioo = gerenciar.buscarUsuario(oferta);
				request.setAttribute("oferta", oferta);
				request.setAttribute("ofertante", usuarioo);
				request.setAttribute("resultado", resultado);
				request.setAttribute("usuarioquecomentou", listausuario);
				request.setAttribute("listarecomendacao", listarecomendacao);
				RequestDispatcher rd = request.getRequestDispatcher("avaliacoes.jsp");
				rd.forward(request, response);
			}else{
				response.sendRedirect("/MaoNaRoda/index.jsp");
			}
		}
	}

	private void verOferta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Oferta oferta = new Oferta();
		Usuario usuario = new Usuario();
		String id = request.getParameter("idd");
		Integer idd = Integer.parseInt(id);
		oferta = gerenciar.buscarOferta(idd);
		if(gerenciar.verificaroferta > 0){
			usuario = gerenciar.buscarUsuario(oferta);
			Integer idof= oferta.getId();
			Object idser = request.getSession(false).getAttribute("idusuario");
			Integer fav = 0;
			if(idser != null){
				Integer idus = Integer.parseInt(idser.toString());
				fav = gerenciar.buscarfavorito(idus, idof);
			}
			if(fav > 0){
				request.setAttribute("favorito", fav);
				request.setAttribute("erro", "");
				request.setAttribute("oferta", oferta);
				request.setAttribute("ofertante", usuario);
				request.getSession().setAttribute("ofertaatual", oferta.getId());
				RequestDispatcher rd = request.getRequestDispatcher("oferta.jsp");
				rd.forward(request, response);
			}else{
				request.setAttribute("favorito", "");
				request.setAttribute("erro", "");
				request.setAttribute("oferta", oferta);
				request.setAttribute("ofertante", usuario);
				request.getSession().setAttribute("ofertaatual", oferta.getId());
				RequestDispatcher rd = request.getRequestDispatcher("oferta.jsp");
				rd.forward(request, response);
			}
		}else{
			request.setAttribute("erro", "Oferta não existe e/ou foi removida");
			RequestDispatcher rd = request.getRequestDispatcher("oferta.jsp");
			rd.forward(request, response);
		}
	}

	private void meuperfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object id = request.getSession(false).getAttribute("idusuario");
		if(id == null){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}else{
			Integer iduser = Integer.parseInt(id.toString());
			Integer valor = gerenciar.buscarreputacao(iduser);
			if(valor == 0){
				request.setAttribute("reputacao", "");
				RequestDispatcher rd = request.getRequestDispatcher("meuperfil.jsp");
				rd.forward(request, response);
			}else{
				request.setAttribute("reputacao", "ok");
				RequestDispatcher rd = request.getRequestDispatcher("meuperfil.jsp");
				rd.forward(request, response);
			}
		}
	}

}
