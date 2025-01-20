package org.rahzex.adapter.weatherproviders;

import lombok.extern.slf4j.Slf4j;
import org.rahzex.adapter.dto.GeoCoordinates;

/**
 * This API is a proprietary API that is only used by the indian weather application company, and
 * it's not open to public.
 */
@Slf4j
public class PrivateWeatherAPI implements WeatherService {

  /**
   * @param geoCoordinates is X-Y coordinate
   * @return temperature in Celsius
   */
  @Override
  public double getTemperature(GeoCoordinates geoCoordinates) {
    log.info("Obtaining temperature form PrivateWeatherAPI...");
    return 25;
  }

  /**
   * @param geoCoordinates is X-Y coordinate separated by comma
   * @return Humidity Percentage
   */
  @Override
  public double getHumidity(GeoCoordinates geoCoordinates) {
    log.info("Obtaining humidity form PrivateWeatherAPI...");
    return 10;
  }
}
