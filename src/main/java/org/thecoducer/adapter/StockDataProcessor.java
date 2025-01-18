package org.thecoducer.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * The StockDataProcessor is a legacy system that can handle only XML files. It performs
 * calculations on the data and returns a new XML file with the processed data.
 */
@Slf4j
public class StockDataProcessor {
  public XMLFile processData(XMLFile xmlFile) {
    // do some calculations with the data
    return new XMLFile(xmlFile.getData().split(".xml")[0] + "_processed.xml");
  }
}
