package com.example.cs2340Project;

import java.util.ArrayList;

public class BlackJackPlayer {
    private ArrayList<BlackJackCard> hand;

    /**
     * Constructor for a player object.
     */
    public BlackJackPlayer() {
        hand = new ArrayList<>();
    }

    /**
     * Add a card to current hand.
     *
     * @param deck
     */
    public void hit(BlackJackDeck deck) {
        hand.add(deck.getCard());
    }

    /**
     * Method to clear the player's hand.
     *
     * @param deck Deck of cards being played with.
     */
    public void clearHand(BlackJackDeck deck) {
        int fullSize = hand.size();
        for (int i = 0; i < fullSize; ++i) {
            deck.addCardToDeck(hand.remove(0));
        }
    }

    /**
     * Gets the current sum of a player's hand.
     *
     * @return The sum of player's hand.
     */
    public int getHandSum() {
        int sum = 0;
        for (BlackJackCard card: hand) {
            sum += card.getValue();
        }
        return sum;
    }

    /**
     * Returns the player's hand.
     *
     * @return The hand.
     */
    public ArrayList<BlackJackCard> getHand() {
        return this.hand;
    }

    /**
     * Prints the player's hand.
     */
    public void printHand() {
        System.out.printf("Your hand:\n");
        for (BlackJackCard card: hand) {
            System.out.printf("%s\n",card);
        }
    }
}
