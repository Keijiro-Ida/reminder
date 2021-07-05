package model;
import java.io.Serializable;
import java.time.LocalTime;

public class SignUp implements Serializable{ //新規登録情報
	private String mail; //新規登録メールアドレス
	private String pass; //パスワード
	private LocalTime defTime; //デフォルトの時刻設定
	
	public SignUp() {}
	public SignUp(String mail, String pass, LocalTime defTime) {
		this.mail = mail;
		this.pass = pass;
		this.defTime = defTime;
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

}
