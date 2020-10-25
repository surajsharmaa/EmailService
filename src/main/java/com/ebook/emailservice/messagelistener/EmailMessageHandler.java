package com.ebook.emailservice.messagelistener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.ebook.api.message.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Component
public class EmailMessageHandler {
	
	@Autowired
    private JavaMailSender javaMailSender;

  @JmsListener(destination = "mailbox")
  public void receiveMessage(Email email) {
    System.out.println("Sending email for OrderID: <" + email.getOrderId() + ">");
    sendEmail(email);
  }
  
  public void sendEmail(Email email) {

      SimpleMailMessage msg = new SimpleMailMessage();
      msg.setTo("surajsapkota1@gmail.com", "suraj.sharma@stjude.org", email.getTo());

      msg.setSubject("Yay! We received your order!");
      msg.setText("Thank you, "+ email.getFirst_name()+"!\n\n"
      		+ "Your order information:\n" 
    		+ "Order Id:"+ email.getOrderId() +"\n\n"
    		
			+ "Shipping Details:\n"
    		+ email.getRecipient_first_name()+ " " + email.getRecipient_last_name() +"\n"
    		+ email.getRecipient_address1() +"\n"
    		+ email.getRecipient_city() +", " + email.getRecipient_state() +", "+ email.getRecipient_zip_code() +"\n"
    		+ "Phone: "+ email.getRecipient_phone_number() +"\n\n"
    		
    		+ "Contact us if you have any questions about your order.\n\n Ebook"
    		
    		  );

      javaMailSender.send(msg);

  }

}
