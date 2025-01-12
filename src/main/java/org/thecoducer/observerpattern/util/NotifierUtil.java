package org.thecoducer.observerpattern.util;

import org.thecoducer.observerpattern.service.EmailNotifierService;
import org.thecoducer.observerpattern.service.SmsNotifierService;

public class NotifierUtil {
  private static final EmailNotifierService emailNotifierService = new EmailNotifierService();
  private static final SmsNotifierService smsNotifierService = new SmsNotifierService();

  public static void sendEmail(String emailId) {
    emailNotifierService.sendEmail(emailId);
  }

  public static void sendSms(String phoneNumber) {
    smsNotifierService.sendSms(phoneNumber);
  }
}
