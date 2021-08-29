package me.gimun;

import java.util.List;

public class StatisticWinning {
    private int firstSumCnt;
    private int firstSumWinningPrice;

    private int secondSumCnt;
    private int secondSumWinningPrice;

    private int thirdSumCnt;
    private int thirdSumWinningPrice;

    private int fourthSumCnt;
    private int fourthSumWinningPrice;

    private int rateOfReturn;

    public void calculate(List<String> winningType, int buyCnt){
        for (int i=0; i<winningType.size();i++){
            switch (winningType.get(i)){
                case "1등":
                    this.firstSumCnt += 1;
                    this.firstSumWinningPrice += 100000000;
                    break;
                case "2등":
                    this.secondSumCnt += 1;
                    this.secondSumWinningPrice += 10000000;
                    break;
                case "3등":
                    this.thirdSumCnt += 1;
                    this.thirdSumWinningPrice += 1000000;
                    break;
                case "4등":
                    this.fourthSumCnt += 1;
                    this.fourthSumWinningPrice += 50000;
                    break;
                default:
                    break;
            }
        }
        int useAmount = buyCnt * 1000;
        this.rateOfReturn = ((( this.firstSumWinningPrice
                + this.secondSumWinningPrice
                + this.thirdSumWinningPrice
                + this.fourthSumWinningPrice) - useAmount) / useAmount ) * 100;

    }

    public int getFirstSumCnt() {
        return firstSumCnt;
    }

    public int getFirstSumWinningPrice() {
        return firstSumWinningPrice;
    }

    public int getSecondSumCnt() {
        return secondSumCnt;
    }

    public int getSecondSumWinningPrice() {
        return secondSumWinningPrice;
    }

    public int getThirdSumCnt() {
        return thirdSumCnt;
    }

    public int getThirdSumWinningPrice() {
        return thirdSumWinningPrice;
    }

    public int getFourthSumCnt() {
        return fourthSumCnt;
    }

    public int getFourthSumWinningPrice() {
        return fourthSumWinningPrice;
    }

    public int getRateOfReturn() {
        return rateOfReturn;
    }
}
