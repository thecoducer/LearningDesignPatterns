package org.rahzex.adapter;

import org.rahzex.adapter.dto.Location;
import org.rahzex.adapter.dto.WeatherData;
import org.rahzex.adapter.wetherproviders.WeatherService;

public class WeatherApplication {

    private final WeatherService weatherService;

    public WeatherApplication(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public WeatherData getWeather(Location location) {
        return WeatherData.builder()
                .temperature(weatherService.getTemperature(location))
                .humidity(weatherService.getHumidity(location))
                .build();
    }
}
