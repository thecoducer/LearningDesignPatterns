package org.thecoducer.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockDataProvider {
  private AnalyticsService analyticsService = new AnalyticsService();

  public void sendData(XMLFile xmlFile) {
    JSONFile jsonFile = JsonXmlConverter.doBackward(xmlFile);
    log.info("Sending JSONFile to AnalyticsService");
    analyticsService.doAnalysis(jsonFile);
  }
}
