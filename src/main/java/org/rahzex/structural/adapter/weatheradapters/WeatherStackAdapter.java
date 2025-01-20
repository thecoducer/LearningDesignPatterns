package org.rahzex.structural.adapter.weatheradapters;

import lombok.extern.slf4j.Slf4j;
import org.rahzex.structural.adapter.converter.GeoCoordinatesConverter;
import org.rahzex.structural.adapter.converter.TemperatureConverter;
import org.rahzex.structural.adapter.dto.GeoCoordinates;
import org.rahzex.structural.adapter.weatherproviders.WeatherService;
import org.rahzex.structural.adapter.weatherproviders.WeatherStackAPI;

/** Weather application can adapt to WeatherStackAPI data using WeatherStackAdapter */
@Slf4j
public class WeatherStackAdapter implements WeatherService {
  private final WeatherStackAPI weatherStackAPI;

  public WeatherStackAdapter() {
    this.weatherStackAPI = new WeatherStackAPI();
  }

  @Override
  public double getTemperature(GeoCoordinates geoCoordinates) {
    log.info("Obtaining temperature from WeatherStackAPI...");
    String place = GeoCoordinatesConverter.getPlaceFromCoordinates(geoCoordinates);
    return TemperatureConverter.convertToCelsius(weatherStackAPI.getCurrentTemp(place));
  }

  @Override
  public double getHumidity(GeoCoordinates geoCoordinates) {
    log.info("Obtaining humidity from WeatherStackAPI...");
    String place = GeoCoordinatesConverter.getPlaceFromCoordinates(geoCoordinates);
    return weatherStackAPI.getCurrentHumidity(place);
  }
}
