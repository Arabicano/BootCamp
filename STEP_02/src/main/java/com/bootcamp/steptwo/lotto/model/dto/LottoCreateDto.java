package com.bootcamp.steptwo.lotto.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class LottoCreateDto {
    
    private Integer countOfAuto;

    List<Integer[]> nonAutoNumbers;

}
