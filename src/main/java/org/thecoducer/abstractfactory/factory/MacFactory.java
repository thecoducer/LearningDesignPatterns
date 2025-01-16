package org.thecoducer.abstractfactory.factory;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.abstractfactory.uicomponent.button.Button;
import org.thecoducer.abstractfactory.uicomponent.button.MacButton;
import org.thecoducer.abstractfactory.uicomponent.checkbox.Checkbox;
import org.thecoducer.abstractfactory.uicomponent.checkbox.MacCheckbox;

@Slf4j
public class MacFactory implements GUIFactory {
  @Override
  public Button createButton() {
    log.info("MacButton created.");
    return new MacButton();
  }

  @Override
  public Checkbox createCheckbox() {
    log.info("MacCheckbox created.");
    return new MacCheckbox();
  }
}
