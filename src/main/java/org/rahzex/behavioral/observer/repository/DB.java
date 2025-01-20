package org.rahzex.behavioral.observer.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.rahzex.behavioral.observer.entity.FontSettings;
import org.rahzex.behavioral.observer.entity.Settings;
import org.rahzex.behavioral.observer.entity.ThemeSettings;
import org.rahzex.behavioral.observer.observers.SettingsEventSubscriber;
import org.thecoducer.inventorymanagementsystem.event.Event;

public class DB {
  @Getter private static FontSettings fontSettings = new FontSettings("ST1", 0);
  @Getter private static ThemeSettings themeSettings = new ThemeSettings("BLACK", "GREY");
  private static Settings settings =
      Settings.builder().themeSettings(themeSettings).fontSettings(fontSettings).build();
  private static final Map<Event, List<SettingsEventSubscriber>> eventSubscriberMap =
      new HashMap<>();

  public static void setFontSettings(FontSettings fontSettings) {
    DB.fontSettings = fontSettings;
  }

  public static void setThemeSettings(ThemeSettings themeSettings) {
    DB.themeSettings = themeSettings;
  }

  public static Map<Event, List<SettingsEventSubscriber>> getEventSubscribers() {
    return eventSubscriberMap;
  }

  public static Settings getSettings() {
    return settings;
  }
}
