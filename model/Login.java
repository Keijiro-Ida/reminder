package model;
import java.io.Serializable;

public class Login implements Serializable{ //ログイン時入力情報インスタンス
	public String mail; //ログイン時メールアドレス
	public String pass; //ログインパスワード
	
	public Login() {}
	public Login(String mail, String pass) {
		this.mail = mail;
		this.pass = pass;
	}
	
	public String getMail() {
		return mail;
	}
	public String getPass() {
		return pass;
	}
}
