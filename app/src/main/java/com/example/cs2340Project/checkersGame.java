package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class checkersGame extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkers_game);

        Button homeButton = findViewById(R.id.toHome);
        homeButton.setOnClickListener(v -> finish());
    }
}