package org.thecoducer.observer.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailNotifierService {

  public void sendEmail(String emailId) {
    log.info("Mail sent to {}.", emailId);
  }
}
