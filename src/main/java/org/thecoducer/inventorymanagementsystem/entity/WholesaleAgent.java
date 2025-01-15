package org.thecoducer.inventorymanagementsystem.entity;

import lombok.Builder;
import lombok.Data;
import org.thecoducer.inventorymanagementsystem.dto.CommunicationIdentifiers;
import org.thecoducer.inventorymanagementsystem.eventsubscriber.EventSubscriber;
import org.thecoducer.inventorymanagementsystem.util.NotifierUtil;

@Data
@Builder
public class WholesaleAgent implements EventSubscriber {
  private String name;
  private CommunicationIdentifiers communicationIdentifiers;

  @Override
  public void update() {
    NotifierUtil.sendCommunication(communicationIdentifiers);
  }
}
