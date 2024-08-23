package com.example.ungdungnghegoi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
public class MainActivity extends AppCompatActivity {



        private EditText etPhoneNumber, etMessage;
        private Button btnCall, btnSendSms;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            etPhoneNumber = findViewById(R.id.etPhoneNumber);
            etMessage = findViewById(R.id.etMessage);
            btnCall = findViewById(R.id.btnCall);
            btnSendSms = findViewById(R.id.btnSendSms);

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makePhoneCall();
                }
            });

            btnSendSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendSms();
                }
            });
        }

        private void makePhoneCall() {
            String phoneNumber = etPhoneNumber.getText().toString();
            if (!phoneNumber.trim().isEmpty()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    String dial = "tel:" + phoneNumber;
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            } else {
                Toast.makeText(MainActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
            }
        }

        private void sendSms() {
            String phoneNumber = etPhoneNumber.getText().toString();
            String message = etMessage.getText().toString();
            if (!phoneNumber.trim().isEmpty() && !message.trim().isEmpty()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.SEND_SMS}, 2);
                } else {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(MainActivity.this, "SMS sent", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Enter phone number and message", Toast.LENGTH_SHORT).show();
            }
        }
}