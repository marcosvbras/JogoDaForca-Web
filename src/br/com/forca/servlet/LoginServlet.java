package br.com.forca.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if("login".equals(acao)) {
			login(request, response);
		} else {
			request.getSession().invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
			rd.forward(request, response);
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		request.getSession().setAttribute("email", email);
		String destino;
		
		if(!"".equals(email)) {
			destino = "/jogo";
			RequestDispatcher rd = request.getRequestDispatcher(destino);
			rd.forward(request, response);
		} else {
			destino = "cadastro.jsp";
			String error_message = "Por favor, digite seu email";
			request.getSession().setAttribute("error_message", error_message);
			RequestDispatcher rd = request.getRequestDispatcher(destino);
			rd.forward(request, response);
		}
	}
}
