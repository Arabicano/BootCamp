package me.gimun;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoStoreTest {
    @Test
    public void 로또구매(){
        LottoStore lottoStore = new LottoStore(5500);

        assertEquals(lottoStore.getLottoCnt(),5);
        assertEquals(lottoStore.getBalance(),500);

        System.out.println("로또 구매 장수 : " + lottoStore.getLottoCnt());
        System.out.println("잔액 : " + lottoStore.getBalance());
    }
}