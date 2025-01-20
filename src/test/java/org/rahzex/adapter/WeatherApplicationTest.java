package org.rahzex.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.rahzex.adapter.dto.Location;
import org.rahzex.adapter.weatheradapters.OpenWeatherAdapter;
import org.rahzex.adapter.weatheradapters.WeatherStackAdapter;
import org.rahzex.adapter.wetherproviders.PrivateWeatherAPI;

public class WeatherApplicationTest {

  @Test
  public void testWeatherApplication() {
    Location location = Location.builder().xCoordinate(28.6754).yCoordinate(78.8327).build();

    WeatherApplication application = new WeatherApplication(new PrivateWeatherAPI());
    var weather = application.getWeather(location);

    assertEquals(25, weather.getTemperature());
    assertEquals(10, weather.getHumidity());

    application = new WeatherApplication(new OpenWeatherAdapter());
    weather = application.getWeather(location);

    assertEquals(46, weather.getTemperature());
    assertEquals(65, weather.getHumidity());

    application = new WeatherApplication(new WeatherStackAdapter());
    weather = application.getWeather(location);

    assertEquals(59, weather.getTemperature());
    assertEquals(70, weather.getHumidity());
  }
}
