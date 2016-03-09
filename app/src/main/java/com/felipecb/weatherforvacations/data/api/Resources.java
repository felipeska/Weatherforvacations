package com.felipecb.weatherforvacations.data.api;

public class Resources {

  public static final String WEATHER_API_VERSION = "/2.5";

  public static final String ROOT_PATH = "/data";

  public static final String LATITUDE_QUERY_PARAM = "lat";
  public static final String LONGITUDE_QUERY_PARAM = "lon";
  public static final String API_KEY_QUERY_PARAM = "appid";
  public static final String UNITS_QUERY_PARAM = "units";

  public static final String CURRENT_WEATHER = ROOT_PATH + WEATHER_API_VERSION + "/weather";
}
