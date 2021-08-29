package me.gimun;

import java.util.ArrayList;
import java.util.List;

public class SetWinNum {
    private List<Integer> winningLotto;
    private int winningBonusNum;

    public SetWinNum(List<Integer> lotto) {
        this.winningLotto = new ArrayList<>();
        //로또 길이가 6이 아니면 에러
        if(lotto.size() != 6){
            throw new IllegalArgumentException("Lotto 길이가 6이 아닙니다.");
        }

        this.winningLotto = lotto;
    }

    public void setBounsNum(int bounsNum){
        this.winningBonusNum = bounsNum;
    }


    public List<Integer> getWinningLotto() {
        return winningLotto;
    }

    public int getWinningBonusNum() {
        return winningBonusNum;
    }
}
