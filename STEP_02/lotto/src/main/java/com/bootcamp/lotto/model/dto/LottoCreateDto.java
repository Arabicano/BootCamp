package com.bootcamp.lotto.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class LottoCreateDto {
    
    private Integer countOfAuto;

    List<Integer[]> nonAutoNumbers;

}
