package me.gimun;

import me.gimun.domain.LottoNumber;

import java.util.*;

public class LottoGenerate {
    private static final int LOTTO_SIZE = 6;

    public static List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.getCACHE());
        Collections.shuffle(lottoNumbers);

        List<LottoNumber> generatedNumber = lottoNumbers.subList(0, LOTTO_SIZE);

        Collections.sort(generatedNumber, new Comparator<LottoNumber>() {
            @Override
            public int compare(LottoNumber num1, LottoNumber num2) {
                return Integer.valueOf(num1.getNumber()).compareTo(Integer.valueOf(num2.getNumber()));
            }
        });
        return generatedNumber;
    }

}
