package org.thecoducer.observer.test;

import org.junit.jupiter.api.Test;
import org.thecoducer.observer.entity.Customer;
import org.thecoducer.observer.entity.Item;
import org.thecoducer.observer.entity.WholesaleAgent;
import org.thecoducer.observer.event.StockEvent;
import org.thecoducer.observer.eventpublisher.StockUpdatePublisher;

public class StockUpdateNotificationTest {

  private final StockUpdatePublisher stockUpdatePublisher = new StockUpdatePublisher();

  @Test
  public void testStockUpdatesSend() {
    Customer rahul = Customer.builder()
        .name("Rahul")
        .emailId("rahul@gmail.com")
        .phoneNumber("9870098990")
        .build();
    Customer pamela = Customer.builder()
        .name("Pamela")
        .emailId("pamela@gmail.com")
        .phoneNumber("9856678990")
        .build();
    WholesaleAgent saumya = WholesaleAgent.builder()
        .name("Saumya")
        .emailId("saumya@gmail.com")
        .phoneNumber("879042556")
        .build();
    WholesaleAgent anwesha = WholesaleAgent.builder()
        .name("Anwesha")
        .emailId("anwesha@gmail.com")
        .phoneNumber("9812345990")
        .build();

    stockUpdatePublisher.subscribe(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE, rahul);
    stockUpdatePublisher.subscribe(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, pamela);
    stockUpdatePublisher.subscribe(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE, saumya);

    stockUpdatePublisher.subscribe(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, anwesha);
    stockUpdatePublisher.unsubscribe(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, anwesha);

    Item itemOne = Item.builder().id(1).name("Sofa").quantity(3).build();
    stockUpdatePublisher.updateStock(itemOne);

    Item itemTwo = Item.builder().id(1).name("Sofa").quantity(1).build();
    stockUpdatePublisher.updateStock(itemTwo);
  }
}
