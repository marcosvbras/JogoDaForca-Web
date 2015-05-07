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
		String sql = "select email, count(acertos) as 'acertos' from ranking group by email order by acertos desc";
		List<Ranking> listaRanking = new ArrayList<Ranking>();
		
		try{
			PreparedStatement stm = connection.prepareStatement(sql);			
			ResultSet rs = stm.executeQuery();
			int cont = 0;
			while(rs.next()){
				Ranking r = new Ranking();
				r.setEmail(rs.getString("email"));
				r.setAcertos(rs.getString("acertos"));
				
				listaRanking.add(cont, r);
				System.out.println("Passei aqui");
				System.out.println(listaRanking.get(cont).getEmail());
				cont++;
			}
			
			rs.close();
			stm.close();
//			for(int i = 0; i < listaRanking.size(); i++) {
//				System.out.println(listaRanking.get(i).getEmail());
//				System.out.println(listaRanking.get(i).getAcertos());
//				System.out.println("\n");
//			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return listaRanking;
	}
}
