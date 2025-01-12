package org.thecoducer.observerpattern.eventsubscriber;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.observerpattern.entity.Customer;
import org.thecoducer.observerpattern.event.Event;
import org.thecoducer.observerpattern.service.EmailNotifierService;
import org.thecoducer.observerpattern.service.SmsNotifierService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StockUpdateSubscriber implements EventSubscriber {
  private EmailNotifierService emailNotifierService = new EmailNotifierService();
  private SmsNotifierService smsNotifierService = new SmsNotifierService();

  @Override
  public void update(List<Customer> customers) {
    customers.forEach(customer -> {
      emailNotifierService.sendEmail(customer.getEmailId());
      smsNotifierService.sendSms(customer.getPhoneNumber());
    });
  }
}
