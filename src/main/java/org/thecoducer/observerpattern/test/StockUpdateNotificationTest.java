package org.thecoducer.observerpattern.test;

import org.junit.jupiter.api.Test;
import org.thecoducer.observerpattern.entity.Customer;
import org.thecoducer.observerpattern.entity.Item;
import org.thecoducer.observerpattern.event.StockEvent;
import org.thecoducer.observerpattern.eventpublisher.StockUpdatePublisherImpl;
import org.thecoducer.observerpattern.eventsubscriber.StockUpdateSubscriber;

public class StockUpdateNotificationTest {

  private StockUpdatePublisherImpl stockPublisherImpl = new StockUpdatePublisherImpl();

  @Test
  public void testStockUpdatesSend() {
    Customer customerOne = Customer.builder()
        .name("Rahul")
        .emailId("rahul@gmail.com")
        .phoneNumber("9870098990")
        .build();
    Customer customerTwo = Customer.builder()
        .name("Pamela")
        .emailId("pamela@gmail.com")
        .phoneNumber("9856678990")
        .build();
    stockPublisherImpl.subscribe(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE, customerOne);
    stockPublisherImpl.subscribe(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, customerTwo);

    Item item = Item.builder().name("Sofa").quantity(3).build();
    stockPublisherImpl.updateStock(item);

    item.setQuantity(2);
    stockPublisherImpl.updateStock(item);
  }
}
