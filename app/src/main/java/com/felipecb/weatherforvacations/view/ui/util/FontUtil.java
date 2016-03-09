package com.felipecb.weatherforvacations.view.ui.util;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;

public class FontUtil {

  private FontUtil() {
  }

  private static final Map<String, Typeface> sTypefaceCache = new HashMap<String, Typeface>();

  public static Typeface get(Context context, String font) {
    synchronized (sTypefaceCache) {
      if (!sTypefaceCache.containsKey(font)) {
        Typeface tf = Typeface.createFromAsset(context.getApplicationContext().getAssets(),
            "fonts/" + font + ".ttf");
        sTypefaceCache.put(font, tf);
      }
      return sTypefaceCache.get(font);
    }
  }
}
