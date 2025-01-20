package org.rahzex.adapter.weatherproviders;

/** WeatherStack is third party weather provider. */
public class WeatherStackAPI {

  /**
   * @param place name
   * @return temperature in Fahrenheit
   */
  public double getCurrentTemp(String place) {
    return 118.0;
  }

  /**
   * @param place name
   * @return humidity in percentage
   */
  public double getCurrentHumidity(String place) {
    return 70.0;
  }
}
