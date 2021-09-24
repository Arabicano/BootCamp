package com.bootcamp.lotto.service;

import java.util.List;

import com.bootcamp.lotto.model.Lotto;
import com.bootcamp.lotto.model.dto.WinningLottoDto;

public interface WinLottoService {

    /**
     * 로또 결과 얻기
     * @param lottos
     */
    WinningLottoDto getResultLotto(List<Lotto> lottos);
    




}
