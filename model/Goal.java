package model;
import java.io.Serializable;
import java.sql.Timestamp;

public class Goal implements Serializable { //目標
	private String usrId; //ユーザーID
	private String goalId; //目標ID
	private String text; //テキスト
	private Timestamp goalTime; //登録時刻
	private Timestamp remindTime; //リマインド時刻設定
	
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
