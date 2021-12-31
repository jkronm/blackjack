package test;

import com.company.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void startAmount() {
        Player newPlayer = new Player(-15);
        assertEquals("$0", newPlayer.getAmountString());
    }

    @Test
    void setAmount() {
        Player newPlayer = new Player();
        assertEquals("$0", newPlayer.getAmountString());
    }

    @Test
    void setAmountWithMoney() {
        Player newPlayer = new Player(1000);
        assertEquals("$1000", newPlayer.getAmountString());
    }

    @Test
    void setAmountWithMoneyNegative() {
        Player newPlayer = new Player(-10);
        assertEquals("$0", newPlayer.getAmountString());
    }

    @Test
    void deposit() {
        Player newPlayer = new Player();
        newPlayer.deposit(500);
        assertEquals("$500", newPlayer.getAmountString());
    }

    @Test
    void withdraw() {
        Player newPlayer = new Player(1000);
        newPlayer.withdraw(250);
        assertEquals("$750", newPlayer.getAmountString());
    }

    @Test
    void withdrawNegative() {
        Player newPlayer = new Player(0);
        newPlayer.withdraw(15);
        assertEquals("$0", newPlayer.getAmountString());
    }

    @Test
    void getAmountString() {
        Player newPlayer = new Player(1000);
        newPlayer.getAmountString();
        assertEquals("$1000", newPlayer.getAmountString());
    }

    @Test
    void getAmountInt() {
        Player newPlayer = new Player(1000);
        newPlayer.getAmountInt();
        assertEquals(1000, newPlayer.getAmountInt());
    }

}