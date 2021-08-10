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
        //1,2,3,4,5,6
        while (jj != 6){
            lotto.add(LottoNumber.valueOf(jj));
            jj++;
        }

        assertEquals(lotto.get(0).getNumber(),1);
        assertEquals(lotto.get(1).getNumber(),2);
        assertEquals(lotto.get(2).getNumber(),3);
        assertEquals(lotto.get(3).getNumber(),4);
        assertEquals(lotto.get(4).getNumber(),5);
        assertEquals(lotto.get(5).getNumber(),6);

        List<Integer> setLotto = new ArrayList<>();
        for (LottoNumber lottoNum : lotto) {
            setLotto.add(lottoNum.getNumber());
        }

        System.out.println(setLotto);
    }

    @Test
    void 잘못된로또번호생성() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> new LottoNumber(0));
        String message = exception.getMessage();

        assertEquals("LottoNumber가 유효하지 않습니다.",message);

    }
}