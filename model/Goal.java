package model;
import java.io.Serializable;
import java.sql.Timestamp;

public class Goal implements Serializable {
	private String usrId;
	private String goalId;
	private String text;
	private Timestamp goalTime;
	private Timestamp remindTime;
	
	public Goal() {}
	public Goal(String usrId, String goalId, String text, 
						Timestamp goalTime, Timestamp remindTime) {
		this.usrId = usrId;
		this.goalId = goalId;
		this.text = text;
		this.goalTime = goalTime;
		this.remindTime = remindTime;
	}
	
	public String getUsrId() {
		return usrId;
	}
	public String getGoalId() {
		return goalId;
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
	
	public void setText(String text) {
		this.text = text;
	}
	public void setRemindTime(Timestamp remindTime) {
		this.remindTime = remindTime;
	}
}
