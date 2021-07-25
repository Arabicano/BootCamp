package com.bootcamp.lotto.service;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.bootcamp.lotto.model.Lotto;

public class CreateLottoService {
    /**
     * 로또 생성 서비스
     */

    InputService inputservice = new InputService();

    // 1. 자동로또
    public Lotto createAutoLotto(int j) {
        // 조건1. 랜덤으로 1~45 사이의 6개의 숫자 조합
        // 조건2. 중복불가/ 번호 정렬
        Random random = new Random(); // 랜덤 객체

        Set<Integer> lottoSet = new TreeSet<>();

        while (lottoSet.size() != 6) {
            int i = random.nextInt(45) + 1; // (1이상 45이하) + 1
            lottoSet.add(i);
        }

        return new Lotto(lottoSet);
    }

    // 2. 수동로또
    // 수동 로또 구매
    public Lotto createNonAutoLotto() {

        Set<Integer> lottoSet = new TreeSet<>();

        // 1. 로또 값 입력
        for (int j = 1; j <= 6; j++) {
            int num = 0;
            boolean flag = false;

            String[] reqMsg = { j + "번째 숫자 : 1 ~ 45 사이의 숫자를 입력하세요." };
            String errorMsg = "잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.";

            do {
                num = inputservice.reqInputInteger(reqMsg, errorMsg);

                // 1_2. 1~45 사이의 값 이외의 다른 값인 경우, 다시 입력받기
                // 1_3. 입력값이 이미 존재하는 값인지 판단하기
                if (num <= 0 || num > 45) {
                    System.out.println("잘못된 숫자를 입력했습니다. 다시 입력해주세요.");
                    continue;
                }

                if (lottoSet.contains(num)) {
                    System.out.println("이미 입력한 숫자입니다. 다시 입력해주세요.");
                    continue;
                } else {
                    lottoSet.add(num);
                    flag = true;
                }

            } while (!flag);
        }

        return new Lotto(lottoSet);
    }

}
