package model;
import java.util.List;

import DAO.GoalDAO;

public class GetGoalListLogic {
	public List<Goal> execute(Users users) {
		GoalDAO dao = new GoalDAO();
		List<Goal> list = dao.findAll(users);
		return list;
	}
}
