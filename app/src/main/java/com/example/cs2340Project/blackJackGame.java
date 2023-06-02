package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class blackJackGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_game);

        Button gameSelectButton = findViewById(R.id.gameSelect);
        gameSelectButton.setOnClickListener(v -> finish());
    }
}