package me.gimun;
import me.gimun.domain.LottoNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //구매급액 입력
        System.out.println("로또를 구매하실 금액을 입력하세요.(장당 천원)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());
        LottoStore lottoStore = new LottoStore(input);
        //로또 당첨번호 입력
        System.out.println("로또 당첨번호를 순서대로 입력 하세요.");

        Set<Integer> set = new HashSet<>();
        int ii =1;
        while (set.size() != 6){
            System.out.println(ii + "번째 번호를 입력하세요.");
            br = new BufferedReader(new InputStreamReader(System.in));
            set.add(Integer.parseInt(br.readLine()));
            ii++;
        }
        List<Integer> winningLotto = new ArrayList<Integer>();
        winningLotto.addAll(set);
        Collections.sort(winningLotto);

        SetWinNum setWinNum = new SetWinNum(winningLotto);
        System.out.println("보너스번호를 입력 하세요.");
        br = new BufferedReader(new InputStreamReader(System.in));
        //보너스번호
        setWinNum.setBounsNum(Integer.parseInt(br.readLine()));

        //로또 구매 내역 출력
        System.out.println("로또 구매 장수 : " + lottoStore.getLottoCnt());
        System.out.println("잔액 : " + lottoStore.getBalance());

        //구매한 로또 번호 출력
        List<List<LottoNumber>> buyLottoList = new ArrayList<>();
        List<LottoNumber> lotto = new ArrayList<>();
        for(int i = 0; i< lottoStore.getLottoCnt(); i++){
            System.out.println("입력하신 금액에 맞는 로또를 생성합니다.");
            System.out.println("수동으로 생성하실 경우 1번을 자동은 2번을 입력하세요.");
            br = new BufferedReader(new InputStreamReader(System.in));

            if(Integer.parseInt(br.readLine()) == 1){
                System.out.println("수동으로 6자리의 로또번호를 입력하세요.");
                int jj = 0;
                while (jj != 6){
                    System.out.println(jj+1 + "번째 번호를 입력하세요.");
                    br = new BufferedReader(new InputStreamReader(System.in));
                    lotto.add(LottoNumber.valueOf(Integer.parseInt(br.readLine())-1));
                    jj++;
                }
            }else{
                lotto = new LottoGenerate().generate();
            }
            buyLottoList.add(lotto);
        }

        //당첨 내역 출력
        System.out.println("당첨번호 : " + setWinNum.getWinningLotto());
        System.out.println("보너스번호 : " + setWinNum.getWinningBonusNum());
        ConfirmWinning confirmWinning = new ConfirmWinning();
        List<String> list = new ArrayList<String>();
        int i = 0;
        for (List<LottoNumber> numbers: buyLottoList) {
            String result = confirmWinning.confirmLotto(setWinNum.getWinningLotto(), setWinNum.getWinningBonusNum(),numbers);
            list.add(result);
            List<Integer> buyLotto = new ArrayList<>();
            for (int j = 0; j < numbers.size(); j++) {
                buyLotto.add(numbers.get(j).getNumber());
            }
            System.out.println(i+1 + "번 로또 : " + buyLotto + " 당첨종류 : " + result);
            i++;
        }
        //당첨통계출력
        StatisticWinning statisticWinning = new StatisticWinning();
        statisticWinning.calculate(list, lottoStore.getLottoCnt());
        System.out.println("수익률(%) : " +statisticWinning.getRateOfReturn());

    }
}
