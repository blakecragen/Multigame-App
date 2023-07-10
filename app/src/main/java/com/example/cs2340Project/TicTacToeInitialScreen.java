package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TicTacToeInitialScreen extends AppCompatActivity {
    private EditText name;
    private Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_toe_initial_screen);
        name = findViewById(R.id.checkersPLayerName);
        add = findViewById(R.id.gameStart);


        Button initButton = findViewById(R.id.gameStart);
        initButton.setOnClickListener(v -> {
            Intent intent = new Intent(TicTacToeInitialScreen.this, TicTacToeGame.class);
            String username = name.getText().toString();
            intent.putExtra("keyname", username);
            startActivity(intent);
            finish();
        });
        Button homeButton = findViewById(R.id.toHome);
        homeButton.setOnClickListener(v -> finish());
    }
}