package org.thecoducer.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MetabaseService {
  private StockDataProvider stockDataProvider = new StockDataProvider();

  public void sendData(JSONFile jsonFile) {
    XMLFile xmlFile = JsonXmlConverter.doForward(jsonFile);
    log.info("Sending XMLFile to StockDataProvider");
    stockDataProvider.sendData(xmlFile);
  }
}
