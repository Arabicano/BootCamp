package com.bootcamp.steptwo.lotto.controller;

import com.bootcamp.steptwo.lotto.model.dto.ChangeDto;
import com.bootcamp.steptwo.lotto.service.LottoService;

import org.springframework.stereotype.Controller;

@Controller
public class LottoController {

    LottoService lottoService;

    public ChangeDto buyLotto(int money) {
        ChangeDto countLotto = lottoService.buyLotto(money);

        return countLotto;
    }

}
