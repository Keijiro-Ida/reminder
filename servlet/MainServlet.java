
package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PostGoal;
import model.PostGoalLogic;
import model.Remind;
import model.RemindGoalLogic;
import model.Users;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		String time = request.getParameter("remindTime");
		text = text.strip();
		if(text.equals("") || time.equals("")) {
			request.setAttribute("errorMsg", "入力値エラー");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		} else {
			LocalDate today = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String datetime = dtf.format(today) + " " + time;
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			LocalDateTime remindLocalTime = LocalDateTime.parse(datetime,dtf2);
			Timestamp remindTime = Timestamp.valueOf(remindLocalTime);
			Timestamp goalTime = new Timestamp(System.currentTimeMillis());
		
			if(remindTime.before(goalTime)) {
				request.setAttribute("errorMsg", "エラー：リマインド時刻が現在より早い時刻となっています。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				Users users = (Users)session.getAttribute("users");
				PostGoal postGoal = new PostGoal(users.getUsrId(), text, goalTime, remindTime);
		
				PostGoalLogic bo = new PostGoalLogic();
				boolean result = bo.execute(postGoal);
				if(result) {
			
			//remindの送信を追記
					Date remindDate = new Date(remindTime.getTime());
					Remind remind = new Remind(users.getMail(), text, remindDate);
					RemindGoalLogic bo2 = new RemindGoalLogic();
					bo2.execute(remind);
				
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainResult.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMsg", "登録に失敗しました。");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
					dispatcher.forward(request, response);
			//エラーのときの画面
				}
			}
			
		}
	}
	
	
}
