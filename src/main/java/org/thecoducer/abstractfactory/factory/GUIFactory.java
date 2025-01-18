package org.thecoducer.abstractfactory.factory;

import org.thecoducer.abstractfactory.enums.UIStyle;
import org.thecoducer.abstractfactory.uicomponent.button.Button;
import org.thecoducer.abstractfactory.uicomponent.checkbox.Checkbox;

public interface GUIFactory {
  static GUIFactory getFactory(UIStyle uiStyle) {
    return switch (uiStyle) {
      case MAC_OS -> new MacFactory();
      case WINDOWS -> new WinFactory();
    };
  }

  Button createButton();

  Checkbox createCheckbox();
}
