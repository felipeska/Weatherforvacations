package com.felipecb.weatherforvacations.view.internal.injection;

/**
 * Interface representing a contract for clients that contains a component for dependency
 * injection.
 *
 * @author Felipe Calderon Barragan
 */
public interface HasComponent<C> {

  /**
   * @return The component for dependency injection
   */
  C getComponent();
}
