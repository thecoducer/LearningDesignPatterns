package org.thecoducer.adapter;

public class AnalyticsServiceAdapter {
  private AnalyticsService analyticsService = new AnalyticsService();

  public void doAnalysis(XMLFile processedXMLFile) {
    analyticsService.doAnalysis(JsonXmlConverter.doBackward(processedXMLFile));
  }
}
