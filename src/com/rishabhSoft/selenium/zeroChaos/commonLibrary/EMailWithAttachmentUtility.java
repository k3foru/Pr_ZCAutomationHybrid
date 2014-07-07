package com.rishabhSoft.selenium.zeroChaos.commonLibrary;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.reporters.EmailableReporter;

import com.rishabhSoft.selenium.zeroChaos.drivers.SetObjectProperties;

public class EMailWithAttachmentUtility extends EmailableReporter{

	private static String SMTP_HOST_NAME;
	private static String SMTP_PORT;
	private static String SSL_FACTORY;
	
	public static void main(String args[]) throws Exception {
		
		//public static void sendEmail() throws Exception {
		SMTP_HOST_NAME = SetObjectProperties.appConfig.getPropertyValue("SMTP_HOST");
		SMTP_PORT = SetObjectProperties.appConfig.getPropertyValue("SMTP_PORT");
		SSL_FACTORY = SetObjectProperties.appConfig.getPropertyValue("SSL_FACTORY");
		final String emailMsgTxt = SetObjectProperties.appConfig.getPropertyValue("EmailBodyMesage");
		final String emailSubjectTxt = SetObjectProperties.appConfig.getPropertyValue("EmailSubjectLine");
		final String emailFromAddress = SetObjectProperties.appConfig.getPropertyValue("FromEmailID");
		final String fromPassword = SetObjectProperties.appConfig.getPropertyValue("FromEmailPass");
		final String[] To = {SetObjectProperties.appConfig.getPropertyValue("ToEmailID")};
		final String[] CC = {"Abhishek.Singh@rishabhsoft.com","abhi.mca22@gmail.com"};
		final String[] BCC = {"Abhishek.Singh@rishabhsoft.com","abhi.mca22@gmail.com"};
		final String attachmentPath = "test-output\\emailable-report.html";
		final String attachmentFile = "emailable-report.html";
		System.out.println("Sending EMail ...");
		if(SetObjectProperties.appConfig.getPropertyValue("EmailNotificationFlag").equalsIgnoreCase("Yes")){
			//Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			new EMailWithAttachmentUtility().sendSSLMessage(To, CC, BCC, emailSubjectTxt, emailMsgTxt, emailFromAddress, fromPassword, attachmentPath, attachmentFile);
		}
	}
	
	public void sendSSLMessage(String to[], String cc[], String bcc[], String subject, String textMessage, final String fromID, final String password, String attachmentPath, String attachmentFile) throws MessagingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "flase");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");
		try{
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromID, password);
				}
			});
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromID));
			for(int i=0; i < to.length; i++){
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			for(int i=0; i < cc.length; i++){
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
			}
			for(int i=0; i < bcc.length; i++){
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
			}
			// Set Subject Line
			message.setSubject(subject);
			
			// create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			
			//fill message
			messageBodyPart.setText(textMessage);
			
			// create Multi part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentPath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachmentFile);
			multipart.addBodyPart(messageBodyPart);
			
			// Put parts in message
			message.setContent(multipart);
			
			// Send the message
			Transport.send(message);
			System.out.println("Report Sent Sucessfully to All Users");
		 }
	    catch (Exception mex){
	    	mex.printStackTrace();
	    	System.out.println("E-Mail Sending Failled");
	    }
	}
}