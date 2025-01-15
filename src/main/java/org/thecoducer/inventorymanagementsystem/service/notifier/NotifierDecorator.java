package org.thecoducer.inventorymanagementsystem.service.notifier;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.inventorymanagementsystem.entity.CommunicationIdentifiers;

@Slf4j
public class NotifierDecorator implements Notifier {
  private final Notifier notifier;

  public NotifierDecorator(Notifier notifier) {
    log.debug("Final notifier: {}", notifier.toString());
    this.notifier = notifier;
  }

  @Override
  public void send(CommunicationIdentifiers communicationIdentifiers) {
    notifier.send(communicationIdentifiers);
  }
}
