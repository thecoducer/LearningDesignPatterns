package org.rahzex.behavioral.observer.observable;

import org.rahzex.behavioral.observer.entity.Settings;
import org.rahzex.behavioral.observer.observers.SettingsEventSubscriber;
import org.thecoducer.inventorymanagementsystem.event.Event;

public interface EventPublisher {
  void subscribe(Event event, SettingsEventSubscriber eventSubscriber);

  void unsubscribe(Event event, SettingsEventSubscriber eventSubscriber);

  void publish(Event event, Settings settings);
}
