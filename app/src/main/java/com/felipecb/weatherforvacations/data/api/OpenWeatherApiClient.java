package com.felipecb.weatherforvacations.data.api;

import com.felipecb.weatherforvacations.data.entity.CurrentWeatherEntity;
import rx.Observable;

/**
 * @author Felipe Calderon Barragan
 */
public class OpenWeatherApiClient {

  private final OpenWeatherApiConfig openWeatherApiConfig;

  public OpenWeatherApiClient(OpenWeatherApiConfig openWeatherApiConfig) {
    this.openWeatherApiConfig = openWeatherApiConfig;
  }

  private <T> T getApi(Class<T> apiRest) {
    return openWeatherApiConfig.getRetrofit().create(apiRest);
  }

  private RestApi getOpenWeatherMapApi() {
    return getApi(RestApi.class);
  }

  public Observable<CurrentWeatherEntity> getCurrentWeather(double latitude, double longitude) {

    final String apiKey = this.openWeatherApiConfig.getApiKey();
    final String units = this.openWeatherApiConfig.getUnits();
    return getOpenWeatherMapApi().fetchCurrentWeather(units, apiKey, longitude, latitude);
  }
}
