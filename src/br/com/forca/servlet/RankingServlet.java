package br.com.forca.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forca.dao.RankingDAO;
import br.com.forca.model.Ranking;

/**
 * Servlet implementation class RankingServlet
 */
@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if("ranking".equals(acao)) {
			exibirRanking(request, response);
		}
	}
	
	protected void exibirRanking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RankingDAO dao = new RankingDAO();
		List<Ranking> listaRanking = dao.buscarRanking();
		
		request.getSession().setAttribute("listaRanking", listaRanking);
		String destino = "ranking.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

}
