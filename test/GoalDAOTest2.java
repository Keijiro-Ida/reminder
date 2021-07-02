package test;

import java.util.List;

import DAO.GoalDAO;
import model.Goal;
import model.Users;

public class GoalDAOTest2 {

	public static void main(String[] args) {
		testFindAll();

	}
	public static void testFindAll() {
		GoalDAO dao = new GoalDAO();
		Users users = new Users("3", "idatt1122@gmail.com", "11221118");
		List<Goal> goalList = dao.findAll(users);
		goalList.forEach(goal -> System.out.println(goal.getText()));
	}

}
