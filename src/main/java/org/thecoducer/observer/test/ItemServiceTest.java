package org.thecoducer.observer.test;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thecoducer.observer.entity.Customer;
import org.thecoducer.observer.entity.WholesaleAgent;
import org.thecoducer.observer.event.PriceUpdateEvent;
import org.thecoducer.observer.event.StockUpdateEvent;
import org.thecoducer.observer.eventpublisher.EventUpdatePublisher;
import org.thecoducer.observer.service.EmailNotifierService;
import org.thecoducer.observer.service.SmsNotifierService;
import org.thecoducer.observer.service.ItemService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemServiceTest {

  private ItemService itemService;
  private EventUpdatePublisher eventUpdatePublisher;
  private LogCaptor emailNotifierLogCaptor;
  private LogCaptor smsNotifierLogCaptor;

  @BeforeEach
  void init() {
    itemService = new ItemService();
    eventUpdatePublisher = new EventUpdatePublisher();
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

    eventUpdatePublisher.subscribe(StockUpdateEvent.OUT_OF_STOCK_ITEM_AVAILABLE, rahul);
    eventUpdatePublisher.subscribe(StockUpdateEvent.OUT_OF_STOCK_ITEM_AVAILABLE, saumya);

    itemService.updateStock(1, 3);

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

    eventUpdatePublisher.subscribe(StockUpdateEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, pamela);
    eventUpdatePublisher.subscribe(StockUpdateEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, anwesha);
    eventUpdatePublisher.unsubscribe(StockUpdateEvent.ITEM_SOON_TO_GO_OUT_OF_STOCK, anwesha);

    itemService.updateStock(4, 1);

    assertEquals("Mail sent to pamela@gmail.com.", emailNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("SMS sent to 9856678990.", smsNotifierLogCaptor.getInfoLogs().getFirst());
  }

  @Test
  public void testPriceIncreased() {
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

    eventUpdatePublisher.subscribe(PriceUpdateEvent.PRICE_INCREASED, pamela);
    eventUpdatePublisher.subscribe(PriceUpdateEvent.PRICE_INCREASED, anwesha);

    itemService.updatePrice(7, 5800);

    assertEquals("Mail sent to pamela@gmail.com.", emailNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("SMS sent to 9856678990.", smsNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("Mail sent to anwesha@gmail.com.", emailNotifierLogCaptor.getInfoLogs().get(1));
    assertEquals("SMS sent to 9812345990.", smsNotifierLogCaptor.getInfoLogs().get(1));
  }

  @Test
  public void testPriceDecreased() {
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

    eventUpdatePublisher.subscribe(PriceUpdateEvent.PRICE_DECREASED, pamela);
    eventUpdatePublisher.subscribe(PriceUpdateEvent.PRICE_DECREASED, anwesha);

    itemService.updatePrice(6, 42000);

    assertEquals("Mail sent to pamela@gmail.com.", emailNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("SMS sent to 9856678990.", smsNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("Mail sent to anwesha@gmail.com.", emailNotifierLogCaptor.getInfoLogs().get(1));
    assertEquals("SMS sent to 9812345990.", smsNotifierLogCaptor.getInfoLogs().get(1));
  }
}
