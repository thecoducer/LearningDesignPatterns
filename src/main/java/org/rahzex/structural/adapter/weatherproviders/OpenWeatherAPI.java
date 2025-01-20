package org.rahzex.structural.adapter.weatherproviders;

/** OpenWeather is a third party weather provider. */
public class OpenWeatherAPI {

  /**
   * @param city name
   * @return temperature in Fahrenheit
   */
  public double fetchTemperature(String city) {
    return 92.0;
  }

  /**
   * @param city name
   * @return humidity in percentage
   */
  public double fetchHumidity(String city) {
    return 65.0;
  }
}
