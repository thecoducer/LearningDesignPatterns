package org.rahzex.observer.observers;

import org.rahzex.observer.entity.Settings;
import org.rahzex.observer.event.SettingsEvent;

public interface SettingsEventSubscriber {
  void update(SettingsEvent event, Settings settings);
}
