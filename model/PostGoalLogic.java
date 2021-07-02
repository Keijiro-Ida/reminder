package model;
import DAO.GoalDAO;
public class PostGoalLogic {
	public  boolean execute(PostGoal postGoal) {
		GoalDAO dao = new GoalDAO();
		return dao.goalCreate(postGoal) == 1;
	}

}
