package com.bootcamp.steptwo.lotto.model;

import java.util.Set;

import lombok.Getter;

@Getter
public class Lotto {
    public static final int LOTTO_PRICE = 1_000;

    private Set<Integer> numbers;
    private Integer bonusNum;


    public Lotto(Set<Integer> numbers) {
        this.numbers = numbers;
    }

    public Lotto(Set<Integer> numbers, Integer bonusNum) {
        this.numbers = numbers;
        this.bonusNum = bonusNum;
    }
}
