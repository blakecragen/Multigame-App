package com.example.cs2340Project;

import org.junit.Test;

public class blackJackFunctionalityTests {

    @Test
    public void testDeckCreation() {
        blackJackDeck myDeck = new blackJackDeck();
    }

    @Test
    public void testShuffle() {
        blackJackDeck myDeck = new blackJackDeck();
        myDeck.shuffle();
    }
}
