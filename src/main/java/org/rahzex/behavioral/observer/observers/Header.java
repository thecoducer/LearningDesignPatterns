package org.rahzex.behavioral.observer.observers;

import java.util.Objects;
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
public class Header implements UIComponent {

  @Override
  public void update(SettingsEvent event, Settings settings) {
    if (Objects.requireNonNull(event) == SettingsEvent.THEME_UPDATED) {
      log.info("Header Theme Updated : {}", settings.getThemeSettings());
    }
  }
}
