package test;

import com.company.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void setAmount() {
        Player newPlayer = new Player();
        assertEquals("$0", newPlayer.getAmount());
    }

    @Test
    void setAmountWithMoney() {
        Player newPlayer = new Player(1000);
        assertEquals("$1000", newPlayer.getAmount());
    }

    @Test
    void deposit() {
        Player newPlayer = new Player();
        newPlayer.deposit(500);
        assertEquals("$500", newPlayer.getAmount());
    }

    @Test
    void withdraw() {
        Player newPlayer = new Player(1000);
        newPlayer.withdraw(250);
        assertEquals("$750", newPlayer.getAmount());
    }
}