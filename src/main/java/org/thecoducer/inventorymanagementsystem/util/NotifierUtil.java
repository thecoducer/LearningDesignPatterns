package org.thecoducer.inventorymanagementsystem.util;

import org.thecoducer.inventorymanagementsystem.dto.CommunicationIdentifiers;
import org.thecoducer.inventorymanagementsystem.service.notifier.NotificationManager;

public class NotifierUtil {
  private static final NotificationManager notificationManager = new NotificationManager();

  public static void sendCommunication(CommunicationIdentifiers communicationIdentifiers) {
    notificationManager.send(communicationIdentifiers);
  }
}
