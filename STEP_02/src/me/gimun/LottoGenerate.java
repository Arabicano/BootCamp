package me.gimun;

import java.util.*;

public class LottoGenerate {
    private List<Integer> lotto;

    public LottoGenerate() {
        this.lotto = new ArrayList<Integer>();
    }

    public void generate(){
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        while (set.size() != 6){
            set.add(random.nextInt(45 ) + 1);
        }

        this.lotto.addAll(set);

        // 정렬
        Collections.sort(this.lotto);

    }

    public List<Integer> getLotto() {
        return lotto;
    }


}
