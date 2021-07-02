package model;
import java.io.Serializable;

public class Users implements Serializable{
	private String usrId;
	private String mail;
	private String pass;
	
	public Users() {}
	public Users(String usrId, String mail, String pass) {
		this.usrId = usrId;
		this.mail = mail;
		this.pass = pass;
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
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
