
package test;

import com.company.Card;
import com.company.HandBLKJK;
import com.company.Suit;
import com.company.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandBlkjkTest {

    @Test
    void testValueEmpty() {
        HandBLKJK newHand = new HandBLKJK();
        assertEquals(0, newHand.value());
    }

    @Test
    void testValueOneCard() {
        HandBLKJK newHand = new HandBLKJK();
        Card newCard = new Card(Value.FOUR, Suit.CLUB);
        newHand.add(newCard);
        assertEquals(4, newHand.value());
    }

    @Test
    void testValueTwoCard() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.FOUR, Suit.CLUB));
        newHand.add(new Card(Value.NINE, Suit.HEART));
        assertEquals(13, newHand.value());
    }

    @Test
    void testValueAceEleven() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.NINE, Suit.HEART));
        assertEquals(20, newHand.value());
    }

    @Test
    void testValueAceOne() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.EIGHT, Suit.HEART));
        newHand.add(new Card(Value.FIVE, Suit.HEART));
        assertEquals(14, newHand.value());
    }

    @Test
    void testValueMultipleAce() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        assertEquals(12, newHand.value());
    }

    @Test
    void testValueAceTen() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.JACK, Suit.CLUB));
        assertEquals(21, newHand.value());
    }

    @Test
    void testValueAceVariation1() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.SEVEN, Suit.CLUB));
        newHand.add(new Card(Value.SIX, Suit.CLUB));
        assertEquals(14, newHand.value());
    }

    @Test
    void testHard() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.SEVEN, Suit.CLUB));
        newHand.add(new Card(Value.TEN, Suit.CLUB));
        assertFalse(newHand.isSoft());
    }

    @Test
    void testSoft() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.SEVEN, Suit.CLUB));
        newHand.add(new Card(Value.TWO, Suit.CLUB));
        assertTrue(newHand.isSoft());
    }

    @Test
    void testSoftTwoAce() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        assertTrue(newHand.isSoft());
    }

    @Test
    void testEmpty() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.empty();
        assertTrue(newHand.cardsInHand().isEmpty());
    }

    @Test
    void sizeOfHand() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.sizeOfHand();
        assertEquals(2, newHand.sizeOfHand());
    }

    @Test
    void removeAndReturnTopCard() {
        HandBLKJK newHand = new HandBLKJK();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.TWO, Suit.HEART));
        Card test = newHand.removeAndReturnTopCard();
        Card control = new Card(Value.TWO, Suit.HEART);
        assertEquals(control, test);
        assertEquals(1, newHand.sizeOfHand());
    }
}