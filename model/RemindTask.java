package model;

import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RemindTask extends TimerTask{
	Remind remind;
	public RemindTask(Remind remind) {
		this.remind = remind;
	}
	public void run() {
		try {
		      Properties property = new Properties();
		      // 各種設定             property.put("mail.smtp.host", "smtp.gmail.com");
		      property.put("mail.smtp.auth", "true");
		      property.put("mail.smtp.starttls.enable", "true");
		      property.put("mail.smtp.host", "smtp.gmail.com");
		      property.put("mail.smtp.port", "587"
		      		+ "");
		      property.put("mail.smtp.debug", "true");
		      Session session = Session.getInstance(property, new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		          return new PasswordAuthentication("reminderappcenter@gmail.com", "bjmiairrrxocaclf");
		        }
		      });
		      
		      MimeMessage mimeMessage = new MimeMessage(session);
		      InternetAddress toAddress = new InternetAddress(remind.getMail());
		      mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
		      InternetAddress fromAddress = new InternetAddress("reminderappcenter@gmail.com");
		      mimeMessage.setFrom(fromAddress);
		      mimeMessage.setSubject(remind.getText(), "ISO-2022-JP");
		      mimeMessage.setText(remind.getText(), "ISO-2022-JP");
		      mimeMessage.setText("--Sent with reminder App--", "ISO-2022-JP");
		      Transport.send(mimeMessage);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		
	}
}