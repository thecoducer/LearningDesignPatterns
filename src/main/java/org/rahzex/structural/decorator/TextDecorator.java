package org.rahzex.structural.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextDecorator implements Text {
  private Text text;

  public TextDecorator(Text text) {
    this.text = text;
  }

  @Override
  public String process() {
    return text.process();
  }
}
