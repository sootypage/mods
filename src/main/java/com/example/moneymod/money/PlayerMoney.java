package com.example.moneymod.money;

public class PlayerMoney {

    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void addMoney(int amount) {
        balance += amount;
    }

    public void setBalance(int amount) {
        balance = amount;
    }
}