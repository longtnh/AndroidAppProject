package com.example.apiconversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    static final int REQUEST = 8888;
    ImageView imgCurrency,imgWeather,imgCompass,imgNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        imgCurrency = (ImageView) findViewById(R.id.moneyConvert1);
        imgWeather = (ImageView) findViewById(R.id.weather1);
        imgCompass = (ImageView) findViewById(R.id.compass1);
        imgNote = (ImageView) findViewById(R.id.note);

        imgCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivityForResult(intent, REQUEST);
            }
        });
        imgWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, WeatherActivity.class);
                startActivityForResult(intent, REQUEST);
            }
        });
        imgCompass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, CompassActivity.class);
                startActivityForResult(intent, REQUEST);
            }
        });
        imgNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, NoteActivity.class);
                startActivityForResult(intent, REQUEST);
            }
        });
    }

}