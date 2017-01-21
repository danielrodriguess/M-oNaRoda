package main.java.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.dao.LoginDao;
import main.java.model.Usuario;
import main.java.util.Criptografia;
import main.java.util.EnvioDeEmail;

/**
 * Servlet implementation class LoginELogoutSERVLET
 */
@WebServlet("/login")
public class LoginELogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    LoginDao login;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginELogoutServlet() {
        super();
        login = new LoginDao();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if(acao == null){
			mostrarlogin(request,response);
		}else if(acao.equals("login")){
			try {
				login(request,response);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}else if(acao.equals("logout")){
			logout(request,response);
		}else if(acao.equals("esqueceu")){
			try {
				esqueceuEnvioDeEmail(request,response);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(acao.equals("trocarsenha")){
			mostrarMudarSenha(request,response);
		}else if(acao.equals("atualizandoSenha")){
			try {
				atualizaSenha(request,response);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

	private void esqueceuEnvioDeEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		String email = request.getParameter("email2");
		String verificaremail = login.pegaremail(email);
		if(!verificaremail.equals("")){
			String verificarRequisicao = login.pegarEsqueceu(email);
			if(!verificarRequisicao.equals("")){
				request.setAttribute("loginErro", "Já existe uma solicitação para esse email. Por favor cheque sua caixa de entrada! :)");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}else{
				int codigo = 500000+(int)(600000*Math.random());
				String cod = ""+codigo;
				String codigoCriptografado = Criptografia.criptografarCodigo(cod);
				String url = "http://localhost:8080/MaoNaRoda/login?acao=trocarsenha&email="+email+"&hash="+codigoCriptografado;
				EnvioDeEmail envio = new EnvioDeEmail();
				envio.send(email, login.nome, url);
				login.salvarRequisicaoEsqueceuaSenha(email, url);
				request.setAttribute("loginErro", "Clique no link que enviamos no seu email");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}else{
			request.setAttribute("loginErro", "Email não encontrado");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}
	
	private void mostrarMudarSenha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String hash = request.getParameter("hash");
		String url = "http://localhost:8080/MaoNaRoda/login?acao=trocarsenha&email="+email+"&hash="+hash;
		String verificarSolicitacao = login.verificarEsqueceu(email,url);
		if(!verificarSolicitacao.equals("")){
			request.setAttribute("url", url);
			request.setAttribute("email", email);
			RequestDispatcher rd = request.getRequestDispatcher("usuario-esqueceu.jsp");
			rd.forward(request, response);
		}else{
			request.setAttribute("loginErro", "Link inválido");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}
	
	private void atualizaSenha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		String url = request.getParameter("url");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String verificarSolicitacao = login.verificarEsqueceu(email,url);
		if(!verificarSolicitacao.equals("")){
			String senhaCriptografada = Criptografia.criptografar(senha);
			login.alteraSenha(email, senhaCriptografada);
			login.apagarequisicao(email,url);
			request.setAttribute("loginErro", "Senha alterada com sucesso. Logue-se!");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else{
			request.setAttribute("loginErro", "Link inválido");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	private void mostrarlogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, ServletException, IOException {
		request.getSession().invalidate();
		Usuario usuario = new Usuario();
		Usuario valor = pegarLogin(request, response);
		String senhaDescriptografada = request.getParameter("senha");
		String senhaCriptografada = Criptografia.criptografar(senhaDescriptografada);
		usuario = login.login(valor);
		if(senhaCriptografada.equals(usuario.getSenha())){
			request.getSession().invalidate();
			request.getSession().setAttribute("usuario", usuario);
			request.getSession().setAttribute("idusuario", usuario.getId());
			response.sendRedirect("index.jsp");
		}else{
			request.setAttribute("loginErro", "E-mail/senha inválidos");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		login.logado = 0;
		request.getSession().invalidate();
		response.sendRedirect("/MaoNaRoda/index.jsp");
	}
	
	private Usuario pegarLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setEmail(request.getParameter("email"));
		return usuario;
	}

}
