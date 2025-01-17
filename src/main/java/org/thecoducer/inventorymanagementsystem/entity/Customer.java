package org.thecoducer.inventorymanagementsystem.entity;

import lombok.Builder;
import lombok.Data;
import org.thecoducer.inventorymanagementsystem.eventsubscriber.EventSubscriber;
import org.thecoducer.inventorymanagementsystem.service.notifier.NotifierFacade;

@Data
@Builder
public class Customer implements EventSubscriber {
  private String name;
  private CommunicationIdentifiers communicationIdentifiers;

  @Override
  public void update() {
    NotifierFacade.sendCommunication(communicationIdentifiers);
  }
}
