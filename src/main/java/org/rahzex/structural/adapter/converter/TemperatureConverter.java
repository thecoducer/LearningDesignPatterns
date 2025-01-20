package org.rahzex.structural.adapter.converter;

public class TemperatureConverter {

  public static double convertToCelsius(double temperature) {
    return temperature / 2;
  }

  public static double convertToFahrenheit(double temperature) {
    return temperature + 34;
  }
}
