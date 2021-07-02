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

import model.Goal;

/**
 * Servlet implementation class EditGoalServlet
 */
@WebServlet("/EditGoalServlet")
public class EditGoalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Goal> goalList = (List<Goal>) session.getAttribute("goalList");
		Goal goal = null;
		
		for(int i = 0; i < goalList.size(); i++) {
			if(request.getParameter("Edit" + i) != null) {
			goal = goalList.get(i);
			} 
		}
		HttpSession sessionGoal = request.getSession();
		sessionGoal.setAttribute("goal", goal);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editGoal.jsp");
		dispatcher.forward(request, response);
	}

}
