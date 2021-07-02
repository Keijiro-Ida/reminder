package model;

import java.util.Date;

public class Remind {
	private String text;
	private String mail;
	private Date remindTime;
	
	public Remind() {}
	public Remind(String mail, String text, Date remindTime) {
		this.mail = mail;
		this.text = text;
		this.remindTime = remindTime;
	}
	
	public String getText() {
		return this.text;
	}
	public String getMail() {
		return mail;
	}
	public Date getRemindTime() {
		return remindTime;
	}
}
