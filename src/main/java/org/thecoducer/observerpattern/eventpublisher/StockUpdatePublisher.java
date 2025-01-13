package org.thecoducer.observerpattern.eventpublisher;

import org.thecoducer.observerpattern.event.Event;
import org.thecoducer.observerpattern.eventsubscriber.EventSubscriber;

public interface StockUpdatePublisher {
  void subscribe(Event event, EventSubscriber eventSubscriber);

  void unsubscribe(Event event, EventSubscriber eventSubscriber);

  void notify(Event event);
}
