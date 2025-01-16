package org.thecoducer.abstractfactory.factory;

import lombok.extern.slf4j.Slf4j;
import org.thecoducer.abstractfactory.enums.UIStyle;
import org.thecoducer.abstractfactory.uicomponent.button.Button;
import org.thecoducer.abstractfactory.uicomponent.checkbox.Checkbox;

@Slf4j
public class BasicUIComponent {

  public static Button createButton(UIStyle uiStyle) {
    return GUIFactory.getFactory(uiStyle).createButton();
  }

  public static Checkbox createCheckbox(UIStyle uiStyle) {
    return GUIFactory.getFactory(uiStyle).createCheckbox();
  }
}
