package com.example.cs2340Project;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class TicTacToeGame extends AppCompatActivity {
    private Button restartButton;
    private Button homeButton;
    private Player player1;
    private int winner;
    private TicTacToeFunctionality game;
    private TicTacToeComputerMovement computerMovement;
    private ArrayList<Button> gameButtons;
    private boolean playerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_toe_game);

        restartButton = findViewById(R.id.t_restart_button);
        player1 = Player.getInstance();
        game = new TicTacToeFunctionality();
        computerMovement = new TicTacToeComputerMovement(game.getPlayerPiece());
        gameButtons = new ArrayList<>();
        playerTurn = true;
        winner = 0;

        initializeButtons();
        makeComputerMove();

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        homeButton = findViewById(R.id.t_home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeGame.this, MainActivity.class);
                player1.setPlayerLives(3);
                startActivity(intent);
            }
        });
    }

    private void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("grid" + (i / 3) + (i % 3), "id", getPackageName());
            Button button = findViewById(resId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    afterPlayerMove(button);
                }
            });
            gameButtons.add(button);

            button.setText("Click to select");
        }
    }

    private void restartGame() {
        game.resetBoard();
        updateBoardUI();
        playerTurn = true;
        makeComputerMove();
    }

    private void makePlayerMove(int index, Button button) {
        int row = index / 3;
        int col = index % 3;
        if (game.placePiece(row, col) != -1) {
            button.setText("X");
            button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.yellow))); // Change the clicked button's background color to yellow

            playerTurn = false;
            makeComputerMove();
        }
    }

    private void afterPlayerMove(Button button) {
        if (playerTurn) {
            int buttonIndex = gameButtons.indexOf(button);
            makePlayerMove(buttonIndex, button);
        }
    }

    private void makeComputerMove() {
        if (!playerTurn) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int compMove = computerMovement.getComputerMove(game.getBoard(), game);
                    int row = compMove / 3;
                    int col = compMove % 3;
                    game.placePiece(row, col);
                    Button button = gameButtons.get(compMove);
                    button.setText("O");
                    button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(TicTacToeGame.this, R.color.red)));

                    afterComputerMove(compMove, R.color.red);

                    winner = game.checkForWinner(); // Update the winner after the computer's move
                    if (winner != 0) {
                        handleGameEnd(winner);
                    } else {
                        playerTurn = true;
                    }
                }
            }, 200); // Delay in milliseconds before the computer moves
        }
    }

    private void afterComputerMove(int index, int colorResource) {
        Button button = gameButtons.get(index);
        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, colorResource)));
    }


    private void updateBoardUI() {
        int[][] board = game.getBoard();
        for (int i = 0; i < 9; i++) {
            Button button = gameButtons.get(i);
            int row = i / 3;
            int col = i % 3;
            int piece = board[row][col];
            if (piece == 1) {
                button.setText("X");
                button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.yellow)));
            } else if (piece == 2) {
                button.setText("O");
                button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red)));
            } else {
                button.setText("");
                button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green)));
            }
        }
    }

    private void handleGameEnd(int winner) {
        String message;
        if (winner == 1) {
            message = "Player wins!";
        } else if (winner == 2) {
            message = "Computer wins!";
        } else {
            message = "It's a draw!";
        }
        showMessageDialog(message);
        restartGame();
    }

    private void showMessageDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}

