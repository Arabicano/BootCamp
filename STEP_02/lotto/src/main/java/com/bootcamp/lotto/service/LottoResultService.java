package com.bootcamp.lotto.service;

import static com.bootcamp.lotto.service.BuyLottoService.LOTTO_PRICE;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.bootcamp.lotto.model.Lotto;

public class LottoResultService {
    /**
     * 로또 결과 안내 서비스
     */

    InputService inputservice = new InputService();
    CreateLottoService createLottoService = new CreateLottoService();

    private static final String FIRST_PRIZE = "1등"; // 15억
    private static final String SECOND_PRIZE = "2등"; // 5천만원
    private static final String THIRD_PRIZE = "3등"; // 150만원
    private static final String FOURTH_PRIZE = "4등"; // 5만원
    private static final String FIVITH_PRIZE = "5등"; // 5천원
    private static final String LOSE_LOTTO = "낙첨";

    private static final int FIRST_PRIZE_REWORD = 1500000000;  // 15억
    private static final int SECOND_PRIZE_REWORD = 50000000;  // 5천만원
    private static final int THIRD_PRIZE_REWORD = 1500000;  // 150만원
    private static final int FOURTH_PRIZE_REWORD = 50000;  // 5만원
    private static final int FIVITH_PRIZE_REWORD = 5000;  // 5천원

    // CASE 2. 당첨확인하기
    public void getResultOfLotto(Set<Lotto> lottos) {
        System.out.println();
        System.out.println("---------------------------");
        // 1. 당첨번호 가져오기
        // Lotto winnerLotto = getAutoWinnerLotto();
        System.out.println("< 당첨 로또 번호 > 를 입력해주십시오.");
        Lotto winnerLotto = getNonAutoWinnerLotto();
        System.out.println();
        System.out.println("당첨 " + winnerLotto.toString());
        System.out.println("---------------------------");

        // 2. 당첨결과 확인
        System.out.println("로또 당첨 결과 입니다.");
        int first, second, third, fourth, fifth, bomb;
        long prize = 0L;
        first = second = third = fourth = fifth = bomb = 0;
        for (Lotto lotto : lottos) {
            String result = getResult(winnerLotto, lotto);
            switch (result) {
                case FIRST_PRIZE:
                    first++;
                    prize += FIRST_PRIZE_REWORD; // 15억
                    break;
                case SECOND_PRIZE:
                    second++;
                    prize += SECOND_PRIZE_REWORD; // 5천만원
                    break;
                case THIRD_PRIZE:
                    third++;
                    prize += THIRD_PRIZE_REWORD; // 150만원
                    break;
                case FOURTH_PRIZE:
                    fourth++;
                    prize += FOURTH_PRIZE_REWORD; // 5만원
                    break;
                case FIVITH_PRIZE:
                    prize += FIVITH_PRIZE_REWORD; // 5천원
                    fifth++;
                    break;
                default:
                    bomb++;
            }
            System.out.println(lotto.toString() + " -> 결과 : " + result);
        }

        double profitRate = ((prize - (lottos.size() * LOTTO_PRICE)) / (lottos.size() * LOTTO_PRICE)) * 100;

        System.out.printf("최종결과: %s %d개, %s %d개, %s %d개, %s %d개, %s %d개", 
                FIRST_PRIZE, first, SECOND_PRIZE, second, THIRD_PRIZE, third, FOURTH_PRIZE, fourth, 
                FIVITH_PRIZE, fifth, bomb);
        System.out.println();
        System.out.printf("수익금은 %d원으로 수익률은 %.0f%% 입니다.", prize, profitRate);
    }

    // 자동 당첨 로또
    private Lotto getAutoWinnerLotto() {
        // 7개의 숫자를 랜덤하게 뽑고 그 중 하나(랜덤)를 보너스 번호로 지정한다.
        Random random = new Random(); // 랜덤 객체

        Set<Integer> lottoSet = new TreeSet<>();

        while (lottoSet.size() != 6) {
            int i = random.nextInt(45) + 1; // (1이상 45이하) + 1
            lottoSet.add(i);
        }

        int bonusNum = 0;
        do {
            bonusNum = random.nextInt(45) + 1;
        } while (lottoSet.contains(bonusNum));

        return new Lotto(lottoSet, bonusNum);
    }

    // 수동 당첨 로또
    private Lotto getNonAutoWinnerLotto() {
        Lotto lotto = createLottoService.createNonAutoLotto();
        int bonusNum = 0;

        String[] reqMsg = { "보너스 번호를 입력해주세요." };
        String errorMsg = "잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.";

        do {
            bonusNum = inputservice.reqInputInteger(reqMsg, errorMsg);

            if (bonusNum <= 0 || bonusNum > 45 || lotto.getNumbers().contains(bonusNum)) {
                bonusNum = 0;
            } else {
                lotto.setBonusNum(bonusNum);
            }
        } while (bonusNum == 0);

        return lotto;
    }

    // 당첨결과 확인
    private String getResult(Lotto winnerLotto, Lotto lotto) {
        // | 등수 | 조건 | 당청금 |
        // | --- | --- | --- |
        // | 1등 | 6개 일치 | 15억원 |
        // | 2등 | 5개 / 보너스 번호 일치 | 5천만원 |
        // | 3등 | 5개 / 보너스 번호 불일치 | 150만원 |
        // | 4등 | 4개 일치 | 5만원 |
        // | 5등 | 3개 일치 | 5천원 |

        int matchCount = 0;
        for (Integer i : winnerLotto.getNumbers()) {
            if (lotto.getNumbers().contains(i)) {
                matchCount++;
            }
        }

        switch (matchCount) {
            case 6:
                return FIRST_PRIZE;
            case 5:
                if (lotto.getNumbers().contains(winnerLotto.getBonusNum())) {
                    return SECOND_PRIZE;
                }
                return THIRD_PRIZE;
            case 4:
                return FOURTH_PRIZE;
            case 3:
                return FOURTH_PRIZE;
            default:
                return LOSE_LOTTO;
        }
    }
}
