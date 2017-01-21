package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.model.Mensagem;
import main.java.model.Usuario;

public class EnviaDao {
	private Connection connection;
	public Integer x = 0;
	public static ArrayList<Integer> listremetente = new ArrayList<Integer>();
	public static ArrayList<Mensagem> listmensagem = new ArrayList<Mensagem>();
	public EnviaDao() {
		this.connection = new ConexaoDao().getConnection();
	}
	
	public void enviamensagem(Mensagem mensagem){
		String sql = "insert into mensagem (idremetente,iddestinatario,mensagem,datahora,lida) values (?,?,?,now(),'Nao')";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, mensagem.getIdremetente());
			ps.setInt(2, mensagem.getIddestinatario());
			ps.setString(3, mensagem.getMensagem());
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}

	public  Integer contamensagem(Integer iduser) {
		String sql = "SELECT count(*) as cont FROM mensagem WHERE iddestinatario = ? and lida = 'Nao'";
		Integer resultado = 0;
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, iduser);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				resultado = rs.getInt("cont");
			}
		}catch(SQLException e){
			
		}
		return resultado;
	}


	
	public ArrayList<Mensagem> chatt(Integer iddestino, Integer idremetente){
		String sql = "select * from mensagem where iddestinatario = ? and idremetente = ? or iddestinatario = ? and idremetente = ? order by datahora asc";
		ArrayList<Mensagem> listmensagemm = new ArrayList<Mensagem>();
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, iddestino);
			ps.setInt(2, idremetente);
			ps.setInt(3, idremetente);
			ps.setInt(4, iddestino);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Mensagem m = new Mensagem();
				m = preencherMensagem(rs);
				listmensagemm.add(m);
			}
		}catch(SQLException e2){
			
		}
		return listmensagemm; 
	}
	
	public ArrayList<Usuario> pegarRemetente(Integer iduser) {
		String sql = "Select * from usuario where id = ?";
		ArrayList<Usuario> listusuario = new ArrayList<Usuario>();
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, iduser);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario = preencherUsuario(rs);
				listusuario.add(usuario);
			}
		}catch(SQLException e){
			
		}
		return listusuario;
	}
	
	public ArrayList<Usuario> PegarTudo(Integer iduser) {
		String sql = "SELECT DISTINCT iddestinatario,idremetente FROM mensagem WHERE iddestinatario = ? or idremetente = ?";
		String sql1 = "Select DISTINCT * from usuario where id = ? or id = ?";
		ArrayList<Usuario> listusuario = new ArrayList<Usuario>();
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, iduser);
			ps.setInt(2, iduser);
			ResultSet rs = ps.executeQuery();
			Integer idremete = 0;
		    Integer iddestino = 0;
			while(rs.next()){
			    Integer idremetente = rs.getInt("idremetente");
			    Integer iddestinatario = rs.getInt("iddestinatario");
			    if(iduser != idremetente){
			    	listmensagem = ContarRemetente(idremetente);
					listremetente.add(x);
			    }
			    PreparedStatement ps1 = connection.prepareStatement(sql1);
			    ps1.setInt(1, iddestinatario);
			    ps1.setInt(2, idremetente);
			    idremete = idremetente;
			    iddestino = iddestinatario;
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()){
					Integer y = 0;
					for(int x = 0;x < listusuario.size();x++){
						if(listusuario.get(x).getId() == rs1.getInt("id")){
							y++;
						}
					}
					if(y > 0){
						
					}else{
						Usuario usuario = new Usuario();
						usuario = preencherUsuario(rs1);
						listusuario.add(usuario);
					}
				}
			}
		}catch(SQLException e){
			
		}
		return listusuario;
	}
	
	public ArrayList<Mensagem> ContarRemetente(Integer idremetente){
		ArrayList<Mensagem> list = new ArrayList<Mensagem>();
		String sql =  "SELECT DISTINCT *,count(*) as cont FROM mensagem WHERE idremetente = ? and lida = 'Nao'";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, idremetente);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Mensagem mensagem = new Mensagem();
				mensagem.setIddestinatario(rs.getInt("iddestinatario"));
				mensagem.setIdremetente(rs.getInt("idremetente"));
				mensagem.setNumero(rs.getInt("cont"));
				list.add(mensagem);
			}
		}catch(SQLException e){
			
		}
		return list;
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
		return u;
	}
	
	public Mensagem preencherMensagem(ResultSet rs) throws SQLException{
		Mensagem m = new Mensagem();
		m.setId(rs.getInt("id"));
		m.setIdremetente(rs.getInt("idremetente"));
		m.setIddestinatario(rs.getInt("iddestinatario"));
		m.setMensagem(rs.getString("mensagem"));
		return m;
	}

	public void enviamensagem(Integer remetente,Integer destinatario,String mensagem) {
		String sql = "insert into mensagem (idremetente,iddestinatario,mensagem,lida,datahora) values (?,?,?,'Nao',now())";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, remetente);
			ps.setInt(2, destinatario);
			ps.setString(3,mensagem);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
		
	}
	
	public void atualizaStatus(Integer iddestino, Integer idremetente){
		String sql = "update mensagem set lida = 'Sim' where iddestinatario = ? and idremetente = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, iddestino);
			ps.setInt(2, idremetente);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
}
