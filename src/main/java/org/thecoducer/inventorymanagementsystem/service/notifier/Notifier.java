package org.thecoducer.inventorymanagementsystem.service.notifier;

import org.thecoducer.inventorymanagementsystem.entity.CommunicationIdentifiers;

public interface Notifier {
  void send(CommunicationIdentifiers communicationIdentifiers);
}
