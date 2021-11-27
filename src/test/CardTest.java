package test;

import com.company.Card;
import com.company.Suit;
import com.company.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    public void testToString() {
        Card card = new Card(Value.KING, Suit.HEART);
        assertEquals("KING, HEART", card.toString());
    }

    @Test
    public void testEquals() {
        Card drawOne = new Card(Value.KING, Suit.SPADE);
        Card drawTwo = new Card(Value.KING, Suit.SPADE);
        assertEquals(drawOne, drawTwo);
    }
}