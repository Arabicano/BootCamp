package com.bootcamp.steptwo.lotto.service;

import com.bootcamp.steptwo.lotto.model.dto.ChangeDto;

public interface BuyLottoService {

    // 로또 구매
    ChangeDto buyLotto(Integer money);

}
