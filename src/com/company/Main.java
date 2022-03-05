package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String reply = "";
        int repInt = 0;

        // new player
        Player player = new Player();
        System.out.println("How much money do you have? (enter integer)");
        reply = sc.nextLine();
        try {
            repInt = Integer.parseInt(reply);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Must be an integer.");
        } //////////////// handler just outputs error and fixes nothing for now.

        player.setAmount(repInt);

        // pick a game
        do {
            System.out.println("Pick a game (i.e. 1, 2, or 3):");
            System.out.println("1) BlackJack");
            System.out.println("2) Baccarat");
            System.out.println("3) Place Holder");
            reply = sc.nextLine();
            try {
                repInt = Integer.parseInt(reply);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Must be an integer.");
            } //////////////// handler just outputs error and fixes nothing for now.

            switch (repInt) {
                case 1 : {
                    BlackJackTable newGame = new BlackJackTable();
                    newGame.playGame(player);
                    break;
                }
                case 2 : {
                    Baccarat newGame = new Baccarat();
                    newGame.playGame(player);
                    break;
                }
                case 3 : System.out.println("No game available yet."); break;
            }
            System.out.println("Would you like to play a different new game? (N/Y)");
            reply = sc.nextLine();
        } while (reply.equalsIgnoreCase("Y"));

        //// play blackjack
        //do {
        //BlackJackTable newGame = new BlackJackTable();
        //newGame.playGame(player);
        //System.out.println("Would you like to play a whole new game? (N/Y)");
        //    reply = sc.nextLine();
        //}
        //while (reply.equalsIgnoreCase("Y"));
    }
}
