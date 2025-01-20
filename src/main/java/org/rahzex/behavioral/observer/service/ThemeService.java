package org.rahzex.behavioral.observer.service;

import org.rahzex.behavioral.observer.entity.ThemeSettings;
import org.rahzex.behavioral.observer.event.SettingsEvent;
import org.rahzex.behavioral.observer.observable.SettingUpdatePublisher;
import org.rahzex.behavioral.observer.repository.DB;

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
