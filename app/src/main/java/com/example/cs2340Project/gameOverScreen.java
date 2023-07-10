package com.example.cs2340Project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class gameOverScreen extends AppCompatActivity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_screen);
        player = Player.getInstance();

        Button toHome = findViewById(R.id.go_home_button);
        toHome.setOnClickListener(v -> finish());

        Button restart = findViewById(R.id.go_restart_button);
        restart.setOnClickListener(v -> {
            Intent intent =  new Intent(gameOverScreen.this, CheckersInitialScreen.class);
            switch(player.getCurrentGame()) {
                case 'c': intent = new Intent(gameOverScreen.this, CheckersInitialScreen.class);
                    break;
                case 'w': intent = new Intent(gameOverScreen.this, WordleInitialScreen.class);
                    break;
                case 'b': intent = new Intent(gameOverScreen.this, BlackJackInitialScreen.class);
                    break;
            }
            startActivity(intent);
            finish();
        });
    }
}

