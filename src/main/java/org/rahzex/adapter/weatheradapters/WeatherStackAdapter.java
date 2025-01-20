package org.rahzex.adapter.weatheradapters;

import lombok.extern.slf4j.Slf4j;
import org.rahzex.adapter.dto.Location;
import org.rahzex.adapter.converter.LocationConverter;
import org.rahzex.adapter.converter.TemperatureConverter;
import org.rahzex.adapter.wetherproviders.WeatherService;
import org.rahzex.adapter.wetherproviders.WeatherStackAPI;

/**
 * Weather application can adapt to WeatherStackAPI data using WeatherStackAdapter
 */
@Slf4j
public class WeatherStackAdapter implements WeatherService {
    private WeatherStackAPI weatherStackAPI;

    public WeatherStackAdapter() {
        this.weatherStackAPI = new WeatherStackAPI();
    }

    @Override
    public double getTemperature(Location location) {
        log.info("Obtaining temperature form WeatherStackAPI...");
        String place = LocationConverter.getPlaceFromLocation(location);
        return TemperatureConverter.convertToCelsius(weatherStackAPI.getCurrentTemp(place));
    }

    @Override
    public double getHumidity(Location location) {
        log.info("Obtaining humidity form WeatherStackAPI...");
        String place = LocationConverter.getPlaceFromLocation(location);
        return weatherStackAPI.getCurrentHumidity(place);
    }
}

