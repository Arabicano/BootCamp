package com.bootcamp.lotto.model.consts;

import com.bootcamp.utils.HandleException;

public class LottoConst {
    
    public enum ErrorCode {
        LESS_THAN_PRICE("400-1", "낸 금액이 로또의 가격보다 작습니다."),
        THE_NUMBER_OF_NUMBERS_ERROR("400-2", "로또 숫자가 5개가 아닙니다."),
        NOT_INCLUDED_LOTTO_NUMBER("400-3", "로또 범위(1~45)에 포함된 숫자가 아닙니다."),
        COUNT_OF_AUTO_LOTTO_ERROR("400-4", "자동 로또 구매 갯수가 잘 못 되었습니다."),
        NOT_EXIST_LOTTO("400-5", "비교할 로또가 존재하지 않습니다."),
        ;

        private String status;
        private String message;

        ErrorCode(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public HandleException throwException() {
            return new HandleException(this.status, this.message);
        }

    }
}
