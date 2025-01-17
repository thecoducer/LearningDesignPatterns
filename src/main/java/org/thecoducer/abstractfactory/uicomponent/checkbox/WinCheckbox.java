package org.thecoducer.abstractfactory.uicomponent.checkbox;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WinCheckbox extends Checkbox {
  @Override
  public void render() {
    log.info("WinCheckbox rendered.");
  }
}
