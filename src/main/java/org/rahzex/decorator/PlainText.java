package org.rahzex.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlainText implements Text {
  @Override
  public String process() {
    log.info("Processing text in plain format...");
    return " Plain Text";
  }
}
