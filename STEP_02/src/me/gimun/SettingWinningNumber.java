package me.gimun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingWinningNumber {
//    로또 당첨 번호 지정
//    로또 당첨 번호를 수동으로 지정해야 한다.
    private List<Integer> winningLotto;
    private int winningBonusNum;

    public SettingWinningNumber() {
        this.winningLotto = new ArrayList<Integer>();
    }

    public void winningNumSet(int[] nums, int bonus){
        if(nums.length != 6){
            throw new IllegalArgumentException();
        }
        Arrays.stream(nums).forEach(n -> this.winningLotto.add(n));
        this.winningBonusNum = bonus;
    }

    public List<Integer> getWinningLotto() {
        return winningLotto;
    }

    public int getWinningBonusNum() {
        return winningBonusNum;
    }
}
