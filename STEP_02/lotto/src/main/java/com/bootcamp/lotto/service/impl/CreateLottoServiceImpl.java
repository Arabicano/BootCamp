package com.bootcamp.lotto.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.bootcamp.lotto.model.Lotto;
import com.bootcamp.lotto.model.consts.LottoConst;
import com.bootcamp.lotto.model.dto.LottoCreateDto;
import com.bootcamp.lotto.service.CreateLottoService;

public class CreateLottoServiceImpl implements CreateLottoService {

    @Override
    public List<Lotto> createLotto(LottoCreateDto lottoCreateDto) {

        List<Lotto> lottos = new ArrayList<>();

        // 수동 로또
        if (lottoCreateDto.getNonAutoNumbers() != null && lottoCreateDto.getNonAutoNumbers().size() != 0) {
            for (Integer[] numbers : lottoCreateDto.getNonAutoNumbers()) {
                Lotto lotto = createNonAutoLotto(numbers);
                lottos.add(lotto);
            }
        }

        // 자동 로또
        if (lottoCreateDto.getCountOfAuto() != null) {
            if (lottoCreateDto.getCountOfAuto() < 1) {
                // val. 자동 로또 요청 갯수가 잘못 된 경우
                throw LottoConst.ErrorCode.COUNT_OF_AUTO_LOTTO_ERROR.throwException();
            }
            for (int i = 0; i < lottoCreateDto.getCountOfAuto(); i++) {
                Lotto lotto = createAutoLotto();
                lottos.add(lotto);
            }
        }

        return lottos;
    }
  
    // 수동 로또 생성
    private Lotto createNonAutoLotto(Integer[] numbers) {

        // val. 로또 숫자 갯수가 6개인지 확인
        if (numbers.length != LottoConst.COUNT_OF_LOTTO_NUMBERS) {
            throw LottoConst.ErrorCode.THE_NUMBER_OF_NUMBERS_ERROR.throwException();
        }
        Set<Integer> lottoNumber = new TreeSet<>();
        for (Integer num : numbers) {
            // val. 숫자가 1~45 사이여야 함
            if (num < LottoConst.MIN_LOTTO_NUM || num > LottoConst.MAX_LOTTO_NUM) {
                throw LottoConst.ErrorCode.NOT_INCLUDED_LOTTO_NUMBER.throwException();
            }
            lottoNumber.add(num);
        }
        // val. 중복 숫자가 있었을 경우 갯수가 6개 미만일 수 있음
        if (lottoNumber.size() != LottoConst.COUNT_OF_LOTTO_NUMBERS) {
            throw LottoConst.ErrorCode.THE_NUMBER_OF_NUMBERS_ERROR.throwException();
        }

        return new Lotto(lottoNumber);
    }

    // 자동 로또 생성
    private Lotto createAutoLotto() {
        // 1 ~ 45 사이의 중복되지 않는 6개의 숫자
        Random random = new Random(); // 랜덤 객체
        Set<Integer> lottoSet = new TreeSet<>();
        while (lottoSet.size() != LottoConst.COUNT_OF_LOTTO_NUMBERS) {
            int i = random.nextInt(LottoConst.MAX_LOTTO_NUM) + 1; // (0이상 45미만) + 1
            lottoSet.add(i);
        }

        return new Lotto(lottoSet);
    }

}
