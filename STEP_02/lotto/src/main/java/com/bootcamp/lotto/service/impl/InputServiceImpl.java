package com.bootcamp.lotto.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.bootcamp.lotto.model.consts.LottoConst;
import com.bootcamp.lotto.service.InputService;

public class InputServiceImpl implements InputService {

    final InputStreamReader reader;
    final BufferedReader bufferedReader;

    public InputServiceImpl(InputStream in) {
        this.reader = new InputStreamReader(in);
        this.bufferedReader = new BufferedReader(reader);
    } 

    @Override
    public boolean insertYn(String currentStep, String closeMsg, String nextStep) throws Exception {
        boolean whileFlag = true;
        String flagAnswer = "";
        
        do {
            System.out.println(currentStep);
            flagAnswer = bufferedReader.readLine();
            if (flagAnswer.toLowerCase().equals("y") || flagAnswer.toLowerCase().equals("yes")) { // Y or YES
                System.out.println();
                System.out.println("----------------- "+ nextStep +" -----------------");
                return true;
            } else if (flagAnswer.toLowerCase().equals("n") || flagAnswer.toLowerCase().equals("no")) { // N or NO
                System.out.println(closeMsg);
                return false;
            } else { // 그외
                System.out.println("다시 입력해주세요.");
            }
        } while (whileFlag);
        return !whileFlag;
    }
    
    @Override
    public Integer insertInteger() throws Exception {
        Integer number = 0;
        
        do {
            try {
                String integerValue = bufferedReader.readLine();
                number = Integer.parseInt(integerValue);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("다시 입력해주세요.");
            }
        } while(true);
    }

    @Override
    public void insertNonAutoLottoNumbers(List<Integer[]> numAutoNumbers, int totalLottoCount) throws Exception {
        do {
            // 수동 로또 번호 입력
            System.out.println("> 수동 로또 : 6개의 숫자를 띄어쓰기로 구분하여 입력해주세요. (ex. 3 5 20 34 55 66)");
            System.out.println("> 더이상 입력하지 않으시려면 'E' 또는 'END'를 입력해주세요.");
            String nonAutoNum = bufferedReader.readLine();
            if (nonAutoNum.toLowerCase().equals("e") || nonAutoNum.toLowerCase().equals("end")) {
                return;
            }
            String[] stringNums = nonAutoNum.split(" ");
            // 중복 확인 및 숫자가 1~45 숫자인지 확인
            Set<Integer> set = new TreeSet<>();

            boolean flag = false;
            for(String str : stringNums) {
                try {
                    int num = Integer.parseInt(str);
                    // 숫자값이 1~45 내의 범위가 아닌 경우
                    if(num < LottoConst.MIN_LOTTO_NUM || num > LottoConst.MAX_LOTTO_NUM) {
                        System.out.println(LottoConst.ErrorCode.NOT_INCLUDED_LOTTO_NUMBER.throwException().getMessage());
                        flag = true;
                        break;
                    }
                    set.add(num);
                } catch (NumberFormatException e) {
                    System.out.println("숫자 외의 값을 잘못 입력하셨습니다. 다시 입력해주세요");
                    flag = true;
                    continue;
                }
            }
            if(flag) {
                System.out.println();
                continue;
            }
            // 숫자 갯수 확인
            if (set.size() != 6) {
                System.out.println("6개의 숫자가 아닙니다. 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            System.out.println("\n* 입력 번호: " + set.toString());
            System.out.println();
            Integer[] numbsrs = set.toArray(new Integer[0]);

            numAutoNumbers.add(numbsrs);
        } while (numAutoNumbers.size() < totalLottoCount);
        
    }

}
