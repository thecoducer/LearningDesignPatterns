package org.thecoducer.observerpattern.entity;

import lombok.Builder;
import lombok.Data;
import org.thecoducer.observerpattern.eventsubscriber.EventSubscriber;
import org.thecoducer.observerpattern.util.NotifierUtil;

@Data
@Builder
public class Customer implements EventSubscriber {
  private String name;
  private String emailId;
  private String phoneNumber;

  @Override
  public void update() {
    NotifierUtil.sendEmail(emailId);
    NotifierUtil.sendSms(phoneNumber);
  }
}
