package org.thecoducer.observerpattern.event;

import lombok.Getter;

@Getter
public enum StockEvent implements Event {

  OUT_OF_STOCK_ITEM_AVAILABLE("The item is available."),
  ITEM_SOON_TO_GO_OUT_OF_STOCK("The item will soon go out of stock.");

  private final String message;

  StockEvent(String message) {
    this.message = message;
  }
}
