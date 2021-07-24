package me.gimun;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SettingWinningNumberTest {

    @Test
    void 당첨번호설정() {
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

        assertEquals(settingWinningNumber.getWinningLotto().get(0),3);
        assertEquals(settingWinningNumber.getWinningLotto().get(5),31);
        assertEquals(settingWinningNumber.getWinningBonusNum(),45);


        System.out.println("winningLotto : " + settingWinningNumber.getWinningLotto());
        System.out.println("winningBonus : " + settingWinningNumber.getWinningBonusNum());
    }
}