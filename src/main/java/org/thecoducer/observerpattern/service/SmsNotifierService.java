package org.thecoducer.observerpattern.service;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.observerpattern.event.Event;

@Slf4j
public class SmsNotifierService {
  public void sendSms(String phoneNumber) {
    log.info("SMS sent to {}.", phoneNumber);
  }
}
