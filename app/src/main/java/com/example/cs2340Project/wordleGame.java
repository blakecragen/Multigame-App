package com.example.cs2340Project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class wordleGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordle_game);

        Button toHome = findViewById(R.id.toMainActivity);
        toHome.setOnClickListener(v -> finish());
    }
}