package com.bootcamp.lotto.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputService {
    /**
     * 사용자로부터 값 입력 서비스
     */

    Scanner sc = new Scanner(System.in);

    public int reqInputInteger(String[] reqMsg, String errorMsg) {
        int intputInteger = -1;
        do {
            try {
                for(int i = 0; i < reqMsg.length; i++) {
                    System.out.println(reqMsg[i]);
                }
                intputInteger = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(errorMsg);
                sc.nextLine();
                continue;
            }
        } while (intputInteger < 0);
        return intputInteger;
    }

}
