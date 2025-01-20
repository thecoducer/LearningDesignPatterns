package org.rahzex.behavioral.observer.observers;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rahzex.behavioral.observer.entity.Settings;
import org.rahzex.behavioral.observer.event.SettingsEvent;

@Data
@Builder
@NoArgsConstructor
@Slf4j
public class TextPanel implements UIComponent {

  @Override
  public void update(SettingsEvent event, Settings settings) {
    switch (event) {
      case FONT_UPDATED -> log.info("Text Panel Font Updated : {}", settings.getFontSettings());
      case THEME_UPDATED -> log.info("Text Panel Theme Updated : {}", settings.getThemeSettings());
    }
  }
}
