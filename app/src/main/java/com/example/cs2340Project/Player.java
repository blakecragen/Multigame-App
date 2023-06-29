package com.example.cs2340Project;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
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

    public void removeLife(ImageView imgView) {
           imgView.setVisibility(View.INVISIBLE);
                myPlayer.playerLives--;

        if(myPlayer.playerLives == 0){
            //signal game over
        }
    }

    public void addLife(ImageView imgView) {
        imgView.setVisibility(View.VISIBLE);
        myPlayer.playerLives++;
    }

    public char getPlayerSprite() {
        return playerSprite;
    }
    public void setPlayerSprite(char playerSprite){
        myPlayer.playerSprite = playerSprite;
    }

    public void setSpriteImage(ImageView sprite){
            if(myPlayer.playerSprite == ('p')){
                sprite.setImageResource(R.drawable.panda);
            }
            else if(myPlayer.playerSprite == ('t')){
                sprite.setImageResource(R.drawable.turtle);
            }
            else {
                sprite.setImageResource(R.drawable.fox);
            }
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
