package main.java.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import main.java.dao.LoginDao;
import main.java.model.Oferta;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet({"/index","/index/*"})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginDao login;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        login = new LoginDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Oferta> listoferta = new ArrayList<Oferta>();
		listoferta = login.listarOferta();
		request.setAttribute("listaoferta", listoferta);
		RequestDispatcher rd = request.getRequestDispatcher("index2.jsp");
		rd.forward(request, response);
	}
}
