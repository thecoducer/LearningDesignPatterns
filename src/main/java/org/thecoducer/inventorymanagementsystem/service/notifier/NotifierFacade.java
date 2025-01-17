package org.thecoducer.inventorymanagementsystem.service.notifier;

import org.thecoducer.inventorymanagementsystem.entity.CommunicationIdentifiers;

public class NotifierFacade {
  private static final NotificationManager notificationManager = new NotificationManager();

  public static void sendCommunication(CommunicationIdentifiers communicationIdentifiers) {
    notificationManager.send(communicationIdentifiers);
  }
}
