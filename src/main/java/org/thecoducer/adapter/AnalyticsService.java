package org.thecoducer.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * The AnalyticsService performs analysis on the data that comes from StockDataProcessor.
 * AnalyticsService can handle only JSON files.
 */
@Slf4j
public class AnalyticsService {
  public void doAnalysis(JSONFile jsonFile) {
    log.info("Analysing... {}", jsonFile.getData());
  }
}
