package me.gimun;

public class LottoStore {
    private final int LOTTO_AMOUNT = Lotto.LOTTO_AMOUNT.getAmount();
    private int lottoCnt;
    private int balance;

    public LottoStore(int amount) {
        if(amount < this.LOTTO_AMOUNT){
            throw new IllegalArgumentException("입력하신 금액이 로또 금액보다 작습니다.");
        }

        this.lottoCnt = amount/this.LOTTO_AMOUNT;
        this.balance = amount%this.LOTTO_AMOUNT;
    }

    public int getLottoCnt() {
        return lottoCnt;
    }

    public int getBalance() {
        return balance;
    }


}
