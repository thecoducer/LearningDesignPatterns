package org.rahzex.observer.observable;

import org.rahzex.observer.entity.Settings;
import org.rahzex.observer.observers.SettingsEventSubscriber;
import org.thecoducer.inventorymanagementsystem.event.Event;

public interface EventPublisher {
  void subscribe(Event event, SettingsEventSubscriber eventSubscriber);

  void unsubscribe(Event event, SettingsEventSubscriber eventSubscriber);

  void publish(Event event, Settings settings);
}
