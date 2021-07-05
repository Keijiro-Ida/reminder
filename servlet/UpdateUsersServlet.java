package servlet;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
public class UpdateUsersServlet extends HttpServlet { //ユーザー情報の変更
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
		String newMail = request.getParameter("mail"); //新メールアドレス
		String newPass = request.getParameter("pass"); //新パスワード
		String newPass2 = request.getParameter("pass2"); //新パスワード再入力
		String time = request.getParameter("defTime"); //デフォルト時刻設定
		LocalTime defTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
		
		newPass.strip();
		newPass2.strip();
		if(!(newPass.equals(newPass2)) || 
					newPass.length() != 8){ //入力パスワードに間違いがあった場合
			request.setAttribute("errorMsg", "パスワードの入力エラーです。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		} else { //入力情報に間違いがない場合に登録情報の変更
			HttpSession session = request.getSession();
			Users users = (Users) session.getAttribute("users");
			users.setMail(newMail);
			users.setPass(newPass);
			users.setDefTime(defTime);
			UpdateUsersLogic bo = new UpdateUsersLogic();
			int result = bo.execute(users);
			if(result == 1) { //更新成功
				HttpSession session2 = request.getSession();
				session2.setAttribute("users", users);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateOK.jsp");
				dispatcher.forward(request, response);
			} else if(result == 0){ //登録失敗
				request.setAttribute("errorMsg", "登録に失敗しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			} else { //データベースのエラー
				request.setAttribute("errorMsg", "システムエラーです。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
