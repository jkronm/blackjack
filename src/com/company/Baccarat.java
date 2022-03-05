package com.company;

import java.util.Scanner;

import static com.company.Value.*;

public class Baccarat {

    HandBCRT dealerHand;
    HandBCRT playerHand;
    int betOnTable;
    int minimumBet = 1;
    Player player;

    /*
    Initialize game
     */
    void playGame(Player player) {
        this.player = player;
        Scanner sc = new Scanner(System.in);
        String reply = "";

        if (checkEnoughMoney(minimumBet) == false) {  //check if enough at start of game.
            System.out.println("Exiting table.");
            return;}
        do {
            //clear variables that may be changed between rounds
            playerHand.cards.clear();
            dealerHand.cards.clear();

            //play game
            playRound(player);

            //check remaining funds and decide to continue to next round
            if (checkEnoughMoney(minimumBet) == false) { //Check if enough money between rounds.
                System.out.println("Better luck next time!");
                reply = "N";
            }
            else {
                System.out.println("You have " + player.getAmountString() + " to play.");
                System.out.println("Another Round? (N/Y)");
                reply = sc.nextLine();
            }
        }
        while (reply.equalsIgnoreCase("Y"));
    }//end playGame

    /*
    play a round of Baccarat
     */
    public void playRound(Player player) {

        //HOW TO PLAY: https://www.wikihow.com/Play-Baccarat

        //Player hit/not hit multiple times
        Scanner sc = new Scanner(System.in);
        String reply = "";
        int repInt = 0;

        //place initial bests
        {
            //cash bet
            do {
                while (true) { //continue trying until no catch
                    try {
                        System.out.println("Place your Bet. (enter integer amount between $" + minimumBet + " and " + player.getAmountString() + ").");
                        reply = sc.nextLine();
                        repInt = Integer.parseInt(reply);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Must be an integer. Try again!");
                    }
                }
                if (checkEnoughMoney(repInt) != false) {
                    break;
                }
            } while (true);
            player.withdraw(repInt);
            betOnTable = repInt;

            //player or dealer bet
            System.out.println("Is your bet for the Player to win or the Bank to win? (P/B)");
            reply = sc.nextLine().toUpperCase();
            Boolean playerOrBank = true; //default "true" which is a bet on player
            switch (reply) {
                case "P" : playerOrBank = true; break;
                case "B" : playerOrBank = false; break;
            }

            //deal cards
            Shoe deck = new Shoe(1); //shoe with 1 deck so just called "deck"
            playerHand.add(deck.draw());
            dealerHand.add(deck.draw());
            playerHand.add(deck.draw());
            dealerHand.add(deck.draw());

            //show table
            showTable();

            //check for natural wins
            if (naturalWins()){return;} //round is over.

            //check for player hand 3rd card (stands on value 6 or 7)
            boolean player3rdCard = false;
            if (playerHand.value() >= 0 && playerHand.value() <= 5) {
                playerHand.add(deck.draw());
                player3rdCard = true;
            }

            //check for dealer hand 3rd card
            if (player3rdCard == false) {
                if (dealerHand.value() >= 0 && dealerHand.value() <= 5) {
                    dealerHand.add(deck.draw());
                }
            } else {
                if (playerHand.cards.get(2).value == ACE ||
                        playerHand.cards.get(2).value == NINE ||
                        playerHand.cards.get(2).value == TEN ||
                        playerHand.cards.get(2).value == JACK ||
                        playerHand.cards.get(2).value == QUEEN ||
                        playerHand.cards.get(2).value == KING) {
                    if (dealerHand.value() >= 0 && dealerHand.value() <= 3) {
                        dealerHand.add(deck.draw());
                    }
                } else if (playerHand.cards.get(2).value == EIGHT) {
                    if (dealerHand.value() >= 0 && dealerHand.value() <= 2) {
                        dealerHand.add(deck.draw());
                    }
                } else if (playerHand.cards.get(2).value == SIX ||
                        playerHand.cards.get(2).value == SEVEN) {
                    if (dealerHand.value() >= 0 && dealerHand.value() <= 6) {
                        dealerHand.add(deck.draw());
                    }
                } else if (playerHand.cards.get(2).value == FOUR ||
                        playerHand.cards.get(2).value == FIVE) {
                    if (dealerHand.value() >= 0 && dealerHand.value() <= 5) {
                        dealerHand.add(deck.draw());
                    }
                } else if (playerHand.cards.get(2).value == TWO ||
                        playerHand.cards.get(2).value == THREE) {
                    if (dealerHand.value() >= 0 && dealerHand.value() <= 4) {
                        dealerHand.add(deck.draw());
                    }
                }
            }

            //determine winner
            int playerValue = playerHand.value();
            int dealerValue = dealerHand.value();
            if (playerValue == dealerValue) {
                System.out.println("Tie! Even Money.");
                player.deposit(betOnTable);
            } else if (playerValue > dealerValue) {
                System.out.println("Player Wins!");
                player.deposit(betOnTable * 2);
            } else {
                System.out.println("Banker Wins!");
            }

            return; //end round
        }
    }//end playRound

    /*
    Show status of table
     */
    private void showTable(){
        System.out.println("#####################");
        System.out.print("Player's hand: ");
        for (Card card : playerHand.cards) {
            System.out.print(" " + card.toString());
        }
        System.out.println();
        System.out.println(" | Value: " + playerHand.value());

        for (Card card : dealerHand.cards) {
            System.out.print(" " + card.toString());
        }
        System.out.println();
        System.out.println(" | Value: " + dealerHand.value());
        System.out.println("#####################");
    }

    /*
    Check for any natural Wins (player first)
     */
    private boolean naturalWins(){
        if (playerHand.value() == 8 || playerHand.value() == 9) {
            System.out.println("Player Natural Win");
            player.deposit(betOnTable);
            betOnTable = 0;
            return true;
        }
        if (dealerHand.value() == 8 || dealerHand.value() == 9) {
            System.out.println("Dealer Natural Win");
            betOnTable = 0;
            return true;
        }
        return false;
    }

    /*
    check on player funds
     */
    private boolean checkEnoughMoney(int need) {
        if (need > player.getAmountInt()) {
            System.out.print("Not enough player funds. ");
            return false;
        }
        else {return true;}
    }//end checkEnoughMoney

}//end class
