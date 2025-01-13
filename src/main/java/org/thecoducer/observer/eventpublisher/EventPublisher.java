package org.thecoducer.observer.eventpublisher;

import org.thecoducer.observer.event.Event;
import org.thecoducer.observer.eventsubscriber.EventSubscriber;

public interface EventPublisher {
  void subscribe(Event event, EventSubscriber eventSubscriber);

  void unsubscribe(Event event, EventSubscriber eventSubscriber);

  void notify(Event event);
}
