package com.felipecb.weatherforvacations.data.repository.datasource;

import android.location.Location;
import com.felipecb.weatherforvacations.data.api.OpenWeatherApiClient;
import com.felipecb.weatherforvacations.data.entity.CurrentWeatherEntity;
import com.felipecb.weatherforvacations.data.mapper.CurrentWeatherMapper;
import com.felipecb.weatherforvacations.domain.CurrentWeather;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @author Felipe Calderon Barragan
 */
public class CloudCurrentWeatherDataStore implements CurrentWeatherDataStore {

  /** The Open Weather rest api instance. */
  private final OpenWeatherApiClient openWeatherApiClient;

  /** The user currentWeatherMapper instance. */
  private final CurrentWeatherMapper currentWeatherMapper;

  @Inject public CloudCurrentWeatherDataStore(OpenWeatherApiClient openWeatherApiClient,
      CurrentWeatherMapper currentWeatherMapper) {
    this.openWeatherApiClient = openWeatherApiClient;
    this.currentWeatherMapper = currentWeatherMapper;
  }

  /** {@inheritDoc} */
  @Override public Observable<CurrentWeather> currentWeather(Location location) {

    return openWeatherApiClient.getCurrentWeather(location.getLatitude(), location.getLongitude())
        .flatMap(new Func1<CurrentWeatherEntity, Observable<CurrentWeather>>() {
          @Override
          public Observable<CurrentWeather> call(final CurrentWeatherEntity currentWeatherEntity) {
            return Observable.create(new Observable.OnSubscribe<CurrentWeather>() {
              @Override public void call(Subscriber<? super CurrentWeather> subscriber) {
                subscriber.onNext(currentWeatherMapper.transform(currentWeatherEntity));
                subscriber.onCompleted();
              }
            });
          }
        });
  }
}
