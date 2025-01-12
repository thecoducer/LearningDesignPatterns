package org.thecoducer.observerpattern.eventpublisher;

import org.thecoducer.observerpattern.entity.Customer;
import org.thecoducer.observerpattern.event.Event;

public interface StockUpdatePublisher {
  void subscribe(Event event, Customer customer);
  void unsubscribe(Event event, Customer customer);
  void notify(Event event);
}
