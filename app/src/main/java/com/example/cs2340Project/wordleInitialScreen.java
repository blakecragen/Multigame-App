package com.example.cs2340Project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class wordleInitialScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordle_initial_screen);

        Button wordleStartScreen = findViewById(R.id.BackButton);
        wordleStartScreen.setOnClickListener(v -> finish());

        Button startGame = findViewById(R.id.startGame);
        startGame.setOnClickListener(v -> {
            Intent intent = new Intent(wordleInitialScreen.this, wordleGame.class);
            startActivity(intent);
            finish();
            //startActivityForResult(intent, 1);
        });
    }
}