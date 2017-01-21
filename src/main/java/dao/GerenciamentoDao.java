package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.model.Oferta;
import main.java.model.Recomendacao;
import main.java.model.Usuario;

public class GerenciamentoDao {
	private Connection connection;
	public static Integer favorito = 0,verificaroferta = 0;
	public GerenciamentoDao() {
		this.connection = new ConexaoDao().getConnection();
	}
	public Integer buscarreputacao(Integer id){
		Integer valor = 0;
		String sql = "select count(*) as cont from recomendacao where idavaliacao = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				valor = rs.getInt("cont");
			}
		}catch(SQLException e2){
			
		}
		return valor;
	}
	public ArrayList<Recomendacao> preencherRecomenda(Integer iduser) {
		ArrayList<Recomendacao> listrecomenda = new ArrayList<Recomendacao>();
		String sql = "select * from recomendacao where idavaliacao = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, iduser);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Recomendacao recomenda = new Recomendacao();
				recomenda.setId(rs.getInt("id"));
				recomenda.setIdavaliacao(rs.getInt("idavaliacao"));
				recomenda.setIdavaliador(rs.getInt("idavaliador"));
				recomenda.setNota(rs.getInt("nota"));
				recomenda.setMensagem(rs.getString("mensagem"));
				recomenda.setDatahora(rs.getDate("datahora"));
				listrecomenda.add(recomenda);
			}
		}catch(SQLException e2){
			
		}
		return listrecomenda;
	}
	
	public ArrayList<Recomendacao> buscarRecomendacao(Integer id){
		ArrayList<Recomendacao> listrecomendacao = new ArrayList<Recomendacao>();
		String sql = "select * from recomendacao where idavaliacao = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Recomendacao recomendacao = new Recomendacao();
				recomendacao.setId(rs.getInt("id"));
				recomendacao.setMensagem(rs.getString("mensagem"));
				recomendacao.setNota(rs.getInt("nota"));
				recomendacao.setIdavaliacao(rs.getInt("idavaliacao"));
				recomendacao.setIdavaliador(rs.getInt("idavaliador"));
				recomendacao.setDatahora(rs.getDate("datahora"));
				listrecomendacao.add(recomendacao);
			}
		}catch(SQLException e2){
			
		}
		return listrecomendacao;
	}

	public ArrayList<Usuario> pegarUsuario(Integer id){
		ArrayList<Usuario> listusuario = new ArrayList<Usuario>();
		String sql = "select * from usuario where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setFoto(rs.getString("foto"));
				listusuario.add(usuario);
				System.out.print(""+usuario.getId());
			}
		}catch(SQLException e2){
			
		}
		return listusuario;
	}
	
	public Oferta buscarOferta(Integer id){
		verificaroferta = 0;
		Oferta ofe = new Oferta();
		String sql = "select * from oferta where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				verificaroferta++;
				ofe = preencherOferta(rs);
			}
		}catch(SQLException e2){
			
		}
		return ofe;
	}
	
	public Integer buscarfavorito(Integer usuario,Integer oferta){
		Integer resultado = 0;
		String sql = "select * from favorito where idusuario = ? and idoferta = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, usuario);
			ps.setInt(2, oferta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				resultado = rs.getInt("id");
			}
		}catch(SQLException e2){
			
		}
		return resultado;
	}
	
	public Usuario buscarUsuario(Oferta oferta){
		Usuario usuario = new Usuario();
		String sql = "select * from usuario where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, oferta.getIdusuario());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTelefonecelular(rs.getString("telefonecelular"));
				usuario.setTelefonefixo(rs.getString("telefonefixo"));
				usuario.setFoto(rs.getString("foto"));
			}

		}catch(SQLException e2){
			
		}
		return usuario;
	}
	
	public Oferta preencherOferta(ResultSet rs) throws SQLException{
		Oferta oferta = new Oferta();
		oferta.setId(rs.getInt("id"));
		oferta.setTitulo(rs.getString("titulo"));
		oferta.setDescricao(rs.getString("descricao"));
		oferta.setManha(rs.getString("manha"));
		oferta.setTarde(rs.getString("tarde"));
		oferta.setNoite(rs.getString("noite"));
		oferta.setOrigem(rs.getString("origem"));
		oferta.setDestino(rs.getString("destino"));
		oferta.setTipooferta(rs.getString("tipooferta"));
		oferta.setIdusuario(rs.getInt("idusuario"));
		oferta.setRemunerado(rs.getString("remunerado"));
		oferta.setStatus(rs.getString("status"));
		oferta.setFoto(rs.getString("foto"));
		return oferta;
	}
}
