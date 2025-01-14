package org.thecoducer.observer.repository;

import lombok.Getter;
import org.thecoducer.observer.entity.Item;
import org.thecoducer.observer.event.Event;
import org.thecoducer.observer.eventsubscriber.EventSubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {
  @Getter
  private static final Map<Event, List<EventSubscriber>> eventSubscriberMap = new HashMap<>();
  @Getter
  private static final Map<Integer, Item> itemMap = new HashMap<>();
}
