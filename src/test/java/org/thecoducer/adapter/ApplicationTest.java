package org.thecoducer.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationTest {
  private MetabaseService metabaseService;
  private PayloadAdapter payloadAdapter;
  private LogCaptor analyticsServiceLogCaptor;

  @BeforeEach
  public void setUp() {
    metabaseService = new MetabaseService();
    payloadAdapter = new PayloadAdapter();
    analyticsServiceLogCaptor = LogCaptor.forClass(AnalyticsService.class);
  }

  @Test
  public void testDataAnalysis() {
    JSONFile jsonFile = metabaseService.getData();
    payloadAdapter.doAnalysis(payloadAdapter.processStockData(jsonFile));
    assertEquals(
        "Analysing... Sales_processed.json", analyticsServiceLogCaptor.getInfoLogs().getFirst());
  }
}
