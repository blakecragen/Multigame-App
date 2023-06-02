package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class blackJackGameScreen extends AppCompatActivity{
    private Button restartButton;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_game_screen);

        restartButton = findViewById(R.id.bj_restart_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(blackJackGameScreen.this, blackJackGameScreen.class);
                startActivity(intent);
            }
        });

        homeButton = findViewById(R.id.bj_game_home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(blackJackGameScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
