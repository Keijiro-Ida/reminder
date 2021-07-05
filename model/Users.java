package model;
import java.io.Serializable;
import java.time.LocalTime;

public class Users implements Serializable{ //ユーザー情報
	private String usrId; //ユーザーID 
	private String mail;  //メールアドレス
	private String pass;  //パスワード
	private LocalTime defTime; //デフォルトのリマインド時刻設定
	
	public Users() {}
	public Users(String usrId, String mail, String pass, LocalTime defTime) {
		this.usrId = usrId;
		this.mail = mail;
		this.pass = pass;
		this.defTime = defTime;
	}
	public String getUsrId() {
		return usrId;
	}
	public String getMail() {
		return mail;
	}
	public String getPass() {
		return pass;
	}
	public LocalTime getDefTime() {
		return defTime;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setDefTime(LocalTime defTime) {
		this.defTime = defTime;
	}
}
