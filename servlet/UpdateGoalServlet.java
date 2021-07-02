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

import model.Goal;
import model.Remind;
import model.RemindGoalLogic;
import model.UpdateGoalLogic;
import model.Users;

/**
 * Servlet implementation class UpdateGoalServlet
 */
@WebServlet("/UpdateGoalServlet")
public class UpdateGoalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Goal goal = (Goal) session.getAttribute("goal");
		HttpSession scope = request.getSession();
		Users users = (Users) scope.getAttribute("users");
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String text = request.getParameter("text");
	
		if(goal.getRemindTime().before(now)) {
			goal.setText(text.strip());
			UpdateGoalLogic bo = new UpdateGoalLogic();
			bo.execute(goal);
			response.sendRedirect("/reminder/GetGoalListServlet");
		} else {
			String time = request.getParameter("remindTime");
			LocalDate today = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String date = today.format(dtf).toString();
			String datetime = date + " " + time;
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			LocalDateTime localDateTime = LocalDateTime.parse(datetime,dtf2);
			Timestamp remindTime = Timestamp.valueOf(localDateTime);
			
			if(remindTime.before(now)) {
				request.setAttribute("errorMsg", "リマインド時間が現在より早い時間に設定できません。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
				
			}else {
				
				goal.setRemindTime(remindTime);
				goal.setText(text);
				
				UpdateGoalLogic bo = new UpdateGoalLogic();
				boolean result = bo.execute(goal);
				if(result) {
					//rimindの再設定
					RemindGoalLogic bo2 = new RemindGoalLogic();
					bo2.cancel();
					Date remindDate = new Date(remindTime.getTime());
					Remind remind = new Remind(users.getMail(), goal.getText(), remindDate);
					bo2.execute(remind);
					response.sendRedirect("/reminder/GetGoalListServlet");
				} else {
					request.setAttribute("errorMsg", "更新に失敗しました。");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	}
}
