package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UpdateUsersLogic;
import model.Users;

/**
 * Servlet implementation class UpdateUsersServlet
 */
@WebServlet("/UpdateUsersServlet")
public class UpdateUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateUsers.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newMail = request.getParameter("mail");
		String newPass = request.getParameter("pass");
		String newPass2 = request.getParameter("pass2");
		newPass.strip();
		newPass.strip();
		if(!(newPass.equals(newPass2)) || 
					newPass.length() != 8){
			request.setAttribute("errorMsg", "パスワードエラーです。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			Users users = (Users) session.getAttribute("users");
			users.setMail(newMail);
			users.setPass(newPass);
			UpdateUsersLogic bo = new UpdateUsersLogic();
			boolean result = bo.execute(users);
			if(result) {
				//更新成功
				HttpSession session2 = request.getSession();
				session2.setAttribute("users", users);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateOK.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMsg", "登録に失敗しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
