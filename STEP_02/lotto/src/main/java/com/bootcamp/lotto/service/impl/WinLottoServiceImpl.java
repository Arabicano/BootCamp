package com.bootcamp.lotto.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.bootcamp.lotto.model.Lotto;
import com.bootcamp.lotto.model.consts.LottoConst;
import com.bootcamp.lotto.model.dto.ResultLottoDto;
import com.bootcamp.lotto.model.dto.WinningLottoDto;
import com.bootcamp.lotto.service.WinLottoService;

public class WinLottoServiceImpl implements WinLottoService {
    

    private final int COUNT_OF_LOTTO_NUMBERS = 6;
    private final int MAX_LOTTO_NUM = 45;
    private final long FIRST_PRIZE = 1_000_000_000;
    private final long SECOND_PRIZE = 50_000_000;
    private final long THIRD_PRIZE = 3_000_000;
    private final long FOURTH_PRIZE = 50_000;
    private final long FIFTH_PRIZE = 5_000;


    @Override
    public WinningLottoDto getResultLotto(List<Lotto> lottos) {

        if(lottos == null || lottos.size() < 1) {
            throw LottoConst.ErrorCode.NOT_EXIST_LOTTO.throwException();
        }

        // 당첨 로또
        Lotto winningLotto = getWinnerLotto();

        // 당첨금
        long prize = 0;
        
        // 각 로또 결과 얻기
        List<ResultLottoDto> results = new ArrayList<>();
        for(Lotto lotto: lottos) {
            if(lotto.getNumbers().size() != COUNT_OF_LOTTO_NUMBERS) {
                throw LottoConst.ErrorCode.THE_NUMBER_OF_NUMBERS_ERROR.throwException();
            }
            ResultLottoDto resultDto = getResultLotto(winningLotto, lotto);
            prize += resultDto.getPrize();
            results.add(resultDto);
        }

        double priceOfLottos = lottos.size() * Lotto.LOTTO_PRICE;
        double profitRate = ((prize - priceOfLottos) / priceOfLottos) * 100;

        // 당첨로또 번호와 총 결과값 반환
        return new WinningLottoDto(winningLotto, results, prize, profitRate);
    }


    // 당첨 로또 번호 얻기
    private Lotto getWinnerLotto() {
        // 7개의 숫자를 랜덤하게 뽑고 그 중 하나(랜덤)를 보너스 번호로 지정한다.
        Random random = new Random(); // 랜덤 객체

        Set<Integer> lottoSet = new TreeSet<>();

        while (lottoSet.size() != 6) {
            int i = random.nextInt(MAX_LOTTO_NUM) + 1; // (0이상 45미만) + 1
            lottoSet.add(i);
        }

        int bonusNum = 0;
        do {
            bonusNum = random.nextInt(MAX_LOTTO_NUM) + 1; // (0이상 45미만) + 1
        } while (lottoSet.contains(bonusNum));

        return new Lotto(lottoSet, bonusNum);
    }

    // 로또별 결과 얻기
    private ResultLottoDto getResultLotto(Lotto winningLotto, Lotto lotto) {
        // | 등수 | 조건 | 당청금 |
        // | --- | --- | --- |
        // | 1등 | 6개 일치 | 10억원 |
        // | 2등 | 5개 / 보너스 번호 일치 | 5천만원 |
        // | 3등 | 5개 / 보너스 번호 불일치 | 300만원 |
        // | 4등 | 4개 일치 | 5만원 |
        // | 5등 | 3개 일치 | 5천원 |

        int matchCount = 0;

        for (Integer i : winningLotto.getNumbers()) {
            if (lotto.getNumbers().contains(i)) {
                matchCount++;
            }
        }

        ResultLottoDto result = new ResultLottoDto();

        switch(matchCount) {
            case 6: result.setRank(1);
                    result.setPrize(FIRST_PRIZE);
                    break;
            case 5:
                    if (lotto.getNumbers().contains(winningLotto.getBonusNum())) {
                        result.setRank(2);
                        result.setPrize(SECOND_PRIZE);
                    } else {
                        result.setRank(3);
                        result.setPrize(THIRD_PRIZE);
                    }
                    break;
            case 4:
                    result.setRank(4);
                    result.setPrize(FOURTH_PRIZE);
                    break;

            case 3:
                    result.setRank(5);
                    result.setPrize(FIFTH_PRIZE);
                    break;
            default: ;
        }

        result.setLotto(lotto);
        return result;
    }



}
