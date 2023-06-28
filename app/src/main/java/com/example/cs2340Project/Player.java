package com.example.cs2340Project;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

public class Player {

    //singleton design pattern
    private static Player myPlayer;
    private static int playerLives = 3;
    private static char playerSprite = 'f';
    private static int score = 0;
    private static String playerName = "Anon";
    private Player(){}
    public static synchronized Player getInstance(){
        if (myPlayer == null) {
            myPlayer = new Player();
        }
        return myPlayer;
    }
    public int getPlayerLives() {
        return playerLives;
    }

    public void removeLife() {
//        if(playerLives == 3){
//            ImageView imgView = (ImageView) findViewById(R.id.life3);
//            imgView.setVisibility(View.VISIBLE);
//        }
//        else if(playerLives == 2){
//            ImageView imgView = (ImageView)findViewById(R.id.life2);
//            imgView.setVisibility(View.VISIBLE);
//        }
//        else if(playerLives == 1){
//            ImageView imgView = (ImageView)findViewById(R.id.life1);
//            imgView.setVisibility(View.VISIBLE);
//        }
                this.playerLives--;

        if(this.playerLives == 0){
            //signal game over
        }
    }

    public char getPlayerSprite() {
        return playerSprite;
    }
    public void setPlayerSprite( char playerSprite){
        myPlayer.playerSprite = playerSprite;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName){
        myPlayer.playerName = playerName;
    }
}
