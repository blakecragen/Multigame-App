package com.example.cs2340Project;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

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

    @Test
    public void testCardToString() {
        blackJackCard card1 = new blackJackCard(8, 'H');
        blackJackCard card2 = new blackJackCard(9, 'D');
        blackJackCard card3 = new blackJackCard(10, 'S');
        blackJackCard card4 = new blackJackCard(10, 'C', "Jack");
        blackJackCard card5 = new blackJackCard(10, 'H', "Queen");
        blackJackCard card6 = new blackJackCard(10, 'D', "King");
        blackJackCard card7 = new blackJackCard(11, 'S', "Ace");
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
}
