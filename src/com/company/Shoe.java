package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shoe {

    List<Card> decks;

    public Shoe() {
        decks = new ArrayList<Card>();

        int deckCount = 8;

        while (deckCount > 0) {
            Deck tmpDeck = new Deck();
            for (Card tmpCard : tmpDeck.cards) {
                decks.add(tmpCard);
            }
            deckCount--;
        }

    shuffle();
    }

    private void shuffle() {
        Collections.shuffle(decks);
    }

    public int count() {
        return decks.size();
    }

    public Card draw() {
        try {
            return decks.remove(0);
        }
        catch (Exception e) {
            System.out.print("Shoe is out of cards! Please Start new Game.");
        }
        return null;
    }
}
