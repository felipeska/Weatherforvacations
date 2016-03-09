package com.felipecb.weatherforvacations.data.repository.datasource;

import android.content.Context;
import com.felipecb.weatherforvacations.data.api.OpenWeatherApiClient;
import com.felipecb.weatherforvacations.data.api.RestApi;
import com.felipecb.weatherforvacations.data.mapper.CurrentWeatherMapper;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations int the future for {@link
 * CurrentWeatherDataStore}.
 *
 * @author Felipe Calderon Barragan
 */
@Singleton public class CurrentWeatherDataStoreFactory {

  @Inject public CurrentWeatherDataStoreFactory() {
    //empty constructor here.
  }

  public CurrentWeatherDataStore getCurrentWeatherDataSource(
      OpenWeatherApiClient openWeatherApiClient, CurrentWeatherMapper currentWeatherMapper) {
    return new CloudCurrentWeatherDataStore(openWeatherApiClient, currentWeatherMapper);
  }
}
