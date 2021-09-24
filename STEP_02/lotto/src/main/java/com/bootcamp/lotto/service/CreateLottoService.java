package com.bootcamp.lotto.service;

import java.util.List;

import com.bootcamp.lotto.model.Lotto;
import com.bootcamp.lotto.model.dto.LottoCreateDto;

public interface CreateLottoService {

    /**
     * 로또 생성
     * @return
     */
    List<Lotto> createLotto(LottoCreateDto lottoCreateDto);
    
}
