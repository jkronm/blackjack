package com.company;

import java.util.ArrayList;
import java.util.List;

import static com.company.Value.ACE;

public class HandBCRT {

    List<Card> cards;

    public HandBCRT(){
        cards = new ArrayList<Card>();
    }

    public void add(Card c) {
        cards.add(c);
    }

    public int value() {  ///NEEDS UPDATEING
        int val = 0;
        for (Card card : cards) {
            val += stringToValue(card.value);
        }
        val = val % 10;
        return val;
    }

    private int stringToValue(Value s) { ///NEEDS UPDATEING
        switch (s) {
            case ACE : return 1;
            case TWO : return 2;
            case THREE : return 3;
            case FOUR : return 4;
            case FIVE : return 5;
            case SIX : return 6;
            case SEVEN : return 7;
            case EIGHT : return 8;
            case NINE : return 9;
            case TEN : return 0;
            case JACK : return 0;
            case QUEEN : return 0;
            case KING : return 0;
        }
        return 0;
    }

    public List<Card> cardsInHand() {
        return cards;
    }

    public void empty() {
        cards.clear();
    }

    public int sizeOfHand() {return cards.size(); }

    public Card removeAndReturnTopCard() {
        return cards.remove(cards.size() - 1);
    }
}
