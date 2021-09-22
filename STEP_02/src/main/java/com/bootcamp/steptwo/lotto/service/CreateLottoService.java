package com.bootcamp.steptwo.lotto.service;

import java.util.List;

import com.bootcamp.steptwo.lotto.model.Lotto;
import com.bootcamp.steptwo.lotto.model.dto.LottoCreateDto;

public interface CreateLottoService {

    /**
     * 로또 생성
     * @return
     */
    List<Lotto> createLotto(LottoCreateDto lottoCreateDto);
    
}
