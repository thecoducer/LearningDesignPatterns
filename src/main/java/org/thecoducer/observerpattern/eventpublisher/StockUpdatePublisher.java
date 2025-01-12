package org.thecoducer.observerpattern.eventpublisher;

import org.thecoducer.observerpattern.event.Event;
import org.thecoducer.observerpattern.eventsubscriber.StockUpdateSubscriber;

public interface StockUpdatePublisher {
  void subscribe(Event event, StockUpdateSubscriber stockUpdateSubscriber);

  void unsubscribe(Event event, StockUpdateSubscriber stockUpdateSubscriber);

  void notify(Event event);
}
