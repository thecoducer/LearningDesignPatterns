package org.thecoducer.observerpattern.service;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.observerpattern.event.Event;

@Slf4j
public class EmailNotifierService {

  public void sendEmail(String emailId) {
    log.info("Mail sent to {}.", emailId);
  }
}
