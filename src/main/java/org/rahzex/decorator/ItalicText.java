package org.rahzex.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItalicText extends TextDecorator {

  public ItalicText(Text text) {
    super(text);
  }

  @Override
  public String process() {
    log.info("Processing text to be in italic...");
    return " Italic Text" + super.process();
  }
}
