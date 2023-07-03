package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class BlackJackGame extends AppCompatActivity{
    private Button restartButton;
    private Random random;
    private int playerScore;
    private int dealerScore;
    private TextView playerScoreView;
    private TextView dealerScoreView;
    private Button homeButton;
    private TextView name;
    private ImageView[] pCards;
    private ImageView[] dCards;
    private int cardIndex;
    private int[] deck;
    private int playerCardIndex;
    private int dealerCardIndex;
    private boolean dealButtonClicked;

    private Player player;

    private BlackJackDeck myDeck;
    private BlackJackDealer dealer;
    private BlackJackPlayer ourPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ourPlayer = new BlackJackPlayer();
        myDeck = new BlackJackDeck();
        dealer = new BlackJackDealer();
        myDeck.shuffle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_game);

        player = player.getInstance();
        selectLives();

        restartButton = findViewById(R.id.bj_restart_button);
        random = new Random();
        name = findViewById(R.id.blackJackConfigScreen);
        String username = getIntent().getStringExtra("keyname");
        name.setText(username);
        playerScoreView = findViewById(R.id.player_show);
        dealerScoreView = findViewById(R.id.dealer_show);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        homeButton = findViewById(R.id.bj_game_home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlackJackGame.this, MainActivity.class);
                startActivity(intent);
            }
        });


        pCards = new ImageView[]{
                findViewById(R.id.pCard1),
                findViewById(R.id.pCard2),
                findViewById(R.id.pCard3),
                findViewById(R.id.pCard4)
        };
        dCards = new ImageView[]{
                findViewById(R.id.dCard1),
                findViewById(R.id.dCard2),
                findViewById(R.id.dCard3),
                findViewById(R.id.dCard4)
        };
        cardIndex = 0;
        deck = new int[]{
                R.drawable.spades_2,
                R.drawable.spades_3,
                R.drawable.spades_4,
                R.drawable.spades_5,
                R.drawable.spades_6,
                R.drawable.spades_7,
                R.drawable.spades_8,
                R.drawable.spades_9,
                R.drawable.spades_10,
                R.drawable.spades_jack,
                R.drawable.spades_queen,
                R.drawable.spades_king,
                R.drawable.spades_ace_simple,
                R.drawable.clubs_2,
                R.drawable.clubs_3,
                R.drawable.clubs_4,
                R.drawable.clubs_5,
                R.drawable.clubs_6,
                R.drawable.clubs_7,
                R.drawable.clubs_8,
                R.drawable.clubs_9,
                R.drawable.clubs_10,
                R.drawable.clubs_jack,
                R.drawable.clubs_queen,
                R.drawable.clubs_king,
                R.drawable.clubs_ace,
                R.drawable.diamonds_2,
                R.drawable.diamonds_3,
                R.drawable.diamonds_4,
                R.drawable.diamonds_5,
                R.drawable.diamonds_6,
                R.drawable.diamonds_7,
                R.drawable.diamonds_8,
                R.drawable.diamonds_9,
                R.drawable.diamonds_10,
                R.drawable.diamonds_jack,
                R.drawable.diamonds_queen,
                R.drawable.diamonds_king,
                R.drawable.diamonds_ace,
                R.drawable.hearts_2,
                R.drawable.hearts_3,
                R.drawable.hearts_4,
                R.drawable.hearts_5,
                R.drawable.hearts_6,
                R.drawable.hearts_7,
                R.drawable.hearts_8,
                R.drawable.hearts_9,
                R.drawable.hearts_10,
                R.drawable.hearts_jack,
                R.drawable.hearts_queen,
                R.drawable.hearts_king,
                R.drawable.hearts_ace
        };

        // Shuffle the deck
        shuffleDeck();

        for (ImageView pCard : pCards) {
            pCard.setVisibility(View.INVISIBLE);
        }
        for (ImageView dCard : dCards) {
            dCard.setVisibility(View.INVISIBLE);
        }

        playerCardIndex = 0;
        dealerCardIndex = 0;
        dealButtonClicked = false;
    }

    public void dealCards(View view) {
        dealer.hit(myDeck);
        player.hit(myDeck);
        dealer.hit(myDeck);
        player.hit(myDeck);
        int playerScore = player.getHand().get(0).getValue();
        //playerScore += player.getHand().get(1).getValue();
        int dealerScore = dealer.getHand().get(0).getValue();
        playerScoreView.setText("Player Score: " + playerScore);
        dealerScoreView.setText("Dealer Score: " + dealerScore);

        int cardIndex = 0;
        switch (player.getHand().get(0).getSuit()) {
            case 'C':
                cardIndex += 13;
                break;
            case 'H':
                cardIndex += 39;
                break;
            case 'D':
                cardIndex += 26;
                break;
        }
        cardIndex += player.getHand().get(0).getValue();
        switch (player.getHand().get(0).getType()) {
            case "Jack":
                cardIndex += 1;
                break;
            case "Queen":
                cardIndex += 2;
                break;
            case "King":
                cardIndex += 3;
                break;
            case "Ace":
                cardIndex += 4;
                break;
        }
        cardIndex -= 2;
        pCards[0].setImageResource(deck[cardIndex]);
        pCards[0].setVisibility(View.VISIBLE);


        cardIndex = 0;
        switch (player.getHand().get(1).getSuit()) {
            case 'C':
                cardIndex += 13;
                break;
            case 'H':
                cardIndex += 39;
                break;
            case 'D':
                cardIndex += 26;
                break;
        }
        cardIndex += player.getHand().get(1).getValue();
        switch (player.getHand().get(1).getType()) {
            case "Jack":
                cardIndex += 1;
                break;
            case "Queen":
                cardIndex += 2;
                break;
            case "King":
                cardIndex += 3;
                break;
            case "Ace":
                cardIndex += 4;
                break;
        }
        cardIndex -= 2;
        pCards[1].setImageResource(deck[cardIndex]);
        pCards[1].setVisibility(View.VISIBLE);


        cardIndex = 0;
        switch (dealer.getHand().get(0).getSuit()) {
            case 'C':
                cardIndex += 13;
                break;
            case 'H':
                cardIndex += 39;
                break;
            case 'D':
                cardIndex += 26;
                break;
        }
        cardIndex += dealer.getHand().get(0).getValue();
        switch (dealer.getHand().get(0).getType()) {
            case "Jack":
                cardIndex += 1;
                break;
            case "Queen":
                cardIndex += 2;
                break;
            case "King":
                cardIndex += 3;
                break;
            case "Ace":
                cardIndex += 4;
                break;
        }
        cardIndex -= 2;
        dCards[0].setImageResource(deck[cardIndex]);
        dCards[0].setVisibility(View.VISIBLE);



        /*
        if (!dealButtonClicked) {
            pCards[playerCardIndex].setVisibility(View.VISIBLE);
            pCards[playerCardIndex].setImageResource(deck[cardIndex]);
            int cardValue = getCardValue(deck[cardIndex]);
            playerScore += cardValue;
            playerScoreView.setText("Player Score: " + playerScore);
            playerCardIndex++;
            playerCardIndex++;
        } else {
            dCards[dealerCardIndex].setVisibility(View.VISIBLE);
            dCards[dealerCardIndex].setImageResource(deck[cardIndex]);
            int cardValue = getCardValue(deck[cardIndex]);
            dealerScore += cardValue;
            dealerScoreView.setText("Dealer Score: " + dealerScore);
            dealerCardIndex++;
        }

        dealButtonClicked = !dealButtonClicked;
        cardIndex++;
         */
    }

    public int getCardValue(int value) {
        if (value == R.drawable.spades_2 || value == R.drawable.clubs_2 || value == R.drawable.diamonds_2 || value == R.drawable.hearts_2) {
            return 2;
        } else if (value == R.drawable.spades_3 || value == R.drawable.clubs_3 || value == R.drawable.diamonds_3 || value == R.drawable.hearts_3) {
            return 3;
        } else if (value == R.drawable.spades_4 || value == R.drawable.clubs_4 || value == R.drawable.diamonds_4 || value == R.drawable.hearts_4) {
            return 4;
        } else if (value == R.drawable.spades_5 || value == R.drawable.clubs_5 || value == R.drawable.diamonds_5 || value == R.drawable.hearts_5) {
            return 5;
        } else if (value == R.drawable.spades_6 || value == R.drawable.clubs_6 || value == R.drawable.diamonds_6 || value == R.drawable.hearts_6) {
            return 6;
        } else if (value == R.drawable.spades_7 || value == R.drawable.clubs_7 || value == R.drawable.diamonds_7 || value == R.drawable.hearts_7) {
            return 7;
        } else if (value == R.drawable.spades_8 || value == R.drawable.clubs_8 || value == R.drawable.diamonds_8 || value == R.drawable.hearts_8) {
            return 8;
        } else if (value == R.drawable.spades_9 || value == R.drawable.clubs_9 || value == R.drawable.diamonds_9 || value == R.drawable.hearts_9) {
            return 9;
        } else if (value == R.drawable.spades_10 ||
                value == R.drawable.clubs_10 ||
                value == R.drawable.diamonds_10 ||
                value == R.drawable.hearts_10 ||
                value == R.drawable.spades_jack ||
                value == R.drawable.clubs_jack ||
                value == R.drawable.diamonds_jack ||
                value == R.drawable.hearts_jack ||
                value == R.drawable.spades_queen ||
                value == R.drawable.clubs_queen ||
                value == R.drawable.diamonds_queen ||
                value == R.drawable.hearts_queen ||
                value == R.drawable.spades_king ||
                value == R.drawable.clubs_king ||
                value == R.drawable.diamonds_king ||
                value == R.drawable.hearts_king) {
            return 10;
        } else if (value == R.drawable.spades_ace_simple || value == R.drawable.clubs_ace || value == R.drawable.diamonds_ace || value == R.drawable.hearts_ace) {
            return 11;
        }
        else {
            return 0;
        }
    }


    private void shuffleDeck () {
        for (int i = deck.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }
    private void selectLives() {
        if(player.getPlayerLives() == 0){
            Intent intent = new Intent(BlackJackGame.this, GameOverScreen.class);
            startActivity(intent);
            finish();
        }
        TextView name = findViewById(R.id.playerDataName);
        name.setText(player.getPlayerName());
        name.setTextColor(-1);
        //update the sprite image
        ImageView sprite = findViewById(R.id.sprite);
        player.setSpriteImage(sprite);
        ImageView i1 = findViewById(R.id.life1);
        ImageView i2 = findViewById(R.id.life2);
        ImageView i3 = findViewById(R.id.life3);
        player.setLives(i1,i2,i3);
    }
}
