package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class blackJackConfigScreen extends AppCompatActivity{
    private Button prevButton;
    private Button continueButton;

    private EditText name;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_config_screen);
        name = findViewById(R.id.player_name);
        add = findViewById(R.id.bj_continue_button);


        continueButton = findViewById(R.id.bj_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(blackJackConfigScreen.this, blackJackGameScreen.class);
                String username = name.getText().toString();
                intent.putExtra("keyname", username);
                startActivity(intent);
            }
        });

        prevButton = findViewById(R.id.bj_prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(blackJackConfigScreen.this, blackJackStartScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
