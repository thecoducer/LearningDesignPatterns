package org.thecoducer.observer.repository;

import lombok.Getter;
import org.thecoducer.observer.entity.Item;
import org.thecoducer.observer.event.Event;
import org.thecoducer.observer.eventsubscriber.EventSubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeDB {
  @Getter
  private static final Map<Event, List<EventSubscriber>> eventSubscriberMap = new HashMap<>();
  private static final Map<Integer, Item> itemMap = new HashMap<>();

  public static Map<Integer, Item> getItemMap() {
    preloadDataForSoonToGoToOutOfStockTest();
    return itemMap;
  }

  private static void preloadDataForSoonToGoToOutOfStockTest() {
    Item itemWithQuantityThree = Item.builder().id(4).name("Dining Table").quantity(3).build();
    itemMap.put(itemWithQuantityThree.getId(), itemWithQuantityThree);
  }
}
