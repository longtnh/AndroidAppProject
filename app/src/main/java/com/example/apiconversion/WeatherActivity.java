package com.example.apiconversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiconversion.Retrofit.DataWeather;
import com.example.apiconversion.Retrofit.RetrofitBuilder;
import com.example.apiconversion.Retrofit.RetrofitInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    ImageView search;
    TextView tempText , descText , humidityText, weatherText;
    EditText textField;
    ImageView img_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setTitle("Weather Forecast");

        search = (ImageView) findViewById(R.id.search);
        tempText = (TextView) findViewById(R.id.tempText);
        descText = (TextView) findViewById(R.id.descText);
        humidityText = (TextView) findViewById(R.id.humidityText);
        textField = (EditText) findViewById(R.id.textField);
        weatherText = (TextView) findViewById(R.id.textWeather);
        img_weather = (ImageView) findViewById(R.id.img_weather);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textField.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Input something !!", Toast.LENGTH_LONG).show();
                }
                else {
                    getWeatherData(textField.getText().toString().trim());
                }
            }
        });
    }

    private void getWeatherData(String name){

        RetrofitInterface apiInterface = RetrofitBuilder.getClient().create(RetrofitInterface.class);

        Call<DataWeather> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<DataWeather>() {
            @Override
            public void onResponse(Call<DataWeather> call, Response<DataWeather> response) {
                Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                        .append(response.body().getWeather().get(0).getIcon())
                        .append(".png").toString()).into(img_weather);
                tempText.setText(response.body().getMain().getTemp() + "\u2103");
                descText.setText("Feels Like : " + response.body().getMain().getFeels_like());
                humidityText.setText("Humidity : " + response.body().getMain().getHumidity());
                weatherText.setText("Weather : " + response.body().getWeather().get(0).getMain());
            }

                @Override
            public void onFailure(Call<DataWeather> call, Throwable t) {

            }
        });

    }
}