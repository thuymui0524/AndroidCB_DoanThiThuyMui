package com.example.animalsound;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.graphics.Color;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

public class M000ActSplash extends AppCompatActivity {

    private int[] colors = {
            R.color.purple_200,
            R.color.purple_500,
            R.color.purple_700,
            R.color.teal_200,
            R.color.teal_700
    };


    private int[] icons = {
            R.drawable.penguin,
            R.drawable.turtle,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.m002_act_splash);
        View mainLayout = findViewById(R.id.animallayout);
        ImageView iconView = findViewById(R.id.imv);

        Random random = new Random();

        int randomColor = colors[random.nextInt(colors.length)];
        int randomIcon = icons[random.nextInt(icons.length)];
        mainLayout.setBackgroundColor(randomColor);
        iconView.setImageResource(randomIcon);

    }
}
