package com.bootcamp.lotto.model.dto;


import com.bootcamp.lotto.model.Lotto;

import lombok.Data;

@Data
public class ResultLottoDto {

    private Lotto lotto;
    private int rank;
    private long prize;
    
}
