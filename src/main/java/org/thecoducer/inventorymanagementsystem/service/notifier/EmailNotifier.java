package org.thecoducer.inventorymanagementsystem.service.notifier;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.inventorymanagementsystem.entity.CommunicationIdentifiers;

/**
 * Primary notifier
 */
@Slf4j
public class EmailNotifier implements Notifier {

  @Override
  public void send(CommunicationIdentifiers communicationIdentifiers) {
    log.info("Mail sent to {}.", communicationIdentifiers.getEmailId());
  }
}
