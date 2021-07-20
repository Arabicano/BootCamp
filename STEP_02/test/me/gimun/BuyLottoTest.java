package me.gimun;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyLottoTest {
    @Test
    public void BuyLotto(){
        BuyLotto buyLotto = new BuyLotto(5500);

        assertEquals(buyLotto.getLottoCnt(),5);
        assertEquals(buyLotto.getBalance(),500);

        System.out.println("로또 구매 장수 : " + buyLotto.getLottoCnt());
        System.out.println("잔액 : " + buyLotto.getBalance());
    }
}