package org.rahzex.behavioral.observer.service;

import org.rahzex.behavioral.observer.entity.FontSettings;
import org.rahzex.behavioral.observer.event.SettingsEvent;
import org.rahzex.behavioral.observer.observable.SettingUpdatePublisher;
import org.rahzex.behavioral.observer.repository.DB;

public class FontService {
  private SettingUpdatePublisher publisher;

  public FontService() {
    publisher = new SettingUpdatePublisher();
  }

  public void update(FontSettings fontSettings) {
    DB.setFontSettings(fontSettings);
    publisher.publish(SettingsEvent.FONT_UPDATED, DB.getSettings());
  }

  public FontSettings getFontSettings() {
    return DB.getFontSettings();
  }
}
