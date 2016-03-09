package com.felipecb.weatherforvacations.view.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.felipecb.weatherforvacations.R;
import com.felipecb.weatherforvacations.domain.CurrentWeather;
import com.felipecb.weatherforvacations.view.CurrentWeatherView;
import com.felipecb.weatherforvacations.view.internal.injection.HasComponent;
import com.felipecb.weatherforvacations.view.internal.injection.components.CurrentWeatherComponent;
import com.felipecb.weatherforvacations.view.internal.injection.components.DaggerCurrentWeatherComponent;
import com.felipecb.weatherforvacations.view.internal.injection.modules.CurrentWeatherModule;
import com.felipecb.weatherforvacations.view.presenter.CurrentWeatherPresenter;
import com.github.pwittchen.weathericonview.WeatherIconView;
import javax.inject.Inject;

import static com.felipecb.weatherforvacations.view.ui.helper.TemperatureFormatter.format;

public class MainActivity extends BaseActivity
    implements CurrentWeatherView, HasComponent<CurrentWeatherComponent> {

  /** The current weather presenter. */
  @Inject CurrentWeatherPresenter currentWeatherPresenter;

  /** The current weather component. */
  private CurrentWeatherComponent currentWeatherComponent;

  @Bind(R.id.weather_temperature_text) TextView mTextViewCurrentTemperature;
  @Bind(R.id.weather_location) TextView mTextViewLocation;
  @Bind(R.id.weather_description) TextView mTextViewDescription;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.progressBar) ProgressBar mProgressBar;
  @Bind(R.id.contentPanel) LinearLayout mContentPanel;
  @Bind(R.id.my_weather_icon) WeatherIconView mWeatherIconView;
  @Bind(R.id.retry_view) RelativeLayout mRelativeLayoutViewRetry;

  /** {@inheritDoc} */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupToolbar();
    this.initializeInjector();
    this.getComponent(CurrentWeatherComponent.class).inject(this);
    this.currentWeatherPresenter.setCurrentView(this);
    this.currentWeatherPresenter.init();
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    final ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayShowTitleEnabled(false);
    }
  }

  /** {@inheritDoc} */
  @Override protected void onResume() {
    super.onResume();
    this.currentWeatherPresenter.resume();
  }

  /** {@inheritDoc} */
  @Override protected void onDestroy() {
    super.onDestroy();
    this.currentWeatherPresenter.destroy();
  }

  /** {@inheritDoc} */
  @Override protected void initializeInjector() {
    this.currentWeatherComponent = DaggerCurrentWeatherComponent.builder()
        .applicationComponent(getApplicationComponent())
        .currentWeatherModule(new CurrentWeatherModule())
        .build();
  }

  /** {@inheritDoc} */
  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  /** {@inheritDoc} */
  @Override public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

  /** {@inheritDoc} */
  @Override public void renderCurrentWeather(CurrentWeather currentWeather) {
    mContentPanel.setVisibility(View.VISIBLE);
    renderData(currentWeather);
  }

  private void renderData(CurrentWeather currentWeather) {
    mTextViewLocation.setText(currentWeather.getLocationName());
    mTextViewCurrentTemperature.setText(format(currentWeather.getTemperature()));
    mTextViewDescription.setText(currentWeather.getDescription());
    mWeatherIconView.setIconResource(getWeatherIconResource(currentWeather));
  }

  private String getWeatherIconResource(CurrentWeather currentWeather) {
    final String iconResourcePartialName = "wi_owm_";
    final String defType = "string";
    int resourceId = this.getResources()
        .getIdentifier(iconResourcePartialName + currentWeather.getId(), defType,
            this.getPackageName());
    return getString(resourceId);
  }

  /** {@inheritDoc} */
  @Override public void hideLoading() {
    mProgressBar.setVisibility(View.GONE);
  }

  /** {@inheritDoc} */
  @Override public void showLoading() {
    mProgressBar.setVisibility(View.VISIBLE);
    mContentPanel.setVisibility(View.GONE);
  }

  /** {@inheritDoc} */
  @Override public void showError() {
    mRelativeLayoutViewRetry.setVisibility(View.VISIBLE);
  }

  /** {@inheritDoc} */
  @Override public CurrentWeatherComponent getComponent() {
    return this.currentWeatherComponent;
  }

  /** {@inheritDoc} */
  @Override public int getLayoutResource() {
    return R.layout.activity_main;
  }
}
