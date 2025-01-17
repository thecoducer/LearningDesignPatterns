package org.rahzex.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnderlinedText extends TextDecorator {

  public UnderlinedText(Text text) {
    super(text);
  }

  @Override
  public String process() {
    log.info("Processing text to be underlined...");
    return " Underlined Text" + super.process();
  }
}
