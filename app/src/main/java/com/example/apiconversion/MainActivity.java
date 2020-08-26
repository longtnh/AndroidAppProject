package com.example.apiconversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiconversion.Retrofit.RetrofitBuilder;
import com.example.apiconversion.Retrofit.RetrofitInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText currencyToBeConverted;
    EditText currencyConverted;
    Spinner convertToDropdown;
    Spinner convertFromDropdown;
    TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Currency Converted");

        currencyConverted = (EditText) findViewById(R.id.currency_converted);
        currencyToBeConverted = (EditText) findViewById(R.id.currency_to_be_converted);
        convertToDropdown = (Spinner) findViewById(R.id.convert_to);
        convertFromDropdown = (Spinner) findViewById(R.id.convert_from);
        button = (Button) findViewById(R.id.button);
        date = (TextView) findViewById(R.id.date);

        String[] dropDownList = {"USD","AED","ARS","AUD","BGN","BRL","BSD","CAD","CHF","CLP","CNY","CZK","DKK","DOP","EUR","FJD","GBP"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dropDownList);
        convertToDropdown.setAdapter(adapter);
        convertFromDropdown.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currencyToBeConverted.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Input something !!", Toast.LENGTH_LONG).show();
                }
                else {
                    RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                    Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropdown.getSelectedItem().toString());
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            JsonObject res = response.body();
                            JsonObject rates = res.getAsJsonObject("rates");
                            JsonPrimitive dateUpdate = res.getAsJsonPrimitive("date");
                            double currency = Double.valueOf(currencyToBeConverted.getText().toString());
                            double multiplier = Double.valueOf(rates.get(convertToDropdown.getSelectedItem().toString()).toString());
                            double result = currency * multiplier;
                            currencyConverted.setText(String.valueOf(result));
                            date.append(dateUpdate.toString());

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                }

            }
        });
    }
}