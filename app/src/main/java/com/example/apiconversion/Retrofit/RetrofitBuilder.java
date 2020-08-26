package com.example.apiconversion.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static Retrofit retrofitMoney  = null;
    private static Retrofit retrofitWeather = null;
    public static Retrofit getRetrofitInstance(){
        if(retrofitMoney == null){
            retrofitMoney = new Retrofit.Builder()
                    .baseUrl("https://api.exchangerate-api.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofitMoney;
    }

    public static  Retrofit getClient(){

        if (retrofitWeather == null){

            retrofitWeather = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofitWeather;

    }
}
