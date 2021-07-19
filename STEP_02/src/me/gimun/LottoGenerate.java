package me.gimun;

import java.util.*;

public class LottoGenerate {
    private List<Integer> lotto;
    private int bonusNum;

    public LottoGenerate() {
        this.lotto = new ArrayList<Integer>();
    }

    public void generate(){
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        while (set.size() != 7){
            set.add(random.nextInt(45 ) + 1);
        }

        this.lotto.addAll(set);

        //보너스 번호 추출
        this.bonusNum = this.lotto.get(6);
        this.lotto.remove(6);

        // 정렬
        Collections.sort(this.lotto);

    }

    public List<Integer> getLotto() {
        return lotto;
    }

    public int getBonusNum() {
        return bonusNum;
    }

}
