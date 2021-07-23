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

    public void winningNumSet(List<Integer> lotto){
        //로또 길이가 7이 아니면 에러
        if(lotto.size() != 7){
            throw new IllegalArgumentException();
        }

        this.winningBonusNum = lotto.get(lotto.size()-1);
        lotto.remove(lotto.size()-1);
        this.winningLotto = lotto;

    }

    public List<Integer> getWinningLotto() {
        return winningLotto;
    }

    public int getWinningBonusNum() {
        return winningBonusNum;
    }
}
