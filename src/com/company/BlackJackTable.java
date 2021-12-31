package com.company;

import java.util.Scanner;

public class BlackJackTable {

    Shoe shoe;
    Hand dealerHand;
    Hand playerHand; // make this a list of hands. then add run checks below against all instances of hands.
    int betOnTable = 0;
    int minimumBet = 1;

    public BlackJackTable() {
        shoe = new Shoe();
        dealerHand = new Hand();
        playerHand = new Hand();
    }

    // Initilize blackjack game. Starts with check on player funds
    public void playGame(Player player) {
        Scanner sc = new Scanner(System.in);
        String reply = "";

        if (checkEnoughMoney(minimumBet, player) == false) {  // Check if enough at start of game.
            System.out.println("Exiting table.");
            return;}

        do {
            playRound(player);
            if (checkEnoughMoney(minimumBet, player) == false) { // Check if enough money between rounds.
                System.out.println("Better luck next time!");
                reply = "N";
            }
            else {
                System.out.println("You have " + player.getAmountString() + " to play.");
                System.out.println("Start Next? (N/Y)");
                reply = sc.nextLine();
            }
        }
        while (reply.equalsIgnoreCase("Y"));

    }

    // play a round of blackjack
    public void playRound(Player player) {
        // Player hit/not hit multiple times
        Scanner sc = new Scanner(System.in);
        String reply = "";
        int repInt = 0;

        // place bests
        do {
            System.out.println("Place your Bet. (enter integer amount between $" + minimumBet + " and " + player.getAmountString() + ").");
            reply = sc.nextLine();
            try {
                repInt = Integer.parseInt(reply);
            } catch (NumberFormatException e) {
                System.out.println("Must be an integer.");
            } //////////////// handler just outputs error and fixes nothing for now.
            if (checkEnoughMoney(repInt, player) != false) {break;}
        } while (true);
        player.withdraw(repInt);
        betOnTable += repInt;

        // deal
        playerHand.empty();
        dealerHand.empty();

        playerHand.add(shoe.draw());
        dealerHand.add(shoe.draw());
        playerHand.add(shoe.draw());
        dealerHand.add(shoe.draw());

        do {
            showTable(false);
            System.out.println("Would player like to Stand, Hit, Double down, or SPlit? (S/H/D/SP)");
            reply = sc.nextLine().toUpperCase();
            switch (reply) {
                case "S" : {break;}
                case "H" : {playerHand.add(shoe.draw()); break;}
                case "D" : {
                    if (checkEnoughMoney(repInt, player) == false) {
                        System.out.println("You receive a card, but no additional bet (a HIT).");
                        playerHand.add(shoe.draw());
                    }
                    else {
                        playerHand.add(shoe.draw());
                        player.withdraw(repInt);
                        betOnTable += repInt;
                    }
                    break;
                }
                case "SP" : {
                    if (playerHand.cards.get(0).getValue() != playerHand.cards.get(0).getValue()) {
                        System.out.println("Cannot split, cards are not the same value. No additional cards or bet (a Stand).");
                        break;
                    }
                    // add this at some point
                    break;
                }
            }

            if (playerHand.value() > 21) {
                showTable(false);
                System.out.println("You Busted!");
                betOnTable = 0;
                return;
            }
        }
        while (reply.equalsIgnoreCase("H"));

        // process dealer hand
        dealerDecision();

        // show final table (with all dealer cards)
        showTable(true);

        // comparisons made win, lose, tie
        if (playerHand.value() <=21 && (dealerHand.value() > 21  || playerHand.value() > dealerHand.value())) {
            if (dealerHand.value() > 21) {
                System.out.print("Dealer Busts, ");
            }
            System.out.println("You Win!");
            player.deposit(betOnTable * 2);
            betOnTable = 0;
            return;
        }
        if (playerHand.value() == dealerHand.value()) {
            System.out.println("You Pushed the House!");
            player.deposit(betOnTable);
            betOnTable = 0;
        }
        else {
            System.out.println("You Lose!");
            betOnTable = 0;
        }
    }

    // output table results
    private void showTable(boolean finalHand) {
        System.out.println("###################");
        System.out.println("Dealer's Hand:");
        if (!finalHand) {
            System.out.print(" * " + dealerHand.cardsInHand().get(0));
        }
        if (finalHand) {
            for (Card card : dealerHand.cardsInHand()) {
                System.out.print(" * " + card);
            }
        }
        System.out.println();
        System.out.println("-------------------");

        System.out.println("Player's Hand:");
        for (Card card : playerHand.cardsInHand()) {
            System.out.print(" * " + card);
        }
        System.out.println();
        System.out.println("###################");
    }

    // process dealer actions
    private void dealerDecision() {
        while (dealerHand.value() <= 16 || (dealerHand.isSoft() && dealerHand.value() == 17)) {
            dealerHand.add(shoe.draw());
        }
    }

    // check on player funds
    private boolean checkEnoughMoney(int need, Player player) {
        if (need > player.getAmountInt()) {
            System.out.print("Not enough player funds. ");
            return false;
        }
        else {return true;}
    }

}
