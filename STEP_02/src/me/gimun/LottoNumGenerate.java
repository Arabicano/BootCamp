package me.gimun;

import me.gimun.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface LottoNumGenerate {
    int LOTTO_SIZE = 6;

    default List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.getCACHE());
        Collections.shuffle(lottoNumbers);

        List<LottoNumber> generatedNumber = lottoNumbers.subList(0, LOTTO_SIZE);
        generatedNumber.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return generatedNumber;
    }
}
