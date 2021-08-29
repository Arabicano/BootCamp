package me.gimun;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticWinningTest {

    @Test
    void 당첨통계() {
        StatisticWinning statisticWinning = new StatisticWinning();
        List<String> list = new ArrayList<String>();
        list.add("4등");
        list.add("낙첨");
        list.add("4등");
        list.add("낙첨");
        statisticWinning.calculate(list, 4);
        assertEquals(statisticWinning.getRateOfReturn(),2400);
        assertEquals(statisticWinning.getFourthSumCnt(),2);
        assertEquals(statisticWinning.getFourthSumWinningPrice(),100000);
        System.out.println("수익률 : " +statisticWinning.getRateOfReturn());
    }
}