package lotto.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import lotto.model.Lotto;

public class LottoService {

    // CASE 1. 로또 구매
    public void buyLotto() {

        int money = 0;
        int counts = 0;

        Scanner sc = new Scanner(System.in);
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

        // 3. 로또 구매 장수에 따른 수동 갯수 받기
        // 3_0. 숫자(자연수) 외의 다른 값인 경우, 다시 입력 받기
        // 3_1. 입력한 값이 로또 구매 장수보다 클 경우 다시 입력받기

        // 4. 수동 갯수에 따른 로또 번호 받기

        // 5. 자동 갯수(전체로또갯수-수동갯수)에 따른 로또 번호 받기
        // 5_0. 자동 갯수가 0인 경우 이 과정을 생략한다.

    }

    // CASE 2. 로또 당첨번호 뽑기 (6+1)
    public void getLottoWinningNumber() {

    }

    // CASE 3. 당첨확인하기
    public void getResultOfLotto(List<Lotto> lotto) {

    }

    private Lotto autoLotto() {
        // 조건1. 랜덤으로 1~45 사이의 6개의 숫자 조합
        // 조건2. 중복불가/ 번호 정렬
        Random random = new Random(); // 랜덤 객체

        Set<Integer> lottoSet = new TreeSet<>();

        while (lottoSet.size() != 6) {
            int i = random.nextInt(45) + 1; // (1이상 45미만) + 1
            lottoSet.add(i);
        }
        Integer[] numbers = lottoSet.toArray(new Integer[lottoSet.size()]);

        return new Lotto(numbers);
    }

    private Lotto setLottoByBuyer() {
        return null;
    }

}
