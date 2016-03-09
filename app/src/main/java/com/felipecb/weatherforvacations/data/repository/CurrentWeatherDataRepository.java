package com.felipecb.weatherforvacations.data.repository;

import android.location.Location;
import com.felipecb.weatherforvacations.data.api.OpenWeatherApiClient;
import com.felipecb.weatherforvacations.data.mapper.CurrentWeatherMapper;
import com.felipecb.weatherforvacations.data.repository.datasource.CurrentWeatherDataStoreFactory;
import com.felipecb.weatherforvacations.domain.CurrentWeather;
import com.felipecb.weatherforvacations.domain.repository.CurrentWeatherRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

@Singleton public class CurrentWeatherDataRepository implements CurrentWeatherRepository {

  private final CurrentWeatherDataStoreFactory currentWeatherDataStoreFactory;

  private final OpenWeatherApiClient openWeatherApiClient;

  private final CurrentWeatherMapper currentWeatherMapper;

  @Inject
  public CurrentWeatherDataRepository(CurrentWeatherDataStoreFactory currentWeatherDataStoreFactory,
      OpenWeatherApiClient openWeatherApiClient, CurrentWeatherMapper currentWeatherMapper) {
    this.currentWeatherDataStoreFactory = currentWeatherDataStoreFactory;
    this.currentWeatherMapper = currentWeatherMapper;
    this.openWeatherApiClient = openWeatherApiClient;
  }

  @Override public Observable<CurrentWeather> currentWeather(Location location) {
    return this.currentWeatherDataStoreFactory.getCurrentWeatherDataSource(openWeatherApiClient,
        currentWeatherMapper).currentWeather(location);
  }
}
