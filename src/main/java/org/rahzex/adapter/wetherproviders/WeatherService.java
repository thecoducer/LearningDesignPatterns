package org.rahzex.adapter.wetherproviders;

import org.rahzex.adapter.dto.Location;

/**
 * Weather Service defines basic contract which the weather application company will follow
 * throughout its all applications. It is also required if in future they plan to integrate new
 * weather providers.
 */
public interface WeatherService {
  double getTemperature(Location location); // Temperature in Celsius

  double getHumidity(Location location); // Humidity in percentage
}
