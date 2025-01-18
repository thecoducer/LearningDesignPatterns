package org.thecoducer.abstractfactory.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;
import org.thecoducer.abstractfactory.enums.UIStyle;
import org.thecoducer.abstractfactory.uicomponent.button.Button;
import org.thecoducer.abstractfactory.uicomponent.button.MacButton;
import org.thecoducer.abstractfactory.uicomponent.button.WinButton;
import org.thecoducer.abstractfactory.uicomponent.checkbox.Checkbox;
import org.thecoducer.abstractfactory.uicomponent.checkbox.MacCheckbox;
import org.thecoducer.abstractfactory.uicomponent.checkbox.WinCheckbox;

public class ApplicationTest {
  private final LogCaptor macButtonLogCaptor = LogCaptor.forClass(MacButton.class);
  private final LogCaptor winButtonLogCaptor = LogCaptor.forClass(WinButton.class);
  private final LogCaptor macCheckboxLogCaptor = LogCaptor.forClass(MacCheckbox.class);
  private final LogCaptor winCheckboxLogCaptor = LogCaptor.forClass(WinCheckbox.class);

  @Test
  public void testMacOSComponents() {
    String buttonText = "Click me";
    Button button = GUIFactory.getFactory(UIStyle.MAC_OS).createButton();
    button.setLabel(buttonText);
    button.render();

    String checkboxText = "To-do";
    Checkbox checkbox = GUIFactory.getFactory(UIStyle.MAC_OS).createCheckbox();
    checkbox.setLabel(checkboxText);
    checkbox.render();

    assertEquals("MacButton rendered.", macButtonLogCaptor.getInfoLogs().getFirst());
    assertEquals(buttonText, button.getLabel());
    assertEquals("MacCheckbox rendered.", macCheckboxLogCaptor.getInfoLogs().getFirst());
    assertEquals(checkboxText, checkbox.getLabel());
  }

  @Test
  public void testWinOSComponents() {
    String buttonText = "Submit";
    Button button = GUIFactory.getFactory(UIStyle.WINDOWS).createButton();
    button.setLabel(buttonText);
    button.render();

    String checkboxText = "Plans";
    Checkbox checkbox = GUIFactory.getFactory(UIStyle.WINDOWS).createCheckbox();
    checkbox.setLabel(checkboxText);
    checkbox.render();

    assertEquals("WinButton rendered.", winButtonLogCaptor.getInfoLogs().getFirst());
    assertEquals(buttonText, button.getLabel());
    assertEquals("WinCheckbox rendered.", winCheckboxLogCaptor.getInfoLogs().getFirst());
    assertEquals(checkboxText, checkbox.getLabel());
  }

  @Test
  public void testBothPlatformComponents() {
    Button buttonOne = GUIFactory.getFactory(UIStyle.WINDOWS).createButton();
    buttonOne.render();

    Button buttonTwo = GUIFactory.getFactory(UIStyle.MAC_OS).createButton();
    buttonTwo.render();

    Checkbox checkbox = GUIFactory.getFactory(UIStyle.WINDOWS).createCheckbox();
    checkbox.render();

    assertEquals("WinButton rendered.", winButtonLogCaptor.getInfoLogs().getFirst());
    assertEquals("MacButton rendered.", macButtonLogCaptor.getInfoLogs().getFirst());
    assertEquals("WinCheckbox rendered.", winCheckboxLogCaptor.getInfoLogs().getFirst());
  }
}
