package com.example.cs2340Project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340Project.blackjack.ui.blackJackStartScreen;
import com.example.cs2340Project.checkers.ui.checkersStartScreen;
import com.example.cs2340Project.wordle.ui.wordleStartScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button blackJackScreenButton = findViewById(R.id.blackJackStartScreen);
        blackJackScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, blackJackStartScreen.class);
            startActivity(intent);
        });

        Button checkersScreenButton = findViewById(R.id.checkersStartScreen);
        checkersScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, checkersStartScreen.class);
            startActivity(intent);
        });


        Button wordleStScreen = findViewById(R.id.wordleStartScreen2);
        wordleStScreen.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, wordleStartScreen.class);
            startActivity(intent);
        });
    }
}