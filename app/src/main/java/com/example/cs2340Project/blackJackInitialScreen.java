package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class blackJackInitialScreen extends AppCompatActivity{
    private Button prevButton;
    private Button continueButton;
    private Player player;
    private EditText name;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_initial_screen);
        name = findViewById(R.id.player_name);
        add = findViewById(R.id.bj_continue_button);

        continueButton = findViewById(R.id.bj_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = Player.getInstance();
                player.setPlayerName(name.getText().toString());
                setSprite();
                Intent intent = new Intent(blackJackInitialScreen.this, blackJackGame.class);
                String username = name.getText().toString();
                intent.putExtra("keyname", username);
                startActivity(intent);
            }
        });

        prevButton = findViewById(R.id.bj_prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(blackJackInitialScreen.this, blackJackStartScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void setSprite(){
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
