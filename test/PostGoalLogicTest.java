package test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.PostGoal;
import model.PostGoalLogic;

public class PostGoalLogicTest {

	public static void main(String[] args) {
		testExecute1();
		testExecute2();
	}
		public static void testExecute1() {
		LocalDateTime now = LocalDateTime.now();
		Timestamp current = Timestamp.valueOf(now);
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String date = today.format(dtf).toString();
		
		String time = "10:30";
		String datetime = date + " " + time;
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		LocalDateTime remind = LocalDateTime.parse(datetime,dtf2);
		Timestamp remindTime = Timestamp.valueOf(remind);
		
		PostGoal postGoal = new PostGoal("5", "ありがとうを３回言う", current, remindTime);
		PostGoalLogic bo = new PostGoalLogic();
		boolean result = bo.execute(postGoal);
		
		if(result) {
			System.out.println("testExecute1:成功");
		} else {
			System.out.println("testExecute1:失敗");
		}

	}
		public static void testExecute2() {
			Timestamp current = new Timestamp(System.currentTimeMillis());
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
			LocalDateTime time = LocalDateTime.parse("21/06/24 10:11",fmt);
			Timestamp ts = Timestamp.valueOf(time);
			PostGoal postGoal = new PostGoal("3", "ありがとうを３回言う",ts, current);
			PostGoalLogic bo = new PostGoalLogic();
			boolean result = bo.execute(postGoal);
			
			if(result) {
				System.out.println("testExecute2:成功");
			} else {
				System.out.println("testExecute2:失敗");
			}

		}

}
