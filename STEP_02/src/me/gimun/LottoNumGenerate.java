package me.gimun;

import me.gimun.domain.LottoNumber;

import java.util.List;

public interface LottoNumGenerate {
    int LOTTO_SIZE = 6;

    List<LottoNumber> generate();
}
