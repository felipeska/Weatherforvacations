package com.felipecb.weatherforvacations.view.internal.injection.components;

import com.felipecb.weatherforvacations.view.ui.activity.MainActivity;
import com.felipecb.weatherforvacations.view.internal.injection.PerActivity;
import com.felipecb.weatherforvacations.view.internal.injection.modules.CurrentWeatherModule;
import dagger.Component;

/**
 * Component interface for the dependency injection for the get the current weather.
 *
 * @author Felipe Calderon Barragan
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { CurrentWeatherModule.class })
public interface CurrentWeatherComponent {

  /**
   * Injects to the MainActivity instance.
   *
   * @param activity The activity instance.
   */
  void inject(MainActivity activity);
}