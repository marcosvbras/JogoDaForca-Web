package br.com.forca.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forca.dao.PalavraDAO;
import br.com.forca.model.Palavra;

/**
 * Servlet implementation class JogoServlet
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
    
    private int chances = 6;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if("verificar".equals(acao)) {
			
		} else {
			novoJogo(request, response);
		}
	}
	
	protected void novoJogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		chances = 6;
		String destino = "jogo.jsp";
		Palavra palavra = new Palavra();
		PalavraDAO dao = new PalavraDAO();
		palavra = dao.escolherPalavra();
		request.getSession().setAttribute("palavra", palavra);
		request.getSession().setAttribute("chances", chances);
		
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
