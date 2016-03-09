package com.felipecb.weatherforvacations.view.internal.injection.components;

import android.content.Context;
import com.felipecb.weatherforvacations.data.api.OpenWeatherApiClient;
import com.felipecb.weatherforvacations.domain.executor.PostExecutionThread;
import com.felipecb.weatherforvacations.domain.executor.ThreadExecutor;
import com.felipecb.weatherforvacations.domain.repository.CurrentWeatherRepository;
import com.felipecb.weatherforvacations.view.ui.activity.BaseActivity;
import com.felipecb.weatherforvacations.view.internal.injection.modules.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

@Singleton @Component(modules = { ApplicationModule.class }) public interface ApplicationComponent {

  /**
   * Injects to the BaseActivity instance.
   *
   * @param activity The activity instance.
   */
  void inject(BaseActivity activity);

  //Exposed to sub-graphs.

  /** The context. */
  Context context();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  /** The repository for the user data. */
  CurrentWeatherRepository userRepository();

  ReactiveLocationProvider reactiveLocationProvider();

  /** The rest api instance. */
  OpenWeatherApiClient openWeatherApiClient();
}
