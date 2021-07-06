package model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RemindLogic { //リマインド通知を行うクラス
	public Remind remind; //リマインド通知の情報
	public ScheduledExecutorService service; //リマインド通知を送るスレッド
	public ScheduledFuture<?> sf; //リマインド通知を送るスレッドの戻り値,キャンセルを行うためのインスタンス。
	public static HashMap<String, ScheduledFuture<?>> map; //goalIDから、スレッドのキャンセルを行うためのmap
	
	public RemindLogic(Remind remind) {
		this.remind = remind;
		service =  Executors.newSingleThreadScheduledExecutor();
		}
		

	public void execute() { //リマインド設定を行う
		
			Runnable task1 = () -> { //リマインド時刻にメールを送信
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
				          return new PasswordAuthentication("reminderappcenter@gmail.com", "");
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
				    } catch (MessagingException e) {
				      e.printStackTrace();
				    }
			};
			Timestamp now = new Timestamp(System.currentTimeMillis());
			long time = remind.getRemindTime().getTime() - now.getTime(); //リマインド時刻と現在時刻の差分
			try {
				sf = service.schedule(task1, time, TimeUnit.MILLISECONDS); //リマインドを設定
				if(map == null) { //mapが生成されていないときに、map生成
					map = new HashMap<String, ScheduledFuture<?>>();
				}
				map.put(remind.getGoalId(), sf); //キャンセルを行う時のための格納
			} finally {
			if(service != null) service.shutdown(); //スレッド終了
		}
		}
}