package com.bootcamp.lotto.service;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.bootcamp.lotto.model.Lotto;

public class BuyLottoService {
    /**
     * 로또 구매 서비스
     */

    Scanner sc = new Scanner(System.in);

    InputService inputservice = new InputService();
    CreateLottoService createLottoService = new CreateLottoService();

    static final int LOTTO_PRICE = 1000;

    // CASE 1. 로또 구매
    public Set<Lotto> buyLotto() {
        // 1. 로또 구매를 위한 금액 받고 로또 장수 계산하기
        int lottoCount = getLottoCount();

        // 2. 로또 장수에 따른 수동 갯수 받기
        int nonAutoLotto = getNonAutoLottoCount(lottoCount);

        // 3. 구매한 로또
        Set<Lotto> lottos = new HashSet<>();

        // 3. 수동 갯수에 따른 로또 번호 받기
        for (int i = 1; i <= nonAutoLotto; i++) {
            System.out.println("< 수동로또 : " + i + " 번째 >");
            Lotto lotto = createLottoService.createNonAutoLotto();
            lottos.add(lotto);
            System.out.println();
        }

        // 4. 자동 갯수(전체로또갯수-수동갯수)에 따른 로또 번호 받기
        int autoLotto = lottoCount - nonAutoLotto;
        // 4_0. 자동 갯수가 0인 경우 이 과정을 생략한다.
        if (autoLotto != 0) {
            System.out.println("< 자동로또 : " + autoLotto + "장 구매 >");
            for (int j = 1; j <= autoLotto; j++) {
                Lotto lotto = createLottoService.createAutoLotto(j);
                lottos.add(lotto);
            }
        }

        return lottos;
    }

    // 로또 구매 장수
    private int getLottoCount() {
        int money = 0; // 사용자가 낸 돈
        int counts = 0; // 구매한 로또

        // 1. 로또 구매를 위해 금액 입력받기
        // 1_0. 숫자 외의 다른 값인 경우, 다시 입력받기
        String[] reqMsg = { "로또는 한 장당 1000원으로 판매됩니다.", "로또 구매를 위한 금액을 입력해주세요(숫자만입력)." };
        String errorMsg = "잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.";

        do {
            money = inputservice.reqInputInteger(reqMsg, errorMsg);

            // 2. 입력받은 금액에 따른 분기
            // 2_1. 입력한 금액이 로또 금액보다 작을 경우
            // 2_2. 입력한 금액에서 나머지 금액은 잔액처리
            // 2_3. 로또 장수가 1보다 클 경우 로또 구매 자동수동 여부 확인
            if (money != 0) {
                if (money < LOTTO_PRICE) {
                    System.out.println("로또 구매의 최소 금액은 1000원 입니다.");
                    money = 0;
                } else {
                    if ((money % LOTTO_PRICE) > 0) {
                        // 2_2. 입력한 금액에서 나머지 금액은 잔액처리
                        int change = money % LOTTO_PRICE;
                        System.out.println("내신 금액에서 거스름돈 " + change + "은(는) 돌려드립니다.");
                    }

                    counts = money / LOTTO_PRICE;
                    System.out.println(counts + "장의 로또를 구매합니다.");
                }
            }

        } while (money == 0);

        return counts;
    }

    // 수동 로또 구매 장수
    public int getNonAutoLottoCount(int lottoCount) {
        // 3. 로또 구매 장수에 따른 수동 갯수 받기
        // 3_0. 숫자(자연수) 외의 다른 값인 경우, 다시 입력 받기
        int nonAutoCount = -1;

        String[] reqMsg = { "몇 장을 수동으로 입력하시겠습니까?(숫자만)" };
        String errorMsg = "잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.";

        do {
            nonAutoCount = inputservice.reqInputInteger(reqMsg, errorMsg);

            if (nonAutoCount < 0 || nonAutoCount > lottoCount) {
                // 3_1. 입력한 값이 로또 구매 장수보다 크거나 잘못된 값일 경우 다시 입력받기
                System.out.println("잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.");
                sc.nextLine();
                nonAutoCount = -1;
            } else {
                System.out.println(nonAutoCount + "장을 수동으로 구매합니다.");
            }
        } while (nonAutoCount < 0);

        return nonAutoCount;
    }

}
