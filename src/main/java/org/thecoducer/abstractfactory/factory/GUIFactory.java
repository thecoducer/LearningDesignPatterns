package org.thecoducer.abstractfactory.factory;

import org.thecoducer.abstractfactory.uicomponent.button.Button;
import org.thecoducer.abstractfactory.uicomponent.checkbox.Checkbox;

public interface GUIFactory {
  Button createButton();

  Checkbox createCheckbox();
}
