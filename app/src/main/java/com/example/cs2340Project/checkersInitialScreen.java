package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class checkersInitialScreen extends AppCompatActivity {
    private EditText name;
    private Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkers_initial_screen);
        name = findViewById(R.id.checkersPLayerName);
        add = findViewById(R.id.gameStart);


        Button initButton = findViewById(R.id.gameStart);
        initButton.setOnClickListener(v -> {
            Intent intent = new Intent(checkersInitialScreen.this, checkersGame.class);
            String username = name.getText().toString();
            intent.putExtra("keyname", username);
            startActivity(intent);
            finish();
        });
        Button homeButton = findViewById(R.id.toHome);
        homeButton.setOnClickListener(v -> finish());
    }
}