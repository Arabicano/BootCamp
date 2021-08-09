package me.gimun;

import me.gimun.domain.LottoNumber;

import java.util.*;

public class LottoGenerate {
    private static final int LOTTO_SIZE = 6;

    public static List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.getCACHE());
        Collections.shuffle(lottoNumbers);

        List<LottoNumber> generatedNumber = lottoNumbers.subList(0, LOTTO_SIZE);
        Collections.sort(generatedNumber, (n1,n2) -> Integer.compare(n1.getNumber(), n2.getNumber()));
        return generatedNumber;
    }

}
