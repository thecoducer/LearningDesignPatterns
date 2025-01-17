package org.rahzex.observer.service;

import org.rahzex.observer.entity.FontSettings;
import org.rahzex.observer.event.SettingsEvent;
import org.rahzex.observer.observable.SettingUpdatePublisher;
import org.rahzex.observer.repository.DB;

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
