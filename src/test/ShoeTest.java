package test;

import com.company.Card;
import com.company.Deck;
import com.company.Shoe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoeTest {

    @Test
    public void testShoe_size8() {
        Shoe shoe = new Shoe(8);
        assertEquals(52 * 8, shoe.count());
    }

    @Test
    public void testShoe_size1() {
        Shoe shoe = new Shoe(1);
        assertEquals(52 * 1, shoe.count());
    }

    @Test
    public void testDraw() {
        Shoe shoe = new Shoe(8);
        Card drawOne = shoe.draw();
        Card drawTwo = shoe.draw();
        assertNotEquals(drawOne, drawTwo);
    }

    @Test
    public void testEmpty() {
        Shoe shoe = new Shoe(8);
        for (int i = 0; i < 52 * 8; i++) {
            Card draw = shoe.draw();
        }
        assertEquals(null, shoe.draw());
    }


}