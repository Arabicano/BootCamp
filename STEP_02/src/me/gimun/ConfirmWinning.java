package me.gimun;

import me.gimun.domain.LottoNumber;

import java.util.HashMap;
import java.util.List;

public class ConfirmWinning {
    public String confirmLotto(List<Integer> winningLotto, int bonus, List<LottoNumber> buyLotto){
        int winNumbers = 0;
        boolean isBonus = false;

        // 기존방식
//        long startTime = System.nanoTime();
//        for (LottoNumber lotto : buyLotto) {
//            for (Integer winning : winningLotto) {
//                if (lotto.getNumber() == winning) {
//                    winNumbers++;
//                } else if (lotto.getNumber() != winning
//                        && lotto.getNumber() == bonus) {
//                    isBonus = true;
//                }
//            }
//        }

        HashMap<Integer, Object> hashMap = new HashMap<>();
        for (int i = 0; i< winningLotto.size(); i++) {
            hashMap.put(winningLotto.get(i),i+1);
        }

        for (LottoNumber lotto : buyLotto) {
            int key = (int) hashMap.getOrDefault(lotto.getNumber(),0);
            if(key != 0){
                winNumbers++;
            }else if (key == 0 && lotto.getNumber() == bonus) {
                isBonus = true;
            }
        }

        return resultView(winNumbers, isBonus);
    }

    public String resultView(int winNumber,boolean isBonus){
        String result;
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
