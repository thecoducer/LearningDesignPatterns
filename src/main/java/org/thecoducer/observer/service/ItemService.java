package org.thecoducer.observer.service;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.observer.config.StockUpdateConfig;
import org.thecoducer.observer.entity.Item;
import org.thecoducer.observer.event.PriceUpdateEvent;
import org.thecoducer.observer.event.StockUpdateEvent;
import org.thecoducer.observer.eventpublisher.EventUpdatePublisher;
import org.thecoducer.observer.repository.FakeDB;

@Slf4j
public class ItemService {
  private final EventUpdatePublisher eventUpdatePublisher;

  public ItemService() {
    eventUpdatePublisher = new EventUpdatePublisher();
  }

  public void updateStock(int itemId, int newQuantity) {
    Item item = getItem(itemId);
    log.debug("Stock updated. Item quantity {} to {}.", item.getQuantity(), newQuantity);
    if (isOutOfStockItemAvailable(item.getQuantity(), newQuantity)) {
      eventUpdatePublisher.notify(StockUpdateEvent.OUT_OF_STOCK_ITEM_AVAILABLE);
    } else if (isItemSoonToGoOutOfStock(newQuantity)) {
      eventUpdatePublisher.notify(StockUpdateEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK);
    }
    addOrUpdateItem(item);
  }

  private boolean isOutOfStockItemAvailable(int currentQuantity, int newQuantity) {
    return currentQuantity == 0 && newQuantity != 0;
  }

  private boolean isItemSoonToGoOutOfStock(int newQuantity) {
    return newQuantity <= StockUpdateConfig.ITEM_SOON_TO_GO_OUT_OF_STOCK_THRESHOLD;
  }

  public void updatePrice(int itemId, double newPrice) {
    Item item = getItem(itemId);
    log.debug("Price updated. Item price {} to {}.", item.getPrice(), newPrice);
    if(newPrice > item.getPrice()) {
      eventUpdatePublisher.notify(PriceUpdateEvent.INCREASED_IN_PRICE);
    }else {
      eventUpdatePublisher.notify(PriceUpdateEvent.DECREASE_IN_PRICE);
    }
    addOrUpdateItem(item);
  }

  private Item getItem(int itemId) {
    return FakeDB.getItem(itemId);
  }

  private void addOrUpdateItem(Item item) {
    FakeDB.addOrUpdateItem(item);
  }
}
