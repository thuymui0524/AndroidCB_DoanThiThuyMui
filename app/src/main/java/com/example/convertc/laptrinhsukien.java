package com.example.convertc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;

public class laptrinhsukien extends AppCompatActivity {

    private EditText etNumber1, etNumber2;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnGcd, btnExit;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptrinhsukien);


        etNumber1 = findViewById(R.id.etnumA);
        etNumber2 = findViewById(R.id.etnumB);
        btnAdd = findViewById(R.id.btnCal);
        btnSubtract = findViewById(R.id.btnDif);
        btnMultiply = findViewById(R.id.btnPro);
        btnDivide = findViewById(R.id.btnQuo);
        btnGcd = findViewById(R.id.btnGcd);
        btnExit = findViewById(R.id.btnExit);
        tvResult = findViewById(R.id.tvresult);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });
        btnGcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('%');
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('x');
            }
        });
    }
    private void calculateGCD() {
        String numberA = etNumber1.getText().toString();
        String numberB = etNumber2.getText().toString();

        if (!numberA.isEmpty() && !numberB.isEmpty()) {
            int a = Integer.parseInt(numberA);
            int b = Integer.parseInt(numberB);

            // Tính ƯCLN bằng thuật toán Euclid
            int gcd = gcd(a, b);

            // Hiển thị kết quả
            tvResult.setText("GCD  " + a  + b + " : " + gcd);
        } else {
            Toast.makeText(laptrinhsukien.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
        }
    }

    // Thuật toán Euclid để tính ƯCLN
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    private void calculate(char operator) {

        String number1Str = etNumber1.getText().toString();
        String number2Str = etNumber2.getText().toString();


        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            Toast.makeText(laptrinhsukien.this, "hãy nhập đủ 2 số", Toast.LENGTH_SHORT).show();
            return;
        }


        double number1 = Double.parseDouble(number1Str);
        double number2 = Double.parseDouble(number2Str);
        double result = 0;


        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    Toast.makeText(laptrinhsukien.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
           case '%':
               calculateGCD();
               break;

            case 'x':
                finish();
                break;
        }



    }
}