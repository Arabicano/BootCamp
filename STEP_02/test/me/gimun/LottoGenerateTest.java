package me.gimun;

import me.gimun.domain.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoGenerateTest {

    @Test
    void 로또생성() {

        List<LottoNumber> lotto = LottoGenerate.generate();

        assertNotNull(lotto);

        for (LottoNumber lottoNum : lotto) {

            System.out.println(lottoNum.getNumber());
        }
    }
}