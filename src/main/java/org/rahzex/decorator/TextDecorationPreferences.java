package org.rahzex.decorator;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TextDecorationPreferences {
  private boolean makeItalic;
  private boolean makeBold;
  private boolean makeUnderlined;
}
