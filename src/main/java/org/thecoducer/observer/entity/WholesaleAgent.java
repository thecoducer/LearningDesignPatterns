package org.thecoducer.observer.entity;

import lombok.Builder;
import lombok.Data;
import org.thecoducer.observer.dto.CommunicationIdentifiers;
import org.thecoducer.observer.eventsubscriber.EventSubscriber;
import org.thecoducer.observer.util.NotifierUtil;

@Data
@Builder
public class WholesaleAgent implements EventSubscriber {
  private String name;
  private CommunicationIdentifiers communicationIdentifiers;

  @Override
  public void update() {
    NotifierUtil.sendEmail(communicationIdentifiers.getEmailId());
    NotifierUtil.sendSms(communicationIdentifiers.getPhoneNumber());
  }
}
