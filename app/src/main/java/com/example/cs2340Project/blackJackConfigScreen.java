package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class blackJackConfigScreen extends AppCompatActivity{
    private Button prevButton;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_config_screen);

        continueButton = findViewById(R.id.bj_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(blackJackConfigScreen.this, blackJackGameScreen.class);
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
