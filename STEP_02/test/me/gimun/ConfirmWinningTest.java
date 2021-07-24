package me.gimun;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ConfirmWinningTest {

    @Test
    void 당첨확인() {
        //로또생성
        LottoGenerate lottoGenerate = new LottoGenerate();
        lottoGenerate.generate();
        List<Integer> lotto = lottoGenerate.getLotto();

        //당첨번호 지정
        SettingWinningNumber settingWinningNumber = new SettingWinningNumber();
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(10);
        set.add(13);
        set.add(21);
        set.add(26);
        set.add(31);

        List<Integer> winningLotto = new ArrayList<Integer>();
        winningLotto.addAll(set);
        Collections.sort(winningLotto);

        //보너스번호
        winningLotto.add(45);
        settingWinningNumber.winningNumSet(winningLotto);

        //당첨확인
        ConfirmWinning confirmWinning = new ConfirmWinning();
        String result = confirmWinning.confirmLotto(settingWinningNumber.getWinningLotto(),settingWinningNumber.getWinningBonusNum(),lotto);

        System.out.println("내가 구매한 로또 번호 : " + lotto);
        System.out.println("당첨번호 : " + settingWinningNumber.getWinningLotto() + " 보너스번호 : " + settingWinningNumber.getWinningBonusNum());
        System.out.println("당첨확인 : " + result);
    }
}