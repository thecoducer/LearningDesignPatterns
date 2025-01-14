package org.thecoducer.observer.service;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.observer.config.StockUpdateConfig;
import org.thecoducer.observer.entity.Item;
import org.thecoducer.observer.event.StockEvent;
import org.thecoducer.observer.eventpublisher.StockUpdatePublisher;
import org.thecoducer.observer.repository.FakeDB;

@Slf4j
public class StockService {
  private final StockUpdatePublisher stockUpdatePublisher;

  public StockService() {
    stockUpdatePublisher = new StockUpdatePublisher();
  }

  public void updateStock(int itemId, int newQuantity) {
    Item item = FakeDB.getItem(itemId);

    log.debug("Stock updated. Item quantity {} to {}.", item.getQuantity(), newQuantity);
    if (isOutOfStockItemAvailable(item, newQuantity)) {
      stockUpdatePublisher.notify(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE);
    } else if (isItemSoonToGoOutOfStock(newQuantity)) {
      stockUpdatePublisher.notify(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK);
    }
    FakeDB.addOrUpdateItem(item);
  }

  private boolean isOutOfStockItemAvailable(Item item, int newQuantity) {
    return item.getQuantity() == 0 && newQuantity != 0;
  }

  private boolean isItemSoonToGoOutOfStock(int newQuantity) {
    return newQuantity <= StockUpdateConfig.ITEM_SOON_TO_GO_OUT_OF_STOCK_THRESHOLD;
  }
}
