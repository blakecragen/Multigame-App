package com.example.cs2340Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class blackJackGame extends AppCompatActivity {
    private Button restartButton;
    private Random random;
    private int playerScore;
    private int dealerScore;
    private Button homeButton;
    private TextView name;
    private ImageView[] pCards;
    private ImageView[] dCards;
    private int cardIndex;
    private int[] deck;
    private int playerCardIndex;
    private int dealerCardIndex;
    private boolean dealButtonClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_game);

        restartButton = findViewById(R.id.bj_restart_button);
        random = new Random();
        name = findViewById(R.id.blackJackConfigScreen);
        String username = getIntent().getStringExtra("keyname");
        name.setText(username);

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
                Intent intent = new Intent(blackJackGame.this, MainActivity.class);
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
                R.drawable.spades_ace_simple,
                R.drawable.spades_jack,
                R.drawable.spades_queen,
                R.drawable.spades_king,
                R.drawable.clubs_2,
                R.drawable.clubs_3,
                R.drawable.clubs_4,
                R.drawable.clubs_5,
                R.drawable.clubs_6,
                R.drawable.clubs_7,
                R.drawable.clubs_8,
                R.drawable.clubs_9,
                R.drawable.clubs_10,
                R.drawable.clubs_ace,
                R.drawable.clubs_jack,
                R.drawable.clubs_queen,
                R.drawable.clubs_king,
                R.drawable.diamonds_2,
                R.drawable.diamonds_3,
                R.drawable.diamonds_4,
                R.drawable.diamonds_5,
                R.drawable.diamonds_6,
                R.drawable.diamonds_7,
                R.drawable.diamonds_8,
                R.drawable.diamonds_9,
                R.drawable.diamonds_10,
                R.drawable.diamonds_ace,
                R.drawable.diamonds_jack,
                R.drawable.diamonds_queen,
                R.drawable.diamonds_king,
                R.drawable.hearts_2,
                R.drawable.hearts_3,
                R.drawable.hearts_4,
                R.drawable.hearts_5,
                R.drawable.hearts_6,
                R.drawable.hearts_7,
                R.drawable.hearts_8,
                R.drawable.hearts_9,
                R.drawable.hearts_10,
                R.drawable.hearts_ace,
                R.drawable.hearts_jack,
                R.drawable.hearts_queen,
                R.drawable.hearts_king
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
        if (!dealButtonClicked) {
            pCards[playerCardIndex].setVisibility(View.VISIBLE);
            pCards[playerCardIndex].setImageResource(deck[playerCardIndex]);

            playerCardIndex++;
        } else {
            dCards[dealerCardIndex].setVisibility(View.VISIBLE);
            dCards[dealerCardIndex].setImageResource(deck[playerCardIndex]);

            dealerCardIndex++;
        }

        dealButtonClicked = !dealButtonClicked;
    }

    private void shuffleDeck() {
        for (int i = deck.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }
}

