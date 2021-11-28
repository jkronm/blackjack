package com.company;

public class Player {

    int money;

    public Player() {
        money = 0;
    }

    public Player(int startAmount) {
        money = startAmount;
    }

    public void setAmount(int set) {
        money = set;
    }

    public void deposit(int dep) {
        money += dep;
    }

    public void withdraw(int withd) {
        money -= withd;
    }

    public String getAmount() {
        return "$" + String.valueOf(money);
    }
}
