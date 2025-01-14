package org.rahzex.observer.observers;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rahzex.observer.event.SettingsEvent;
import org.rahzex.observer.entity.Settings;

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
