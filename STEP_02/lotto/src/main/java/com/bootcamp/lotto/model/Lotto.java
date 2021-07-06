package com.bootcamp.lotto.model;

import java.util.Set;
import java.util.TreeSet;

public class Lotto {
    private TreeSet<Integer> numbers;
    private int bonusNum;

    public Lotto(Set<Integer> lottoSet) {
        this.numbers = (TreeSet<Integer>) lottoSet;
    }

    public Lotto(Set<Integer> lottoSet, int bonusNum) {
        this.numbers = (TreeSet<Integer>) lottoSet;
        this.bonusNum = bonusNum;
    }

    public TreeSet<Integer> getNumbers() {
        return this.numbers;
    }

    public int getBonusNum() {
        return this.bonusNum;
    }

    public void setBonusNum(int bonusNum) {
        this.bonusNum = bonusNum;
    }

    public String toString() {
        String result = "LOTTO : [ ";
        for (Integer e : this.numbers) {
            result += (e + " ");
        }
        if (bonusNum != 0) {
            result += "(" + this.bonusNum + ") ";
        }
        result += "]";
        return result;
    }
}
