package org.rahzex.observer.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.rahzex.observer.entity.Settings;
import org.rahzex.observer.event.SettingsEvent;
import org.rahzex.observer.observers.SettingsEventSubscriber;
import org.rahzex.observer.repository.DB;
import org.thecoducer.inventorymanagementsystem.event.Event;

public class SettingUpdatePublisher implements EventPublisher {
  private final Map<Event, List<SettingsEventSubscriber>> eventSubscriberMap =
      DB.getEventSubscribers();

  @Override
  public void subscribe(Event event, SettingsEventSubscriber eventSubscriber) {
    eventSubscriberMap.computeIfAbsent(event, _ -> new ArrayList<>()).add(eventSubscriber);
  }

  @Override
  public void unsubscribe(Event event, SettingsEventSubscriber eventSubscriber) {
    eventSubscriberMap.computeIfPresent(
        event,
        (_, eventSubscribers) -> {
          eventSubscribers.remove(eventSubscriber);
          return eventSubscribers.isEmpty() ? null : eventSubscribers;
        });
  }

  @Override
  public void publish(Event event, Settings settings) {
    List<SettingsEventSubscriber> subscribers = eventSubscriberMap.get(event);
    subscribers.forEach(subscriber -> subscriber.update((SettingsEvent) event, settings));
  }
}
