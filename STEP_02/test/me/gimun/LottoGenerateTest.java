package me.gimun;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoGenerateTest {

    @Test
    void 로또생성() {
        LottoGenerate lottoGenerate = new LottoGenerate();

        lottoGenerate.generate();
        List<Integer> lotto = lottoGenerate.getLotto();

        assertNotNull(lotto);

        System.out.println(lotto);
    }
}