package com.example.cs2340Project.wordle.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340Project.R;

public class wordleGame extends AppCompatActivity {
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordle_game);
        name = findViewById(R.id.wordleHeading);
        String username = getIntent().getStringExtra("keyname");
        name.setText(username);
        Button toHome = findViewById(R.id.toMainActivity);
        toHome.setOnClickListener(v -> finish());

        Button restart = findViewById(R.id.wd_restart_button);
        restart.setOnClickListener(v -> recreate());

    }
}