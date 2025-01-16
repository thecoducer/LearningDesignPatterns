package org.thecoducer.abstractfactory.test;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;
import org.thecoducer.abstractfactory.enums.UIStyle;
import org.thecoducer.abstractfactory.factory.BasicUIComponent;
import org.thecoducer.abstractfactory.uicomponent.button.Button;
import org.thecoducer.abstractfactory.uicomponent.button.MacButton;
import org.thecoducer.abstractfactory.uicomponent.button.WinButton;
import org.thecoducer.abstractfactory.uicomponent.checkbox.Checkbox;
import org.thecoducer.abstractfactory.uicomponent.checkbox.MacCheckbox;
import org.thecoducer.abstractfactory.uicomponent.checkbox.WinCheckbox;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
  private final LogCaptor macButtonLogCaptor = LogCaptor.forClass(MacButton.class);
  private final LogCaptor winButtonLogCaptor = LogCaptor.forClass(WinButton.class);
  private final LogCaptor macCheckboxLogCaptor = LogCaptor.forClass(MacCheckbox.class);
  private final LogCaptor winCheckboxLogCaptor = LogCaptor.forClass(WinCheckbox.class);

  @Test
  public void testMacOSComponents() {
    String buttonText = "Click me";
    Button button = BasicUIComponent.createButton(UIStyle.MAC_OS);
    button.setLabel(buttonText);
    button.render();

    String checkboxText = "To-do";
    Checkbox checkbox = BasicUIComponent.createCheckbox(UIStyle.MAC_OS);
    checkbox.setLabel(checkboxText);
    checkbox.render();

    assertEquals("MacButton rendered.", macButtonLogCaptor.getInfoLogs().getFirst());
    assertEquals(buttonText, button.getLabel());
    assertEquals("MacCheckbox rendered.", macCheckboxLogCaptor.getInfoLogs().getFirst());
    assertEquals(checkboxText, checkbox.getLabel());
  }

  @Test
  public void testWinOSComponents() {
    Button button = BasicUIComponent.createButton(UIStyle.WINDOWS_OS);
    button.render();

    Checkbox checkbox = BasicUIComponent.createCheckbox(UIStyle.WINDOWS_OS);
    checkbox.render();

    assertEquals("WinButton rendered.", winButtonLogCaptor.getInfoLogs().getFirst());
    assertEquals("WinCheckbox rendered.", winCheckboxLogCaptor.getInfoLogs().getFirst());
  }

  @Test
  public void testBothPlatformComponents() {
    Button buttonOne = BasicUIComponent.createButton(UIStyle.WINDOWS_OS);
    buttonOne.render();

    Button buttonTwo = BasicUIComponent.createButton(UIStyle.MAC_OS);
    buttonTwo.render();

    Checkbox checkbox = BasicUIComponent.createCheckbox(UIStyle.WINDOWS_OS);
    checkbox.render();

    assertEquals("WinButton rendered.", winButtonLogCaptor.getInfoLogs().getFirst());
    assertEquals("MacButton rendered.", macButtonLogCaptor.getInfoLogs().getFirst());
    assertEquals("WinCheckbox rendered.", winCheckboxLogCaptor.getInfoLogs().getFirst());
  }
}
