package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;
import model.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		Login login = new Login(mail, pass);
		LoginLogic bo = new LoginLogic();
		Users users = bo.execute(login);
		
		if(users != null) {
			HttpSession session = request.getSession();
			session.setAttribute("users", users);
		} 
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}

}
