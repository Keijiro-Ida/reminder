package model;
import DAO.GoalDAO;

public class DeleteGoalLogic {
	public int execute(Goal goal) {
		GoalDAO dao = new GoalDAO();
		int result = dao.deleteGoal(goal);
		return result;
	}

}
