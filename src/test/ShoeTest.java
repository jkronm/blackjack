package test;

import com.company.Card;
import com.company.Deck;
import com.company.Shoe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoeTest {

    @Test
    public void testShoe() {
        Shoe shoe = new Shoe();
        assertEquals(52 * 8, shoe.count());
    }

    @Test
    public void testDraw() {
        Shoe shoe = new Shoe();
        Card drawOne = shoe.draw();
        Card drawTwo = shoe.draw();
        assertNotEquals(drawOne, drawTwo);
    }

    @Test
    public void testEmpty() {
        Shoe shoe = new Shoe();
        for (int i = 0; i < 52 * 8; i++) {
            Card draw = shoe.draw();
        }
        assertEquals(null, shoe.draw());
    }

}