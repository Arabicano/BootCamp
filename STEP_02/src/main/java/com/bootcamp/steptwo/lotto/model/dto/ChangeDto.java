package com.bootcamp.steptwo.lotto.model.dto;

import lombok.Data;

@Data
public class ChangeDto { // 로또 구매에 따른 로또 갯수와 나머지 반환 Dto

    private int lottoCount;
    private int change;

    public ChangeDto(int lottoCount, int change) {
        this.lottoCount = lottoCount;
        this.change = change;
    }

}