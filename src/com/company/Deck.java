package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();

        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(value, suit));
            }
        }
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public int count() {
        return cards.size();
    }

    public Card draw() {
        return cards.remove(0);
    }

}
