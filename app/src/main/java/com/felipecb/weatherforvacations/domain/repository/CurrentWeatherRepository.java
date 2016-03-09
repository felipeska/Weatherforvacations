package com.felipecb.weatherforvacations.domain.repository;

import android.location.Location;
import com.felipecb.weatherforvacations.domain.CurrentWeather;
import rx.Observable;

/**
 * Repository interface for the access of the data from a current weather.
 *
 * @author Felipe Calderon Barragan
 */
public interface CurrentWeatherRepository {

  /**
   * Executes the current user.
   *
   * @return A {@link Observable} with the corresponding weather.
   */
  Observable<CurrentWeather> currentWeather(Location location);
}
