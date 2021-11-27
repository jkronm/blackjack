package test;

import com.company.Card;
import com.company.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    public void testDeck() {
        Deck FirstDeck = new Deck();
        assertEquals(52, FirstDeck.count());
    }

    @Test
    public void testDraw() {
        Deck FirstDeck = new Deck();
        Card drawOne = FirstDeck.draw();
        Card drawTwo = FirstDeck.draw();
        assertNotEquals(drawOne, drawTwo);
    }

}