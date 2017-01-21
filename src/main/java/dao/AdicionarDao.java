package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.model.Favorito;
import main.java.model.Oferta;
import main.java.model.Recomendacao;
import main.java.model.Tag;

public class AdicionarDao {
	private Connection connection;
	
	public AdicionarDao() {
		this.connection = new ConexaoDao().getConnection();
	}
	
	public void adicionarFavorito(Favorito favorito){
		String sql = "insert into favorito (idoferta,idusuario) values (?,?)";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, favorito.getIdoferta());
			ps.setInt(2, favorito.getIdusuario());
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public ArrayList<Oferta> mostrarFavorito(Integer id){
		ArrayList<Oferta> listafav = new ArrayList<Oferta>();
		String sql = "select * from favorito where idusuario = ?";
		String sql2 = "select * from oferta where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Integer iddaoferta = rs.getInt("idoferta");
				PreparedStatement ps1 = connection.prepareStatement(sql2);
				ps1.setInt(1, iddaoferta);
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()){
					Oferta oferta = new Oferta();
					oferta = preencherOferta(rs1);
					listafav.add(oferta);
				}
			}
		}catch(SQLException e2){
			
		}
		return listafav;
	}
	
	public ArrayList<Favorito> buscarfav(Integer id){
		String sql = "select * from favorito where idusuario = ?";
		ArrayList<Favorito> list = new ArrayList<Favorito>();
		
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Favorito fav = new Favorito();
				fav.setId(rs.getInt("id"));
				list.add(fav);
			}
		}catch(SQLException e2){
			
		}
		return list;
	}
	
	public void removerFavorito(Integer id){
		String sql = "delete from favorito where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	
	public void adicionarTAG(Tag tag){
		String sql = "insert into tag (descricao,idoferta) values (?,?)";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, tag.getDescricao());
			ps.setInt(2, tag.getIdoferta());
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public ArrayList<Tag> mostrarTag(Integer id){
		ArrayList<Tag> listatag = new ArrayList<Tag>();
		String sql = "select * from tag where idoferta = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Tag tag = new Tag();
				tag.setId(rs.getInt("id"));
				tag.setDescricao(rs.getString("descricao"));
				tag.setIdoferta(rs.getInt("idoferta"));
				listatag.add(tag);
			}
		}catch(SQLException e2){
			
		}
		return listatag;
	}
	
	public void removerTag(Integer id){
		String sql = "delete from tag where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public void adicionarRecomendacao(Recomendacao recomendacao){
		double reco = 0.00;
		Integer quant = 0;
		String sql = "insert into recomendacao (idavaliacao,idavaliador,nota,mensagem,datahora) values (?,?,?,?,now())";
		String sql1 = "select * from recomendacao where idavaliacao = ?";
		String sql2 = "update oferta set nota = ? where idusuario = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, recomendacao.getIdavaliacao());
			ps.setInt(2, recomendacao.getIdavaliador());
			ps.setInt(3, recomendacao.getNota());
			ps.setString(4, recomendacao.getMensagem());
			ps.execute();
			ps.close();
			
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, recomendacao.getIdavaliacao());
			ResultSet rs = ps1.executeQuery();
			while(rs.next()){
				quant++;
				reco = rs.getInt("nota") + reco;
			}
			ps1.close();
			if(quant > 0){
				reco = reco / quant;
			}
			
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ps2.setDouble(1, reco);
			ps2.setInt(2, recomendacao.getIdavaliacao());
			ps2.execute();
			ps2.close();
		}catch(SQLException e2){
			
		}
	}
	
	public void removerRecomendacao(Integer id){
		double reco = 0.00;
		Integer quant = 0;
		String sql = "select * from recomendacao where id = ?";
		String sql1 = "delete from recomendacao where id = ?";
		String sql2 = "select * from recomendacao where idavaliacao = ?";
		String sql3 = "update oferta set nota = ? where idusuario = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Integer idavaliacao = 0;
			while(rs.next()){
				idavaliacao = rs.getInt("idavaliacao");
			}
			ps.close();
			
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, id);
			ps1.execute();
			ps1.close();
			
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ps2.setInt(1, idavaliacao);
			ResultSet rs1 = ps2.executeQuery();
			while(rs1.next()){
				quant++;
				reco = rs1.getInt("nota") + reco;
			}
			ps2.close();
			if(quant > 0){
				reco = reco / quant;
			}
			
			PreparedStatement ps3 = connection.prepareStatement(sql3);
			ps3.setDouble(1, reco);
			ps3.setInt(2, idavaliacao);
			ps3.execute();
			ps3.close();
		}catch(SQLException e2){
			
		}
	}

	public Oferta preencherOferta(ResultSet rs) throws SQLException{
		Oferta oferta = new Oferta();
		oferta.setId(rs.getInt("id"));
		oferta.setTitulo(rs.getString("titulo"));
		oferta.setDescricao(rs.getString("descricao"));
		oferta.setManha(rs.getString("manha"));
		oferta.setTarde(rs.getString("tarde"));
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