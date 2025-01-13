package org.thecoducer.observer.util;

import org.thecoducer.observer.service.EmailNotifierService;
import org.thecoducer.observer.service.SmsNotifierService;

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
