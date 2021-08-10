package me.gimun.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 45;
    private static final List<LottoNumber> CACHE = new ArrayList<>();

    static {
        IntStream stream = IntStream.rangeClosed(LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_UPPER_BOUND);
        stream.forEach(i -> {
            CACHE.add(new LottoNumber(i));
        });
    }

    public static LottoNumber valueOf(final int number) {
        LottoNumber lottoNumber = CACHE.get(number);

        if (Objects.isNull(lottoNumber)) {
            lottoNumber = new LottoNumber(number);
        }
        return lottoNumber;
    }

    public static List<LottoNumber> getCACHE() {
        return new ArrayList<>(CACHE);
    }

    private final int number;

    private void validate(final int number) {
        if (number < LOTTO_NUMBER_LOWER_BOUND || number > LOTTO_NUMBER_UPPER_BOUND) {
            throw new IllegalArgumentException("LottoNumber가 유효하지 않습니다.");
        }
    }

    public LottoNumber(final int number) {
        validate(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }


}
