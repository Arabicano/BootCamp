package me.gimun;

public enum Lotto {
    LOTTO_AMOUNT(1000);

    private final int amount;

    Lotto(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
