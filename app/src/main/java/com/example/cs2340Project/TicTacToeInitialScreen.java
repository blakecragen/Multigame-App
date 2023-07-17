package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class TicTacToeInitialScreen extends AppCompatActivity implements SpriteSettable{
        private Button prevButton;
        private Button continueButton;
        private Player player;
        private EditText name;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tic_tac_toe_initial_screen);
            name = findViewById(R.id.player_name);

            continueButton = findViewById(R.id.t_continue_button);
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player = Player.getInstance();
                    player.setPlayerName(name.getText().toString());
                    setSprite();
                    Intent intent = new Intent(com.example.cs2340Project.TicTacToeInitialScreen.this, TicTacToeGame.class);
                    startActivity(intent);
                }
            });

            prevButton = findViewById(R.id.t_prev_button);
            prevButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(com.example.cs2340Project.TicTacToeInitialScreen.this, TicTacToeStartScreen.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
        @Override
        public void setSprite(){
            RadioButton pandaButton = findViewById(R.id.pandaButton);
            RadioButton foxButton = findViewById(R.id.foxButton);
            RadioButton turtleButton = findViewById(R.id.turtleButton);
            ImageView sprite = findViewById(R.id.sprite);
            if(pandaButton.isChecked()){
                player.setPlayerSprite('p');
                //sprite.setImageResource(R.drawable.panda);
            }
            else if(turtleButton.isChecked()){
                player.setPlayerSprite('t');
                //sprite.setImageResource(R.drawable.turtle);
            }
            else {
                player.setPlayerSprite('f');
                //sprite.setImageResource(R.drawable.fox);
            }
        }
    }