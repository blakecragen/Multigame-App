package com.example.cs2340Project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class wordleGame extends AppCompatActivity implements View.OnClickListener {
    private EditText[] editTextFields;
    private wordleGameFunctionality wordle = new wordleGameFunctionality();
    private int letsInRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordle_game);

        letsInRow = 0;
        wordle.selectNewWord();

        Button toHome = findViewById(R.id.toMainActivity);
        toHome.setOnClickListener(v -> finish());

        Button restart = findViewById(R.id.wd_restart_button);
        restart.setOnClickListener(v -> recreate());

        // Initialize the array of EditText fields
        editTextFields = new EditText[30];
        editTextFields[0] = findViewById(R.id.editText1);
        editTextFields[1] = findViewById(R.id.editText2);
        editTextFields[2] = findViewById(R.id.editText3);
        editTextFields[3] = findViewById(R.id.editText4);
        editTextFields[4] = findViewById(R.id.editText5);
        editTextFields[5] = findViewById(R.id.editText6);
        editTextFields[6] = findViewById(R.id.editText7);
        editTextFields[7] = findViewById(R.id.editText8);
        editTextFields[8] = findViewById(R.id.editText9);
        editTextFields[9] = findViewById(R.id.editText10);
        editTextFields[10] = findViewById(R.id.editText11);
        editTextFields[11] = findViewById(R.id.editText12);
        editTextFields[12] = findViewById(R.id.editText13);
        editTextFields[13] = findViewById(R.id.editText14);
        editTextFields[14] = findViewById(R.id.editText15);
        editTextFields[15] = findViewById(R.id.editText16);
        editTextFields[16] = findViewById(R.id.editText17);
        editTextFields[17] = findViewById(R.id.editText18);
        editTextFields[18] = findViewById(R.id.editText19);
        editTextFields[19] = findViewById(R.id.editText20);
        editTextFields[20] = findViewById(R.id.editText21);
        editTextFields[21] = findViewById(R.id.editText22);
        editTextFields[22] = findViewById(R.id.editText23);
        editTextFields[23] = findViewById(R.id.editText24);
        editTextFields[24] = findViewById(R.id.editText25);
        editTextFields[25] = findViewById(R.id.editText26);
        editTextFields[26] = findViewById(R.id.editText27);
        editTextFields[27] = findViewById(R.id.editText28);
        editTextFields[28] = findViewById(R.id.editText29);
        editTextFields[29] = findViewById(R.id.editText30);
        // Add the rest of the EditText fields in a similar way...

        // Set click listeners for all the buttons in the GridLayout
        int[] buttonIds = {
                R.id.buttonA, R.id.buttonB, R.id.buttonC, R.id.buttonD, R.id.buttonE,
                R.id.buttonF, R.id.buttonG, R.id.buttonH, R.id.buttonI, R.id.buttonJ,
                R.id.buttonK, R.id.buttonL, R.id.buttonM, R.id.buttonN, R.id.buttonO,
                R.id.buttonP, R.id.buttonQ, R.id.buttonR, R.id.buttonS, R.id.buttonT,
                R.id.buttonU, R.id.buttonV, R.id.buttonW, R.id.buttonX, R.id.buttonY, R.id.buttonZ
        };
        for (int i = 0; i < buttonIds.length; i++) {
            Button button = findViewById(buttonIds[i]);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        for (int i = 0; i < editTextFields.length; i++) {
            EditText editText = editTextFields[i];
            if (editText.getText().toString().isEmpty() && letsInRow < 5) {
                editText.setText(buttonText);
                // Move focus to the next EditText field if available
                if (i + 1 < editTextFields.length) {
                    editTextFields[i + 1].requestFocus();
                }
                letsInRow++;
                break;
            }
        }
    }

}