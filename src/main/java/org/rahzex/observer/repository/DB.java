package org.rahzex.observer.repository;

import lombok.Getter;
import org.thecoducer.observer.event.Event;
import org.rahzex.observer.entity.FontSettings;
import org.rahzex.observer.entity.Settings;
import org.rahzex.observer.entity.ThemeSettings;
import org.rahzex.observer.observers.SettingsEventSubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {
    @Getter
    private static FontSettings fontSettings = new FontSettings("ST1", 0);
    @Getter
    private static ThemeSettings themeSettings = new ThemeSettings("BLACK", "GREY");
    private static Settings settings = Settings.builder()
            .themeSettings(themeSettings)
            .fontSettings(fontSettings)
            .build();
    private static final Map<Event, List<SettingsEventSubscriber>> eventSubscriberMap = new HashMap<>();

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
