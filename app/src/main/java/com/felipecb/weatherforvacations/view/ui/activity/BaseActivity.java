package com.felipecb.weatherforvacations.view.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.felipecb.weatherforvacations.WeatherForVacationsApplication;
import com.felipecb.weatherforvacations.view.internal.injection.HasComponent;
import com.felipecb.weatherforvacations.view.internal.injection.components.ApplicationComponent;
import com.felipecb.weatherforvacations.view.ui.LayoutResource;

public abstract class BaseActivity extends AppCompatActivity implements LayoutResource {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResource());
    ButterKnife.bind(this);
    this.getApplicationComponent().inject(this);
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link ApplicationComponent}
   */
  protected ApplicationComponent getApplicationComponent() {
    return ((WeatherForVacationsApplication) getApplication()).getApplicationComponent();
  }

  /**
   * Gets a component for dependency injection by its type.
   */
  @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((HasComponent<C>) this).getComponent());
  }

  protected abstract void initializeInjector();
}
