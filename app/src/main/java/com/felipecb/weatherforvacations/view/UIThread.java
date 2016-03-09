package com.felipecb.weatherforvacations.view;

import com.felipecb.weatherforvacations.domain.executor.PostExecutionThread;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * MainThread (UI Thread) implementation based on a {@link rx.Scheduler}
 * which will execute actions on the Android UI thread
 *
 * @author Felipe Calderon Barragan
 */
@Singleton public class UIThread implements PostExecutionThread {

  @Inject public UIThread() {
    // Empty constructor for injection
  }

  /**
   * Get the {@link Scheduler} for observe the data.
   *
   * @return The Android UI thread.
   */
  @Override public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
