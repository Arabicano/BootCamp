package me.gimun;

import me.gimun.domain.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultLottoNumGeneratorTest {

    @Test
    void 로또생성() {

        List<LottoNumber> lotto = new DefaultLottoNumGenerator().generate();

        assertNotNull(lotto.get(0).getNumber());

        List<Integer> setLotto = new ArrayList<>();
        for (LottoNumber lottoNum : lotto) {
            setLotto.add(lottoNum.getNumber());
        }

        System.out.println(setLotto);
    }
}