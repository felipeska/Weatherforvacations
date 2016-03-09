package com.felipecb.weatherforvacations.view.ui.helper;


public final class TemperatureFormatter {

  public static String format(float temperature) {
    return String.valueOf(Math.round(temperature)) + "°";
  }
}
