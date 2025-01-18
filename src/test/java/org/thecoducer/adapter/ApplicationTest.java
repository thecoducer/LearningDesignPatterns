package org.thecoducer.adapter;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
  private StockDataProvider stockDataProvider;
  private MetabaseService metabaseService;
  private LogCaptor analyticsServiceLogCaptor;
  private LogCaptor metabaseServiceLogCaptor;

  @BeforeEach
  public void setUp() {
    stockDataProvider = new StockDataProvider();
    analyticsServiceLogCaptor = LogCaptor.forClass(AnalyticsService.class);
    metabaseService = new MetabaseService();
    metabaseServiceLogCaptor = LogCaptor.forClass(MetabaseService.class);
  }

  @Test
  public void testSendDataFromStockDataProvider() {
    XMLFile xmlFile = new XMLFile("January/Sales.xml");
    stockDataProvider.sendData(xmlFile);
    assertEquals("Analysing... January/Sales.json", analyticsServiceLogCaptor.getInfoLogs().getFirst());
  }

  @Test
  public void testSendDataFromMetabaseService() {
    JSONFile jsonFile = new JSONFile("January/Sales.json");
    metabaseService.sendData(jsonFile);
    assertEquals("Sending XMLFile to StockDataProvider", metabaseServiceLogCaptor.getInfoLogs().getFirst());
    assertEquals("Analysing... January/Sales.json", analyticsServiceLogCaptor.getInfoLogs().getFirst());
  }

}