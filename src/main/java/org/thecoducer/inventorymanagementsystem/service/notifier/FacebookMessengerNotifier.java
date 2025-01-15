package org.thecoducer.inventorymanagementsystem.service.notifier;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.inventorymanagementsystem.entity.CommunicationIdentifiers;

@Slf4j
public class FacebookMessengerNotifier extends NotifierDecorator {

  public FacebookMessengerNotifier(Notifier notifier) {
    super(notifier);
  }

  @Override
  public void send(CommunicationIdentifiers communicationIdentifiers) {
    super.send(communicationIdentifiers);
    log.info("Facebook messenger text sent to {}.", communicationIdentifiers.getFacebookMessengerId());
  }
}
