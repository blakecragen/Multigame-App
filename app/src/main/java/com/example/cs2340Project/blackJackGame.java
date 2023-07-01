package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class blackJackGame extends AppCompatActivity{
    private Button restartButton;
    private Button homeButton;
    private TextView name;

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_game);

        player = Player.getInstance();
        selectLives();

        restartButton = findViewById(R.id.bj_restart_button);
//        name = findViewById(R.id.blackJackConfigScreen);
//        String username = getIntent().getStringExtra("keyname");
//        name.setText(username);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        homeButton = findViewById(R.id.bj_game_home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(blackJackGame.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @SuppressLint("ResourceAsColor")
    private void selectLives() {
        if(player.getPlayerLives() == 0){
            Intent intent = new Intent(blackJackGame.this, gameOverScreen.class);
            startActivity(intent);
            finish();
        }
        TextView name = findViewById(R.id.playerDataName);
        name.setText(player.getPlayerName());
        //update the sprite image
        ImageView sprite = findViewById(R.id.sprite);
        player.setSpriteImage(sprite);
        ImageView i1 = findViewById(R.id.life1);
        ImageView i2 = findViewById(R.id.life2);
        ImageView i3 = findViewById(R.id.life3);
        player.setLives(i1,i2,i3);
    }}
