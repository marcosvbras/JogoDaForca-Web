package br.com.forca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.forca.model.Palavra;

public class PalavraDAO {
	
	private Connection connection;
	
	public PalavraDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Palavra escolherPalavra() {
		Palavra palavra = new Palavra();
		String sql = "select * from palavras order by rand() limit 1";
		
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			rs.first();
			
			palavra.setId(rs.getInt("id"));
			palavra.setPalavra(rs.getString("palavra"));
			palavra.setDica(rs.getString("dica"));
			
			rs.close();
			stmt.close();
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return palavra;
	}
}
