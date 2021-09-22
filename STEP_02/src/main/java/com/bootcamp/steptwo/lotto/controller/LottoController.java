package com.bootcamp.steptwo.lotto.controller;

import java.util.List;

import com.bootcamp.steptwo.lotto.model.Lotto;
import com.bootcamp.steptwo.lotto.model.dto.ChangeDto;
import com.bootcamp.steptwo.lotto.model.dto.LottoCreateDto;
import com.bootcamp.steptwo.lotto.service.BuyLottoService;
import com.bootcamp.steptwo.lotto.service.CreateLottoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/lottos")
public class LottoController {

    private final BuyLottoService buyLottoService;
    private final CreateLottoService lottoService;

    @PostMapping("/buy")
    public ChangeDto buyLotto(@RequestBody Integer money) {

        ChangeDto countLotto = buyLottoService.buyLotto(money);

        return countLotto;
    }

    @PostMapping("/lotto")
    public List<Lotto> createLotto(@RequestBody LottoCreateDto lottoCreateDto) {

        List<Lotto> result = lottoService.createLotto(lottoCreateDto);

        return result;
    }

}
