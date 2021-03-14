package com.shareBuddy.utility;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.shareBuddy.pojos.Order;
import com.shareBuddy.pojos.User;

@Component
public class MailContructor {
	
	@Autowired
	private Environment env;

	@Autowired
	private TemplateEngine templateEngine;

	public SimpleMailMessage constructResetTokenMail(String contextPath, Locale locale, String token, User user, String password) {
		
		String url = contextPath+"/newAccount?token="+token;
		String message = "\n Please click on this to Reset Your password is: \n "+password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject(" ShareBuddy Account's Password Reset  ");
		email.setText(url+message);
		email.setFrom(env.getProperty("support.mail"));
		return email;
	}
	
	
	public MimeMessagePreparator constructOrderConfirmationEmail (User user, Order order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Interested Buyer Offer For Your Book - "+order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("dream.fello.12.12@gmail.com"));
			}
		};
		
		return messagePreparator;
	}


	public MimeMessagePreparator constructInterestEmail(User user, Order order, Locale english) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		String text = templateEngine.process("orderInterestedEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Interested Buyer Offer For Your Book - "+order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("dream.fello.12.12@gmail.com"));
			}
		};
		
		return messagePreparator;
	}
}
