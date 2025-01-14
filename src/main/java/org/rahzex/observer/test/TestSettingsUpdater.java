package org.rahzex.observer.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rahzex.observer.entity.FontSettings;
import org.rahzex.observer.entity.ThemeSettings;
import org.rahzex.observer.event.SettingsEvent;
import org.rahzex.observer.observable.SettingUpdatePublisher;
import org.rahzex.observer.observers.Header;
import org.rahzex.observer.observers.TextPanel;
import org.rahzex.observer.service.FontService;
import org.rahzex.observer.service.ThemeService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSettingsUpdater {
    private FontService fontService;
    private ThemeService themeService;
    private SettingUpdatePublisher updatePublisher;

    @BeforeEach
    void init() {
        fontService = new FontService();
        themeService = new ThemeService();
        updatePublisher = new SettingUpdatePublisher();
    }

    @Test
    void testSettingsUpdate() {
        Header header = new Header();
        TextPanel textPanel = new TextPanel();

        updatePublisher.subscribe(SettingsEvent.THEME_UPDATED, header);
        updatePublisher.subscribe(SettingsEvent.THEME_UPDATED, textPanel);
        updatePublisher.subscribe(SettingsEvent.FONT_UPDATED, textPanel);

        themeService.update(ThemeSettings.builder().primaryColor("Red").accentColor("Maroon").build());
        fontService.update(FontSettings.builder().fontSize(20).fontStyle("ST11").build());

        updatePublisher.unsubscribe(SettingsEvent.THEME_UPDATED, header);
        themeService.update(ThemeSettings.builder().primaryColor("White").accentColor("Cream").build());

        assertEquals(20, fontService.getFontSettings().getFontSize());
        assertEquals("ST11", fontService.getFontSettings().getFontStyle());

        assertEquals("White", themeService.getThemeSettings().getPrimaryColor());
        assertEquals("Cream", themeService.getThemeSettings().getAccentColor());
    }
}
