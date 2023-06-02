package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class blackJackInitialScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_initial_screen);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Button startGame = findViewById(R.id.startBlackJack);
        startGame.setOnClickListener(v -> {
            Intent intent = new Intent(blackJackInitialScreen.this, blackJackGame.class);
            startActivity(intent);
            finish();
        });
    }
}