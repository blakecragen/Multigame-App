package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button wordleScreenButton = (Button) findViewById(R.id.homeToWordleStart);
        wordleScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWordleStartScreen();
            }
        });
    }

    public void openWordleStartScreen() {
        Intent intent = new Intent(this, wordleStartScreen.class);
        startActivity(intent);
    }
}