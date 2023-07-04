package com.example.cs2340Project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlackJackGame extends AppCompatActivity {
    private Button restartButton;
    private List<Integer> deck;
    private ImageView[] pCards;
    private ImageView[] dCards;
    private int pCardCount;
    private int dCardCount;
    private Random random;
    private int playerScore;
    private int dealerScore;
    private TextView playerScoreView;
    private TextView dealerScoreView;
    private Button homeButton;
    private TextView name;
    private int cardIndex;

    private boolean dealButtonClicked;

    private BlackJackPlayer player;

    private BlackJackDeck myDeck;
    private BlackJackDealer dealer;
    private BlackJackPlayer ourPlayer;

    private Player player1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_game);

        Button dealButton = findViewById(R.id.deal_button);
        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealButtonClicked = true;
                dealCard();
                dealButton.setEnabled(false);
            }
        });
        pCards = new ImageView[4];
        dCards = new ImageView[4];

        myDeck = new BlackJackDeck();
        dealer = new BlackJackDealer();
        ourPlayer = new BlackJackPlayer();

        myDeck.shuffle();

        initializeDeck();
        initializeViews();

        player = BlackJackPlayer.getInstance();

        restartButton = findViewById(R.id.bj_restart_button);
        random = new Random();
        name = findViewById(R.id.blackJackConfigScreen);
        String username = getIntent().getStringExtra("keyname");
        name.setText(username);
        playerScoreView = findViewById(R.id.player_show);
        dealerScoreView = findViewById(R.id.dealer_show);
        player1 = Player.getInstance();
        selectLives();
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlackJackGame.this, BlackJackInitialScreen.class);
                player1.setPlayerLives((3));
                startActivity(intent);
                finish();
            }
        });

        homeButton = findViewById(R.id.bj_game_home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlackJackGame.this, MainActivity.class);
                player1.setPlayerLives((3));
                startActivity(intent);
            }
        });

        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealButtonClicked = true;
                dealCard();
            }
        });

        Button hitButton = findViewById(R.id.hit_button);

        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dealButtonClicked) {
                    hit();
                }
            }
        });

        Button standButton = findViewById(R.id.stand_button);
        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dealButtonClicked) {
                    stand();
                }
            }
        });
    }

    private void initializeViews() {
        pCards[0] = findViewById(R.id.pCard1);
        pCards[1] = findViewById(R.id.pCard2);
        pCards[2] = findViewById(R.id.pCard3);
        pCards[3] = findViewById(R.id.pCard4);
        dCards[0] = findViewById(R.id.dCard1);
        dCards[1] = findViewById(R.id.dCard2);
        dCards[2] = findViewById(R.id.dCard3);
        dCards[3] = findViewById(R.id.dCard4);
    }

    private void initializeDeck() {
        deck = new ArrayList<>(Arrays.asList(
                R.drawable.spades_2, R.drawable.spades_3, R.drawable.spades_4, R.drawable.spades_5,
                R.drawable.spades_6, R.drawable.spades_7, R.drawable.spades_8, R.drawable.spades_9,
                R.drawable.spades_10, R.drawable.spades_jack, R.drawable.spades_queen, R.drawable.spades_king,
                R.drawable.hearts_2, R.drawable.hearts_3, R.drawable.hearts_4, R.drawable.hearts_5,
                R.drawable.hearts_6, R.drawable.hearts_7, R.drawable.hearts_8, R.drawable.hearts_9,
                R.drawable.hearts_10, R.drawable.hearts_jack, R.drawable.hearts_queen, R.drawable.hearts_king,
                R.drawable.diamonds_2, R.drawable.diamonds_3, R.drawable.diamonds_4, R.drawable.diamonds_5,
                R.drawable.diamonds_6, R.drawable.diamonds_7, R.drawable.diamonds_8, R.drawable.diamonds_9,
                R.drawable.diamonds_10, R.drawable.diamonds_jack, R.drawable.diamonds_queen, R.drawable.diamonds_king,
                R.drawable.clubs_2, R.drawable.clubs_3, R.drawable.clubs_4, R.drawable.clubs_5,
                R.drawable.clubs_6, R.drawable.clubs_7, R.drawable.clubs_8, R.drawable.clubs_9,
                R.drawable.clubs_10, R.drawable.clubs_jack, R.drawable.clubs_queen, R.drawable.clubs_king
        ));
        pCardCount = 0;
        dCardCount = 0;
        cardIndex = 0;
    }

    private void dealCard() {
        if (pCardCount < 4 && dCardCount < 4 && cardIndex < deck.size()) {
            if (pCardCount < 2) {
                // Deal two cards to the player initially
                int randomIndex = random.nextInt(deck.size()); // Generate a random index
                int card = deck.get(randomIndex);
                deck.remove(randomIndex); // Remove the dealt card from the deck
                pCards[pCardCount].setImageResource(card);
                pCards[pCardCount].setVisibility(View.VISIBLE);
                pCardCount++;
                playerScore += myDeck.getCardRank(card);
                playerScoreView.setText(String.valueOf(playerScore));
            } else if (dCardCount < 2) {
                int randomIndex = random.nextInt(deck.size());
                int card = deck.get(randomIndex);
                deck.remove(randomIndex);
                dCards[dCardCount].setImageResource(card);
                dCards[dCardCount].setVisibility(View.VISIBLE);
                dCardCount++;
                dealerScore += myDeck.getCardRank(card);
                cardIndex++;
            } else {
                int randomIndex = random.nextInt(deck.size());
                int card = deck.get(randomIndex);
                deck.remove(randomIndex);
                if (pCardCount < 4) {
                    pCards[pCardCount].setImageResource(card);
                    pCards[pCardCount].setVisibility(View.VISIBLE);
                    pCardCount++;
                    playerScore += myDeck.getCardRank(card);
                    playerScoreView.setText(String.valueOf(playerScore));
                } else if (dCardCount < 4) {
                    dCards[dCardCount].setImageResource(card);
                    dCards[dCardCount].setVisibility(View.VISIBLE);
                    dCardCount++;
                    dealerScore += myDeck.getCardRank(card);
                    dealerScoreView.setText(String.valueOf(dealerScore));
                }
                cardIndex++;
            }
        }
    }

    private void hit() {
        if (pCardCount < 4) {
            int card = deck.get(cardIndex);
            pCards[pCardCount].setImageResource(card);
            pCardCount++;
            playerScore += myDeck.getCardRank(card);
            playerScoreView.setText(String.valueOf(playerScore));
            cardIndex++;
        }
        if (playerScore > 21) {
            player1.setPlayerLives((player1.getPlayerLives())-1);
            selectLives();
            Intent intent = new Intent(BlackJackGame.this, BlackJackGame.class);
            gameOver("Player Busted!");
        }
    }

    private void stand() {
        while (dealerScore < 17 && dCardCount < 4) {
            int card = deck.get(cardIndex);
            dCards[dCardCount].setImageResource(card);
            dCardCount++;
            dealerScore += myDeck.getCardRank(card);
            dealerScoreView.setText(String.valueOf(dealerScore));
            cardIndex++;
        }
        if (dealerScore > 21) {
            gameOver("Dealer Busted!");
            Intent intent = new Intent(BlackJackGame.this, BlackJackGame.class);
            startActivity(intent);
        } else if (dealerScore > playerScore || dealerScore == playerScore) {
            player1.setPlayerLives((player1.getPlayerLives())-1);
            selectLives();
            Intent intent = new Intent(BlackJackGame.this, BlackJackGame.class);
            gameOver("Dealer Wins!");
            startActivity(intent);
        } else if (dealerScore < playerScore) {
            gameOver("Player Wins!");
            Intent intent = new Intent(BlackJackGame.this, BlackJackGame.class);
            startActivity(intent);
        }
    }

    private void gameOver(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        restartButton.setVisibility(View.VISIBLE);
    }

    private void selectLives() {
        if(player1.getPlayerLives() == 0){
            Intent intent = new Intent(BlackJackGame.this, GameOverScreen.class);
            startActivity(intent);
            finish();
        }
        TextView name = findViewById(R.id.playerDataName);
        name.setText(player1.getPlayerName());
        name.setTextColor(-1);
        //update the sprite image
        ImageView sprite = findViewById(R.id.sprite);
        player1.setSpriteImage(sprite);
        ImageView i1 = findViewById(R.id.life1);
        ImageView i2 = findViewById(R.id.life2);
        ImageView i3 = findViewById(R.id.life3);
        player1.setLives(i1,i2,i3);
    }
}