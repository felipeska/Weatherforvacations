package com.felipecb.weatherforvacations.domain.subscriber;

/**
 * Default subscriber base class to be used whenever you want default error handling.
 *
 * @author Felipe Calderon Barragan
 */
public class DefaultSubscriber<T> extends rx.Subscriber<T> {

  /** {@inheritDoc} */
  @Override public void onCompleted() {

  }

  /** {@inheritDoc} */
  @Override public void onError(Throwable e) {

  }

  /** {@inheritDoc} */
  @Override public void onNext(T t) {

  }
}
