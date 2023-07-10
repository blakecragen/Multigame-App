package com.example.cs2340Project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeFunctionalityTests {

    @Test
    public void testDeckCreation() {
        BlackJackDeck myDeck = new BlackJackDeck();
        assertEquals(myDeck, new BlackJackDeck());
    }

    @Test
    public void testShuffle() {
        BlackJackDeck myDeck = new BlackJackDeck();
        myDeck.makeDeck();
        BlackJackDeck testDeck = myDeck;
        myDeck.shuffle();
        assertNotEquals(testDeck.getCard(), myDeck.getCard());
    }

    @Test
    public void testPlayerWin() {
        //player win
        BlackJackDeck deck = new BlackJackDeck();
        deck.makeDeck();

        BlackJackPlayer player = new BlackJackPlayer();
        BlackJackDealer dealer = new BlackJackDealer();

        player.hit(deck);
        dealer.hit(deck);
        player.hit(deck);
        dealer.hit(deck);

        ArrayList<BlackJackCard> playerHand = player.getHand();
        ArrayList<BlackJackCard> dealerHand = dealer.getHand();

        playerHand.get(0).setValue(10);
        playerHand.get(1).setValue(10);

        dealerHand.get(0).setValue(10);
        dealerHand.get(1).setValue(5);

        assertEquals(dealer.playerWin(player), 2);
    }

    @Test
    public void testGetHand() {
        BlackJackPlayer player = new BlackJackPlayer();
        ArrayList<BlackJackCard> test = new ArrayList<>();

        assertEquals(player.getHand(), test);

        BlackJackDeck deck = new BlackJackDeck();
        deck.makeDeck();

        player.hit(deck);
        assertEquals(player.getHand().size(), 1);
    }

}
