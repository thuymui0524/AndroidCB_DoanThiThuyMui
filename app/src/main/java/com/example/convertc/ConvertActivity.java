package com.example.convertc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class ConvertActivity extends AppCompatActivity {
    private EditText etFah, etCel;
    private Button btnFah,btnCel, btnClear;
    private TextView textViewCel,textViewFah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);


        etFah= findViewById(R.id.etFah);
        etCel= findViewById(R.id.etCel);
        btnFah = findViewById(R.id.btnFah);
        btnCel = findViewById(R.id.btnCel);
        btnClear = findViewById(R.id.btnClear);
        textViewFah = findViewById(R.id.textViewFah);
        textViewCel = findViewById(R.id.textViewCel);

        btnFah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToFahrenheit();
            }
        });


        btnCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToCelsius();
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
            }
        });
    }


    private void convertToFahrenheit() {
        String tempInput = etCel.getText().toString();
        if (!tempInput.isEmpty()) {
            double celsius = Double.parseDouble(tempInput);
            double fahrenheit = (celsius * 9 / 5) + 32;
            etFah.setText("Temperature in Fahrenheit: " + fahrenheit);
        } else {
            Toast.makeText(ConvertActivity.this, "Please enter a temperature!", Toast.LENGTH_SHORT).show();
        }
    }


    private void convertToCelsius() {
        String tempInput = etFah.getText().toString();
        if (!tempInput.isEmpty()) {
            double fahrenheit = Double.parseDouble(tempInput);
            double celsius = (fahrenheit - 32) * 5 / 9;
            etCel.setText("Temperature in Celsius: " + celsius);
        } else {
            Toast.makeText(ConvertActivity.this, "Please enter a temperature!", Toast.LENGTH_SHORT).show();
        }
    }


    private void clearInput() {
        etFah.setText("");
        etCel.setText("");

    }
}
