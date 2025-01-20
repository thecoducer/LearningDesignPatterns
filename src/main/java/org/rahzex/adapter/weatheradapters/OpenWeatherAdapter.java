package org.rahzex.adapter.weatheradapters;

import lombok.extern.slf4j.Slf4j;
import org.rahzex.adapter.converter.LocationConverter;
import org.rahzex.adapter.converter.TemperatureConverter;
import org.rahzex.adapter.dto.Location;
import org.rahzex.adapter.wetherproviders.OpenWeatherAPI;
import org.rahzex.adapter.wetherproviders.WeatherService;

/** Weather application can adapt to OpenWeatherAPI data using OpenWeatherAdapter */
@Slf4j
public class OpenWeatherAdapter implements WeatherService {
  private final OpenWeatherAPI openWeatherAPI;

  public OpenWeatherAdapter() {
    this.openWeatherAPI = new OpenWeatherAPI();
  }

  @Override
  public double getTemperature(Location location) {
    log.info("Obtaining temperature form OpenWeatherAPI...");
    String city = LocationConverter.getCityFromLocation(location);
    return TemperatureConverter.convertToCelsius(openWeatherAPI.fetchTemperature(city));
  }

  @Override
  public double getHumidity(Location location) {
    log.info("Obtaining humidity form OpenWeatherAPI...");
    String city = LocationConverter.getCityFromLocation(location);
    return openWeatherAPI.fetchHumidity(city);
  }
}
