package org.thecoducer.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnalyticsService {
  public void doAnalysis(JSONFile jsonFile) {
    log.info("Analysing... {}", jsonFile.getData());
  }
}
