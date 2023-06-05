package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class blackJackGameScreen extends AppCompatActivity{
    private Button restartButton;
    private Button homeButton;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_game_screen);

        restartButton = findViewById(R.id.bj_restart_button);
        name = findViewById(R.id.blackJackConfigScreen);
        String username = getIntent().getStringExtra("keyname");
        name.setText(username);
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
