package test;

import java.util.List;

import model.GetGoalListLogic;
import model.Goal;
import model.Users;

public class GetGoalListLogicTest {

	public static void main(String[] args) {
		testExecute();

	}
	public static void testExecute() {
		GetGoalListLogic bo = new GetGoalListLogic();
		Users users = new Users("3", "idatt1122@gmail.com", "11221118");
		List<Goal> list = bo.execute(users);
		list.forEach(s -> System.out.println(s.getText()));
	}
}
