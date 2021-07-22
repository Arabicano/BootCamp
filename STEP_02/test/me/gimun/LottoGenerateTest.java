package me.gimun;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoGenerateTest {

    @Test
    void generate() {
        LottoGenerate lottoGenerate = new LottoGenerate();

        lottoGenerate.generate();
        List<Integer> lotto = lottoGenerate.getLotto();
        int bonus = lottoGenerate.getBonusNum();

        assertNotNull(lotto);
        assertNotNull(bonus);

        System.out.println(lotto);
        System.out.println(bonus);
    }
}