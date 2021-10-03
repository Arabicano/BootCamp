package com.bootcamp.lotto.model.dto;

import java.util.Set;
import java.util.TreeSet;

import com.bootcamp.lotto.model.Lotto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LottoDto {
 
    private Set<Integer> numbers;

    public Lotto toLotto() {
        Set<Integer> numbers = new TreeSet<>();
        for(Integer i : this.numbers) {
            numbers.add(i);
        }
        return new Lotto(numbers);
    }
}
