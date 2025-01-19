package org.thecoducer.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationTest {
  private MetabaseService metabaseService;
  private StockDataProcessorAdapter stockDataProcessorAdapter;
  private AnalyticsServiceAdapter analyticsServiceAdapter;
  private LogCaptor analyticsServiceLogCaptor;

  @BeforeEach
  public void setUp() {
    metabaseService = new MetabaseService();
    stockDataProcessorAdapter = new StockDataProcessorAdapter();
    analyticsServiceAdapter = new AnalyticsServiceAdapter();
    analyticsServiceLogCaptor = LogCaptor.forClass(AnalyticsService.class);
  }

  @Test
  public void testDataAnalysis() {
    // Fetch the real data from Metabase
    JSONFile jsonFile = metabaseService.getData();
    // Process the data using StockDataProcessor and get the processed XML file
    XMLFile processedFile = stockDataProcessorAdapter.processStockData(jsonFile);
    // Perform analysis on the processed data
    analyticsServiceAdapter.doAnalysis(processedFile);
    assertEquals(
        "Analysing... Sales_processed.json", analyticsServiceLogCaptor.getInfoLogs().getFirst());
  }
}
