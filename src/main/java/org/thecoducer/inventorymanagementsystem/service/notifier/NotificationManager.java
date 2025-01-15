package org.thecoducer.inventorymanagementsystem.service.notifier;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.inventorymanagementsystem.dto.CommunicationIdentifiers;

@Slf4j
public class NotificationManager {

  public void send(CommunicationIdentifiers communicationIdentifiers) {
    Notifier notifier = new EmailNotifier();
    if (communicationIdentifiers.isSmsEnabled()) {
      notifier = new SmsNotifier(notifier);
    }
    if (communicationIdentifiers.isSlackEnabled()) {
      notifier = new SlackNotifier(notifier);
    }
    if (communicationIdentifiers.isFacebookMessengerEnabled()) {
      notifier = new FacebookMessengerNotifier(notifier);
    }
    notifier.send(communicationIdentifiers);
  }
}
