package com.example.cs2340Project;

import java.util.ArrayList;

public class blackJackPlayer {
    private ArrayList<blackJackCard> hand;

    /**
     * Constructor for a player object.
     */
    public blackJackPlayer() {
        hand = new ArrayList<blackJackCard>();
    }

    /**
     * Add a card to current hand.
     *
     * @param deck
     */
    public void hit(blackJackDeck deck) {
        hand.add(deck.getCard());
    }

    /**
     * Method to clear the player's hand.
     *
     * @param deck Deck of cards being played with.
     */
    public void clearHand(blackJackDeck deck) {
        for (int i = 0; i < hand.size(); ++i) {
            deck.addCardToDeck(hand.remove(i));
        }
    }

    /**
     * Gets the current sum of a player's hand.
     *
     * @return The sum of player's hand.
     */
    public int getHandSum() {
        int sum = 0;
        for (blackJackCard card: hand) {
            sum += card.getValue();
        }
        return sum;
    }

    /**
     * Returns the player's hand.
     *
     * @return The hand.
     */
    public ArrayList<blackJackCard> getHand() {
        return this.hand;
    }

    /**
     * Prints the player's hand.
     */
    public void printHand() {
        System.out.printf("Your hand:\n");
        for (blackJackCard card: hand) {
            System.out.printf("%s\n",card);
        }
    }
}
