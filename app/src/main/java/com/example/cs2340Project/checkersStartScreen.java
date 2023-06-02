package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class checkersStartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkers_start_screen);

        Button initButton = findViewById(R.id.initializeButton);
        initButton.setOnClickListener(v -> {
            Intent intent = new Intent(checkersStartScreen.this, checkersInitialScreen.class);
            startActivity(intent);
            finish();
        });
        Button homeButton = findViewById(R.id.toHome);
        homeButton.setOnClickListener(v -> finish());
    }
}