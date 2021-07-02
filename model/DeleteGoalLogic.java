package model;
import DAO.GoalDAO;

public class DeleteGoalLogic {
	public boolean execute(Goal goal) {
		GoalDAO dao = new GoalDAO();
		int result = dao.deleteGoal(goal);
		return result == 1;
	}

}
