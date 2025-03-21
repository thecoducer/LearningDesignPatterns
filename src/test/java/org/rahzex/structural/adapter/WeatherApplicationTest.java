package org.rahzex.structural.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.rahzex.structural.adapter.dto.GeoCoordinates;
import org.rahzex.structural.adapter.weatheradapters.OpenWeatherAdapter;
import org.rahzex.structural.adapter.weatheradapters.WeatherStackAdapter;
import org.rahzex.structural.adapter.weatherproviders.PrivateWeatherAPI;

public class WeatherApplicationTest {

  @Test
  public void testWeatherApplication() {
    GeoCoordinates geoCoordinates =
        GeoCoordinates.builder().longitude(28.6754).latitude(78.8327).build();

    WeatherApplication application = new WeatherApplication(new PrivateWeatherAPI());
    var weather = application.getWeather(geoCoordinates);

    assertEquals(25, weather.getTemperature());
    assertEquals(10, weather.getHumidity());

    application = new WeatherApplication(new OpenWeatherAdapter());
    weather = application.getWeather(geoCoordinates);

    assertEquals(46, weather.getTemperature());
    assertEquals(65, weather.getHumidity());

    application = new WeatherApplication(new WeatherStackAdapter());
    weather = application.getWeather(geoCoordinates);

    assertEquals(59, weather.getTemperature());
    assertEquals(70, weather.getHumidity());
  }
}
