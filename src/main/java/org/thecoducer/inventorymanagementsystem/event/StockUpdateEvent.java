package org.thecoducer.inventorymanagementsystem.event;

import lombok.Getter;

@Getter
public enum StockUpdateEvent implements Event {
  OUT_OF_STOCK_ITEM_AVAILABLE,
  ITEM_SOON_TO_GO_OUT_OF_STOCK
}
