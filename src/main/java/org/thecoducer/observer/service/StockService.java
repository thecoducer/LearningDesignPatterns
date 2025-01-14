package org.thecoducer.observer.service;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.observer.config.StockUpdateConfig;
import org.thecoducer.observer.entity.Item;
import org.thecoducer.observer.event.StockEvent;
import org.thecoducer.observer.eventpublisher.StockUpdatePublisher;
import org.thecoducer.observer.repository.FakeDB;

import java.util.Map;

@Slf4j
public class StockService {
  private final Map<Integer, Item> itemMap;
  private final StockUpdatePublisher stockUpdatePublisher;

  public StockService() {
    stockUpdatePublisher = new StockUpdatePublisher();
    itemMap = FakeDB.getItemMap();
  }

  public void updateStock(Item item) {
    log.debug("Stock updated. Item quantity {} to {}.", getCurrentItemQuantity(item.getId()), item.getQuantity());
    if (isOutOfStockItemAvailable(item)) {
      stockUpdatePublisher.notify(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE);
    } else if (isItemSoonToGoOutOfStock(item)) {
      stockUpdatePublisher.notify(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK);
    }
    addOrUpdateItem(item);
  }

  private boolean isOutOfStockItemAvailable(Item item) {
    int currentQuantity = getCurrentItemQuantity(item.getId());
    return currentQuantity == 0 && item.getQuantity() != 0;
  }

  private boolean isItemSoonToGoOutOfStock(Item item) {
    return item.getQuantity() <= StockUpdateConfig.ITEM_SOON_TO_GO_OUT_OF_STOCK_THRESHOLD;
  }

  private void addOrUpdateItem(Item item) {
    itemMap.put(item.getId(), item);
  }

  private int getCurrentItemQuantity(int itemId) {
    return itemMap.getOrDefault(itemId, Item.builder().quantity(0).build()).getQuantity();
  }
}
