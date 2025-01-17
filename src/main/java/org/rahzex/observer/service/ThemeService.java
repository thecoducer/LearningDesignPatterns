package org.rahzex.observer.service;

import org.rahzex.observer.entity.ThemeSettings;
import org.rahzex.observer.event.SettingsEvent;
import org.rahzex.observer.observable.SettingUpdatePublisher;
import org.rahzex.observer.repository.DB;

public class ThemeService {
  private SettingUpdatePublisher publisher;

  public ThemeService() {
    publisher = new SettingUpdatePublisher();
  }

  public void update(ThemeSettings themeSettings) {
    DB.setThemeSettings(themeSettings);
    publisher.publish(SettingsEvent.THEME_UPDATED, DB.getSettings());
  }

  public ThemeSettings getThemeSettings() {
    return DB.getThemeSettings();
  }
}
