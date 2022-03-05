package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJackTable {

    Shoe shoe;
    HandBLKJK dealerHand;
    List<HandBLKJK> playerHands;
    List<Integer> betOnTable;
    int minimumBet = 1;

    public BlackJackTable() {
        shoe = new Shoe(8); //shoe with 8 decks
        dealerHand = new HandBLKJK();
        playerHands = new ArrayList<HandBLKJK>();
        betOnTable = new ArrayList<Integer>();
    }

    // Initilize blackjack game. Starts with check on player funds
    public void playGame(Player player) {
        Scanner sc = new Scanner(System.in);
        String reply = "";

        if (checkEnoughMoney(minimumBet, player) == false) {  // Check if enough at start of game.
            System.out.println("Exiting table.");
            return;}

        do {
            // clear variables that may be changed between rounds
            playerHands.removeAll(playerHands);
            betOnTable.removeAll(betOnTable);

            // play game
            playRound(player);

            // check remaining funds and decide to continue to next round
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

        // place initial bests
        {
            do {
                while (true) {  // continue trying until no catch
                    try {
                        System.out.println("Place your Bet. (enter integer amount between $" + minimumBet + " and " + player.getAmountString() + ").");
                        reply = sc.nextLine();
                        repInt = Integer.parseInt(reply);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Must be an integer. Try again!");
                    }
                }
                if (checkEnoughMoney(repInt, player) != false) {
                    break;
                }
            } while (true);
            player.withdraw(repInt);
            betOnTable.add(repInt);
        }

        // initial deal
        {
        HandBLKJK firstHand = new HandBLKJK();
        playerHands.add(firstHand);

        for (HandBLKJK playerHand : playerHands) {playerHand.empty();}
        dealerHand.empty();

        for (HandBLKJK playerHand : playerHands) {
            playerHand.add(shoe.draw()); // <> replace when done testing split
            //playerHand.add(new Card(Value.TWO, Suit.CLUB)); // <> for testing split
        }
        dealerHand.add(shoe.draw());
        for (HandBLKJK playerHand : playerHands) {
            playerHand.add(shoe.draw()); // <> replace when done testing split
            //playerHand.add(new Card(Value.TWO, Suit.CLUB)); // <> for testing split
        }
        dealerHand.add(shoe.draw());
        }

        // player decide what to Stand, Hit, Double down, or SPlit
        for (int handIndex = 0; handIndex < playerHands.size(); handIndex++) {
            do {
                showTable(false);
                if (playerHands.size() > 1) {System.out.print("Hand " + (handIndex + 1) + ": ");}
                System.out.println("Would player like to Stand, Hit, Double down, or SPlit? (S/H/D/SP)");
                reply = sc.nextLine().toUpperCase();
                switch (reply) {
                    case "S": {
                        break;
                    }
                    case "H": {
                        playerHands.get(handIndex).add(shoe.draw());
                        break;
                    }
                    case "D": {
                        if (checkEnoughMoney(betOnTable.get(handIndex), player) == false) {
                            System.out.println("You receive a card, but no additional bet (a HIT).");
                            playerHands.get(handIndex).add(shoe.draw());
                        } else {
                            playerHands.get(handIndex).add(shoe.draw());
                            player.withdraw(betOnTable.get(handIndex));
                            betOnTable.set(handIndex, betOnTable.get(handIndex) * 2);
                        }
                        break;
                    }
                    case "SP": {
                        if (playerHands.get(handIndex).cards.get(0).getValue() != playerHands.get(handIndex).cards.get(1).getValue()) {
                            System.out.println("Cannot split, cards are not the same value. No additional cards or bet (a Stand).");
                            break;
                        }
                        else if (checkEnoughMoney(betOnTable.get(handIndex), player) == false) {
                            System.out.println("You receive a card for your original hand with no additional bet (a HIT).");
                            playerHands.get(handIndex).add(shoe.draw());
                        }
                        else {
                            HandBLKJK newHand = new HandBLKJK();
                            newHand.add(playerHands.get(handIndex).removeAndReturnTopCard()); // get most recent card
                            playerHands.add(newHand);
                            playerHands.get(handIndex).add(shoe.draw()); // <> replace when done testing multiple split
                            //playerHands.get(handIndex).add(new Card(Value.TWO, Suit.CLUB)); // <> for testing multiple split.
                            playerHands.get(playerHands.size() - 1).add(shoe.draw());
                            betOnTable.add(betOnTable.get(handIndex));  // places identical bet from original hand on new hand.
                            player.withdraw(betOnTable.get(handIndex));
                            break;
                        }
                    }
                }

                if (playerHands.get(handIndex).value() > 21) {
                    showTable(false);
                    System.out.println("You busted this hand!");
                    playerHands.remove(handIndex);
                    betOnTable.remove(handIndex);
                    // prevent cycle on last hand bust. don't subtract 1 from size() b/c removed hand.
                    if (handIndex == playerHands.size()) {break;}
                }
            }
            while (reply.equalsIgnoreCase("H") || reply.equalsIgnoreCase("SP"));
        }

        // process dealer hand
        dealerDecision();

        // show final table (with all dealer cards)
        showTable(true);

        // comparisons made win, lose, tie (already checked if player busted)
        for (int handIndex = 0; handIndex < playerHands.size(); handIndex++) {
            if (playerHands.size() > 1) {System.out.print("Hand " + (handIndex + 1) + ": ");}
            if (playerHands.get(handIndex).value() == dealerHand.value()) {
                System.out.println("You Pushed the House!");
                player.deposit(betOnTable.get(handIndex));
                betOnTable.set(handIndex, 0);
            }
            else if (playerHands.get(handIndex).value() <= 21 && (dealerHand.value() > 21 || playerHands.get(handIndex).value() > dealerHand.value())) {
                if (dealerHand.value() > 21) {
                    System.out.print("Dealer Busts, ");
                }
                System.out.println("You Win!");
                player.deposit(betOnTable.get(handIndex) * 2);
                betOnTable.set(handIndex, 0);
                //return; //
            }
            else {
                System.out.println("You Lose!");
                betOnTable.set(handIndex, 0);
            }
        }

        return;
    }

    // output table results
    private void showTable(boolean finalHand) {
        System.out.println("###################");
        System.out.println("Dealer:");
        System.out.print("Hand: ");
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

        System.out.print("Player's Hand:");
        for (int handIndex = 0; handIndex < playerHands.size(); handIndex++){
            System.out.println();
            System.out.print("Hand");
            if (playerHands.size() > 1) {System.out.print(" " + (handIndex + 1)); }
            System.out.print(": ");
            for (Card card : playerHands.get(handIndex).cardsInHand()) {
                System.out.print(" * " + card);
            }
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
