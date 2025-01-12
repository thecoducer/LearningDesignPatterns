package org.thecoducer.observerpattern.eventpublisher;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.observerpattern.entity.Customer;
import org.thecoducer.observerpattern.entity.Item;
import org.thecoducer.observerpattern.event.Event;
import org.thecoducer.observerpattern.event.StockEvent;
import org.thecoducer.observerpattern.eventsubscriber.StockUpdateSubscriber;

import java.util.*;

@Slf4j
public class StockUpdatePublisherImpl implements StockUpdatePublisher {
  private final Map<Event, List<Customer>> eventSubscriberMap = new HashMap<>();
  Map<Integer, Item> itemMap = new HashMap<>();
  private final StockUpdateSubscriber stockUpdateSubscriber = new StockUpdateSubscriber();

  @Override
  public void subscribe(Event event, Customer customer) {
    eventSubscriberMap.computeIfAbsent(event, v -> new ArrayList<>()).add(customer);
  }

  @Override
  public void unsubscribe(Event event, Customer customer) {
    eventSubscriberMap.computeIfPresent(event, (key, eventSubscribers) -> {
      eventSubscribers.remove(customer);
      return eventSubscribers.isEmpty() ? null : eventSubscribers;
    });
  }

  @Override
  public void notify(Event event) {
    List<Customer> customers = eventSubscriberMap.get(event);
    if(customers != null) {
      stockUpdateSubscriber.update(customers);
    }
  }

  public void updateStock(Item item) {
    int currentQuantity = itemMap.getOrDefault(item.getId(), Item.builder().quantity(0).build()).getQuantity();
    itemMap.putIfAbsent(item.getId(), item);
    int quantity = item.getQuantity();

    if (currentQuantity == 0 && quantity != 0) {
      notify(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE);
    } else if (currentQuantity <= 2 || quantity <= 2) {
      notify(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK);
    }
    itemMap.get(item.getId()).setQuantity(quantity);

  }
}
