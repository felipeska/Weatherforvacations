package com.felipecb.weatherforvacations.data.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * @author Felipe Calderon Barragan
 */
public class OpenWeatherApiConfig {

  private static OpenWeatherApiConfig singleton;
  private final String apiKey;
  private final String units;
  private final boolean debug;
  private final Retrofit retrofit;

  public OpenWeatherApiConfig(String apiKey, String units, boolean debug, Retrofit retrofit) {
    this.apiKey = apiKey;
    this.units = units;
    this.debug = debug;
    this.retrofit = retrofit;
  }

  public static OpenWeatherApiConfig with(String apiKey) {
    if (singleton == null) {
      singleton = new Builder(apiKey).build();
    }
    return singleton;
  }

  public Retrofit getRetrofit() {
    return retrofit;
  }

  public String getApiKey() {
    return apiKey;
  }

  public boolean isDebug() {
    return debug;
  }

  public String getUnits() {
    return units;
  }

  @SuppressWarnings("UnusedDeclaration") public static class Builder {

    private static final String OPEN_WEATHER_API_BASE_URL = "http://api.openweathermap.org";
    private static final String METRIC_UNITS = "metric";
    private final String apiKey;
    private boolean debug;
    private String units = METRIC_UNITS;
    private Retrofit retrofit;
    private String baseUrl = OPEN_WEATHER_API_BASE_URL;

    public Builder(String apiKey) {
      if (apiKey == null) {
        throw new IllegalArgumentException("api key must not be null.");
      }

      this.apiKey = apiKey;
    }

    public Builder debug() {
      this.debug = true;
      return this;
    }

    public Builder baseUrl(String url) {
      this.baseUrl = url;
      return this;
    }

    public Builder units(String units) {
      this.units = units;
      return this;
    }

    public Builder retrofit(Retrofit retrofit) {
      if (retrofit == null) {
        throw new IllegalArgumentException("retrofit must not be null.");
      }
      this.retrofit = retrofit;
      return this;
    }

    public OpenWeatherApiConfig build() {
      if (retrofit == null) {
        retrofit = createDefaultRetrofit(baseUrl, debug);
      }
      return new OpenWeatherApiConfig(apiKey, units, debug, retrofit);
    }

    private Retrofit createDefaultRetrofit(String baseUrl, boolean debug) {
      OkHttpClient client = new OkHttpClient();
      if (debug) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);
      }

      return new Retrofit.Builder().baseUrl(baseUrl)
          .client(client)
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
  }
}
