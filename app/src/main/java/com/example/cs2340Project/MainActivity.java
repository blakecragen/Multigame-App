package com.example.cs2340Project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button wordleScreenButton = findViewById(R.id.homeToWordleStart);
        wordleScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, wordleStartScreen.class);
            startActivity(intent);
        });

        Button blackJackScreenButton = findViewById(R.id.blackJackStartScreen);
        blackJackScreenButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, blackJackStartScreen.class)));
    }
}