package me.gimun;

import java.util.ArrayList;
import java.util.List;

public class SetWinNum {
    private List<Integer> winningLotto;
    private int winningBonusNum;

    public SetWinNum(List<Integer> lotto) {
        this.winningLotto = new ArrayList<>();
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
