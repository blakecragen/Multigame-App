package com.example.cs2340Project;

import java.util.ArrayList;

public class TicTacToeComputerMovement {

    int computerPiece;

    /**
     * Constructor for computer movement.
     *
     * @param playerPiece Make computer opposite player piece.
     */
    public TicTacToeComputerMovement(int playerPiece) {
        if (playerPiece == 1) {
            computerPiece = 2;
        } else {
            computerPiece = 1;
        }
    }

    /**
     * Checks if the other player has 2 pieces "in a row"
     *
     * @param board Current board being played with.
     * @return 0 If there is no potential win, where to place if there is.
     */
    public int checkAlmostWin(int[][] board, int checkFor) {
        // Checks rows for almost wins
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == checkFor && board[i][1] == checkFor && board[i][2] == 0) {
                return 3 * i + 3;
            }
            if (board[i][0] == checkFor && board[i][2] == checkFor && board[i][1] == 0) {
                return 3 * i + 2;
            }
            if (board[i][2] == checkFor && board[i][1] == checkFor && board[i][0] == 0) {
                return 3 * i + 1;
            }
        }
        if ((board[0][0] == checkFor && board[1][1] == checkFor && board[2][2] == 0) ||
                (board[0][0] == checkFor && board[2][2] == checkFor && board[1][1] == 0) ||
                (board[1][1] == checkFor && board[2][2] == checkFor && board[0][0] == 0)) {
            return 9;
        }
        if ((board[0][2] == checkFor && board[1][1] == checkFor && board[2][0] == 0) ||
                (board[0][2] == checkFor && board[2][0] == checkFor && board[1][1] == 0) ||
                (board[1][1] == checkFor && board[2][0] == checkFor && board[0][2] == 0)) {
            return 7;
        }
        return 0;
    }

    public ArrayList<Integer> getPossibleMoves(int[][] board, TicTacToeFunctionality game) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (game.canPlacePiece(3 * i + 1 + j, null)) {
                    out.add(3 * i + 1 + j);
                }
            }
        }
        return out;
    }
}
