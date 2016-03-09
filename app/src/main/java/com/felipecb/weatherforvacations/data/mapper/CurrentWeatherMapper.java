package com.felipecb.weatherforvacations.data.mapper;

import com.felipecb.weatherforvacations.data.entity.CurrentWeatherEntity;
import com.felipecb.weatherforvacations.domain.CurrentWeather;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Felipe Calderon Barragan
 */
@Singleton public class CurrentWeatherMapper {

  /**
   * Constructor for injection.
   */
  @Inject public CurrentWeatherMapper() {
    //empty constructor here
  }

  public CurrentWeather transform(CurrentWeatherEntity currentWeatherEntity) {
    CurrentWeather currentWeather = null;

    if (currentWeatherEntity != null) {
      currentWeather = new CurrentWeather();
      currentWeather.setLocationName(currentWeatherEntity.locationName);
      currentWeather.setTimestamp(currentWeatherEntity.timestamp);
      currentWeather.setDescription(currentWeatherEntity.weather.get(0).description);
      currentWeather.setId(currentWeatherEntity.weather.get(0).id);
      currentWeather.setTemperature(currentWeatherEntity.main.temp);
      currentWeather.setMaximumTemperature(currentWeatherEntity.main.temp_max);
      currentWeather.setMinimumTemperature(currentWeatherEntity.main.temp_min);
    }

    return currentWeather;
  }
}
