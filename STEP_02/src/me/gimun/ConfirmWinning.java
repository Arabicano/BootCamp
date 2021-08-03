package me.gimun;

import me.gimun.domain.LottoNumber;

import java.util.List;

public class ConfirmWinning {
    public String confirmLotto(List<Integer> winningLotto, int bonus, List<LottoNumber> buyLotto){
        String result = "";
        int winNumbers = 0;
        boolean isBonus = false;
        for (int i = 0; i < buyLotto.size(); i++) {
            for (int j = 0; j < winningLotto.size(); j++) {
                if (buyLotto.get(i).getNumber() == winningLotto.get(j)) {
                    winNumbers++;
                }
                else if(buyLotto.get(i).getNumber() != winningLotto.get(j)
                            && buyLotto.get(i).getNumber() == bonus){
                    isBonus = true;
                }
            }
        }


        return resultView(winNumbers, isBonus);
    }

    public String resultView(int winNumber,boolean isBonus){
        String result = null;
        switch (winNumber){
            case 6:
                result = "1등";
                break;
            case 5:
                if(isBonus){
                    result = "2등";
                }else{
                    result = "낙첨";
                }
                break;
            case 4:
                result = "3등";
                break;
            case 3:
                result = "4등";
                break;
            default:
                result = "낙첨";
                break;
        }
        return result;
    }

}
