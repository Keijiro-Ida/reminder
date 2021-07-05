package test;

import java.sql.Timestamp;

import model.Remind;
import model.RemindLogic;

public class SendMailTest {

	public static void main(String[] args) {
		Timestamp time = new Timestamp(System.currentTimeMillis() + 1000L);
		
		Remind remind = new Remind("1","idatt1122@gmail.com", "2英語"
				+ "", time);
		RemindLogic bo = new RemindLogic(remind);
		bo.execute();
		RemindLogic.map.get("1").cancel(true);
	}

}
