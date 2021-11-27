package com.company;

public class Card {

    Value value;
    Suit suit;

    public Card(Value v, Suit s) {
        value = v;
        suit = s;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return value.toString() + ", " + suit.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Card)){
            return false;
        }

        Card c = (Card) o;

        if (c.value == this.value && c.suit == this.suit) {return true;}
        else {return false;}
    }

}
