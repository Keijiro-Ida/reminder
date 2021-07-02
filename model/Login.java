package model;
import java.io.Serializable;

public class Login implements Serializable{
	public String mail;
	public String pass;
	
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
