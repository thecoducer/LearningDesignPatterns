package org.thecoducer.adapter;

public class PayloadAdapter {
  private StockDataProcessor stockDataProcessor = new StockDataProcessor();
  AnalyticsService analyticsService = new AnalyticsService();

  public XMLFile processStockData(JSONFile jsonFile) {
    XMLFile xmlFile = JsonXmlConverter.doForward(jsonFile);
    return stockDataProcessor.processData(xmlFile);
  }

  public void doAnalysis(XMLFile processedXMLFile) {
    analyticsService.doAnalysis(JsonXmlConverter.doBackward(processedXMLFile));
  }
}
