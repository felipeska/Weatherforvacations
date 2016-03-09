package com.felipecb.weatherforvacations.data.entity;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class CurrentWeatherEntity {

  private final static String SERIALIZED_LOCATION_NAME = "name";
  private final static String SERIALIZED_TIMESTAMP = "dt";

  @SerializedName(SERIALIZED_LOCATION_NAME) public String locationName;
  @SerializedName(SERIALIZED_TIMESTAMP) public long timestamp;
  public ArrayList<WeatherEntity> weather;
  public WeatherInfoEntity main;
}
