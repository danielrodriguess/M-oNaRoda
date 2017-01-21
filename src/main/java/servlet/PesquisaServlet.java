package main.java.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.PesquisaDao;
import main.java.model.Pesquisa;

/**
 * Servlet implementation class PesquisaSERVLET
 */
@WebServlet("/pesquisa")
public class PesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PesquisaDao pesquisa;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisaServlet() {
        super();
        pesquisa = new PesquisaDao();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if(acao == null){
			response.sendRedirect("/MaoNaRoda/index.jsp");
		}else if(acao.equals("pesquisa")){
			pesquisa(request,response);
		}
	}

	private void pesquisa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] filtro = request.getParameterValues("filtro");
		String termo = request.getParameter("termo");
		String ordenacao = request.getParameter("ordenacao");
		String consulta = "";
		String origemdestino = "";
		Integer valor = filtro.length;
		if(valor == 1){
			if(!ordenacao.equals("reputacao")){
				ArrayList<Pesquisa> listapesquisa = new ArrayList<Pesquisa>();
				listapesquisa = pesquisa.semfiltragem(termo);
				request.setAttribute("listaoferta", listapesquisa);
				request.setAttribute("entrei", "ok");
				RequestDispatcher rd = request.getRequestDispatcher("pesquisa.jsp");
				rd.forward(request,response);
			}else{
				ArrayList<Pesquisa> listapesquisa = new ArrayList<Pesquisa>();
				listapesquisa = pesquisa.semfiltragemReputacao(termo);
				request.setAttribute("listaoferta", listapesquisa);
				request.setAttribute("entrei", "ok");
				RequestDispatcher rd = request.getRequestDispatcher("pesquisa.jsp");
				rd.forward(request,response);
			}
		}else{
			for(int x = 0;x<filtro.length;x++){
				 if(x < filtro.length - 2){
					 if(filtro[x].equals("carros")){
						 consulta += "tipooferta = 'carro' and ";
					 }else if(filtro[x].equals("vans")){
						 consulta += "tipooferta = 'van' and "; 
					 }else if(filtro[x].equals("manha")){
						 consulta += "manha = 'sim' and "; 
					 }else if(filtro[x].equals("tarde")){
						 consulta += "tarde = 'sim' and ";  
					 }else if(filtro[x].equals("noite")){
						 consulta += "noite = 'sim' and ";  
					 }else if(filtro[x].equals("origem")){
						 origemdestino += "origem LIKE ? and ";
					 }else if(filtro[x].equals("destino")){
						 origemdestino += "destino LIKE ? and ";
					 }else if(filtro[x].equals("tipooferta")){
						 consulta += "remunerado = 'gratis' and ";
					 }
				 }else{
					 if(filtro[x].equals("carros")){
						 consulta += "tipooferta = 'carro'";
					 }else if(filtro[x].equals("vans")){
						 consulta += "tipooferta = 'van'"; 
					 }else if(filtro[x].equals("manha")){
						 consulta += "manha = 'sim'"; 
					 }else if(filtro[x].equals("tarde")){
						 consulta += "tarde = 'sim'";  
					 }else if(filtro[x].equals("noite")){
						 consulta += "noite = 'sim'";  
					 }else if(filtro[x].equals("origem")){
						 origemdestino += "origem LIKE ?";
					 }else if(filtro[x].equals("destino")){
						 origemdestino += "destino LIKE ?";
					 }else if(filtro[x].equals("tipooferta")){
						 consulta += "remunerado = 'gratis'";
					 }
				 }
			}
			if(!ordenacao.equals("reputacao")){
				ArrayList<Pesquisa> listapesquisa = new ArrayList<Pesquisa>();
				listapesquisa = pesquisa.filtragem(termo, consulta,origemdestino);
				request.setAttribute("consulta", consulta);
				request.setAttribute("listaoferta", listapesquisa);
				RequestDispatcher rd = request.getRequestDispatcher("pesquisa.jsp");
				rd.forward(request,response);
			}else{
				ArrayList<Pesquisa> listapesquisa = new ArrayList<Pesquisa>();
				listapesquisa = pesquisa.filtragemReputacao(termo,consulta,origemdestino);
				request.setAttribute("listaoferta", listapesquisa);
				request.setAttribute("entrei", "ok");
				RequestDispatcher rd = request.getRequestDispatcher("pesquisa.jsp");
				rd.forward(request,response);
			}
		}
	}
}