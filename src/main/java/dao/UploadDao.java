package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadDao {
private Connection connection;
	
	public UploadDao() {
		this.connection = new ConexaoDao().getConnection();
	}
	
	public void alteraPerfil(String caminho,Integer id){
		String sql = "update usuario set foto = ? where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, caminho);
			ps.setInt(2, id);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
	
	public void alteraOferta(String caminho,Integer id){
		String sql = "update oferta set foto = ? where id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, caminho);
			ps.setInt(2, id);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
	}
}
