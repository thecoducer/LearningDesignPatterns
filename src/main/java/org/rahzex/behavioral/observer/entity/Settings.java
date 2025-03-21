package org.rahzex.behavioral.observer.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Settings {
  private FontSettings fontSettings;
  private ThemeSettings themeSettings;
}
