package com.bootcamp.steptwo.lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.bootcamp.steptwo.lotto.model.Lotto;
import com.bootcamp.steptwo.lotto.model.dto.ChangeDto;
import com.bootcamp.steptwo.lotto.model.dto.LottoCreateDto;
import com.bootcamp.steptwo.lotto.model.dto.LottoDto;
import com.bootcamp.steptwo.lotto.model.dto.WinningLottoDto;
import com.bootcamp.steptwo.lotto.service.BuyLottoService;
import com.bootcamp.steptwo.lotto.service.CreateLottoService;
import com.bootcamp.steptwo.lotto.service.WinLottoService;

import org.springframework.web.bind.annotation.GetMapping;
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
    private final WinLottoService winLottoService;

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

    @GetMapping("/winner")
    public WinningLottoDto winLotto(@RequestBody List<LottoDto> lottos) {

        List<Lotto> lottoEntity = lottos.stream().map(LottoDto::toLotto).collect(Collectors.toList());
        WinningLottoDto winningLottoResult = winLottoService.getResultLotto(lottoEntity);
        return winningLottoResult;
    }
    

}
