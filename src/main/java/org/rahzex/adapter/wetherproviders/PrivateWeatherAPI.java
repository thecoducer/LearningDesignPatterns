package org.rahzex.adapter.wetherproviders;

import lombok.extern.slf4j.Slf4j;
import org.rahzex.adapter.dto.Location;

/**
 * This API is a proprietary API that is only used by the indian weather application company, and
 * it's not open to public.
 */
@Slf4j
public class PrivateWeatherAPI implements WeatherService {

  /**
   * @param location is X-Y coordinate
   * @return temperature in Celsius
   */
  @Override
  public double getTemperature(Location location) {
    log.info("Obtaining temperature form PrivateWeatherAPI...");
    return 25;
  }

  /**
   * @param location is X-Y coordinate separated by comma
   * @return Humidity Percentage
   */
  @Override
  public double getHumidity(Location location) {
    log.info("Obtaining humidity form PrivateWeatherAPI...");
    return 10;
  }
}
