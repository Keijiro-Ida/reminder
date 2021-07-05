package model;

import java.sql.Timestamp;

public class Remind { //リマインド通知の情報
	private String goalId; //目標ID
	private String text; //リマインドするテキスト
	private String mail; //メールアドレス
	private Timestamp remindTime; //リマインド時刻設定
	
	public Remind() {}
	public Remind(String goalId, String mail, String text, Timestamp remindTime) {
		this.goalId = goalId;
		this.mail = mail;
		this.text = text;
		this.remindTime = remindTime;
	}
	public String getGoalId() {
		return goalId;
	}
	public String getText() {
		return text;
	}
	public String getMail() {
		return mail;
	}
	public Timestamp getRemindTime() {
		return remindTime;
	}
}
