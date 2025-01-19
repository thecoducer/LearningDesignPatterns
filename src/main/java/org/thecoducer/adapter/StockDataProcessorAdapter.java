package org.thecoducer.adapter;

public class StockDataProcessorAdapter {
  private StockDataProcessor stockDataProcessor = new StockDataProcessor();

  public XMLFile processStockData(JSONFile jsonFile) {
    XMLFile xmlFile = JsonXmlConverter.doForward(jsonFile);
    return stockDataProcessor.processData(xmlFile);
  }
}
