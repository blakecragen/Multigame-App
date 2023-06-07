package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class checkersGame extends AppCompatActivity {
    private TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkers_game);
        name = findViewById(R.id.checkersHeading);
        String username = getIntent().getStringExtra("keyname");
        name.setText(username);

        Button homeButton = findViewById(R.id.toHome);
        homeButton.setOnClickListener(v -> finish());

        Button restart = findViewById(R.id.ck_restart_button);
        restart.setOnClickListener(v -> recreate());
    }
}