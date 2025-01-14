package org.thecoducer.observer.test;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thecoducer.observer.entity.Customer;
import org.thecoducer.observer.entity.Item;
import org.thecoducer.observer.entity.WholesaleAgent;
import org.thecoducer.observer.event.StockEvent;
import org.thecoducer.observer.eventpublisher.StockUpdatePublisher;
import org.thecoducer.observer.service.EmailNotifierService;
import org.thecoducer.observer.service.SmsNotifierService;
import org.thecoducer.observer.service.StockService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockUpdateNotificationTest {

  private StockService stockService;
  private StockUpdatePublisher stockUpdatePublisher;
  private LogCaptor emailNotifierLogCaptor;
  private LogCaptor smsNotifierLogCaptor;

  @BeforeEach
  void init() {
    stockService = new StockService();
    stockUpdatePublisher = new StockUpdatePublisher();
    emailNotifierLogCaptor = LogCaptor.forClass(EmailNotifierService.class);
    smsNotifierLogCaptor = LogCaptor.forClass(SmsNotifierService.class);
  }

  @Test
  public void testOutOfStockAvailableUpdates() {
    Customer rahul = Customer.builder()
        .name("Rahul")
        .emailId("rahul@gmail.com")
        .phoneNumber("9870098990")
        .build();
    WholesaleAgent saumya = WholesaleAgent.builder()
        .name("Saumya")
        .emailId("saumya@gmail.com")
        .phoneNumber("879042556")
        .build();

    stockUpdatePublisher.subscribe(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE, rahul);
    stockUpdatePublisher.subscribe(StockEvent.OUT_OF_STOCK_ITEM_AVAILABLE, saumya);

    Item itemOne = Item.builder().id(1).name("Sofa").quantity(3).build();
    stockService.updateStock(itemOne);

    assertEquals("Mail sent to rahul@gmail.com.", emailNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("SMS sent to 9870098990.", smsNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("Mail sent to saumya@gmail.com.", emailNotifierLogCaptor.getInfoLogs().get(1));
    assertEquals("SMS sent to 879042556.", smsNotifierLogCaptor.getInfoLogs().get(1));
  }

  @Test
  public void testSoonToGoOutOfStockUpdates() {
    Customer pamela = Customer.builder()
        .name("Pamela")
        .emailId("pamela@gmail.com")
        .phoneNumber("9856678990")
        .build();
    WholesaleAgent anwesha = WholesaleAgent.builder()
        .name("Anwesha")
        .emailId("anwesha@gmail.com")
        .phoneNumber("9812345990")
        .build();

    stockUpdatePublisher.subscribe(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, pamela);
    stockUpdatePublisher.subscribe(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, anwesha);
    stockUpdatePublisher.unsubscribe(StockEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, anwesha);

    Item itemTwo = Item.builder().id(4).name("Sofa").quantity(1).build();
    stockService.updateStock(itemTwo);

    assertEquals("Mail sent to pamela@gmail.com.", emailNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("SMS sent to 9856678990.", smsNotifierLogCaptor.getInfoLogs().getFirst());
  }
}
