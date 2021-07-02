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
public class GetGoalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionUser = request.getSession();
		Users users = (Users) sessionUser.getAttribute("users");
		
		String action = request.getParameter("action");
		
		GetGoalListLogic bo = new GetGoalListLogic();
		List<Goal> goalList = bo.execute(users);
		
		HttpSession session = request.getSession();
		session.setAttribute("goalList", goalList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/goalList.jsp");
		dispatcher.forward(request, response);
	}

}
