package com.felipecb.weatherforvacations.view;

import com.felipecb.weatherforvacations.domain.CurrentWeather;

public interface CurrentWeatherView {

  /**
   * Render the current weather info.
   *
   * @param currentWeather The current weather
   */
  void renderCurrentWeather(CurrentWeather currentWeather);

  /**
   * Hide the loading view.
   */
  void hideLoading();

  /**
   * Show the loading view.
   */
  void showLoading();

  /**
   * Show an error in the login process.
   */
  void showError();
}
