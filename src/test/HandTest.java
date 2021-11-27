package test;

import com.company.Card;
import com.company.Hand;
import com.company.Suit;
import com.company.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void testValueEmpty() {
        Hand newHand = new Hand();
        assertEquals(0, newHand.value());
    }

    @Test
    void testValueOneCard() {
        Hand newHand = new Hand();
        Card newCard = new Card(Value.FOUR, Suit.CLUB);
        newHand.add(newCard);
        assertEquals(4, newHand.value());
    }

    @Test
    void testValueTwoCard() {
        Hand newHand = new Hand();
        newHand.add(new Card(Value.FOUR, Suit.CLUB));
        newHand.add(new Card(Value.NINE, Suit.HEART));
        assertEquals(13, newHand.value());
    }

    @Test
    void testValueAceEleven() {
        Hand newHand = new Hand();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.NINE, Suit.HEART));
        assertEquals(20, newHand.value());
    }

    @Test
    void testValueAceOne() {
        Hand newHand = new Hand();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.EIGHT, Suit.HEART));
        newHand.add(new Card(Value.FIVE, Suit.HEART));
        assertEquals(14, newHand.value());
    }

    @Test
    void testValueMultipleAce() {
        Hand newHand = new Hand();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        assertEquals(12, newHand.value());
    }

    @Test
    void testValueAceTen() {
        Hand newHand = new Hand();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.JACK, Suit.CLUB));
        assertEquals(21, newHand.value());
    }

    @Test
    void testValueAceVariation1() {
        Hand newHand = new Hand();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.SEVEN, Suit.CLUB));
        newHand.add(new Card(Value.SIX, Suit.CLUB));
        assertEquals(14, newHand.value());
    }

    @Test
    void testHard() {
        Hand newHand = new Hand();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.SEVEN, Suit.CLUB));
        newHand.add(new Card(Value.TEN, Suit.CLUB));
        assertFalse(newHand.isSoft());
    }

    @Test
    void testSoft() {
        Hand newHand = new Hand();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.SEVEN, Suit.CLUB));
        newHand.add(new Card(Value.TWO, Suit.CLUB));
        assertTrue(newHand.isSoft());
    }

    @Test
    void testSoftTwoAce() {
        Hand newHand = new Hand();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        assertTrue(newHand.isSoft());
    }
}