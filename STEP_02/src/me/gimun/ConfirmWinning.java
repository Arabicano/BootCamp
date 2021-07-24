package me.gimun;

import java.util.List;

public class ConfirmWinning {
    public String confirmLotto(List<Integer> winningLotto, int bonus, List<Integer> buyLotto){
        String result = "";
        int winNumbers = 0;
        boolean isBonus = false;
        for (int i = 0; i < buyLotto.size(); i++) {
            for (int j = 0; j < winningLotto.size(); j++) {
                if (buyLotto.get(i) == winningLotto.get(j)) {
                    winNumbers++;
                }
                else if(buyLotto.get(i) != winningLotto.get(j)
                            && buyLotto.get(i) == bonus){
                    isBonus = true;
                }
            }
        }
        switch (winNumbers){
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
