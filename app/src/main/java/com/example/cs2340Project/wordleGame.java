package com.example.cs2340Project;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class wordleGame extends AppCompatActivity implements View.OnClickListener {
    private EditText[] letterBox;
    private Button enter;
    private Button del;

    private int letsInRow;
    private wordleGameFunctionality wordle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordle_game);

        letsInRow = 0;
        wordle = new wordleGameFunctionality();
        wordle.selectNewWord();

        Button toHome = findViewById(R.id.toMainActivity);
        toHome.setOnClickListener(v -> finish());

        Button restart = findViewById(R.id.wd_restart_button);
        restart.setOnClickListener(v -> recreate());

        letterBox = new EditText[30];
        letterBox[0] = findViewById(R.id.editText1);
        letterBox[1] = findViewById(R.id.editText2);
        letterBox[2] = findViewById(R.id.editText3);
        letterBox[3] = findViewById(R.id.editText4);
        letterBox[4] = findViewById(R.id.editText5);
        letterBox[5] = findViewById(R.id.editText6);
        letterBox[6] = findViewById(R.id.editText7);
        letterBox[7] = findViewById(R.id.editText8);
        letterBox[8] = findViewById(R.id.editText9);
        letterBox[9] = findViewById(R.id.editText10);
        letterBox[10] = findViewById(R.id.editText11);
        letterBox[11] = findViewById(R.id.editText12);
        letterBox[12] = findViewById(R.id.editText13);
        letterBox[13] = findViewById(R.id.editText14);
        letterBox[14] = findViewById(R.id.editText15);
        letterBox[15] = findViewById(R.id.editText16);
        letterBox[16] = findViewById(R.id.editText17);
        letterBox[17] = findViewById(R.id.editText18);
        letterBox[18] = findViewById(R.id.editText19);
        letterBox[19] = findViewById(R.id.editText20);
        letterBox[20] = findViewById(R.id.editText21);
        letterBox[21] = findViewById(R.id.editText22);
        letterBox[22] = findViewById(R.id.editText23);
        letterBox[23] = findViewById(R.id.editText24);
        letterBox[24] = findViewById(R.id.editText25);
        letterBox[25] = findViewById(R.id.editText26);
        letterBox[26] = findViewById(R.id.editText27);
        letterBox[27] = findViewById(R.id.editText28);
        letterBox[28] = findViewById(R.id.editText29);
        letterBox[29] = findViewById(R.id.editText30);


        int[] buttonIds = {
                R.id.buttonA, R.id.buttonB, R.id.buttonC, R.id.buttonD, R.id.buttonE,
                R.id.buttonF, R.id.buttonG, R.id.buttonH, R.id.buttonI, R.id.buttonJ,
                R.id.buttonK, R.id.buttonL, R.id.buttonM, R.id.buttonN, R.id.buttonO,
                R.id.buttonP, R.id.buttonQ, R.id.buttonR, R.id.buttonS, R.id.buttonT,
                R.id.buttonU, R.id.buttonV, R.id.buttonW, R.id.buttonX, R.id.buttonY, R.id.buttonZ
        };
        for (int i = 0; i < buttonIds.length; i++) {
            Button b = findViewById(buttonIds[i]);
            b.setOnClickListener(this);
        }

        del = findViewById(R.id.delete);
        del.setOnClickListener(this);

        enter = findViewById(R.id.enter);
        enter.setOnClickListener(this);
    }

    private String buttonLetter() {
        StringBuilder letter = new StringBuilder();
        for (int i = 0; i < letsInRow; i++) {
            letter.append(letterBox[i].getText().toString());
        }
        return letter.toString();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.delete) {
            Button del = (Button) v;
            String delText = del.getText().toString();
            if (delText.equals("Delete") && letsInRow > 0) {
                letterBox[--letsInRow].setText("");
            } else {
                for (int i = 0; i < letterBox.length; i++) {
                    EditText editText = letterBox[i];
                    if (editText.getText().toString().isEmpty() && letsInRow < 5) {
                        editText.setText(delText);
                        if (i + 1 < letterBox.length) {
                            letterBox[i + 1].requestFocus();
                        }
                        letsInRow++;
                        break;
                    }
                }
            }
        } else if (v.getId() == R.id.enter) {
            Button enter = (Button) v;
            String enterText = enter.getText().toString();
            if (enterText.equals("Enter")) {
                if (letsInRow == 5) {
                    String playerGuess = buttonLetter();
                    char[] guess = playerGuess.toCharArray();
                    String answer = "MOCHA";
                    char[] solution = answer.toCharArray();
                    int count = 0;
                    for (int i = 0; i < guess.length; i++) {
                        if (guess[i] == solution[i]) {
                            letterBox[count].setBackgroundResource(R.color.green);
                        } else if (answer.contains(String.valueOf(guess[i]))) {
                            letterBox[count].setBackgroundResource(R.color.purple);
                        } else {
                            letterBox[count].setBackgroundResource(R.color.red);
                        }
                        count++;
                    }
                    letsInRow = 0;
                }
            }
        } else {
            Button b = (Button) v;
            String bText = b.getText().toString();
            if (!bText.isEmpty()) {
                for (int i = 0; i < letterBox.length; i++) {
                    EditText letters = letterBox[i];
                    if (letters.getText().toString().isEmpty() && letsInRow < 5) {
                        letters.setText(bText);
                        if (i + 1 < letterBox.length) {
                            letterBox[i + 1].requestFocus();
                        }
                        letsInRow++;
                        break;
                    }
                }
            }

        }
    }
}

