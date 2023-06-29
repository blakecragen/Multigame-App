package com.example.cs2340Project;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class wordleGame extends AppCompatActivity implements View.OnClickListener {
    private EditText[][] editTextFields;
    private Button enterButton;
    private Button deleteButton;
    private int currentRow;

    private String answer;
    private wordleGameFunctionality wordle;
    private Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordle_game);

        currentRow = 0;
        wordle = new wordleGameFunctionality();
        wordle.selectNewWord();
        answer = wordle.getSolution();
        player = Player.getInstance();
        TextView name = findViewById(R.id.playerDataName);
        name.setText(player.getPlayerName());
        //update the sprite image
        ImageView sprite = findViewById(R.id.sprite);
        player.setSpriteImage(sprite);

        Button toHome = findViewById(R.id.toMainActivity);
        toHome.setOnClickListener(v -> finish());

        Button restart = findViewById(R.id.wd_restart_button);
        restart.setOnClickListener(v -> {
            Intent intent = new Intent(wordleGame.this, wordleInitialScreen.class);
            startActivity(intent);
            finish();
        });

        editTextFields = new EditText[5][5];
        editTextFields[0][0] = findViewById(R.id.editText1);
        editTextFields[0][1] = findViewById(R.id.editText2);
        editTextFields[0][2] = findViewById(R.id.editText3);
        editTextFields[0][3] = findViewById(R.id.editText4);
        editTextFields[0][4] = findViewById(R.id.editText5);
        editTextFields[1][0] = findViewById(R.id.editText6);
        editTextFields[1][1] = findViewById(R.id.editText7);
        editTextFields[1][2] = findViewById(R.id.editText8);
        editTextFields[1][3] = findViewById(R.id.editText9);
        editTextFields[1][4] = findViewById(R.id.editText10);
        editTextFields[2][0] = findViewById(R.id.editText11);
        editTextFields[2][1] = findViewById(R.id.editText12);
        editTextFields[2][2] = findViewById(R.id.editText13);
        editTextFields[2][3] = findViewById(R.id.editText14);
        editTextFields[2][4] = findViewById(R.id.editText15);
        editTextFields[3][0] = findViewById(R.id.editText16);
        editTextFields[3][1] = findViewById(R.id.editText17);
        editTextFields[3][2] = findViewById(R.id.editText18);
        editTextFields[3][3] = findViewById(R.id.editText19);
        editTextFields[3][4] = findViewById(R.id.editText20);
        editTextFields[4][0] = findViewById(R.id.editText21);
        editTextFields[4][1] = findViewById(R.id.editText22);
        editTextFields[4][2] = findViewById(R.id.editText23);
        editTextFields[4][3] = findViewById(R.id.editText24);
        editTextFields[4][4] = findViewById(R.id.editText25);

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

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                EditText toBeDisabled = editTextFields[row][col];
                toBeDisabled.setFocusable(false);
                toBeDisabled.setFocusableInTouchMode(false);
            }
        }

        deleteButton = findViewById(R.id.delete);
        deleteButton.setOnClickListener(this);

        enterButton = findViewById(R.id.enter);
        enterButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.delete) {
            Button deleteButton = (Button) v;
            String deleteButtonText = deleteButton.getText().toString();
            if (deleteButtonText.equals("Delete")) {
                for (int col = 4; col >= 0; col--) {
                    EditText editText = editTextFields[currentRow][col];
                    if (!editText.getText().toString().isEmpty()) {
                        editText.setText("");
                        editText.requestFocus();
                        break;
                    }
                }
            } else {
                for (int col = 0; col < 5; col++) {
                    EditText editText = editTextFields[currentRow][col];
                    if (editText.getText().toString().isEmpty()) {
                        editText.setText(deleteButtonText);
                        if (col + 1 < 5) {
                            editTextFields[currentRow][col + 1].requestFocus();
                        }
                        break;
                    }
                }
            }
        }  else if (v.getId() == R.id.enter) {
            if (currentRow < 5 && isRowFilled(currentRow)) {
                String playerGuess = getRowCharacters(currentRow);
                // Store as lowercase
                playerGuess = playerGuess.toLowerCase();
                char[] guess = playerGuess.toCharArray();
                char[] solution = answer.toCharArray();
                int counter = 0;
                // Check if string playerGuess is found in guessList
                int valid = wordle.checkGuessValid(playerGuess);
                if (valid != -1) {
                    // Perform the desired function for the current row
                    for (int i = 0; i < guess.length; i++) {
                        if (guess[i] == solution[i]) {
                            editTextFields[currentRow][counter].setBackgroundResource(R.color.green);
                        } else if (answer.contains(String.valueOf(guess[i]))) {
                            editTextFields[currentRow][counter].setBackgroundResource(R.color.purple);
                        } else {
                            editTextFields[currentRow][counter].setBackgroundResource(R.color.red);
                        }
                        counter++;
                    }


                    // Check if the player guess matches all characters in the right position of the solution
                    boolean isCorrectGuess = true;
                    for (int i = 0; i < guess.length; i++) {
                        if (guess[i] != solution[i]) {
                            isCorrectGuess = false;
                            if(currentRow == 4) {
                                Toast.makeText(this, "You are out of tries, the correct answer was " + answer, Toast.LENGTH_SHORT).show();
                                if(player.getPlayerLives() == 3) {
                                    player.removeLife(findViewById(R.id.life1));
                                }
                                else if(player.getPlayerLives() == 2) {
                                    player.removeLife(findViewById(R.id.life2));
                                }
                                else {
                                    player.removeLife(findViewById(R.id.life3));
                                }
                                //remove life
                            }
                            break;
                        }
                    }

                    // Show toast message if the guess is correct
                    if (isCorrectGuess) {
                        Toast.makeText(this, "Congratulations! You got the right answer!", Toast.LENGTH_SHORT).show();
                    }

                    // Keep this code in the if statement
                    // Determines whether or not it should go to the next row
                    currentRow++;
                    if (currentRow < 5) {
                        editTextFields[currentRow][0].requestFocus();
                    }
                } else {
                    // Add animation
                    TextView tv = findViewById(R.id.wordleHeading);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    tv.startAnimation(animation);
                }

            }
        } else if (v.getId() == R.id.wd_restart_button) {
            recreate();
        } else {
            keyboard(v);
        }

    }

    private boolean isRowFilled(int row) {
        for (int col = 0; col < 5; col++) {
            if (editTextFields[row][col].getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String getRowCharacters(int row) {
        StringBuilder rowCharacters = new StringBuilder();
        for (int col = 0; col < 5; col++) {
            rowCharacters.append(editTextFields[row][col].getText().toString());
        }
        return rowCharacters.toString();
    }

    private void keyboard(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        if (!buttonText.isEmpty()) {
            for (int col = 0; col < 5; col++) {
                EditText editText = editTextFields[currentRow][col];
                if (editText.getText().toString().isEmpty()) {
                    editText.setText(buttonText);
                    if (col + 1 < 5) {
                        editTextFields[currentRow][col + 1].requestFocus();
                    }
                    break;
                }
            }
        }
    }
}

