package org.thecoducer.observer.test;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;
import org.thecoducer.observer.entity.Customer;
import org.thecoducer.observer.entity.Item;
import org.thecoducer.observer.entity.WholesaleAgent;
import org.thecoducer.observer.event.StockEvent;
import org.thecoducer.observer.eventpublisher.StockUpdatePublisher;
import org.thecoducer.observer.service.EmailNotifierService;
import org.thecoducer.observer.service.SmsNotifierService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockUpdateNotificationTest {

  private final StockUpdatePublisher stockUpdatePublisher = new StockUpdatePublisher();

  @Test
  public void testStockUpdatesSend() {
    LogCaptor emailNotifierLogCaptor = LogCaptor.forClass(EmailNotifierService.class);
    LogCaptor smsNotifierLogCaptor = LogCaptor.forClass(SmsNotifierService.class);

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

    assertEquals("Mail sent to rahul@gmail.com.", emailNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("SMS sent to 9870098990.", smsNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("Mail sent to saumya@gmail.com.", emailNotifierLogCaptor.getInfoLogs().get(1));
    assertEquals("SMS sent to 879042556.", smsNotifierLogCaptor.getInfoLogs().get(1));
    assertEquals("Mail sent to pamela@gmail.com.", emailNotifierLogCaptor.getInfoLogs().get(2));
    assertEquals("SMS sent to 9856678990.", smsNotifierLogCaptor.getInfoLogs().get(2));
  }
}
