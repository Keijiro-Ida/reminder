package model;

import java.util.Timer;

public class RemindGoalLogic {
	public static Timer timer;
		
	public void execute(Remind remind) {
		timer = new Timer();
		timer.schedule(new RemindTask(remind), remind.getRemindTime());
		
	}
	public void cancel() {
		timer.cancel();
		System.out.println("キャンセルOK");
		
	}
}

