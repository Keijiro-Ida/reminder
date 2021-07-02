package model;
import java.sql.Timestamp;

public class PostGoal {
	private String usrId;
	private String text;
	private Timestamp goalTime;
	private Timestamp remindTime;
	
	public PostGoal(String usrId, String text, Timestamp goalTime, Timestamp remindTime) {
		this.usrId = usrId;
		this.text = text;
		this.goalTime = goalTime;
		this.remindTime = remindTime;
	}
	
	public String getUsrId() {
		return usrId;
	}
	
	public String getText() {
		return text;
	}
	
	public Timestamp getGoalTime() {
		return goalTime;
	}
	public Timestamp getRemindTime() {
		return remindTime;
	}

}
