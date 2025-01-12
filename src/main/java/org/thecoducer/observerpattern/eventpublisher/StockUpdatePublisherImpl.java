package org.thecoducer.observerpattern.eventpublisher;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.observerpattern.entity.Item;
import org.thecoducer.observerpattern.event.Event;
import org.thecoducer.observerpattern.event.StockEvent;
import org.thecoducer.observerpattern.eventsubscriber.StockUpdateSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class StockUpdatePublisherImpl implements StockUpdatePublisher {
  private final Map<Event, List<StockUpdateSubscriber>> eventSubscriberMap = new HashMap<>();
  private final Map<Integer, Item> itemMap = new HashMap<>();

  @Override
  public void subscribe(Event event, StockUpdateSubscriber stockUpdateSubscriber) {
    eventSubscriberMap.computeIfAbsent(event, v -> new ArrayList<>()).add(stockUpdateSubscriber);
  }

  @Override
  public void unsubscribe(Event event, StockUpdateSubscriber stockUpdateSubscriber) {
    eventSubscriberMap.computeIfPresent(event, (key, eventSubscribers) -> {
      eventSubscribers.remove(stockUpdateSubscriber);
      return eventSubscribers.isEmpty() ? null : eventSubscribers;
    });
  }

  @Override
  public void notify(Event event) {
    List<StockUpdateSubscriber> subscribers = eventSubscriberMap.get(event);
    subscribers.forEach(StockUpdateSubscriber::update);
  }

  public void updateStock(Item item) {
    int currentQuantity = itemMap.getOrDefault(item.getId(), Item.builder().quantity(0).build()).getQuantity();
    int quantity = item.getQuantity();

    log.info("Stock updated. Item quantity {} to {}.", currentQuantity, quantity);
    if (currentQuantity == 0 && quantity != 0) {
      notify(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE);
    } else if (currentQuantity <= 2 || quantity <= 2) {
      notify(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK);
    }

    itemMap.put(item.getId(), item);
  }
}
