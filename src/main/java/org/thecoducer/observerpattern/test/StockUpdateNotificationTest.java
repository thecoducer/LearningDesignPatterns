package org.thecoducer.observerpattern.test;

import org.junit.jupiter.api.Test;
import org.thecoducer.observerpattern.entity.Customer;
import org.thecoducer.observerpattern.entity.Item;
import org.thecoducer.observerpattern.entity.WholesaleAgent;
import org.thecoducer.observerpattern.event.StockEvent;
import org.thecoducer.observerpattern.eventpublisher.StockUpdatePublisherImpl;

public class StockUpdateNotificationTest {

  private final StockUpdatePublisherImpl stockPublisherImpl = new StockUpdatePublisherImpl();

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
    WholesaleAgent wholesaleAgent = WholesaleAgent.builder()
        .name("Saumya")
        .emailId("saumya@gmail.com")
        .phoneNumber("879042556")
        .build();

    stockPublisherImpl.subscribe(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE, customerOne);
    stockPublisherImpl.subscribe(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, customerTwo);
    stockPublisherImpl.subscribe(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE, wholesaleAgent);

    Item itemOne = Item.builder().id(1).name("Sofa").quantity(3).build();
    stockPublisherImpl.updateStock(itemOne);

    Item itemTwo = Item.builder().id(1).name("Sofa").quantity(1).build();
    stockPublisherImpl.updateStock(itemTwo);
  }
}
