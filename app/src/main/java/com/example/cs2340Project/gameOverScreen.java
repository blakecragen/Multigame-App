package com.example.cs2340Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class gameOverScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_screen);

        Button home = findViewById(R.id.go_home_button);
        Button restart = findViewById(R.id.go_restart_button);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameOverScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameOverScreen.this, BlackJackInitialScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
