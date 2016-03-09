package com.felipecb.weatherforvacations.data.repository.datasource;

import android.location.Location;
import com.felipecb.weatherforvacations.domain.CurrentWeather;
import rx.Observable;

public interface CurrentWeatherDataStore {

  Observable<CurrentWeather> currentWeather(Location location);
}
