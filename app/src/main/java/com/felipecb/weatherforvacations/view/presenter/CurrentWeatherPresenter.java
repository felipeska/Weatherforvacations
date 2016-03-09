package com.felipecb.weatherforvacations.view.presenter;

import android.location.Location;
import com.felipecb.weatherforvacations.domain.CurrentWeather;
import com.felipecb.weatherforvacations.domain.executor.PostExecutionThread;
import com.felipecb.weatherforvacations.domain.executor.ThreadExecutor;
import com.felipecb.weatherforvacations.domain.interactor.CurrentWeatherInteractor;
import com.felipecb.weatherforvacations.domain.subscriber.DefaultSubscriber;
import com.felipecb.weatherforvacations.view.CurrentWeatherView;
import javax.inject.Inject;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class CurrentWeatherPresenter implements Presenter {

  private final CurrentWeatherInteractor currentWeatherInteractor;

  private final ThreadExecutor threadExecutor;

  private final PostExecutionThread postExecutionThread;

  private final ReactiveLocationProvider reactiveLocationProvider;

  private Subscription currentWeatherSubscription;

  private Subscription lastKnownLocationSubscription;

  private CurrentWeatherView currentWeatherView;

  @Inject public CurrentWeatherPresenter(CurrentWeatherInteractor currentWeatherInteractor,
      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
      ReactiveLocationProvider reactiveLocationProvider) {
    this.currentWeatherInteractor = currentWeatherInteractor;
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
    this.reactiveLocationProvider = reactiveLocationProvider;
  }

  public void init() {
    this.lastKnownLocationSubscription = reactiveLocationProvider.getLastKnownLocation()
        .first()
        .subscribeOn(Schedulers.from(this.threadExecutor))
        .observeOn(this.postExecutionThread.getScheduler())
        .subscribe(new Action1<Location>() {
          @Override public void call(Location location) {
            fetchCurrentWeather(location);
          }
        });
  }

  private void fetchCurrentWeather(Location location) {
    this.currentWeatherSubscription = this.currentWeatherInteractor.getCurrentWeather(location)
        .subscribeOn(Schedulers.from(this.threadExecutor))
        .observeOn(this.postExecutionThread.getScheduler())
        .subscribe(new CurrentWeatherSubscriber());
  }

  public void setCurrentView(CurrentWeatherView currentWeatherView) {
    this.currentWeatherView = currentWeatherView;
  }

  @Override public void resume() {

  }

  @Override public void pause() {

  }

  /**
   * Call the view to hide the loading view.
   */
  private void hideLoading() {
    this.currentWeatherView.hideLoading();
  }

  /**
   * Call the view to show the loading view.
   */
  private void showLoading() {
    this.currentWeatherView.showLoading();
  }

  private void showError() {
    this.currentWeatherView.showError();
  }

  private void renderCurrentWeather(CurrentWeather currentWeather) {
    this.currentWeatherView.renderCurrentWeather(currentWeather);
  }

  @Override public void destroy() {
    if (currentWeatherSubscription != null && !currentWeatherSubscription.isUnsubscribed()) {
      currentWeatherSubscription.unsubscribe();
    }

    if (lastKnownLocationSubscription != null && !lastKnownLocationSubscription.isUnsubscribed()) {
      lastKnownLocationSubscription.unsubscribe();
    }
  }

  private final class CurrentWeatherSubscriber extends DefaultSubscriber<CurrentWeather> {

    @Override public void onStart() {
      super.onStart();
      CurrentWeatherPresenter.this.showLoading();
    }

    @Override public void onError(Throwable e) {
      super.onError(e);
      e.printStackTrace();
      CurrentWeatherPresenter.this.hideLoading();
      CurrentWeatherPresenter.this.showError();
    }

    @Override public void onCompleted() {
      super.onCompleted();
      CurrentWeatherPresenter.this.hideLoading();
    }

    @Override public void onNext(CurrentWeather currentWeather) {
      super.onNext(currentWeather);
      CurrentWeatherPresenter.this.renderCurrentWeather(currentWeather);
    }
  }
}