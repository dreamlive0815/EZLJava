package org.dreamlive0815.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil
{
    public static void sendMail(String s)
    {
        String to = "995928339@qq.com";
	      // 发件人电子邮箱
	      String from = "sunshineminiapp@163.com";
	      // 邮件服务器
	      String host = "smtp.163.com";
	      
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      properties.put("mail.smtp.auth", "true");
	      
	      // SSL加密
	      
	 
	      try{

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
	      sf.setTrustAllHosts(true);
	      properties.put("mail.smtp.ssl.enable", "true");
	      properties.put("mail.smtp.ssl.socketFactory", sf);
	      
	      // 获取默认session对象 设置auth
	      Session session = Session.getDefaultInstance(properties, new Authenticator() {
	    	  public PasswordAuthentication getPasswordAuthentication() {
	    		  return new PasswordAuthentication("sunshineminiapp@163.com", "kirisame233");
	          }
	      });
	         // 创建默认的 MimeMessage 对象
	         MimeMessage message = new MimeMessage(session);
	         // 发件人
	         message.setFrom(new InternetAddress(from));
	         // 收件人
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         // 标题
	         message.setSubject("Test Title");
	         // 正文
	         message.setText("Test Body");
	         // 发送消息
	         Transport.send(message);
	         
	         System.out.println("Mail sent successfully");
	      }catch (Exception mex) {
	         mex.printStackTrace();
	      }
    }
}