package com.example.cs2340Project;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

public class Player {

    //singleton design pattern
    private static Player myPlayer;
    private Player(){}
    public static Player getInstance(){
        if (myPlayer == null) {
            synchronized (Player.class){
                if (myPlayer == null) {
                    myPlayer = new Player();
                }
            }
        }
        return myPlayer;
    }
    private int playerLives;
    private int playerSprite;
    private int score;
    private String playerName;

    public Player(int playerSprite, String playerName) {
        this.playerLives = 3;
        this.playerSprite = playerSprite;
        this.score = 0;
        this.playerName = playerName;

//        TextView name = findViewByID(R.id.playerDataName);
//        name.setText(playerName);
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

    public int getPlayerSprite() {
        return playerSprite;
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
}
