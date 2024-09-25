package com.example.convertc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;


import java.text.DecimalFormat;

public class BMI_activity extends AppCompatActivity {

    private EditText weightInput;
    private EditText heightInput, editchuandoan,resultText;

    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


        weightInput = findViewById(R.id.etWeight);
        heightInput = findViewById(R.id.etHeight);
        calculateButton = findViewById(R.id.btnCal);
        editchuandoan=findViewById(R.id.etchuandoan);
        resultText = findViewById(R.id.etbmi);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weightStr = weightInput.getText().toString();
                String heightStr = heightInput.getText().toString();

                if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
                    float weight = Float.parseFloat(weightStr);
                    float height = Float.parseFloat(heightStr);


                    if (height > 0) {

                        float bmi = calculateBMI(weight, height);

                        String classification = classifyBMI(bmi);
                       DecimalFormat dcf=new DecimalFormat(" #.0");
                       resultText.setText(dcf.format(bmi));
                       editchuandoan.setText(classification);
                    } else {
                        resultText.setText("Chiều cao phải lớn hơn 0!");
                    }
                } else {
                    resultText.setText("Vui lòng nhập đầy đủ thông tin!");
                }
            }
        });
    }


    private float calculateBMI(float weight, float height) {
        return weight / (height * height);
    }


    private String classifyBMI(float bmi) {
        if (bmi < 18) {
            return "bạn gầy";
        } else if ( bmi <= 24.9) {
            return "bạn bình thường";
        } else if ( bmi <= 29.9) {
            return "bạn béo phì độ I";
        } else if ( bmi <= 34.9) {
            return "bạn béo phì độ II";
        } else {
            return "bạn béo phì độ III";
        }
    }

}
