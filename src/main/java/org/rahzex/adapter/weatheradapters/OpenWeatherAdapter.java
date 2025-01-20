package org.rahzex.adapter.weatheradapters;

import lombok.extern.slf4j.Slf4j;
import org.rahzex.adapter.converter.GeoCoordinatesConverter;
import org.rahzex.adapter.converter.TemperatureConverter;
import org.rahzex.adapter.dto.GeoCoordinates;
import org.rahzex.adapter.weatherproviders.OpenWeatherAPI;
import org.rahzex.adapter.weatherproviders.WeatherService;

/** Weather application can adapt to OpenWeatherAPI data using OpenWeatherAdapter */
@Slf4j
public class OpenWeatherAdapter implements WeatherService {
  private final OpenWeatherAPI openWeatherAPI;

  public OpenWeatherAdapter() {
    this.openWeatherAPI = new OpenWeatherAPI();
  }

  @Override
  public double getTemperature(GeoCoordinates geoCoordinates) {
    log.info("Obtaining temperature from OpenWeatherAPI...");
    String city = GeoCoordinatesConverter.getCityFromCoordinates(geoCoordinates);
    return TemperatureConverter.convertToCelsius(openWeatherAPI.fetchTemperature(city));
  }

  @Override
  public double getHumidity(GeoCoordinates geoCoordinates) {
    log.info("Obtaining humidity from OpenWeatherAPI...");
    String city = GeoCoordinatesConverter.getCityFromCoordinates(geoCoordinates);
    return openWeatherAPI.fetchHumidity(city);
  }
}
