package br.com.forca.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import br.com.forca.dao.PalavraDAO;
import br.com.forca.dao.RankingDAO;
import br.com.forca.model.Palavra;

/**
 * Servlet implementation class PalavraServlet
 */
@WebServlet("/jogo")
public class JogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JogoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if("verificar".equals(acao)) {
			comparar(request, response);
		}else {
			novoJogo(request, response);
		}
		
	}
	
	private int chances; 
	
	protected void novoJogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String destino = "jogo.jsp";
		chances = 6;
		//request.getSession().setAttribute("chances", 6);
		Palavra palavra = (Palavra)request.getSession().getAttribute("palavra");
		if (palavra == null) {
			PalavraDAO dao = new PalavraDAO();
			palavra = dao.escolherPalavra();
			request.getSession().setAttribute("palavra", palavra);
			request.getSession().setAttribute("chances", 6);
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	protected void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RankingDAO ranking = new RankingDAO();
//		ranking.salvar(email, acertos);
	}
	
	protected void comparar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Palavra palavra = (Palavra)request.getSession().getAttribute("palavra");
//		if (palavra == null) {
//			PalavraDAO dao = new PalavraDAO();
//			palavra = dao.escolherPalavra();
//			request.getSession().setAttribute("palavra", palavra);
//			request.getSession().setAttribute("chances", 6);
//		}
		String tentativa = request.getParameter("tentativa").toUpperCase();
		String arrayPalavra[] = palavraToArray(palavra.getPalavra());
		
		String[] arrayTentativas = (String[])request.getSession().getAttribute("arrayTentativas");
		arrayTentativas = compararLetra(arrayTentativas, arrayPalavra, tentativa);
		String mensagem = null;
		if(chances > 0)
		{
			int cont = 0;
			for(int i = 0; i < 6; i++) {
				if(arrayTentativas[i] == null) {
					cont++;
				}
			}
			
			if(cont > 0) {
				request.getSession().setAttribute("arrayTentativas", arrayTentativas);
				request.getSession().setAttribute("chances", chances);	
			}else {
				request.getSession().setAttribute("arrayTentativas", arrayTentativas);
				mensagem = "Você acertou!";
				request.getSession().setAttribute("mensagem", mensagem);
				String email = (String)request.getSession().getAttribute("email");
				RankingDAO dao = new RankingDAO();
				dao.salvar(email, 1);
			}
		}
		else
		{
			mensagem = "Você perdeu!";
			request.getSession().setAttribute("mensagem", mensagem);
			request.getSession().setAttribute("chances", chances);	
			request.getSession().setAttribute("mensagem", mensagem);
			String email = (String)request.getSession().getAttribute("email");
			RankingDAO dao = new RankingDAO();
			dao.salvar(email, 0);
		}
		
		String destino = "jogo.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	protected String[] compararLetra(String[] arrayTentativas, String[] arrayPalavra, String tentativa) {
		if (arrayTentativas == null) {
			arrayTentativas = new String[6];
		}
		int cont = 0;
		
		for(int i = 0; i < 6; i++) {
			if(arrayTentativas[i] == null) {
				if(tentativa.equals(arrayPalavra[i])) {
					arrayTentativas[i] = tentativa;
					cont++;
				}
			}
		}
		
		if(cont == 0) {
			chances -= 1;
		}
		
		return arrayTentativas;
	}
	
	
	protected String[] palavraToArray(String palavra) {
        String arrayPalavra[] = new String[palavra.length()];
        char[] p = palavra.toCharArray();
        for (int i = 0; i < palavra.length(); i++) {
        	arrayPalavra[i] = Character.toString(p[i]);	
		}
        return arrayPalavra;
	}
}
