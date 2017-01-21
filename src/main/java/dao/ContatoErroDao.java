package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContatoErroDao {
	private Connection connection;
	public ContatoErroDao() {
		this.connection = new ConexaoDao().getConnection();
	}
	
	public void enviarcontato(String email, String mensagem) {
		String sql = "insert into contato(email,mensagem,lida,datahora) values (?,?,'Nao',now())";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, mensagem);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
		
	}
	
	public void enviarerro(String email, String merro) {
		String sql = "insert into erro(email,erro,lida,datahora) values (?,?,'Nao',now())";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, merro);
			ps.execute();
			ps.close();
		}catch(SQLException e2){
			
		}
		
	}
}
