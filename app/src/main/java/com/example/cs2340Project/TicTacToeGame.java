package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class TicTacToeGame extends AppCompatActivity {
    private Button restartButton;
    private Button homeButton;
    private Player player1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_toe_game);


        restartButton = findViewById(R.id.t_restart_button);
        player1 = Player.getInstance();
        selectLives();
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeGame.this, TicTacToeInitialScreen.class);
                player1.setPlayerLives((3));
                startActivity(intent);
                finish();
            }
        });

        homeButton = findViewById(R.id.t_home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeGame.this, MainActivity.class);
                player1.setPlayerLives((3));
                startActivity(intent);
            }
        });
    }

    private void selectLives() {
        if(player1.getPlayerLives() == 0){
            Intent intent = new Intent(TicTacToeGame.this, GameOverScreen.class);
            startActivity(intent);
            finish();
        }
        TextView name = findViewById(R.id.playerDataName);
        name.setText(player1.getPlayerName());
        name.setTextColor(-1);
        //update the sprite image
        ImageView sprite = findViewById(R.id.sprite);
        player1.setSpriteImage(sprite);
        ImageView i1 = findViewById(R.id.life1);
        ImageView i2 = findViewById(R.id.life2);
        ImageView i3 = findViewById(R.id.life3);
        player1.setLives(i1,i2,i3);
    }
}