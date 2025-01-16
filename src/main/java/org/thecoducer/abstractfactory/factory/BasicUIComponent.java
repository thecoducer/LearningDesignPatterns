package org.thecoducer.abstractfactory.factory;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.abstractfactory.enums.UIStyle;
import org.thecoducer.abstractfactory.uicomponent.button.Button;
import org.thecoducer.abstractfactory.uicomponent.checkbox.Checkbox;

@Slf4j
public class BasicUIComponent {
  private static GUIFactory guiFactory;

  private static GUIFactory initializeGuiFactory(UIStyle uiStyle) {
    return switch (uiStyle) {
      case MAC_OS -> new MacFactory();
      case WINDOWS_OS -> new WinFactory();
    };
  }

  public static Button createButton(UIStyle uiStyle) {
    guiFactory = initializeGuiFactory(uiStyle);
    return guiFactory.createButton();
  }

  public static Checkbox createCheckbox(UIStyle uiStyle) {
    guiFactory = initializeGuiFactory(uiStyle);
    return guiFactory.createCheckbox();
  }
}
