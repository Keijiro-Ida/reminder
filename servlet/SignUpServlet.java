package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SignUp;
import model.SignUpLogic;

/**
 * Servlet implementation class SinginServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
		dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");
		mail.strip();
		if(pass.length() == 8 && 
				pass.equals(pass2) && 
					!(mail.equals(""))) {
			SignUp signUp = new SignUp(mail, pass);
			SignUpLogic bo = new SignUpLogic();
			boolean result = bo.execute(signUp);
		
			if(result) {
				request.setAttribute("SignUp", signUp);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUpResult.jsp");
		dispatcher.forward(request, response);
	}

}
