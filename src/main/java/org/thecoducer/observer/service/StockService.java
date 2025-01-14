package org.thecoducer.observer.service;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.observer.entity.Item;
import org.thecoducer.observer.event.StockEvent;
import org.thecoducer.observer.eventpublisher.StockUpdatePublisher;
import org.thecoducer.observer.repository.DB;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class StockService {
  private final Map<Integer, Item> itemMap;
  private final StockUpdatePublisher stockUpdatePublisher;

  public StockService() {
    stockUpdatePublisher = new StockUpdatePublisher();
    itemMap = DB.getItemMap();
  }

  public void updateStock(Item item) {
    int currentQuantity = itemMap.getOrDefault(item.getId(), Item.builder().quantity(0).build()).getQuantity();
    int quantity = item.getQuantity();

    log.debug("Stock updated. Item quantity {} to {}.", currentQuantity, quantity);
    if (currentQuantity == 0 && quantity != 0) {
      stockUpdatePublisher.notify(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE);
    } else if (currentQuantity <= 2 || quantity <= 2) {
      stockUpdatePublisher.notify(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK);
    }

    itemMap.put(item.getId(), item);
  }
}
