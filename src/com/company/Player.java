package com.company;

public class Player {

    int money;

    public Player() {
        money = 0;
    }

    public Player(int startAmount) {
        money = Math.max(startAmount, 0);
    }

    public void setAmount(int set) {
        money = Math.max(set, 0);
    }

    public void deposit(int dep) {money += dep; }

    public void withdraw(int withd) {
        money -= withd;
        money = Math.max(money, 0); }

    public String getAmountString() {return "$" + String.valueOf(money); }

    public int getAmountInt() {
        return money;
    }

}