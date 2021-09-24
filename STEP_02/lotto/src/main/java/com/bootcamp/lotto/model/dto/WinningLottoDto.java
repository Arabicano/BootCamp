package com.bootcamp.lotto.model.dto;

import java.util.List;

import com.bootcamp.lotto.model.Lotto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WinningLottoDto {

    private Lotto winningLotto;
    private List<ResultLottoDto> results;
    private long totalPrize;
    private double profitRate;

    @Override
    public String toString() {
        // 당첨 확인
        int first, second, third, fourth, fifth;
        first = second = third = fourth = fifth = 0;
        for (ResultLottoDto result : this.results) {
            switch (result.getRank()) {
                case 1:
                    first++;
                    break;
                case 2:
                    second++;
                    break;
                case 3:
                    third++;
                    break;
                case 4:
                    fourth++;
                    break;
                case 5:
                    fifth++;
                    break;
                default:
                    ;
            }
        }

        return "1등: " + first + "개, 2등: " + second + "개, 3등: " + third + "개, 4등: " + fourth + "개, 5등: " + fifth
                + "개입니다. \n" + "당첨금은 " + totalPrize +"원이고 " + "최종 수익률은 " + profitRate + "% 입니다.";
    }

}
