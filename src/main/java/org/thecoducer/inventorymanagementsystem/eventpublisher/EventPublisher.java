package org.thecoducer.inventorymanagementsystem.eventpublisher;

import org.thecoducer.inventorymanagementsystem.event.Event;
import org.thecoducer.inventorymanagementsystem.eventsubscriber.EventSubscriber;

public interface EventPublisher {
  void subscribe(Event event, EventSubscriber eventSubscriber);

  void unsubscribe(Event event, EventSubscriber eventSubscriber);

  void notify(Event event);
}
