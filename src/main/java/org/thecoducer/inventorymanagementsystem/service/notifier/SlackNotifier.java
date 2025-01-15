package org.thecoducer.inventorymanagementsystem.service.notifier;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.inventorymanagementsystem.dto.CommunicationIdentifiers;

@Slf4j
public class SlackNotifier extends NotifierDecorator {

  public SlackNotifier(Notifier notifier) {
    super(notifier);
  }

  @Override
  public void send(CommunicationIdentifiers communicationIdentifiers) {
    super.send(communicationIdentifiers);
    log.info("Slack message sent to {}.", communicationIdentifiers.getSlackId());
  }
}
