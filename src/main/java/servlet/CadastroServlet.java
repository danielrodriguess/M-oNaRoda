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

import main.java.dao.CadastroDao;
import main.java.dao.LoginDao;
import main.java.model.Oferta;
import main.java.model.Usuario;
import main.java.util.Criptografia;
import main.java.util.EnvioDeEmail;

/**
 * Servlet implementation class CadastroSERVLET
 */
@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CadastroDao cadastro;
    LoginDao login;
    Integer valor = 0;
    public static Integer idofertaatual;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroServlet() {
        super();
        cadastro = new CadastroDao();
        login = new LoginDao();
    }
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if(acao == null){
			mostrarcadastroDeUsuario(request,response);
		}else if(acao.equals("caduser")){
			try {
				cadastrarUsuario(request,response);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}else if(acao.equals("editinformacoesusuario")){
			editarinformacoesUsuario(request,response);
		}else if(acao.equals("editarcontatoUsuario")){
			editarcontatoUsuario(request,response);
		}else if(acao.equals("deleteusuario")){
			deletarUsuario(request,response);
		}else if(acao.equals("cadoferta")){
			cadastrarOferta(request,response);
		}else if(acao.equals("editoferta")){
			mostrareditarOferta(request,response);
		}else if(acao.equals("editaroferta")){
			editarOferta(request,response);
		}else if(acao.equals("deleteoferta")){
			deletarOferta(request,response);
		}else if(acao.equals("minhasofertas")){
			String valor = request.getParameter("idd");
			if(valor.equals("minhasofertas")){
				minhasOfertas(request,response);
			}
		}else if(acao.equals("mostraroferta")){
			String valora = request.getParameter("idd");
			if(valora.equals("oferta")){
				Object id = request.getSession(false).getAttribute("idusuario");
				Integer iduser = Integer.parseInt(id.toString());
				Integer valor = cadastro.contarofertas(iduser);
				request.setAttribute("ofertas", valor);
				RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
				rd.forward(request, response);
			}
		}
	}
	
	private void mostrarcadastroDeUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
	
	private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario = pegarUsuario(request, response);
		String verificaremail = login.pegaremail(usuario.getEmail());
		if(!verificaremail.equals("")){
			if(verificaremail.equals(usuario.getEmail())){
				request.setAttribute("loginErro", "Esse email já existe, clique no '+' e tente novamente");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}else{
				cadastro.cadastrarUsuario(usuario);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}else{
			cadastro.cadastrarUsuario(usuario);
			EnvioDeEmail envio = new EnvioDeEmail();
			envio.boasVindas(usuario.getEmail(), usuario.getNome());
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}
	
	private void editarinformacoesUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		String nome = request.getParameter("nome");
		String data = request.getParameter("data");
		String endereco = request.getParameter("endereco");
		Object id = request.getSession(false).getAttribute("idusuario");
		Integer iduser = Integer.parseInt(id.toString());
		cadastro.editarinformacoesUsuario(nome, data, iduser,endereco);
		usuario = login.attsessao(iduser);
		request.getSession().invalidate();
		request.getSession().setAttribute("usuario", usuario);
		request.getSession().setAttribute("idusuario", usuario.getId());
		Object ida = request.getSession(false).getAttribute("idusuario");
		Integer idusera = Integer.parseInt(ida.toString());
		Integer valor = cadastro.contarofertas(idusera);
		request.setAttribute("ofertas", valor);
		response.sendRedirect("/MaoNaRoda/gerenciamento/perfil");
	}
	
	private void editarcontatoUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		String telefonecelular = request.getParameter("telefonecelular");
		String telefonefixo = request.getParameter("telefonefixo");
		String email = request.getParameter("email");
		Object id = request.getSession(false).getAttribute("idusuario");
		Integer iduser = Integer.parseInt(id.toString());
		cadastro.editarcontatoUsuario(telefonecelular, telefonefixo,email, iduser);
		usuario = login.attsessao(iduser);
		request.getSession().invalidate();
		request.getSession().setAttribute("usuario", usuario);
		request.getSession().setAttribute("idusuario", usuario.getId());
		Object ida = request.getSession(false).getAttribute("idusuario");
		Integer idusera = Integer.parseInt(ida.toString());
		Integer valor = cadastro.contarofertas(idusera);
		request.setAttribute("ofertas", valor);
		response.sendRedirect("/MaoNaRoda/gerenciamento/perfil");
	}
	
	private void deletarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object ida = request.getSession(false).getAttribute("idusuario");
		Integer idusera = Integer.parseInt(ida.toString());
		cadastro.deletarUsuario(idusera);
		request.getSession().invalidate();
		response.sendRedirect("/MaoNaRoda/index.jsp");
	}
	
	private void cadastrarOferta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		valor = 0;
		Oferta oferta = new Oferta();
		oferta = pegarOferta(request, response);
		cadastro.cadastrarOferta(oferta);
		response.sendRedirect("/MaoNaRoda/index/minhasofertas");
	}
	
	private void mostrareditarOferta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		valor++;
		Oferta oferta = new Oferta();
		Oferta pegaroferta = new Oferta();
		oferta = pegarOferta(request, response);
		pegaroferta = cadastro.buscarOferta(oferta);
		request.setAttribute("editarOferta", pegaroferta);
		idofertaatual = pegaroferta.getId();
		RequestDispatcher rd = request.getRequestDispatcher("oferta-editar.jsp");
		rd.forward(request, response);
	}
	
	private void editarOferta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Oferta oferta = new Oferta();
		Oferta pegaroferta = new Oferta();
		oferta.setTitulo(request.getParameter("titulo"));
		oferta.setDescricao(request.getParameter("descricao"));
		oferta.setManha(request.getParameter("manha"));
		oferta.setTarde(request.getParameter("tarde"));
		oferta.setNoite(request.getParameter("noite"));
		String tipodeviagem = request.getParameter("tipodeviagem");
		if(tipodeviagem.equals("ida")){
			oferta.setOrigem(request.getParameter("origem1"));
		}else if(tipodeviagem.equals("volta")){
			oferta.setDestino(request.getParameter("destino1"));
		}else if(tipodeviagem.equals("idavolta")){
			oferta.setDestino(request.getParameter("destino1"));
			oferta.setOrigem(request.getParameter("origem2"));
		}
		oferta.setRemunerado(request.getParameter("remunerado"));
		oferta.setTipooferta(request.getParameter("tipooferta"));
		String idoferta = request.getParameter("idoferta");
		Integer id = Integer.parseInt(idoferta);
		oferta.setId(id);
		cadastro.editarOferta(oferta);
		pegaroferta = cadastro.buscarOferta(oferta);
		request.setAttribute("editarOferta", pegaroferta);
		RequestDispatcher rd = request.getRequestDispatcher("oferta-editar.jsp");
		rd.forward(request, response);
	}
	
	private void minhasOfertas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object id = request.getSession(false).getAttribute("idusuario");
		if(id != null){
			Integer iduser = Integer.parseInt(id.toString());
			ArrayList<Oferta> listaoferta = new ArrayList<Oferta>();
			listaoferta = cadastro.minhasofertas(iduser);
			request.setAttribute("listaoferta", listaoferta);
			RequestDispatcher rd = request.getRequestDispatcher("minhasofertas.jsp");
			rd.forward(request, response);
		}else{
			response.sendRedirect("/MaoNaRoda/index.jsp");
		}
	}
	
	private void deletarOferta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idofer = request.getParameter("id");
		Integer id = Integer.parseInt(idofer);
		cadastro.deletarOferta(id);
		Object idd = request.getSession(false).getAttribute("idusuario");
		if(idd != null){
			Integer iduser = Integer.parseInt(idd.toString());
			ArrayList<Oferta> listaoferta = new ArrayList<Oferta>();
			listaoferta = cadastro.minhasofertas(iduser);
			request.setAttribute("listaoferta", listaoferta);
			response.sendRedirect("/MaoNaRoda/index/minhasofertas");
		}else{
			response.sendRedirect("/MaoNaRoda/index.jsp");
		}
	}
	
	private Usuario pegarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		String senhaDescriptografada = request.getParameter("senha");
		String senhaCriptogtafada = Criptografia.criptografar(senhaDescriptografada);
		usuario.setSenha(senhaCriptogtafada);
		usuario.setTelefonecelular(request.getParameter("telefonecelular"));
		usuario.setTelefonefixo(request.getParameter("telefonefixo"));
		usuario.setDatadenascimento(request.getParameter("data"));
		return usuario;
	}
	
	private Oferta pegarOferta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Oferta oferta = new Oferta();
		if(valor == 0){
			oferta.setTitulo(request.getParameter("titulo"));
			oferta.setDescricao(request.getParameter("descricao"));
			oferta.setManha(request.getParameter("manha"));
			oferta.setTarde(request.getParameter("tarde"));
			oferta.setNoite(request.getParameter("noite"));
			String tipodeviagem = request.getParameter("tipodeviagem");
			if(tipodeviagem.equals("ida")){
				oferta.setOrigem(request.getParameter("origem1"));
			}else if(tipodeviagem.equals("volta")){
				oferta.setDestino(request.getParameter("destino1"));
			}else if(tipodeviagem.equals("idavolta")){
				oferta.setDestino(request.getParameter("destino1"));
				oferta.setOrigem(request.getParameter("origem2"));
			}
			oferta.setRemunerado(request.getParameter("remunerado"));
			oferta.setTipooferta(request.getParameter("tipooferta"));
			String iddouser = request.getParameter("id");
			Integer id = Integer.parseInt(iddouser);
			oferta.setIdusuario(id);
		}else{
			String idd = request.getParameter("id");
			Integer id = Integer.parseInt(idd);
			oferta.setId(id);
		}
		return oferta;
	}
}
