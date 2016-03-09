package com.felipecb.weatherforvacations.data.api;

import com.felipecb.weatherforvacations.data.entity.CurrentWeatherEntity;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

import static com.felipecb.weatherforvacations.data.api.Resources.API_KEY_QUERY_PARAM;
import static com.felipecb.weatherforvacations.data.api.Resources.CURRENT_WEATHER;
import static com.felipecb.weatherforvacations.data.api.Resources.LATITUDE_QUERY_PARAM;
import static com.felipecb.weatherforvacations.data.api.Resources.LONGITUDE_QUERY_PARAM;
import static com.felipecb.weatherforvacations.data.api.Resources.UNITS_QUERY_PARAM;

public interface RestApi {

  @GET(CURRENT_WEATHER) Observable<CurrentWeatherEntity> fetchCurrentWeather(
      @Query(UNITS_QUERY_PARAM) String units, @Query(API_KEY_QUERY_PARAM) String apiKey,
      @Query(LONGITUDE_QUERY_PARAM) double longitude, @Query(LATITUDE_QUERY_PARAM) double latitude);
}
