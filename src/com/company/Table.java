package com.company;

import java.util.Scanner;

public class Table {

    Deck deck;
    Hand dealerHand;
    Hand playerHand;

    public Table() {
        deck = new Deck();
        dealerHand = new Hand();
        playerHand = new Hand();
    }

    public void playRound() {
        // deal
        playerHand.add(deck.draw());
        dealerHand.add(deck.draw());
        playerHand.add(deck.draw());
        dealerHand.add(deck.draw());

        // Player hit/not hit multiple times
        Scanner sc = new Scanner(System.in);
        String reply = "";

        do {
            showTable(false);
            System.out.println("Would player like to Stand or Hit? (S/H)");
            reply = sc.nextLine();
            if (reply.equalsIgnoreCase("H")){
                playerHand.add(deck.draw());
            }
            if (playerHand.value() > 21) {
                showTable(false);
                System.out.println("You Busted!");
                return;
            }
        }
        while (reply.equalsIgnoreCase("H"));

        // process dealer hand
        dealerDecision();

        // show final table (with all dealer cards)
        showTable(true);

        // comparisons made win, lose, tie
        if (dealerHand.value() > 21) {
            System.out.print("Dealer Busts, You Win!");
            return;
        }
        if (playerHand.value() == dealerHand.value()) {
            System.out.println("You Pushed the House!");
        }
        else if (playerHand.value() > dealerHand.value()) {
            System.out.println("You Win!");
        }
        else {
            System.out.println("You Lose!");
        }
    }

    private void showTable(boolean finalHand) {
        System.out.println("Dealer's Hand:");
        if (!finalHand) {
            System.out.println(dealerHand.cardsInHand().get(0));
        }
        if (finalHand) {
            for (Card card : dealerHand.cardsInHand()) {
                System.out.println(card);
            }
        }
        System.out.println();

        System.out.println("Player's Hand:");
        for (Card card : playerHand.cardsInHand()) {
            System.out.println(card);
        }
    }

    private void dealerDecision() {
        while (dealerHand.value() <= 16 || (dealerHand.isSoft() && dealerHand.value() == 17)) {
            dealerHand.add(deck.draw());
        }
    }
}
