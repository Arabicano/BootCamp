package me.gimun;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SetWinNumTest {

    @Test
    void 당첨번호설정() {

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


        SetWinNum setWinNum = new SetWinNum(winningLotto);

        //보너스번호
        setWinNum.setBounsNum(45);

        assertEquals(setWinNum.getWinningLotto().get(0),3);
        assertEquals(setWinNum.getWinningLotto().get(5),31);
        assertEquals(setWinNum.getWinningBonusNum(),45);


        System.out.println("winningLotto : " + setWinNum.getWinningLotto());
        System.out.println("winningBonus : " + setWinNum.getWinningBonusNum());
    }
}