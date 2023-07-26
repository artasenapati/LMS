package com.eeft.lms.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.eeft.lms.model.Mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Service
public class MailServiceImpl implements MailService
{
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(Mail mail) 
	{
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(mail.getMailSubject());
			System.out.println("....."+mail.getMailSubject());
//			mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
//			System.out.println("......"+mail.getMailFrom());
			mimeMessageHelper.setTo(mail.getMailTo());
			System.out.println("......"+mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent());
			javaMailSender.send(mimeMessageHelper.getMimeMessage());
		} 
		catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
