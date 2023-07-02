package com.example.cs2340Project;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.Assert.*;

public class blackJackFunctionalityTests {

    @Test
    public void testDeckCreation() {
        blackJackDeck myDeck = new blackJackDeck();
        assertEquals(myDeck, new blackJackDeck());
    }

    @Test
    public void testShuffle() {
        blackJackDeck myDeck = new blackJackDeck();
        myDeck.makeDeck();
        blackJackDeck testDeck = myDeck;
        myDeck.shuffle();
        assertNotEquals(testDeck.getCard(), myDeck.getCard());
    }

    @Test
    public void testCardToString() {
        blackJackCard card1 = new blackJackCard(8, 'H');
        blackJackCard card2 = new blackJackCard(9, 'D');
        blackJackCard card3 = new blackJackCard(10, 'S');
        blackJackCard card4 = new blackJackCard(10, 'C', "Jack");
        blackJackCard card5 = new blackJackCard(10, 'H', "Queen");
        blackJackCard card6 = new blackJackCard(10, 'D', "King");
        blackJackCard card7 = new blackJackCard(11, 'S', "Ace");
        blackJackCard card8 = new blackJackCard(8,'H');
        System.out.println(card1);
        System.out.println(card2);
        System.out.println(card3);
        System.out.println(card4);
        System.out.println(card5);
        System.out.println(card6);
        System.out.println(card7);

        String test = card1.toString();
        String test2 = card8.toString();
        String test3 = card3.toString();

        assertEquals(test, test2);
        assertNotEquals(test, test3);
    }

    @Test
    public void testClearHand() {
        blackJackDeck myDeck = new blackJackDeck();
        blackJackPlayer me = new blackJackPlayer();
        blackJackDealer dealer = new blackJackDealer();

        dealer.hit(myDeck);
        me.hit(myDeck);
        dealer.hit(myDeck);
        me.hit(myDeck);

        me.printHand();
        dealer.printHand();
        me.clearHand(myDeck);
        dealer.clearHand(myDeck);

        dealer.hit(myDeck);
        me.hit(myDeck);
        dealer.hit(myDeck);
        me.hit(myDeck);

        me.printHand();
        dealer.printHand();
        me.clearHand(myDeck);
        dealer.clearHand(myDeck);

        dealer.hit(myDeck);
        me.hit(myDeck);
        dealer.hit(myDeck);
        me.hit(myDeck);

        me.printHand();
        dealer.printHand();
        me.clearHand(myDeck);
        dealer.clearHand(myDeck);

        assertEquals(me.getHand().size(), 0);
        assertEquals(dealer.getHand().size(), 0);
    }
    @Test
    public void testHitDealerHand() {
        blackJackDeck myDeck = new blackJackDeck();
        blackJackDealer dealer = new blackJackDealer();
        myDeck.makeDeck();
        dealer.hit(myDeck);
        ArrayList<blackJackCard> test = dealer.getHand();

        dealer.dealerHit(myDeck);

        test = dealer.getHand();
        assertEquals(test.size(), 2);
    }

    @Test
    public void testFunctionality() throws IOException {
        Scanner scan = new Scanner(System.in);
        blackJackDeck myDeck = new blackJackDeck();
        blackJackPlayer me = new blackJackPlayer();
        blackJackDealer dealer = new blackJackDealer();
        int money = 500;
        int bet = 0;
        String cont = "y";
        String hit = "n";
        while ("y".equals(cont)) {
            bet = scan.nextInt();
            dealer.hit(myDeck);
            me.hit(myDeck);
            dealer.hit(myDeck);
            me.hit(myDeck);
            while (me.getHandSum() < 21 & hit.equals("y")) {
                Runtime.getRuntime().exec("cls");
                dealer.printShownCard();
                System.out.println();
                me.printHand();
                System.out.println();
                System.out.println("Hit?");
                hit = scan.nextLine();
                if ("y".equals(hit)) {
                    me.hit(myDeck);
                }
            }
            dealer.hit(myDeck);
            Runtime.getRuntime().exec("cls");
            dealer.printHand();
            me.printHand();
            int won = dealer.playerWin(me);
            if (won == 2) {
                System.out.println("You won!");
            } else if (won == 1) {
                System.out.println("Tie");
            } else {
                System.out.println("Loss :(");
            }
            System.out.println();
            System.out.println("Play again?");
            cont = scan.nextLine();
            Runtime.getRuntime().exec("cls");
        }
    }

    @Test
    public void testPlayerHandSum() {
        blackJackDeck deck = new blackJackDeck();
        deck.makeDeck();
        blackJackPlayer player = new blackJackPlayer();

        player.hit(deck);
        player.hit(deck);

        ArrayList<blackJackCard> hand = player.getHand();

        hand.get(0).setValue(5);
        hand.get(1).setValue(10);

        assertEquals(15,player.getHandSum());
    }

    @Test
    public void testPlayerWin() {
        //player win
        blackJackDeck deck = new blackJackDeck();
        deck.makeDeck();

        blackJackPlayer player = new blackJackPlayer();
        blackJackDealer dealer = new blackJackDealer();

        player.hit(deck);
        dealer.hit(deck);
        player.hit(deck);
        dealer.hit(deck);

        ArrayList<blackJackCard> playerHand = player.getHand();
        ArrayList<blackJackCard> dealerHand = dealer.getHand();

        playerHand.get(0).setValue(10);
        playerHand.get(1).setValue(10);

        dealerHand.get(0).setValue(10);
        dealerHand.get(1).setValue(5);

        assertEquals(dealer.playerWin(player), 2);
    }

    @Test
    public void testGetHand() {
        blackJackPlayer player = new blackJackPlayer();
        ArrayList<blackJackCard> test = new ArrayList<>();

        assertEquals(player.getHand(), test);

        blackJackDeck deck = new blackJackDeck();
        deck.makeDeck();

        player.hit(deck);
        assertEquals(player.getHand().size(), 1);
    }

    @Test
    public void testGetShownCard() {
        blackJackDeck deck = new blackJackDeck();
        deck.makeDeck();

        blackJackDealer dealer = new blackJackDealer();
        dealer.hit(deck);
        dealer.hit(deck);

        ArrayList<blackJackCard> hand = dealer.getHand();
        assertEquals(dealer.getShownCard(),hand.get(0));
    }

    @Test
    public void testHit() {
        blackJackDeck deck = new blackJackDeck();
        deck.makeDeck();

        blackJackPlayer player = new blackJackPlayer();
        player.hit(deck);
        player.hit(deck);
        player.hit(deck);

        ArrayList<blackJackCard> test = player.getHand();

        test.get(0).setValue(4);
        test.get(1).setValue(5);
        test.get(2).setValue(3);

        assertEquals(player.getHand().size(), 3);
        assertEquals(player.getHandSum(), 12);
    }

    @Test
    public void testPrintHand() {
        blackJackDeck deck = new blackJackDeck();
        deck.makeDeck();

        blackJackPlayer player = new blackJackPlayer();
        blackJackDealer dealer = new blackJackDealer();

        player.hit(deck);
        dealer.hit(deck);
        player.hit(deck);
        dealer.hit(deck);

        ArrayList<blackJackCard> pHand = player.getHand();
        ArrayList<blackJackCard> dHand = dealer.getHand();

        /* pHand and dHand represent the player's and dealer's hand, so turning them
        * to strings and comparing them will act like printing their respective hands like in
        * testCardToString */
        assertNotEquals(pHand.toString(), null);
        assertNotEquals(dHand.toString(), null);
        player.printHand();
        dealer.printHand();
    }
}
