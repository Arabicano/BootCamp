package me.gimun;
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
        BuyLotto buyLotto = new BuyLotto(input);
        //로또 당첨번호 입력
        System.out.println("로또 당첨번호를 순서대로 입력 하세요.");
        SettingWinningNumber settingWinningNumber = new SettingWinningNumber();
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

        System.out.println("보너스번호를 입력 하세요.");
        br = new BufferedReader(new InputStreamReader(System.in));
        winningLotto.add(Integer.parseInt(br.readLine()));
        settingWinningNumber.winningNumSet(winningLotto);

        //로또 구매 내역 출력
        System.out.println("로또 구매 장수 : " + buyLotto.getLottoCnt());
        System.out.println("잔액 : " + buyLotto.getBalance());

        //구매한 로또 번호 출력
        List<List<Integer>> buyLottoList = new ArrayList<List<Integer>>();
        for(int i=0; i< buyLotto.getLottoCnt(); i++){
            LottoGenerate lottoGenerate = new LottoGenerate();
            System.out.println("입력하신 금액에 맞는 로또를 생성합니다.");
            System.out.println("수동으로 생성하실 경우 1번을 자동은 2번을 입력하세요.");
            br = new BufferedReader(new InputStreamReader(System.in));

            if(Integer.parseInt(br.readLine()) == 1){
                List<Integer> lotto = new ArrayList<Integer>();
                System.out.println("수동으로 6자리의 로또번호를 입력하세요.");
                int jj = 1;
                while (jj != 7){
                    System.out.println(jj + "번째 번호를 입력하세요.");
                    br = new BufferedReader(new InputStreamReader(System.in));
                    lotto.add(Integer.parseInt(br.readLine()));
                    jj++;
                }
                lottoGenerate.setLotto(lotto);
            }else{
                lottoGenerate.generate();
            }
            List<Integer> lotto = lottoGenerate.getLotto();
            buyLottoList.add(lotto);
        }

        //당첨 내역 출력
        System.out.println("당첨번호 : " + settingWinningNumber.getWinningLotto());
        System.out.println("보너스번호 : " + settingWinningNumber.getWinningBonusNum());
        ConfirmWinning confirmWinning = new ConfirmWinning();
        List<String> list = new ArrayList<String>();
        for (int i=0; i < buyLottoList.size(); i++){
            String result = confirmWinning.confirmLotto(settingWinningNumber.getWinningLotto(),settingWinningNumber.getWinningBonusNum(),buyLottoList.get(i));
            list.add(result);
            System.out.println(i+1 + "번 로또 : " + buyLottoList.get(i) + " 당첨종류 : " + result);
        }
        //당첨통계출력
        StatisticWinning statisticWinning = new StatisticWinning();
        statisticWinning.calculate(list, buyLotto.getLottoCnt());
        System.out.println("수익률(%) : " +statisticWinning.getRateOfReturn());

    }
}
