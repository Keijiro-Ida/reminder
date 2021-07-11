package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Goal;
import model.Remind;
import model.SendMailLogic;
import model.UpdateGoalLogic;
import model.Users;

/**
 * Servlet implementation class UpdateGoalServlet
 */
@WebServlet("/UpdateGoalServlet")
public class UpdateGoalServlet extends HttpServlet { //目標の更新
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Goal goal = (Goal) session.getAttribute("goal"); //セッションスコープから目標の獲得
		Users users = (Users) session.getAttribute("users"); //ユーザー情報の獲得
		Timestamp now = new Timestamp(System.currentTimeMillis()); //今の時刻設定
		
		String text = request.getParameter("text");
		
		
		if(text.equals("") || text.length() == 0 ) { //テキスト入力がない場合
			request.setAttribute("errorMsg", "テキストの入力がありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		} else if(goal.getRemindTime().before(now)) { //リマインド済みの場合はテキスト変更のみの登録
			goal.setText(text.strip());
			UpdateGoalLogic bo = new UpdateGoalLogic();
			bo.execute(goal);
			response.sendRedirect("/reminder/GetGoalListServlet");
			
		}else { //リマインドの再設定
			String time = request.getParameter("remindTime");
		
			LocalDate today = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String date = today.format(dtf).toString();	
			String datetime = date + " " + time;
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			LocalDateTime localDateTime = LocalDateTime.parse(datetime,dtf2);
			Timestamp remindTime = Timestamp.valueOf(localDateTime);
			
			if(text.equals(goal.getText()) && remindTime.equals(goal.getRemindTime())){ //テキスト、時刻に変更ない場合
				request.setAttribute("errorMsg", "入力値に変更がありません。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			
			} else if(remindTime.before(now)){ //リマインド時間が現在時刻より早い場合はあすの設定
				LocalDate tomorrow = today.plusDays(1);
				String tomorrowDateTime = tomorrow.format(dtf).toString() + " " + time;
				LocalDateTime tomorrowLocalDateTime = LocalDateTime.parse(tomorrowDateTime, dtf2);
				remindTime = Timestamp.valueOf(tomorrowLocalDateTime); //リマインド時刻が現在より早い場合は明日の設定
				goal.setRemindTime(remindTime);
				goal.setText(text);	
			} else { //リマインド時刻が現在時刻より遅い場合は今日設定
				goal.setRemindTime(remindTime);
				goal.setText(text);
			}
			UpdateGoalLogic bo = new UpdateGoalLogic();	
			int result = bo.execute(goal);
			if(result == 1) { //更新成功
					//remindの再設定
				
				if(SendMailLogic.map.get(goal.getGoalId()) != null) { //リマインド通知のキャンセルと再設定

					SendMailLogic.map.get(goal.getGoalId()).cancel(true); //スレッドのキャンセル
					SendMailLogic.map2.get(goal.getGoalId()).shutdown(); //スレッド閉鎖
					Remind remind = new Remind(goal.getGoalId(),users.getMail(), text, remindTime);
					SendMailLogic bo3 = new SendMailLogic(remind);
					bo3.execute();
					response.sendRedirect("/reminder/GetGoalListServlet");
				} else { //サーバー電源オフなどでスレッドキャンセルされていたとき
					Remind remind = new Remind(goal.getGoalId(),users.getMail(), text, remindTime);
					SendMailLogic bo3 = new SendMailLogic(remind);
					bo3.execute();
						
					response.sendRedirect("/reminder/GetGoalListServlet");
				}	
			}else if(result == 0){ //更新失敗
				request.setAttribute("errorMsg", "更新に失敗しました。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			} else { //データベースエラー
				request.setAttribute("errorMsg", "システムエラー");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
	}
}