package com.bootcamp.lotto.service;

import com.bootcamp.lotto.model.dto.ChangeDto;

public interface BuyLottoService {

    // 로또 구매
    ChangeDto buyLotto(Integer money);

}
