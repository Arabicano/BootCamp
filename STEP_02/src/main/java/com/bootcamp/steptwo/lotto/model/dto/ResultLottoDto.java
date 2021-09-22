package com.bootcamp.steptwo.lotto.model.dto;


import com.bootcamp.steptwo.lotto.model.Lotto;

import lombok.Data;

@Data
public class ResultLottoDto {

    private Lotto lotto;
    private int rank;
    private long prize;
    
}
