package org.thecoducer.inventorymanagementsystem.test;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thecoducer.inventorymanagementsystem.dto.CommunicationIdentifiers;
import org.thecoducer.inventorymanagementsystem.entity.Customer;
import org.thecoducer.inventorymanagementsystem.entity.WholesaleAgent;
import org.thecoducer.inventorymanagementsystem.event.PriceUpdateEvent;
import org.thecoducer.inventorymanagementsystem.event.StockUpdateEvent;
import org.thecoducer.inventorymanagementsystem.eventpublisher.EventUpdatePublisher;
import org.thecoducer.inventorymanagementsystem.service.ItemService;
import org.thecoducer.inventorymanagementsystem.service.notifier.EmailNotifier;
import org.thecoducer.inventorymanagementsystem.service.notifier.FacebookMessengerNotifier;
import org.thecoducer.inventorymanagementsystem.service.notifier.SlackNotifier;
import org.thecoducer.inventorymanagementsystem.service.notifier.SmsNotifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemServiceTest {

  private ItemService itemService;
  private EventUpdatePublisher eventUpdatePublisher;
  private LogCaptor emailNotifierLogCaptor;
  private LogCaptor smsNotifierLogCaptor;
  private LogCaptor slackNotifierLogCaptor;
  private LogCaptor facebookMessengerLogCaptor;

  @BeforeEach
  void init() {
    itemService = new ItemService();
    eventUpdatePublisher = new EventUpdatePublisher();
    emailNotifierLogCaptor = LogCaptor.forClass(EmailNotifier.class);
    smsNotifierLogCaptor = LogCaptor.forClass(SmsNotifier.class);
    slackNotifierLogCaptor = LogCaptor.forClass(SlackNotifier.class);
    facebookMessengerLogCaptor = LogCaptor.forClass(FacebookMessengerNotifier.class);
  }

  @Test
  public void testOutOfStockAvailableUpdates() {
    Customer rahul = Customer.builder()
        .name("Rahul")
        .communicationIdentifiers(CommunicationIdentifiers.builder()
            .emailId("rahul@gmail.com")
            .phoneNumber("9870098990")
            .slackId("rahzex")
            .isSmsEnabled(true)
            .isSlackEnabled(true)
            .build())
        .build();
    WholesaleAgent saumya = WholesaleAgent.builder()
        .name("Saumya")
        .communicationIdentifiers(CommunicationIdentifiers.builder()
            .emailId("saumya@gmail.com")
            .phoneNumber("879042556")
            .facebookMessengerId("saumya34")
            .isFacebookMessengerEnabled(true)
            .build())
        .build();

    eventUpdatePublisher.subscribe(StockUpdateEvent.OUT_OF_STOCK_ITEM_AVAILABLE, rahul);
    eventUpdatePublisher.subscribe(StockUpdateEvent.OUT_OF_STOCK_ITEM_AVAILABLE, saumya);

    itemService.updateStock(1, 3);

    assertEquals("Mail sent to rahul@gmail.com.", emailNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("SMS sent to 9870098990.", smsNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("Slack message sent to rahzex.", slackNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("Mail sent to saumya@gmail.com.", emailNotifierLogCaptor.getInfoLogs().get(1));
    assertEquals("Facebook messenger text sent to saumya34.", facebookMessengerLogCaptor.getInfoLogs().getFirst());
  }

  @Test
  public void testSoonToGoOutOfStockUpdates() {
    Customer pamela = Customer.builder()
        .name("Pamela")
        .communicationIdentifiers(CommunicationIdentifiers.builder()
            .emailId("pamela@gmail.com")
            .phoneNumber("9856678990")
            .isSmsEnabled(true)
            .build())
        .build();
    WholesaleAgent anwesha = WholesaleAgent.builder()
        .name("Anwesha")
        .communicationIdentifiers(CommunicationIdentifiers.builder()
            .emailId("anwesha@gmail.com")
            .phoneNumber("9812345990")
            .build())
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
        .communicationIdentifiers(CommunicationIdentifiers.builder()
            .emailId("pamela@gmail.com")
            .phoneNumber("9856678990")
            .isSmsEnabled(true)
            .build())
        .build();
    WholesaleAgent anwesha = WholesaleAgent.builder()
        .name("Anwesha")
        .communicationIdentifiers(CommunicationIdentifiers.builder()
            .emailId("anwesha@gmail.com")
            .phoneNumber("9812345990")
            .isSmsEnabled(true)
            .build())
        .build();

    eventUpdatePublisher.subscribe(PriceUpdateEvent.INCREASE_IN_PRICE, pamela);
    eventUpdatePublisher.subscribe(PriceUpdateEvent.INCREASE_IN_PRICE, anwesha);

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
        .communicationIdentifiers(CommunicationIdentifiers.builder()
            .emailId("pamela@gmail.com")
            .slackId("pamela")
            .isSlackEnabled(true)
            .build())
        .build();
    WholesaleAgent anwesha = WholesaleAgent.builder()
        .name("Anwesha")
        .communicationIdentifiers(CommunicationIdentifiers.builder()
            .emailId("anwesha@gmail.com")
            .phoneNumber("9812345990")
            .isSmsEnabled(true)
            .build())
        .build();

    eventUpdatePublisher.subscribe(PriceUpdateEvent.DECREASE_IN_PRICE, pamela);
    eventUpdatePublisher.subscribe(PriceUpdateEvent.DECREASE_IN_PRICE, anwesha);

    itemService.updatePrice(6, 42000);

    assertEquals("Mail sent to pamela@gmail.com.", emailNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("Slack message sent to pamela.", slackNotifierLogCaptor.getInfoLogs().getFirst());
    assertEquals("Mail sent to anwesha@gmail.com.", emailNotifierLogCaptor.getInfoLogs().get(1));
    assertEquals("SMS sent to 9812345990.", smsNotifierLogCaptor.getInfoLogs().getFirst());
  }
}
