package com.example.cs2340Project;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class BlackJackFunctionalityTests {

    @Test
    public void testDeckCreation() {
        BlackJackDeck myDeck = new BlackJackDeck();
    }

    @Test
    public void testShuffle() {
        BlackJackDeck myDeck = new BlackJackDeck();
        myDeck.shuffle();
    }

    @Test
    public void testCardToString() {
        BlackJackCard card1 = new BlackJackCard(8, 'H');
        BlackJackCard card2 = new BlackJackCard(9, 'D');
        BlackJackCard card3 = new BlackJackCard(10, 'S');
        BlackJackCard card4 = new BlackJackCard(10, 'C', "Jack");
        BlackJackCard card5 = new BlackJackCard(10, 'H', "Queen");
        BlackJackCard card6 = new BlackJackCard(10, 'D', "King");
        BlackJackCard card7 = new BlackJackCard(11, 'S', "Ace");
        System.out.println(card1);
        System.out.println(card2);
        System.out.println(card3);
        System.out.println(card4);
        System.out.println(card5);
        System.out.println(card6);
        System.out.println(card7);
    }

    @Test
    public void testClearHand() {
        BlackJackDeck myDeck = new BlackJackDeck();
        BlackJackPlayer me = new BlackJackPlayer();
        BlackJackDealer dealer = new BlackJackDealer();

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
    }

    @Test
    public void testHitDealerHand() {
        BlackJackDeck myDeck = new BlackJackDeck();
        BlackJackDealer dealer = new BlackJackDealer();
    }

    @Test
    public void testFunctionality() throws IOException {
        Scanner scan = new Scanner(System.in);
        BlackJackDeck myDeck = new BlackJackDeck();
        BlackJackPlayer me = new BlackJackPlayer();
        BlackJackDealer dealer = new BlackJackDealer();
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
}
