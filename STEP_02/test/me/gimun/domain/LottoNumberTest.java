package me.gimun.domain;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @Test
    void 로또번호객체생성() {
        List<LottoNumber> lotto = new ArrayList<>();
        int jj = 0;
        while (jj != 6){
            lotto.add(LottoNumber.valueOf(jj));
            jj++;
        }

        assertNotNull(lotto);

        for (LottoNumber lottoNum : lotto) {
            System.out.println(lottoNum.getNumber());
        }
    }
}