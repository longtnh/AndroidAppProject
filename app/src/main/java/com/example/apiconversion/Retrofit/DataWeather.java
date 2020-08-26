package com.example.apiconversion.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataWeather {
    @SerializedName("main")
    private Main main;
    @SerializedName("weather")
    private List<Weather> weather;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
