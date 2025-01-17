package org.thecoducer.abstractfactory.uicomponent.button;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WinButton extends Button {
  @Override
  public void render() {
    log.info("WinButton rendered.");
  }
}
