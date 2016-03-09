package com.felipecb.weatherforvacations.view.internal.injection.modules;

import android.content.Context;
import com.felipecb.weatherforvacations.WeatherForVacationsApplication;
import com.felipecb.weatherforvacations.data.api.OpenWeatherApiClient;
import com.felipecb.weatherforvacations.data.api.OpenWeatherApiConfig;
import com.felipecb.weatherforvacations.data.executor.JobExecutor;
import com.felipecb.weatherforvacations.data.repository.CurrentWeatherDataRepository;
import com.felipecb.weatherforvacations.domain.executor.PostExecutionThread;
import com.felipecb.weatherforvacations.domain.executor.ThreadExecutor;
import com.felipecb.weatherforvacations.domain.repository.CurrentWeatherRepository;
import com.felipecb.weatherforvacations.view.UIThread;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

@Module public class ApplicationModule {

  /** The application instance. */
  private final WeatherForVacationsApplication app;

  /**
   * Module constructor.
   *
   * @param app The application instance.
   */
  public ApplicationModule(WeatherForVacationsApplication app) {
    this.app = app;
  }

  /**
   * @return The application context.
   */
  @Provides @Singleton Context provideApplicationContext() {
    return this.app.getApplicationContext();
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  /**
   * @return The scheduler instance for the use in observables subscription.
   */
  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton CurrentWeatherRepository provideCurrentWeatherRepository(
      CurrentWeatherDataRepository currentWeatherDataRepository) {
    return currentWeatherDataRepository;
  }

  @Provides @Singleton ReactiveLocationProvider providerReactiveLocationProvider(Context context) {
    return new ReactiveLocationProvider(context);
  }

  /**
   * Creates and returns the rest api instance.
   *
   * @return The rest api instance to use for the connections.
   */
  @Provides @Singleton OpenWeatherApiClient provideOpenWeatherApiClient() {
    OpenWeatherApiConfig openWeatherApiConfig = new OpenWeatherApiConfig.Builder(
        WeatherForVacationsApplication.OPEN_WEATHER_API_KEY).build();
    return new OpenWeatherApiClient(openWeatherApiConfig);
  }
}
