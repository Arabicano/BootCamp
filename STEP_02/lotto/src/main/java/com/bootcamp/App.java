package com.bootcamp;

import java.util.Set;

import com.bootcamp.lotto.model.Lotto;
import com.bootcamp.lotto.service.BuyLottoService;
import com.bootcamp.lotto.service.LottoResultService;

public class App {
    public static void main(String[] args) {
        BuyLottoService lottoService = new BuyLottoService();
        LottoResultService resultService = new LottoResultService();
        
        Set<Lotto> lottos = lottoService.buyLotto();
        resultService.getResultOfLotto(lottos);
    }
}
