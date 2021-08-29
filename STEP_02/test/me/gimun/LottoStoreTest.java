package me.gimun;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoStoreTest {
    @Test
    public void 로또구매(){
        LottoStore lottoStoreDiscount = new LottoStore(10000,(int amount) -> amount >= 10000 ? 900 : 1000);

        assertEquals(lottoStoreDiscount.getLottoCnt(),11);
        assertEquals(lottoStoreDiscount.getBalance(),100);

        System.out.println("로또 구매 장수(할인) : " + lottoStoreDiscount.getLottoCnt());
        System.out.println("잔액(할인) : " + lottoStoreDiscount.getBalance());

        LottoStore lottoStoreNoDiscount = new LottoStore(9500);

        assertEquals(lottoStoreNoDiscount.getLottoCnt(),9);
        assertEquals(lottoStoreNoDiscount.getBalance(),500);

        System.out.println("로또 구매 장수(할인X) : " + lottoStoreNoDiscount.getLottoCnt());
        System.out.println("잔액(할인X) : " + lottoStoreNoDiscount.getBalance());


    }
}