package com.bootcamp.steptwo.lotto.service;

import java.util.List;

import com.bootcamp.steptwo.lotto.model.Lotto;
import com.bootcamp.steptwo.lotto.model.dto.WinningLottoDto;

public interface WinLottoService {

    /**
     * 로또 결과 얻기
     * @param lottos
     */
    WinningLottoDto getResultLotto(List<Lotto> lottos);
    




}
