package org.thecoducer.abstractfactory.factory;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.abstractfactory.uicomponent.button.Button;
import org.thecoducer.abstractfactory.uicomponent.button.WinButton;
import org.thecoducer.abstractfactory.uicomponent.checkbox.Checkbox;
import org.thecoducer.abstractfactory.uicomponent.checkbox.WinCheckbox;

@Slf4j
public class WinFactory implements GUIFactory {
  @Override
  public Button createButton() {
    return new WinButton();
  }

  @Override
  public Checkbox createCheckbox() {
    return new WinCheckbox();
  }
}
