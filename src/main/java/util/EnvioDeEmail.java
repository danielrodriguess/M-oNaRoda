package main.java.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvioDeEmail {

	public void send(String to,String nome,String link) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("batalhamoral","batalhamoral2016");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("password@batalhamoral.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("RecuperaÁ„o de senha - M„o na Roda");
			message.setText("Ol· "+nome+",\n\n"
					  + "Conforme solicitado, segue o link de acesso para redefiniÁ„o senha:\n\n"
					  + link
					  + "\nAcesse o link acima para que uma nova senha seja criada\n\n"
				  	  + "Obrigado. N„o responda ‡ esse e-mail!");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void boasVindas(String to,String nome) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("batalhamoral","batalhamoral2016");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("password@batalhamoral.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Seja bem-vindo - M√£o na Roda");
			message.setText("Ol√° "+nome+",\n\n"
					  + "Seja bem-vindo ao nosso sistema, ele ir√° te ajudar muito na sua jornada Senai\n"
					  + "Qualquer d√∫vida entre em contato com a nossa central.\n\n"
				  	  + "Obrigado. N√£o responda √† esse e-mail!");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}