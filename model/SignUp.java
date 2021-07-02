package model;
import java.io.Serializable;

public class SignUp implements Serializable{
	private String mail;
	private String pass;
	
	public SignUp() {}
	public SignUp(String mail, String pass) {
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
