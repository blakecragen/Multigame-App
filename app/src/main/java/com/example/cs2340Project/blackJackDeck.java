package com.example.cs2340Project;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class blackJackDeck {
    /**
     * Deck of cards being played with.
     */
    private ArrayDeque<blackJackCard> deck;

    /**
     * Number of decks to play with.
     */
    private int numDecks;

    /**
     * Constructor that takes in the number of decks to work with.
     *
     * @param numDecks The number of decks to play with.
     */
    public blackJackDeck(int numDecks) {
        this.numDecks = numDecks;
        this.deck = new ArrayDeque<blackJackCard>();
        this.makeDeck();
    }

    /**
     * Constructor that defaults to working with 2 decks.
     */
    public blackJackDeck() {
        this(2);
    }

    /**
     * Helper method to add all of the individual cards to the deck.
     */
    public void makeDeck() {
        char curSuite;
        for (int i = 0; i < 4; ++i) {
            for (int k = 2; k < 15; ++k) {
                for (int j = 0; j < numDecks; ++j) {
                    switch (i) {
                        case 0:
                            curSuite = 'S';
                            break;
                        case 1:
                            curSuite = 'C';
                            break;
                        case 2:
                            curSuite = 'H';
                            break;
                        default:
                            curSuite = 'D';
                    }
                    switch (k) {
                        case 11:
                            deck.addLast(new blackJackCard(10, curSuite, "jack"));
                            break;
                        case 12:
                            deck.addLast(new blackJackCard(10, curSuite, "queen"));
                            break;
                        case 13:
                            deck.addLast(new blackJackCard(10, curSuite, "king"));
                            break;
                        case 14:
                            deck.addLast(new blackJackCard(11, curSuite, "ace"));
                            break;
                        default:
                            deck.addLast(new blackJackCard(k, curSuite));
                    }
                }
            }
        }
    }

    public void shuffle() {
        blackJackCard[] asArray = new blackJackCard[deck.size()];
        int fullSize = deck.size();
        for (int i = 0; i < fullSize; ++i) {
            asArray[i] = deck.removeFirst();
        }
        ArrayList<blackJackCard> shuffleArray = new ArrayList<blackJackCard>();
        shuffleArray.addAll(Arrays.asList(asArray));
        Random random = new Random();
        deck.clear();
        for (int i = 0; i <fullSize; ++i) {
            deck.addLast(shuffleArray.remove(random.nextInt(shuffleArray.size())));
        }
    }
}
