package com.felipecb.weatherforvacations.domain.interactor;

import android.location.Location;
import com.felipecb.weatherforvacations.domain.CurrentWeather;
import com.felipecb.weatherforvacations.domain.repository.CurrentWeatherRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Felipe Calderon Barragan
 */
public class CurrentWeatherInteractorImpl implements CurrentWeatherInteractor {

  /** The repository instance. */
  private final CurrentWeatherRepository currentWeatherRepository;

  @Inject public CurrentWeatherInteractorImpl(CurrentWeatherRepository currentWeatherRepository) {
    this.currentWeatherRepository = currentWeatherRepository;
  }

  @Override public Observable<CurrentWeather> getCurrentWeather(Location location) {
    return this.currentWeatherRepository.currentWeather(location);
  }
}
