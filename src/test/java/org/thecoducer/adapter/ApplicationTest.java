package org.thecoducer.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationTest {
  private StockDataProcessor stockDataProcessor;
  private MetabaseService metabaseService;
  private AnalyticsService analyticsService;
  private LogCaptor analyticsServiceLogCaptor;

  @BeforeEach
  public void setUp() {
    stockDataProcessor = new StockDataProcessor();
    metabaseService = new MetabaseService();
    analyticsService = new AnalyticsService();
    analyticsServiceLogCaptor = LogCaptor.forClass(AnalyticsService.class);
  }

  @Test
  public void testDataAnalysis() {
    JSONFile jsonFile = metabaseService.getData();
    XMLFile xmlFile = JsonXmlConverter.doForward(jsonFile);
    XMLFile processedXMLFile = stockDataProcessor.processData(xmlFile);
    analyticsService.doAnalysis(JsonXmlConverter.doBackward(processedXMLFile));
    assertEquals(
        "Analysing... Sales_processed.json", analyticsServiceLogCaptor.getInfoLogs().getFirst());
  }
}
