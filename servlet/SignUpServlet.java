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

import model.SignUp;
import model.SignUpLogic;

/**
 * Servlet implementation class SinginServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet { //新規登録s
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
		
		String mail = request.getParameter("mail"); //入力メールアドレス
		String pass = request.getParameter("pass"); //入力パスワード1
		String pass2 = request.getParameter("pass2"); //入力パスワード2
		String time = request.getParameter("defTime"); //リマインド時刻設定
		mail.strip(); //空白を除く
		if(pass.length() == 8 && 
				pass.equals(pass2) && 
					!(mail.equals("")) && time != null) { //入力に漏れがない場合
			LocalTime defTime = LocalTime.parse(time,DateTimeFormatter.ofPattern("HH:mm"));
			SignUp signUp = new SignUp(mail, pass, defTime);
			SignUpLogic bo = new SignUpLogic();
			int result = bo.execute(signUp);
		
			if(result == 1) { //新規登録成功時
				request.setAttribute("SignUp", signUp);
			}else if(result == 0) { //新規登録失敗 
				request.setAttribute("errorMsg", "新規登録に失敗しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorTOP.jsp");
				dispatcher.forward(request, response);
			} else {  //DBのシステムエラー
				request.setAttribute("errorMsg", "システムエラーです。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/errorTOP.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUpResult.jsp");
		dispatcher.forward(request, response);
	}	

}
