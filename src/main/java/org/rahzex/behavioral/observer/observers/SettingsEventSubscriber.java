package org.rahzex.behavioral.observer.observers;

import org.rahzex.behavioral.observer.entity.Settings;
import org.rahzex.behavioral.observer.event.SettingsEvent;

public interface SettingsEventSubscriber {
  void update(SettingsEvent event, Settings settings);
}
