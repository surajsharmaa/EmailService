package com.ebook.emailservice.messagelistener;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.ebook.api.message.Email;

@Component
public class EmailMessageHandler {

  @JmsListener(destination = "mailbox")
  public void receiveMessage(Email email) {
    System.out.println("Received <" + email.getBody() + ">");
  }

}
