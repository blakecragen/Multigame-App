package com.example.cs2340Project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class blackJackStartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_start_screen);
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> finish());

        Button initButton = findViewById(R.id.init);
        initButton.setOnClickListener(v -> {
            Intent intent = new Intent(blackJackStartScreen.this, blackJackConfigScreen.class);
            startActivity(intent);
            finish();
        });
    }
}
