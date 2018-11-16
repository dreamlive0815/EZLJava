package org.dreamlive0815.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil
{
	Properties properties;
	Session session;

	private String from;
	private String to;

	public MailUtil(String host)
	{
		System.out.print("host: "); System.out.println(host);
		properties = System.getProperties();
		properties.put("mail.smtp.host", host);
	}

	public void setCredentials(String username, String password) throws Exception
	{
		System.out.print("username: "); System.out.println(username);
		System.out.print("password: "); System.out.println(password);
		properties.put("mail.smtp.auth", "true");
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		//sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		
		session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public void setFromTo(String from, String to) throws Exception
	{
		this.from = from;
		this.to = to;
	}

	public void sendText(String title, String body) throws Exception
    {
		System.out.print("from: "); System.out.println(from);
		System.out.print("to: "); System.out.println(to);
		if(session == null) session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	    message.setSubject(title);
	    message.setText(body);
		Transport.send(message);
		System.out.println("mail sent successfully");
    }
}