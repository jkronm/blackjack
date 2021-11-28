package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String reply = "";

        do {
        BlackJackTable newGame = new BlackJackTable();
        newGame.playGame();
        System.out.println("Would you like to play a whole new game? (N/Y)");
        reply = sc.nextLine();
        }
        while (reply.equalsIgnoreCase("Y"));
    }
}
