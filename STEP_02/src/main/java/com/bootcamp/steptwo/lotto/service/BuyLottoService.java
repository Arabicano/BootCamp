package com.bootcamp.steptwo.lotto.service;

import com.bootcamp.steptwo.lotto.model.dto.ChangeDto;

import org.springframework.stereotype.Service;

public interface BuyLottoService {

    // 로또 구매
    ChangeDto buyLotto(Integer money);

}
