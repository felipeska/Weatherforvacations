package com.felipecb.weatherforvacations.view.internal.injection.modules;

import com.felipecb.weatherforvacations.domain.interactor.CurrentWeatherInteractor;
import com.felipecb.weatherforvacations.domain.interactor.CurrentWeatherInteractorImpl;
import dagger.Module;
import dagger.Provides;

@Module public class CurrentWeatherModule {

  @Provides CurrentWeatherInteractor provideCurrentWeatherInteractor(
      CurrentWeatherInteractorImpl currentWeatherInteractor) {
    return currentWeatherInteractor;
  }
}
