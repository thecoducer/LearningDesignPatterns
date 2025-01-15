package org.thecoducer.inventorymanagementsystem.service.notifier;

import org.thecoducer.inventorymanagementsystem.dto.CommunicationIdentifiers;

public class NotifierDecorator implements Notifier {
  private final Notifier notifier;

  public NotifierDecorator(Notifier notifier) {
    this.notifier = notifier;
  }

  @Override
  public void send(CommunicationIdentifiers communicationIdentifiers) {
    notifier.send(communicationIdentifiers);
  }
}
