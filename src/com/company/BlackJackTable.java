package com.company;

import java.util.Scanner;

public class BlackJackTable {

    Shoe shoe;
    Hand dealerHand;
    Hand playerHand;

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

        do {
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
            player.withdraw(repInt);  ///////////// money is withdrawn but no money is placed back into player; still building.

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
            System.out.println("Dealer Busts, You Win!");
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
