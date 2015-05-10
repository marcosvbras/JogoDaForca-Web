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
    
    private final String MENSAGEM_GANHOU = "Você acertou!";
    private final String MENSAGEM_PERDEU = "Você perdeu!";
    
    private int palavra_size;
    private int chances; 
    private String[] arrayAcertos, arrayPalavra;
    private String palavra, letras;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if("verificar".equals(acao)) {
			verificar(request, response);
		}else {
			novoJogo(request, response);
		}
		
	}
		
	protected void novoJogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PalavraDAO dao = new PalavraDAO();
		Palavra p = dao.escolherPalavra();
		request.getSession().setAttribute("objetoPalavra", p);
		this.palavra = p.getPalavra();
		request.getSession().setAttribute("palavra", this.palavra);
		this.arrayPalavra = palavraToStringArray(palavra);
		request.getSession().setAttribute("arrayPalavra", this.arrayPalavra);
		this.arrayAcertos = new String[this.palavra.length()];
		request.getSession().setAttribute("arrayAcertos", this.arrayAcertos);
		this.letras = montarAcertos(this.arrayAcertos);
		System.out.println("novoJogo - Letras: " + this.letras);
		request.getSession().setAttribute("letras", letras);
		chances = 6;
		request.getSession().setAttribute("chances", 6);
		
		String destino = "jogo.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private String montarAcertos(String[] arrayAcertos) {
		String letras = "";
		
		for(int i = 0; i < arrayAcertos.length; i++) {
			if(arrayAcertos[i] == null) {
				letras += "_ ";
			} else {
				letras += arrayAcertos[i] + " ";
			}
		}
		
		return letras;
	}
	
	private void pegarValoresDaSessao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.arrayAcertos = (String[]) request.getSession().getAttribute("arrayAcertos");
		for(int i = 0; i < arrayAcertos.length; i++) {
			System.out.println(arrayAcertos[i]);
		}
		this.arrayPalavra = (String[]) request.getSession().getAttribute("arrayPalavra");
		this.palavra = (String) request.getSession().getAttribute("palavra");
		this.letras = (String) request.getSession().getAttribute("letras");
		System.out.println("pegarValoresDaSessao - Letras: " + this.letras);
		this.chances = (int) request.getSession().getAttribute("chances");
	}
	
	protected void verificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tentativa = request.getParameter("tentativa").toUpperCase();
		pegarValoresDaSessao(request, response);
		this.arrayAcertos = compararLetra(tentativa);
		request.getSession().setAttribute("arrayAcertos", this.arrayAcertos); 
		this.letras = montarAcertos(this.arrayAcertos);
		System.out.println("verificar - Letras: " + this.letras + "\n");
		request.getSession().setAttribute("letras", this.letras);
		request.getSession().setAttribute("chances", this.chances);	
		String mensagem = null;
		
		if(this.chances > 0)
		{
			if(ganhou()) {
				mensagem = MENSAGEM_GANHOU;
				String email = (String)request.getSession().getAttribute("email");
				RankingDAO dao = new RankingDAO();
				dao.salvar(email);
			}
		}
		else
		{
			mensagem = MENSAGEM_PERDEU;
		}
		
		request.getSession().setAttribute("mensagem", mensagem);
		String destino = "jogo.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private String[] compararLetra(String tentativa) {
		int cont = 0; 

		for(int i = 0; i < this.palavra.length() ; i++) {
			if(tentativa.equals(this.arrayPalavra[i])) {
				this.arrayAcertos[i] = tentativa;
				cont++;
			}
		}
		
		if(cont == 0) {
			chances--;
		}
		
		return arrayAcertos;
	}
	
	private boolean ganhou() {
		boolean resultado = false;
		int cont = 0;
		for(int i = 0; i < this.palavra.length(); i ++) {
			if(this.arrayAcertos[i] == null) {
				cont++;
			}
		}
		
		if(cont == 0) {
			resultado = true;
		}
		return resultado;
	}
	
	private String[] palavraToStringArray(String palavra) {
        String arrayPalavra[] = new String[palavra.length()];
        char[] p = palavra.toCharArray();
        for (int i = 0; i < palavra.length(); i++) {
        	arrayPalavra[i] = Character.toString(p[i]);	
		}
        return arrayPalavra;
	}
}
