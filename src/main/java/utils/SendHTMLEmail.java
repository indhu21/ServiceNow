package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendHTMLEmail {
	public static void sendEmail(String sysID,Integer errorcode,String errorDescription) throws AddressException, MessagingException {
		try {
			
			String to = "aman.seth@punchitinc.com";
			final String from = "PunchITEmailTrigger@gmail.com";

			Properties properties = new Properties();
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");

			Session session = Session.getInstance(properties,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, "Punchitinc.com");
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Record Update Failure  Notification - sys ID : " +sysID);
			String HTMltext="<h2>Record Status Update Failure <br></h2>Record sys id : "+sysID+"<br>Error code : "+ errorcode +"<br> Error Description ;"+errorDescription;
			message.setContent(HTMltext, "text/html" );
			Transport.send(message);

			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
