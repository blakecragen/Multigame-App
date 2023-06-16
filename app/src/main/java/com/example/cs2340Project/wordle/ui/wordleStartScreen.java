package com.example.cs2340Project.wordle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340Project.R;

public class wordleStartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordle_start_screen);

        Button homeButton = findViewById(R.id.toHome);
        homeButton.setOnClickListener(v -> finish());


        Button initButton = findViewById(R.id.initButton);
        initButton.setOnClickListener(v -> {
            Intent intent = new Intent(wordleStartScreen.this, wordleInitialScreen.class);
            startActivity(intent);
            finish();
        });
    }
}