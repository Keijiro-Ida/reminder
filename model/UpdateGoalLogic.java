package model;
import DAO.GoalDAO;

public class UpdateGoalLogic {
	public boolean execute(Goal goal) {
		GoalDAO dao = new GoalDAO();
		int result = dao.updateGoal(goal);
		
		return result == 1;
	}
}
