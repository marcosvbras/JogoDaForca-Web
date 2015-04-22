package br.com.forca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.forca.model.Palavra;
import br.com.forca.model.Ranking;

public class RankingDAO {
	
	private Connection connection;

	public RankingDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void salvar(String email)
	{
		String sql = "insert into ranking values(?, ?)";
		
		try{
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, email);
			stm.setInt(2, 1);

			stm.execute();
			stm.close();
			
		}catch(SQLException e){
			
		}
	}
	
	public List<Ranking> buscarRanking()
	{
		List<Ranking> listaRanking = new ArrayList<Ranking>();
		Ranking ranking = new Ranking();
		String sql = "select email, count(acertos) as 'acertos' from ranking group by email order by acertos desc";
		
		try{
			PreparedStatement stm = connection.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				ranking.setEmail(rs.getString("email"));
				ranking.setAcertos(rs.getString("acertos"));
				
				listaRanking.add(ranking);
			}

			rs.close();
			stm.close();
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return listaRanking;
	}
}
