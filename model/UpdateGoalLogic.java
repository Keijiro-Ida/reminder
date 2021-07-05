package model;
import DAO.GoalDAO;

public class UpdateGoalLogic { //目標の更新
	public int execute(Goal goal) {
		GoalDAO dao = new GoalDAO();
		int result = dao.updateGoal(goal);
		
		return result;
	}
}
