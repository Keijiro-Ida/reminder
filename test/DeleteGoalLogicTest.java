package test;

import java.time.LocalTime;
import java.util.List;

import model.DeleteGoalLogic;
import model.GetGoalListLogic;
import model.Goal;
import model.Users;

public class DeleteGoalLogicTest {

	public static void main(String[] args) {
		GetGoalListLogic bo = new GetGoalListLogic();
		LocalTime now = LocalTime.now();
		Users users = new Users("3", "idatt1122@gmail.com", "11221118", now);
		List<Goal> list = bo.execute(users);
		Goal goal = list.get(0);
		
		DeleteGoalLogic bo2 = new DeleteGoalLogic();
		int result = bo2.execute(goal);
		if(result == 1) {
			System.out.println("Test成功");
		} else {
			System.out.println("Test失敗");
		}

	}

}
