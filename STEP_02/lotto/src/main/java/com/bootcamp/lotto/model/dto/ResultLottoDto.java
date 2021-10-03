package com.bootcamp.lotto.model.dto;


import com.bootcamp.lotto.model.Lotto;

import lombok.Data;

@Data
public class ResultLottoDto {

    private Lotto lotto;
    private int rank;
    private long prize;

    public String toString() {
        String result = lotto.toString();
        if(rank != 0) {
            result += " " + this.rank + "등 - 당청금: " + this.prize; 
        }
        return result;
    }
    
}
