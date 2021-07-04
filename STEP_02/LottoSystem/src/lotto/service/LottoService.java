package lotto.service;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import lotto.model.Lotto;

public class LottoService {

    Scanner sc = new Scanner(System.in);

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
            Lotto lotto = setNonAutoLotto();
            lottos.add(lotto);
            System.out.println();
        }

        // 4. 자동 갯수(전체로또갯수-수동갯수)에 따른 로또 번호 받기
        int autoLotto = lottoCount - nonAutoLotto;
        // 4_0. 자동 갯수가 0인 경우 이 과정을 생략한다.
        if (autoLotto != 0) {
            for (int j = 1; j <= autoLotto; j++) {
                Lotto lotto = getAutoLotto(j);
                lottos.add(lotto);
            }
        }

        return lottos;
    }

    // CASE 2. 당첨확인하기
    public void getResultOfLotto(Set<Lotto> lottos) {
        System.out.println();
        System.out.println("---------------------------");
        // 1. 당첨번호 가져오기
        // Lotto winnerLotto = getAutoWinnerLotto();
        System.out.println("< 당첨 로또 번호 > 를 입력해주십시오.");
        Lotto winnerLotto = getNonAutoWinnerLotto();
        System.out.println("당첨 " + winnerLotto.toString());
        System.out.println("---------------------------");

        // 2. 당첨결과 확인
        System.out.println("로또 당첨 결과 입니다.");
        int first, second, third, fourth, fifth, bomb;
        first = second = third = fourth = fifth = bomb = 0;
        for (Lotto lotto : lottos) {
            String result = getResult(winnerLotto, lotto);
            switch(result) {
                case "1등" : first++; break;
                case "2등" : 
                    second++; break;
                case "3등" : 
                    third++; break;
                case "4등" : 
                    fourth++; break;
                case "5등" : 
                    fifth++; break;
                default : bomb++;
            }
            System.out.println(lotto.toString() + " -> 결과 : " + result);
        }

        System.out.printf("최종결과: 1등 %d개, 2등 %d개, 3등 %d개, 4등 %d개, 5등 %d개, 꽝 %d개", first, second, third, fourth, fifth, bomb);
        
    }

    // 당첨결과 확인
    private String getResult(Lotto winnerLotto, Lotto lotto) {
        // | 등수 | 조건 |
        // | --- | --- |
        // | 1등 | 6개 일치 |
        // | 2등 | 5개 / 보너스 번호 일치 |
        // | 3등 | 5개 / 보너스 번호 불일치 |
        // | 4등 | 4개 일치 |
        // | 5등 | 3개 일치 |

        int matchCount = 0;
        for (Integer i : winnerLotto.getNumbers()) {
            if (lotto.getNumbers().contains(i)) {
                matchCount++;
            }
        }

        switch (matchCount) {
            case 6:
                return "1등";
            case 5:
                if (lotto.getNumbers().contains(winnerLotto.getBonusNum())) {
                    return "2등";
                }
                return "3등";
            case 4:
                return "4등";
            case 3:
                return "5등";
            default:
                return "꽝";
        }
    }

    // 로또 구매 장수
    public int getLottoCount() {
        int money = 0; // 사용자가 낸 돈
        int counts = 0; // 구매한 로또

        // 1. 로또 구매를 위해 금액 입력받기
        do {
            try {
                System.out.println("로또 구매를 위한 금액을 입력해주세요(숫자만입력).");
                money = sc.nextInt();
            } catch (InputMismatchException e) {
                // 2. 입력받은 금액에 따른 처리
                // 2_0. 숫자 외의 다른 값인 경우, 다시 입력받기
                System.out.println("잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.");
                sc.nextLine();
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 2_1. 입력한 금액이 로또 금액보다 작을 경우
            if (money != 0) {
                if (money < 1000) {
                    System.out.println("로또 구매의 최소 금액은 1000원 입니다.");
                    money = 0;
                } else {
                    if ((money % 1000) > 0) {
                        // 2_2. 입력한 금액에서 나머지 금액은 잔액처리
                        int change = money % 10000;
                        System.out.println("내신 금액에서 거스름돈 " + change + "은(는) 돌려드립니다.");
                    }
                    // 2_3. 로또 장수가 1보다 클 경우 로또 구매 자동수동 여부 확인
                    counts = money / 1000;
                    System.out.println(counts + "장의 로또를 구매합니다.");
                }
            }
        } while (money == 0);

        return counts;
    }

    // 수동 로또 구매 장수
    public int getNonAutoLottoCount(int lottoCount) {
        // 3. 로또 구매 장수에 따른 수동 갯수 받기
        int nonAutoCount = -1;

        // Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println("몇 장을 수동으로 입력하시겠습니까?(숫자만)");
                nonAutoCount = sc.nextInt();
            } catch (InputMismatchException e) {
                // 3_0. 숫자(자연수) 외의 다른 값인 경우, 다시 입력 받기
                System.out.println("잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.");
                sc.nextLine();
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (nonAutoCount < 0 || nonAutoCount > lottoCount) {
                // 3_1. 입력한 값이 로또 구매 장수보다 크거나 잘못된 값일 경우 다시 입력받기
                System.out.println();
                nonAutoCount = -1;
            } else {
                System.out.println(nonAutoCount + "장을 수동으로 구매합니다.");
            }
        } while (nonAutoCount < 0);

        return nonAutoCount;
    }

    // 수동 로또 구매
    private Lotto setNonAutoLotto() {

        Set<Integer> lottoSet = new TreeSet<>();

        // Scanner sc = new Scanner(System.in);

        // 1. 로또 값 입력
        for (int j = 1; j <= 6; j++) {
            int num = 0;
            boolean flag = false;
            do {
                try {
                    System.out.println(j + "번째 숫자 : 1 ~ 45 사이의 숫자를 입력하세요.");
                    num = sc.nextInt();
                } catch (InputMismatchException e) {
                    // 1_1. 숫자(자연수) 외의 다른 값인 경우, 다시 입력 받기
                    System.out.println("잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.");
                    sc.nextLine();
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 1_2. 1~45 사이의 값 이외의 다른 값인 경우, 다시 입력받기
                if (num <= 0 || num > 45) {
                    System.out.println("잘못된 숫자를 입력했습니다. 다시 입력해주세요.");
                    continue;
                }

                // 1_3. 입력값이 이미 존재하는 값인지 판단하기
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

    // 자동 로또
    private Lotto getAutoLotto(int j) {
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
        Lotto lotto = setNonAutoLotto();
        int bonusNum = 0;
        do {
            try {
                System.out.println("보너스 번호를 입력해주세요.");
                bonusNum = sc.nextInt();
            } catch (InputMismatchException e) {
                // 3_0. 숫자(자연수) 외의 다른 값인 경우, 다시 입력 받기
                System.out.println("잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.");
                sc.nextLine();
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bonusNum <= 0 || bonusNum > 45 || lotto.getNumbers().contains(bonusNum)) {
                bonusNum = 0;
            } else {
                lotto.setBonusNum(bonusNum);
            }
        } while (bonusNum == 0);

        return lotto;
    }
}
