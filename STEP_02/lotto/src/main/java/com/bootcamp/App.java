package com.bootcamp;

import java.util.Set;

import com.bootcamp.lotto.model.Lotto;
import com.bootcamp.lotto.service.BuyLottoService;
import com.bootcamp.lotto.service.LottoService;

public class App {
    public static void main(String[] args) {
        BuyLottoService lottoService = new BuyLottoService();
        
        Set<Lotto> lottos = lottoService.buyLotto();
        // lottoService.getResultOfLotto(lottos);
    }
}
