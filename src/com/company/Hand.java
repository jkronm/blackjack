package com.company;

import java.util.ArrayList;
import java.util.List;

import static com.company.Value.ACE;

public class Hand {

    List<Card> cards;

    public Hand(){
        cards = new ArrayList<Card>();
    }

    public void add(Card c) {
        cards.add(c);
    }

    public int value() {
        int val = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            if (card.getValue() == ACE) {hasAce = true;}
            val += stringToValue(card.getValue());
        }

        if (val <= 11 && hasAce) { val += 10;}

        return val;
    }

    private int stringToValue(Value s) {
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
            case TEN : return 10;
            case JACK : return 10;
            case QUEEN : return 10;
            case KING : return 10;
        }
        return 0;
    }

    public List<Card> cardsInHand() {
        return cards;
    }

    public boolean isSoft() {
        int val = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            if (card.getValue() == ACE) {hasAce = true;}
            val += stringToValue(card.getValue());
        }

        if (val <= 11 && hasAce) {return true;}

        return false;
    }

    public void empty() {
        cards.clear();
    }

    public int sizeOfHand() {return cards.size(); }

    public Card removeAndReturnTopCard() {
        return cards.remove(cards.size() - 1);
    }
}
