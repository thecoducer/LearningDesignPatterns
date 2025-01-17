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
    preloadDataForPriceUpdateTest();
    return itemMap;
  }

  public static Item getItem(int itemId) {
    return getItemMap().getOrDefault(itemId, Item.builder().quantity(0).price(0).build());
  }

  public static void addOrUpdateItem(Item item) {
    getItemMap().put(item.getId(), item);
  }

  private static void preloadDataForSoonToGoToOutOfStockTest() {
    Item itemWithQuantityThree = Item.builder().id(4).name("Dining Table").quantity(3).build();
    itemMap.put(itemWithQuantityThree.getId(), itemWithQuantityThree);
  }

  private static void preloadDataForPriceUpdateTest() {
    Item itemOne = Item.builder().id(6).name("Wardrobe").quantity(4).price(60000).build();
    Item itemTwo = Item.builder().id(7).name("Bed side table").quantity(5).price(5000).build();
    itemMap.put(itemOne.getId(), itemOne);
    itemMap.put(itemTwo.getId(), itemTwo);
  }
}
