package org.thecoducer.observerpattern.entity;

import lombok.Builder;
import lombok.Data;
import org.thecoducer.observerpattern.event.Event;
import org.thecoducer.observerpattern.eventsubscriber.EventSubscriber;

@Data
@Builder
public class Customer {
  private String name;
  private String emailId;
  private String phoneNumber;
}
