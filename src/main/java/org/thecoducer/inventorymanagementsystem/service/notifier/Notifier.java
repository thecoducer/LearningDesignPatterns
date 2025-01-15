package org.thecoducer.inventorymanagementsystem.service.notifier;

import org.thecoducer.inventorymanagementsystem.dto.CommunicationIdentifiers;

public interface Notifier {
  void send(CommunicationIdentifiers communicationIdentifiers);
}
