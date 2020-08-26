package com.example.apiconversion.Retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("v4/latest/{currency}")
    Call<JsonObject> getExchangeCurrency(@Path("currency") String currency);

    @GET("weather?appid=92756c24107bc39dd0a7541f66ba55c5&units=metric")
    Call<DataWeather> getWeatherData(@Query("q") String name);

}
