package com.felipecb.weatherforvacations.domain.interactor;

import android.location.Location;
import com.felipecb.weatherforvacations.domain.CurrentWeather;
import rx.Observable;

/**
 * @author Felipe Calderon Barragan
 */
public interface CurrentWeatherInteractor {

  Observable<CurrentWeather> getCurrentWeather(Location location);
}
