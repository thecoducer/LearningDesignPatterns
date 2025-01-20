package org.rahzex.structural.adapter.converter;

import org.rahzex.structural.adapter.dto.GeoCoordinates;

public class GeoCoordinatesConverter {

  public static String getCityFromCoordinates(GeoCoordinates coordinates) {
    return "Bengaluru";
  }

  public static String getPlaceFromCoordinates(GeoCoordinates coordinates) {
    return "Maleshwaram";
  }
}
