package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.model.Oferta;
import main.java.model.Usuario;

public class CadastroDao {
	private Connection connection;
	
	public CadastroDao() {
		this.connection = new ConexaoDao().getConnection();
	}
	
	public void cadastrarUsuario(Usuario usuario){
		String sql = "insert into usuario (nome,foto,email,senha,telefonecelular,telefonefixo,datadenascimento) values (?,?,?,?,?,?,?)";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, "img/fotodeperfil/temporaria.jpg");
			ps.setString(3, usuario.getEmail());
			ps.setString(4, usuario.getSenha());
			if(usuario.getTelefonecelular().equals("")){
				ps.setString(5, null);
			}else{
				ps.setString(5, usuario.getTelefonecelular());
			}
			if(usuario.getTelefonefixo().equals("")){
				ps.setString(6, null);
			}else{
				ps.setString(6, usuario.getTelefonefixo());
			}
			if(usuario.getDatadenascimento().equals("")){
				ps.setString(7, null);
			}else{
				ps.setString(7, usuario.getDatadenascimento());
			}
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			System.out.println("errocada");
		}
	}
	
	public void editarinformacoesUsuario(String nome, String datadenascimento,Integer id,String endereco){
		String sql = "update usuario set nome = ?,datadenascimento = ?,endereco = ? where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, datadenascimento);
			ps.setString(3, endereco);
			ps.setInt(4, id);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public void editarcontatoUsuario(String telefonecelular,String telefonefixo,String email,Integer id){
		String sql = "update usuario set telefonecelular = ?,telefonefixo = ?,email = ? where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, telefonecelular);
			ps.setString(2, telefonefixo);
			ps.setString(3, email);
			ps.setInt(4, id);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public void deletarUsuario(Integer id){
		String sql = "delete from usuario where id = ?";
		String sql1 = "delete from mensagem where idremetente = ? or iddestinatario = ?";
		String sql2 = "delete from favorito where idusuario = ?";
		String sql3 = "delete from recomendacao where idavaliador = ? or idavaliacao = ?";
		String sql4 = "select * from oferta where idusuario = ?";
		String sql5 = "delete from tag where idoferta = ?";
		String sql6 = "delete from oferta where idusuario = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();	
		}catch(SQLException e2){
			System.out.println("erro1");
		}
		
		try{
			PreparedStatement ps = connection.prepareStatement(sql1);
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.execute();
			ps.close();	
		}catch(SQLException e2){
			System.out.println("erro2");
		}
		
		try{
			PreparedStatement ps = connection.prepareStatement(sql2);
			ps.setInt(1, id);
			ps.execute();
			ps.close();	
		}catch(SQLException e2){
			System.out.println("erro3");
		}
		
		try{
			PreparedStatement ps = connection.prepareStatement(sql3);
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.execute();
			ps.close();	
		}catch(SQLException e2){
			System.out.println("erro4");
		}
			
		try{
			PreparedStatement ps = connection.prepareStatement(sql4);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Integer idoferta = rs.getInt("id");
				PreparedStatement ps1 = connection.prepareStatement(sql5);
				ps1.setInt(1, idoferta);
				ps1.execute();
				ps1.close();
			}
			ps.close();	
		}catch(SQLException e2){
			System.out.println("erro5");
		}
			
			
		try{
			PreparedStatement ps = connection.prepareStatement(sql6);
			ps.setInt(1, id);
			ps.execute();
			ps.close();	
		}catch(SQLException e2){
			System.out.println("erro6");
		}
	}
	
	public void cadastrarOferta(Oferta oferta){
		double recomendacao = 0.00;
		Integer quant = 0;
		String sql1 = "select * from recomendacao where idavaliacao = ?";
		String sql = "insert into oferta (titulo,descricao,manha,tarde,noite,origem,destino,tipooferta,idusuario,remunerado,status,foto,nota) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, oferta.getIdusuario());
			ResultSet rs = ps1.executeQuery();
			while(rs.next()){
				quant++;
				recomendacao = rs.getInt("nota") + recomendacao;
			}
			if(quant > 0){
				recomendacao = recomendacao / quant;
			}
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, oferta.getTitulo());
			ps.setString(2, oferta.getDescricao());
			ps.setString(3, oferta.getManha());
			ps.setString(4, oferta.getTarde());
			ps.setString(5, oferta.getNoite());
			ps.setString(6, oferta.getOrigem());
			ps.setString(7, oferta.getDestino());
			ps.setString(8, oferta.getTipooferta());
			ps.setInt(9, oferta.getIdusuario());
			ps.setString(10, oferta.getRemunerado());
			ps.setString(11, "Disponível");
			if(oferta.getTipooferta().equals("carro")){
				ps.setString(12, "img/fotodeperfil/temporariacarro.jpg");
			}else{
				ps.setString(12, "img/fotodeperfil/temporariavan.jpg");
			}
			ps.setDouble(13, recomendacao);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public ArrayList<Oferta> minhasofertas(Integer id){
		ArrayList<Oferta> listaoferta = new ArrayList<Oferta>();
		String sql = "select * from oferta where idusuario = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Oferta oferta = new Oferta();
				oferta = preencherOferta(rs);
				listaoferta.add(oferta);
			}
		}catch(SQLException e2){
			
		}
		return listaoferta;
	}
	
	public Integer contarofertas(Integer id){
		Integer valor = 0;
		String sql = "select count(*) as cont from oferta where idusuario = ?";
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
	
	public Oferta buscarOferta(Oferta oferta){
		Oferta ofe = new Oferta();
		String sql = "select * from oferta where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, oferta.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ofe = preencherOferta(rs);
			}
		}catch(SQLException e2){
			
		}
		return ofe;
	}
	
	public void editarOferta(Oferta oferta){
		String sql = "update oferta set titulo = ?,descricao = ?,manha = ?,tarde = ?,noite=?,origem=?,destino=?,tipooferta=?,remunerado=? where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, oferta.getTitulo());
			ps.setString(2, oferta.getDescricao());
			ps.setString(3, oferta.getManha());
			ps.setString(4, oferta.getTarde());
			ps.setString(5, oferta.getNoite());
			ps.setString(6, oferta.getOrigem());
			ps.setString(7, oferta.getDestino());
			ps.setString(8, oferta.getTipooferta());
			ps.setString(9, oferta.getRemunerado());
			ps.setInt(10, oferta.getId());
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public void deletarOferta(Integer id){
		String sql = "delete from oferta where id = ?";
		String sql1 = "delete from tag where idoferta = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setInt(1, id);
			ps1.execute();
			ps1.close();
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
