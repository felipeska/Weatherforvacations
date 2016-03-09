package com.felipecb.weatherforvacations;

import android.app.Application;
import com.felipecb.weatherforvacations.view.internal.injection.components.ApplicationComponent;
import com.felipecb.weatherforvacations.view.internal.injection.components.DaggerApplicationComponent;
import com.felipecb.weatherforvacations.view.internal.injection.modules.ApplicationModule;

public class WeatherForVacationsApplication extends Application {

  public static final String OPEN_WEATHER_API_KEY = "44db6a862fba0b067b1930da0d769e98";

  /** The application component for the dependency injection. */
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeInjector();
  }

  /**
   * @return The application component.
   */
  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  /**
   * Initializes the dependency injector for the global dependencies.
   */
  private void initializeInjector() {
    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }
}
