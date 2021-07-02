package test;

import model.RemindGoalLogic;

public class CancelRemindTest {

	public static void main(String[] args) {
		RemindGoalLogic bo = new RemindGoalLogic();
		bo.cancel();
		System.out.println("キャンセル成功");
		
	}

}
