package com.example.cs2340Project;

import java.io.IOException;
import java.util.Scanner;

public class blackJackFullFunctionality {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        blackJackDeck myDeck = new blackJackDeck();
        blackJackPlayer me = new blackJackPlayer();
        blackJackDealer dealer = new blackJackDealer();
        String cont = "y";
        String hit = "n";
        while ("y".equals(cont)) {
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
