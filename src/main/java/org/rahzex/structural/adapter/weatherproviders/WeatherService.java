package org.rahzex.structural.adapter.weatherproviders;

import org.rahzex.structural.adapter.dto.GeoCoordinates;

/**
 * Weather Service defines basic contract which the weather application company will follow
 * throughout its all applications. It is also required if in future they plan to integrate new
 * weather providers.
 */
public interface WeatherService {
  double getTemperature(GeoCoordinates geoCoordinates); // Temperature in Celsius

  double getHumidity(GeoCoordinates geoCoordinates); // Humidity in percentage
}
