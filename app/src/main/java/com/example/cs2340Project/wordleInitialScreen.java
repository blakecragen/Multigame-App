package com.example.cs2340Project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class wordleInitialScreen extends AppCompatActivity {
    private EditText name;
    private Button add;
    private Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordle_initial_screen);
        name = findViewById(R.id.wordlePLayerName);
        add = findViewById(R.id.startGame);


        Button wordleStartScreen = findViewById(R.id.BackButton);
        wordleStartScreen.setOnClickListener(v -> finish());

        Button startGame = findViewById(R.id.startGame);
        startGame.setOnClickListener(v -> {
            player = Player.getInstance();
            player.setPlayerName(name.getText().toString());
            Intent intent = new Intent(wordleInitialScreen.this, wordleGame.class);
            startActivity(intent);
            finish();
            //startActivityForResult(intent, 1);
        });
    }
}