package org.thecoducer.inventorymanagementsystem.eventpublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.thecoducer.inventorymanagementsystem.event.Event;
import org.thecoducer.inventorymanagementsystem.eventsubscriber.EventSubscriber;
import org.thecoducer.inventorymanagementsystem.repository.FakeDB;

@Slf4j
public class EventUpdatePublisher implements EventPublisher {
  private final Map<Event, List<EventSubscriber>> eventSubscriberMap =
      FakeDB.getEventSubscriberMap();

  @Override
  public void subscribe(Event event, EventSubscriber eventSubscriber) {
    eventSubscriberMap.computeIfAbsent(event, _ -> new ArrayList<>()).add(eventSubscriber);
  }

  @Override
  public void unsubscribe(Event event, EventSubscriber eventSubscriber) {
    eventSubscriberMap.computeIfPresent(
        event,
        (_, eventSubscribers) -> {
          eventSubscribers.remove(eventSubscriber);
          return eventSubscribers.isEmpty() ? null : eventSubscribers;
        });
  }

  @Override
  public void notify(Event event) {
    Optional.ofNullable(eventSubscriberMap.get(event))
        .ifPresent(subscribers -> subscribers.forEach(EventSubscriber::update));
  }
}
