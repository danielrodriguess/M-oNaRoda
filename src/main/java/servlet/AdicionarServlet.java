package main.java.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.AdicionarDao;
import main.java.model.Favorito;
import main.java.model.Oferta;
import main.java.model.Recomendacao;
import main.java.model.Tag;

/**
 * Servlet implementation class AdicionarSERVLET
 */
@WebServlet("/adicionar")
public class AdicionarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AdicionarDao adicionar;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionarServlet() {
        super();
        adicionar = new AdicionarDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if(acao == null){
			response.sendRedirect("/MaoNaRoda/index.jsp");
		}else if(acao.equals("tag")){
			tag(request,response);
		}else if(acao.equals("mostrartag")){
			mostrarTag(request,response);
		}else if(acao.equals("removertag")){
			removerTag(request, response);
		}else if(acao.equals("recomendacao")){
			recomendacao(request,response);
		}else if(acao.equals("apagarrecomendacao")){
			apagarRecomendacao(request,response);
		}else if(acao.equals("favorito")){
			favorito(request,response);
		}else if(acao.equals("mostrarfavorito")){
			mostrarFavorito(request,response);
		}else if(acao.equals("removerfavorito")){
			removerfavorito(request,response);
		}else if(acao.equals("removerfavoritooferta")){
			removerfavoritooferta(request,response);
		}
	}

	private void removerfavoritooferta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idfav = request.getParameter("id");
		Integer id = Integer.parseInt(idfav);
		String oferta = request.getParameter("idoferta");
		Integer idoferta = Integer.parseInt(oferta);
		adicionar.removerFavorito(id);
		response.sendRedirect("/MaoNaRoda/pesquisa/"+idoferta);
	}

	private void removerfavorito(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idfav = request.getParameter("id");
		Integer id = Integer.parseInt(idfav);
		adicionar.removerFavorito(id);
		response.sendRedirect("/MaoNaRoda/perfil/meusfavoritos");
	}

	private void favorito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Favorito favorito = new Favorito();
		favorito = pegarFavoritos(request, response);
		adicionar.adicionarFavorito(favorito);
		response.sendRedirect("/MaoNaRoda/pesquisa/"+favorito.getIdoferta());
	}
	
	private void mostrarFavorito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object id = request.getSession(false).getAttribute("idusuario");
		if(id != null){
			Integer iduser = Integer.parseInt(id.toString());
			ArrayList<Oferta> listaoferta = new ArrayList<Oferta>();
			listaoferta = adicionar.mostrarFavorito(iduser);
			ArrayList<Favorito> lisfavorito = new ArrayList<Favorito>();
			lisfavorito = adicionar.buscarfav(iduser);
			request.setAttribute("listaoferta",listaoferta);
			request.setAttribute("favorito",lisfavorito);
			RequestDispatcher rd = request.getRequestDispatcher("favoritos.jsp");
			rd.forward(request, response);
		}else{
			response.sendRedirect("/MaoNaRoda/index.jsp");
		}
	}

	private void recomendacao(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Recomendacao recomendacao = new Recomendacao();
		recomendacao = pegarRecomendacao(request, response);
		adicionar.adicionarRecomendacao(recomendacao);
		response.sendRedirect("/MaoNaRoda/recomendacao/"+recomendacao.getIdavaliacao());
	}
	
	private void apagarRecomendacao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		Integer idrec = Integer.parseInt(id);
		String idofer = request.getParameter("idofer");
		adicionar.removerRecomendacao(idrec);
		response.sendRedirect("/MaoNaRoda/recomendacao/"+idofer);
	}

	private void tag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tag tag = new Tag();
		tag = pegarTag(request, response);
		adicionar.adicionarTAG(tag);
		ArrayList<Tag> favorito = new ArrayList<Tag>();
		favorito = adicionar.mostrarTag(tag.getIdoferta());
		request.setAttribute("oferta",tag.getIdoferta());
		request.setAttribute("listafavorito",favorito);
		RequestDispatcher rd = request.getRequestDispatcher("tags.jsp");
		rd.forward(request, response);
	}
	
	private void mostrarTag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idoferta = request.getParameter("idoferta");
		Integer id = Integer.parseInt(idoferta);
		ArrayList<Tag> favorito = new ArrayList<Tag>();
		favorito = adicionar.mostrarTag(id);
		request.setAttribute("oferta",id);
		request.setAttribute("listafavorito",favorito);
		RequestDispatcher rd = request.getRequestDispatcher("tags.jsp");
		rd.forward(request, response);
	}
	
	private void removerTag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tag = request.getParameter("id");
		Integer idtag = Integer.parseInt(tag);
		String idoferta = request.getParameter("idoferta");
		adicionar.removerTag(idtag);
		response.sendRedirect("/MaoNaRoda/adicionar?acao=mostrartag&idoferta="+idoferta);
	}
	
	private Favorito pegarFavoritos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Favorito favorito = new Favorito();
		String iddaoferta = request.getParameter("idoferta");
		String iddouser = request.getParameter("iduser");
		Integer idoferta = Integer.parseInt(iddaoferta);
		Integer iduser = Integer.parseInt(iddouser);
		favorito.setIdoferta(idoferta);
		favorito.setIdusuario(iduser);
		return favorito;
	}
	
	private Recomendacao pegarRecomendacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Recomendacao recomendacao = new Recomendacao();
		String id1 = request.getParameter("idavaliacao");
		Integer idavaliacao = Integer.parseInt(id1);
		recomendacao.setIdavaliacao(idavaliacao);
		
		String id2 = request.getParameter("idavaliador");
		Integer idavaliador = Integer.parseInt(id2);
		recomendacao.setIdavaliador(idavaliador);
		
		String id3 = request.getParameter("nota");
		Integer nota = Integer.parseInt(id3);
		if(nota > 5){
			nota = 5;
		}else if(nota < 0){
			nota = 0;
		}
		recomendacao.setNota(nota);
		
		recomendacao.setMensagem(request.getParameter("comentario"));
		
		return recomendacao;
	}
	
	private Tag pegarTag(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Tag tag = new Tag();
		tag.setDescricao(request.getParameter("descricao"));
		String idofer = request.getParameter("idoferta");
		Integer idoferta = Integer.parseInt(idofer);
		tag.setIdoferta(idoferta);
		return tag;
	}
}
