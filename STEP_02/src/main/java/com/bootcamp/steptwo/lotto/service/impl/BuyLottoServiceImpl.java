package com.bootcamp.steptwo.lotto.service.impl;

import com.bootcamp.steptwo.lotto.model.Lotto;
import com.bootcamp.steptwo.lotto.model.consts.LottoConst;
import com.bootcamp.steptwo.lotto.model.dto.ChangeDto;
import com.bootcamp.steptwo.lotto.service.BuyLottoService;

import org.springframework.stereotype.Service;

@Service
public class BuyLottoServiceImpl implements BuyLottoService {
    
    @Override
    public ChangeDto buyLotto(Integer money) {
        /**
         * 받은 돈으로 로또 장수 계산하기 - 1. 낸 금액이 로또 가격 미만이면 구매 불가 : 에러처리 - 2. 구매 장수를 구하고 남은 금액은
         * 나머지로 처리
         */

        int countOfLotto = 0;
        int change = 0;

        if (money < Lotto.LOTTO_PRICE) {
            // 1. 예외 처리
            throw LottoConst.ErrorCode.LESS_THAN_PRICE.throwException();
        } else {
            // 2. 구매 장수와 나머지 반환
            countOfLotto = money / Lotto.LOTTO_PRICE;
            change = money - countOfLotto * Lotto.LOTTO_PRICE;
        }

        return new ChangeDto(countOfLotto, change);

    }

}
