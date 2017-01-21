package main.java.dao;

import java.util.ArrayList;

import main.java.model.Oferta;
import main.java.model.Usuario;
import java.sql.*;

public class LoginDao {
	private Connection connection;
	public static String nome = "";
	public static Integer logado;
	public LoginDao() {
		this.connection = new ConexaoDao().getConnection();
	}
	
	public Usuario login(Usuario usuario){
		Usuario user = new Usuario();
		String sql = "select * from usuario where email = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, usuario.getEmail());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				logado = 1;
				user = preencherUsuario(rs);
			}
		}catch(SQLException e){
			
		}
		return user;
	}
	
	public String pegaremail(String usuario){
		String user = ""; 
		String sql = "select * from usuario where email = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, usuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				user = rs.getString("email");
				nome= rs.getString("nome");
			}
		}catch(SQLException e){
			
		}
		return user;
	}
	
	public Usuario attsessao(Integer id){
		Usuario user = new Usuario();
		String sql = "select * from usuario where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				user = preencherUsuario(rs);
			}
		}catch(SQLException e){
			
		}
		return user;
	}
	
	public String pegarEsqueceu(String email){
		String user = "";
		String sql = "select * from trocadesenha where email = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				user = rs.getString("email");
			}
		}catch(SQLException e2){
			
		}
		return user;
	}
	
	public String verificarEsqueceu(String email,String url){
		String user = "";
		String sql = "select * from trocadesenha where email = ? and url = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, url);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				user = rs.getString("email");
			}
		}catch(SQLException e2){
			
		}
		return user;
	}
	
	public void alteraSenha(String email,String senha){
		String sql = "update usuario set senha = ? where email = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, senha);
			ps.setString(2, email);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public void salvarRequisicaoEsqueceuaSenha(String email, String url){
		String sql = "insert into trocadesenha (email,url) values (?,?)";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, url);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public void apagarequisicao(String email,String url){
		String sql = "delete from trocadesenha where email = ? and url = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, url);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public ArrayList<Oferta> listarOferta(){
		ArrayList<Oferta> listoferta = new ArrayList<Oferta>();
		String sql = "select * from oferta";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Oferta oferta = new Oferta();
				oferta = preencherOferta(rs);
				listoferta.add(oferta);
			}
		}catch(SQLException e2){
			
		}
		return listoferta;
	}
	
	public ArrayList<Oferta> ofertarecomendada(Integer iduser){
		ArrayList<Oferta> ofertarecomendada = new ArrayList<Oferta>();
		String sql = "select * from usuario where id = ?";
		String sql1 = "select * from oferta where origem LIKE ? || destino LIKE ?";
		String sql2 = "select * from tag where descricao LIKE ?";
		String sql3 = "select * from oferta where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, iduser);
			ResultSet rs = ps.executeQuery();
			Integer verificar = 0;
			while(rs.next()){
				String endereco = rs.getString("endereco");
				if(!endereco.equals("")){
					PreparedStatement ps1 = connection.prepareStatement(sql1);
					ps1.setString(1, "%"+endereco+"%");
					ps1.setString(2, "%"+endereco+"%");
					ResultSet rs1 = ps1.executeQuery();
					while(rs1.next()){
						verificar++;
						Oferta o = new Oferta();
						o = preencherOferta(rs1);
						ofertarecomendada.add(o);
					}
					if(verificar == 0){
						PreparedStatement ps2 = connection.prepareStatement(sql2);
						ps2.setString(1, "%"+endereco+"%");
						ResultSet rs2 = ps2.executeQuery();
						while(rs2.next()){
							Integer idoferta = rs.getInt("idoferta");
							PreparedStatement ps3 = connection.prepareStatement(sql3);
							ps3.setString(1, "%"+endereco+"%");
							ResultSet rs3 = ps3.executeQuery();
							while(rs3.next()){
								Oferta o = new Oferta();
								o = preencherOferta(rs3);
								ofertarecomendada.add(o);
							}
						}
					}
				}
			}
		}catch(SQLException e2){
			
		}
		return ofertarecomendada;
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
	
	public Usuario preencherUsuario(ResultSet rs) throws SQLException{
		Usuario u = new Usuario();
		u.setId(rs.getInt("id"));
		u.setNome(rs.getString("nome"));
		u.setFoto(rs.getString("foto"));
		u.setEmail(rs.getString("email"));
		u.setSenha(rs.getString("senha"));
		u.setTelefonecelular(rs.getString("telefonecelular"));
		u.setTelefonefixo(rs.getString("telefonefixo"));
		u.setDatadenascimento(rs.getString("datadenascimento"));
		u.setTipoderota(rs.getString("tipoderota"));
		u.setEndereco(rs.getString("endereco"));
		return u;
	}
}