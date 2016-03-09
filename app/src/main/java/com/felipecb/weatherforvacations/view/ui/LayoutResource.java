package com.felipecb.weatherforvacations.view.ui;

import android.support.annotation.LayoutRes;

/**
 * This interface is the contract for provide the layout resource id in Fragments or Activities.
 *
 * @author Felipe Calderon Barragan
 */
public interface LayoutResource {

  /**
   * Provide the container layout xml resource id to the Fragment or Activity.
   *
   * @return The container layout xml resource id.
   */
  @LayoutRes int getLayoutResource();
}
