package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteGoalLogic;
import model.Goal;
import model.RemindGoalLogic;

/**
 * Servlet implementation class DeleteGoalServlet
 */
@WebServlet("/DeleteGoalServlet")
public class DeleteGoalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Goal goal = (Goal) session.getAttribute("goal");
		DeleteGoalLogic bo = new DeleteGoalLogic();
		boolean result = bo.execute(goal);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		if(result) {
			if(RemindGoalLogic.timer != null) {
				RemindGoalLogic.timer.cancel();
			}
		response.sendRedirect("/reminder/GetGoalListServlet");
		} else {
			request.setAttribute("errorMsg", "更新に失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
