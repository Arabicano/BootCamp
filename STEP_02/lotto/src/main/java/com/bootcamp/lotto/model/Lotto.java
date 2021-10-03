package com.bootcamp.lotto.model;

import java.util.Set;

import lombok.Getter;

@Getter
public class Lotto {
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
