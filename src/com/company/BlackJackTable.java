package com.company;

import java.util.Scanner;

public class BlackJackTable {

    Shoe shoe;
    Hand dealerHand;
    Hand playerHand;
    int betOnTable = 0;

    public BlackJackTable() {
        shoe = new Shoe();
        dealerHand = new Hand();
        playerHand = new Hand();
    }

    public void playGame(Player player) {
        Scanner sc = new Scanner(System.in);
        String reply = "";

        do {
            playRound(player);
            System.out.println("You have " + player.getAmount() + " to play with.");
            System.out.println("Next Round? (N/Y)");
            reply = sc.nextLine();
        }
        while (reply.equalsIgnoreCase("Y"));

    }

    public void playRound(Player player) {
        // Player hit/not hit multiple times
        Scanner sc = new Scanner(System.in);
        String reply = "";
        int repInt = 0;

        // place bests
        System.out.println("Place your Bet. (enter integer amount between $1 and " + player.getAmount() + ")");
        reply = sc.nextLine();
        try {
            repInt = Integer.parseInt(reply);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Must be an integer.");
        } //////////////// handler just outputs error and fixes nothing for now.
        player.withdraw(repInt);
        betOnTable += repInt;

        do {
             // deal
            playerHand.empty();
            dealerHand.empty();

            playerHand.add(shoe.draw());
            dealerHand.add(shoe.draw());
            playerHand.add(shoe.draw());
            dealerHand.add(shoe.draw());

            showTable(false);
            System.out.println("Would player like to Stand, Hit, or Double down? (S/H/D)");
            reply = sc.nextLine();
            if (reply.equalsIgnoreCase("H")){
                playerHand.add(shoe.draw());
            }
            else if (reply.equalsIgnoreCase("D")) {
                playerHand.add(shoe.draw());
                // add money handler here once you have that mechanic
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
        if (dealerHand.value() > 21  || playerHand.value() > dealerHand.value()) {
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

    private void showTable(boolean finalHand) {
        System.out.println("###################");
        System.out.println("Dealer's Hand:");
        if (!finalHand) {
            System.out.println("* " + dealerHand.cardsInHand().get(0));
        }
        if (finalHand) {
            for (Card card : dealerHand.cardsInHand()) {
                System.out.println("* " + card);
            }
        }
        System.out.println("-------------------");

        System.out.println("Player's Hand:");
        for (Card card : playerHand.cardsInHand()) {
            System.out.println("* " + card);
        }
        System.out.println("###################");
    }

    private void dealerDecision() {
        while (dealerHand.value() <= 16 || (dealerHand.isSoft() && dealerHand.value() == 17)) {
            dealerHand.add(shoe.draw());
        }
    }

}
