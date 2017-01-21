package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.model.Pesquisa;

public class PesquisaDao {
	private Connection connection;
	
	public PesquisaDao() {
		this.connection = new ConexaoDao().getConnection();
	}
	
	public ArrayList<Pesquisa> semfiltragem(String termo){
		ArrayList<Pesquisa> listapesquisa = new ArrayList<Pesquisa>();
		String sql = "select * from tag where descricao LIKE ?";
		String contarsql = "select count(*) as cont from tag where descricao LIKE ?";
		String sql1 = "select * from oferta where id = ?";
		String sql2 = "select * from oferta where titulo LIKE ? or descricao LIKE ? or origem LIKE ? or destino LIKE ?";
		try{
			Integer cont = 0;
			Integer idoferta = 0;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%"+termo+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idoferta = rs.getInt("idoferta");
				PreparedStatement contar = connection.prepareStatement(contarsql);
				contar.setString(1, "%"+termo+"%");
				ResultSet conta = contar.executeQuery();			
				while(conta.next()){
					cont = conta.getInt("cont");
				}
				if(cont > 0 && idoferta != 0){
					PreparedStatement ps1 = connection.prepareStatement(sql1);
					ps1.setInt(1, idoferta);
					ResultSet rs1 = ps1.executeQuery();
					while(rs1.next()){
						Pesquisa pesquisa = new Pesquisa();
						pesquisa = preencherPesquisa(rs1);
						listapesquisa.add(pesquisa);
					}
				}
			}
			if(cont > 0 && idoferta != 0){
				
			}else{
				PreparedStatement ps1 = connection.prepareStatement(sql2);
				ps1.setString(1, "%"+termo+"%");
				ps1.setString(2, "%"+termo+"%");
				ps1.setString(3, "%"+termo+"%");
				ps1.setString(4, "%"+termo+"%");
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()){
					Pesquisa pesquisa = new Pesquisa();
					pesquisa = preencherPesquisa(rs1);
					listapesquisa.add(pesquisa);
				}
			}
			
		}catch(SQLException e2){
			
		}
		return listapesquisa;
	}
	
	public ArrayList<Pesquisa> semfiltragemReputacao(String termo){
		ArrayList<Pesquisa> listapesquisa = new ArrayList<Pesquisa>();
		String sql = "select * from tag where descricao LIKE ?";
		String contarsql = "select count(*) as cont from tag where descricao LIKE ?";
		String sql1 = "select * from oferta where id = ? order by nota desc";
		String sql2 = "select * from oferta where titulo LIKE ? or descricao LIKE ? or origem LIKE ? or destino LIKE ? order by nota desc";
		try{
			Integer cont = 0;
			Integer idoferta = 0;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%"+termo+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idoferta = rs.getInt("idoferta");
				PreparedStatement contar = connection.prepareStatement(contarsql);
				contar.setString(1, "%"+termo+"%");
				ResultSet conta = contar.executeQuery();			
				while(conta.next()){
					cont = conta.getInt("cont");
				}
				if(cont > 0 && idoferta != 0){
					PreparedStatement ps1 = connection.prepareStatement(sql1);
					ps1.setInt(1, idoferta);
					ResultSet rs1 = ps1.executeQuery();
					while(rs1.next()){
						Pesquisa pesquisa = new Pesquisa();
						pesquisa = preencherPesquisa(rs1);
						listapesquisa.add(pesquisa);
					}
				}
			}
			if(cont > 0 && idoferta != 0){
				
			}else{
				PreparedStatement ps1 = connection.prepareStatement(sql2);
				ps1.setString(1, "%"+termo+"%");
				ps1.setString(2, "%"+termo+"%");
				ps1.setString(3, "%"+termo+"%");
				ps1.setString(4, "%"+termo+"%");
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()){
					Pesquisa pesquisa = new Pesquisa();
					pesquisa = preencherPesquisa(rs1);
					listapesquisa.add(pesquisa);
				}
			}
			
		}catch(SQLException e2){
			
		}
		return listapesquisa;
	}
	
	public ArrayList<Pesquisa> filtragem(String termo,String filtroConsulta,String origemdestino){
		if(!filtroConsulta.equals("") && !origemdestino.equals("")){
			filtroConsulta = filtroConsulta.substring(0, filtroConsulta.length()-4);
			if(filtroConsulta.contains("remunerado = 'gra")){
				filtroConsulta += "tis'";
			}
		}
		Integer verificar=5;
		if(origemdestino.contains("origem") && origemdestino.contains("destino")){
			verificar = 1;
		}else if(origemdestino.contains("origem") && !origemdestino.contains("destino")){
			verificar = 2;
		}else if(origemdestino.contains("destino") && !origemdestino.contains("origem")){
			verificar = 3;
		}else if(!origemdestino.contains("destino") && !origemdestino.contains("origem")){
			verificar = 5;
		}
		ArrayList<Pesquisa> listapesquisa = new ArrayList<Pesquisa>();
		String sql = "select * from tag where descricao LIKE ?";
		String contarsql = "select count(*) as cont from tag where descricao LIKE ?";
		String sql1 = "";
		if(!filtroConsulta.equals("")){
			sql1 = "select * from oferta where id = ? and "+filtroConsulta;
		}else{
			sql1 = "select * from oferta where id = ?";
		}
		String sql2 = "";
		if(verificar == 1 || verificar == 2 || verificar == 3){
			if(!filtroConsulta.equals("") && !origemdestino.equals("")){
				sql2 =  "select * from oferta where "+filtroConsulta+" and "+origemdestino;
			}else if(!origemdestino.equals("") && filtroConsulta.equals("")){
				sql2 =  "select * from oferta where "+origemdestino;
			}else if(origemdestino.equals("") && !filtroConsulta.equals("")){
				sql2 =  "select * from oferta where "+filtroConsulta;
			}
			
		}else if(verificar == 5){
			sql2 =  "select * from oferta where (titulo LIKE ? or descricao LIKE ? or origem LIKE ? or destino LIKE ?) and "+filtroConsulta;
		}
		try{
			Integer cont = 0;
			Integer idoferta = 0;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%"+termo+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idoferta = rs.getInt("idoferta");
				PreparedStatement contar = connection.prepareStatement(contarsql);
				contar.setString(1, "%"+termo+"%");
				ResultSet conta = contar.executeQuery();			
				while(conta.next()){
					cont = conta.getInt("cont");
				}
				if(cont > 0 && idoferta != 0){
					PreparedStatement ps1 = connection.prepareStatement(sql1);
					ps1.setInt(1, idoferta);
					ResultSet rs1 = ps1.executeQuery();
					while(rs1.next()){
						Pesquisa pesquisa = new Pesquisa();
						pesquisa = preencherPesquisa(rs1);
						listapesquisa.add(pesquisa);
					}
				}
			}
			if(cont > 0 && idoferta != 0){
				
			}else{
				PreparedStatement ps1 = connection.prepareStatement(sql2);
				if(verificar == 1){
					ps1.setString(1, "%"+termo+"%");
					ps1.setString(2, "%"+termo+"%");
				}else if(verificar == 2 || verificar == 3){
					ps1.setString(1, "%"+termo+"%");
				}else if(verificar == 5){
					ps1.setString(1, "%"+termo+"%");
					ps1.setString(2, "%"+termo+"%");
					ps1.setString(3, "%"+termo+"%");
					ps1.setString(4, "%"+termo+"%");
				}
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()){
					Pesquisa pesquisa = new Pesquisa();
					pesquisa = preencherPesquisa(rs1);
					listapesquisa.add(pesquisa);
				}
			}
			
		}catch(SQLException e2){
			
		}
		return listapesquisa;
	}
	
	public ArrayList<Pesquisa> filtragemReputacao(String termo,String filtroConsulta,String origemdestino){
		if(!filtroConsulta.equals("") && !origemdestino.equals("")){
			filtroConsulta = filtroConsulta.substring(0, filtroConsulta.length()-4);
			if(filtroConsulta.contains("remunerado = 'gra")){
				filtroConsulta += "tis'";
			}
		}
		Integer verificar=5;
		if(origemdestino.contains("origem") && origemdestino.contains("destino")){
			verificar = 1;
		}else if(origemdestino.contains("origem") && !origemdestino.contains("destino")){
			verificar = 2;
		}else if(origemdestino.contains("destino") && !origemdestino.contains("origem")){
			verificar = 3;
		}else if(!origemdestino.contains("destino") && !origemdestino.contains("origem")){
			verificar = 5;
		}
		ArrayList<Pesquisa> listapesquisa = new ArrayList<Pesquisa>();
		String sql = "select * from tag where descricao LIKE ?";
		String contarsql = "select count(*) as cont from tag where descricao LIKE ?";
		String sql1 = "";
		if(!filtroConsulta.equals("")){
			sql1 = "select * from oferta where id = ? and "+filtroConsulta+" order by nota desc";
		}else{
			sql1 = "select * from oferta where id = ? order by nota desc";
		}
		String sql2 = "";
		if(verificar == 1 || verificar == 2 || verificar == 3){
			if(!filtroConsulta.equals("") && !origemdestino.equals("")){
				sql2 =  "select * from oferta where "+filtroConsulta+" and "+origemdestino+" order by nota desc";
			}else if(!origemdestino.equals("") && filtroConsulta.equals("")){
				sql2 =  "select * from oferta where "+origemdestino+" order by nota desc";
			}else if(origemdestino.equals("") && !filtroConsulta.equals("")){
				sql2 =  "select * from oferta where "+filtroConsulta+" order by nota desc";
			}
			
		}else if(verificar == 5){
			sql2 =  "select * from oferta where (titulo LIKE ? or descricao LIKE ? or origem LIKE ? or destino LIKE ?) and "+filtroConsulta+" order by nota desc";
		}
		try{
			Integer cont = 0;
			Integer idoferta = 0;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%"+termo+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idoferta = rs.getInt("idoferta");
				PreparedStatement contar = connection.prepareStatement(contarsql);
				contar.setString(1, "%"+termo+"%");
				ResultSet conta = contar.executeQuery();			
				while(conta.next()){
					cont = conta.getInt("cont");
				}
				if(cont > 0 && idoferta != 0){
					PreparedStatement ps1 = connection.prepareStatement(sql1);
					ps1.setInt(1, idoferta);
					ResultSet rs1 = ps1.executeQuery();
					while(rs1.next()){
						Pesquisa pesquisa = new Pesquisa();
						pesquisa = preencherPesquisa(rs1);
						listapesquisa.add(pesquisa);
					}
				}
			}
			if(cont > 0 && idoferta != 0){
				
			}else{
				PreparedStatement ps1 = connection.prepareStatement(sql2);
				if(verificar == 1){
					ps1.setString(1, "%"+termo+"%");
					ps1.setString(2, "%"+termo+"%");
				}else if(verificar == 2 || verificar == 3){
					ps1.setString(1, "%"+termo+"%");
				}else if(verificar == 5){
					ps1.setString(1, "%"+termo+"%");
					ps1.setString(2, "%"+termo+"%");
					ps1.setString(3, "%"+termo+"%");
					ps1.setString(4, "%"+termo+"%");
				}
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()){
					Pesquisa pesquisa = new Pesquisa();
					pesquisa = preencherPesquisa(rs1);
					listapesquisa.add(pesquisa);
				}
			}
			
		}catch(SQLException e2){
			
		}
		return listapesquisa;
	}
	
	public Pesquisa preencherPesquisa(ResultSet rs) throws SQLException{
		Pesquisa pesquisa = new Pesquisa();
		pesquisa.setId(rs.getInt("id"));
		pesquisa.setTitulo(rs.getString("titulo"));
		pesquisa.setDescricao(rs.getString("descricao"));
		pesquisa.setManha(rs.getString("manha"));
		pesquisa.setTarde(rs.getString("tarde"));
		pesquisa.setOrigem(rs.getString("origem"));
		pesquisa.setDestino(rs.getString("destino"));
		pesquisa.setTipooferta(rs.getString("tipooferta"));
		pesquisa.setIdusuario(rs.getInt("idusuario"));
		pesquisa.setRemunerado(rs.getString("remunerado"));
		pesquisa.setStatus(rs.getString("status"));
		pesquisa.setFoto(rs.getString("foto"));
		return pesquisa;
	}
}