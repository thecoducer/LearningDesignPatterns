package org.rahzex.structural.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TextDecorationManagerTest {
  private TextDecorationManager textDecorationManager;

  @BeforeEach
  void init() {
    textDecorationManager = new TextDecorationManager();
  }

  @Test
  void testPlainTextDecoration() {
    TextDecorationPreferences preferences = TextDecorationPreferences.builder().build();

    var result = textDecorationManager.decorate(preferences);

    assertEquals(" Plain Text", result);
  }

  @Test
  void testBoldAndItalicTextDecoration() {
    TextDecorationPreferences preferences =
        TextDecorationPreferences.builder()
            .makeItalic(true)
            .makeUnderlined(false)
            .makeBold(true)
            .build();

    var result = textDecorationManager.decorate(preferences);

    assertEquals(" Italic Text Bold Text Plain Text", result);
  }

  @Test
  void testAllTextDecoration() {
    TextDecorationPreferences preferences =
        TextDecorationPreferences.builder()
            .makeItalic(true)
            .makeUnderlined(true)
            .makeBold(true)
            .build();

    var result = textDecorationManager.decorate(preferences);

    assertEquals(" Underlined Text Italic Text Bold Text Plain Text", result);
  }
}
