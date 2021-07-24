package me.gimun;

public class BuyLotto {
    private int lottoCnt;
    private int balance;

    public BuyLotto(int amount) {
        if(amount < 1000){
            throw new IllegalArgumentException();
        }

        this.lottoCnt = (int) Math.floor(amount/1000);
        this.balance = amount%1000;
    }

    public int getLottoCnt() {
        return lottoCnt;
    }

    public int getBalance() {
        return balance;
    }


}
