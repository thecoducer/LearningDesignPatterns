package org.rahzex.adapter;

import org.rahzex.adapter.dto.GeoCoordinates;
import org.rahzex.adapter.dto.WeatherData;
import org.rahzex.adapter.weatherproviders.WeatherService;

public class WeatherApplication {

  private final WeatherService weatherService;

  public WeatherApplication(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  public WeatherData getWeather(GeoCoordinates geoCoordinates) {
    return WeatherData.builder()
        .temperature(weatherService.getTemperature(geoCoordinates))
        .humidity(weatherService.getHumidity(geoCoordinates))
        .build();
  }
}
