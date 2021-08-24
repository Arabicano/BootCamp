package me.gimun;

import java.util.Optional;

public class LottoStore {
    private int lotto_amount = 1000;
    private int lottoCnt;
    private int balance;

    public LottoStore(int amount) {
        if(amount < this.lotto_amount){
            throw new IllegalArgumentException("입력하신 금액이 로또 금액보다 작습니다.");
        }

        this.lottoCnt = amount/this.lotto_amount;
        this.balance = amount%this.lotto_amount;
    }

    public LottoStore(int amount, LottoStoreIf p) {
        if(amount < this.lotto_amount){
            throw new IllegalArgumentException("입력하신 금액이 로또 금액보다 작습니다.");
        }

        Optional.ofNullable(p).ifPresent(
                (val)-> this.lotto_amount = val.LottoDisCount(amount)
        );

        this.lottoCnt = amount/this.lotto_amount;
        this.balance = amount%this.lotto_amount;
    }

    public int getLottoCnt() {
        return lottoCnt;
    }

    public int getBalance() {
        return balance;
    }

}
