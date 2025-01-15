package org.thecoducer.inventorymanagementsystem.service.notifier;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.inventorymanagementsystem.dto.CommunicationIdentifiers;

@Slf4j
public class SmsNotifier extends NotifierDecorator {

  public SmsNotifier(Notifier notifier) {
    super(notifier);
  }

  @Override
  public void send(CommunicationIdentifiers communicationIdentifiers) {
    super.send(communicationIdentifiers);
    log.info("SMS sent to {}.", communicationIdentifiers.getPhoneNumber());
  }
}
