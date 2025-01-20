package org.rahzex.structural.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoldText extends TextDecorator {

  public BoldText(Text text) {
    super(text);
  }

  @Override
  public String process() {
    log.info("Processing text to be in bold...");
    return " Bold Text" + super.process();
  }
}
