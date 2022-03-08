
package test;

import com.company.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTestBCRT {

    @Test
    void testValueEmpty() {
        HandBCRT newHand = new HandBCRT();
        assertEquals(0, newHand.value());
    }

    @Test
    void testValueOneCard() {
        HandBCRT newHand = new HandBCRT();
        Card newCard = new Card(Value.FOUR, Suit.CLUB);
        newHand.add(newCard);
        assertEquals(4, newHand.value());
    }

    @Test
    void TestValue1() {
        HandBCRT newHand = new HandBCRT();
        newHand.add(new Card(Value.FOUR, Suit.CLUB));
        newHand.add(new Card(Value.NINE, Suit.HEART));
        assertEquals(3, newHand.value());
    }

    @Test
    void TestValue2() {
        HandBCRT newHand = new HandBCRT();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.NINE, Suit.HEART));
        assertEquals(0, newHand.value());
    }

    @Test
    void TestValue3() {
        HandBCRT newHand = new HandBCRT();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.EIGHT, Suit.HEART));
        newHand.add(new Card(Value.FIVE, Suit.HEART));
        assertEquals(4, newHand.value());
    }

    @Test
    void TestValue4() {
        HandBCRT newHand = new HandBCRT();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        assertEquals(2, newHand.value());
    }

    @Test
    void TestValue5() {
        HandBCRT newHand = new HandBCRT();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.JACK, Suit.CLUB));
        assertEquals(1, newHand.value());
    }

    @Test
    void TestValue6() {
        HandBCRT newHand = new HandBCRT();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.SEVEN, Suit.CLUB));
        newHand.add(new Card(Value.SIX, Suit.CLUB));
        assertEquals(4, newHand.value());
    }

    @Test
    void testEmpty() {
        HandBCRT newHand = new HandBCRT();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.empty();
        assertTrue(newHand.cardsInHand().isEmpty());
    }

    @Test
    void sizeOfHand() {
        HandBCRT newHand = new HandBCRT();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.sizeOfHand();
        assertEquals(2, newHand.sizeOfHand());
    }

    @Test
    void removeAndReturnTopCard() {
        HandBCRT newHand = new HandBCRT();
        newHand.add(new Card(Value.ACE, Suit.CLUB));
        newHand.add(new Card(Value.TWO, Suit.HEART));
        Card test = newHand.removeAndReturnTopCard();
        Card control = new Card(Value.TWO, Suit.HEART);
        assertEquals(control, test);
        assertEquals(1, newHand.sizeOfHand());
    }
}