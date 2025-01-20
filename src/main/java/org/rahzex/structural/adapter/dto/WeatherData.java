package org.rahzex.structural.adapter.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WeatherData {
  private double temperature;
  private double humidity;
}
