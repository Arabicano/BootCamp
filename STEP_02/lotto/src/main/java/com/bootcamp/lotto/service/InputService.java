package com.bootcamp.lotto.service;

import java.util.List;

public interface InputService {

    boolean insertYn(String currentStep, String closeMsg, String nextStep) throws Exception;

    Integer insertInteger() throws Exception;

    void insertNonAutoLottoNumbers(List<Integer[]> numAutoNumbers, int totalLottoCount) throws Exception;
}
