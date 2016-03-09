package com.felipecb.weatherforvacations.domain;

public class CurrentWeather {

  private String locationName;
  private long timestamp;
  private String description;
  private float minimumTemperature;
  private float maximumTemperature;
  private float temperature;
  private int id;

  public CurrentWeather() {
    //public constructor here;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getMinimumTemperature() {
    return minimumTemperature;
  }

  public void setMinimumTemperature(float minimumTemperature) {
    this.minimumTemperature = minimumTemperature;
  }

  public float getMaximumTemperature() {
    return maximumTemperature;
  }

  public void setMaximumTemperature(float maximumTemperature) {
    this.maximumTemperature = maximumTemperature;
  }

  public float getTemperature() {
    return temperature;
  }

  public void setTemperature(float temperature) {
    this.temperature = temperature;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
