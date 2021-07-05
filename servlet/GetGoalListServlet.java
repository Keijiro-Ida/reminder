package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetGoalListLogic;
import model.Goal;
import model.Users;

/**
 * Servlet implementation class GetGoalListServlet
 */
@WebServlet("/GetGoalListServlet")
public class GetGoalListServlet extends HttpServlet { //目標の履歴表示 1ページ目
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionUser = request.getSession();
		Users users = (Users) sessionUser.getAttribute("users");
		
		
		GetGoalListLogic bo = new GetGoalListLogic(); //履歴リストを獲得
		List<Goal> goalList = bo.execute(users);
		
		HttpSession session = request.getSession(); //リストをセッションスコープに格納
		session.setAttribute("goalList", goalList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/goalList.jsp");
		dispatcher.forward(request, response);
	}

}
