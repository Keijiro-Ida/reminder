package test;

import java.util.List;

import model.DeleteGoalLogic;
import model.GetGoalListLogic;
import model.Goal;
import model.Users;

public class DeleteGoalLogicTest {

	public static void main(String[] args) {
		GetGoalListLogic bo = new GetGoalListLogic();
		Users users = new Users("3", "idatt1122@gmail.com", "11221118");
		List<Goal> list = bo.execute(users);
		Goal goal = list.get(0);
		
		DeleteGoalLogic bo2 = new DeleteGoalLogic();
		boolean result = bo2.execute(goal);
		if(result) {
			System.out.println("Test成功");
		} else {
			System.out.println("Test失敗");
		}

	}

}
