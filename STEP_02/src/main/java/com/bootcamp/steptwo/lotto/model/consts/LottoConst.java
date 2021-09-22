package com.bootcamp.steptwo.lotto.model.consts;

import com.bootcamp.steptwo.util.ApiCommonException;

import org.springframework.http.HttpStatus;

public class LottoConst {
    
    public enum ErrorCode {
        LESS_THAN_PRICE(HttpStatus.BAD_REQUEST, "400-1", "낸 금액이 로또의 가격보다 작습니다."),
        THE_NUMBER_OF_NUMBERS_ERROR(HttpStatus.BAD_REQUEST, "400-2", "로또 숫자가 5개가 아닙니다."),
        NOT_INCLUDED_LOTTO_NUMBER(HttpStatus.BAD_REQUEST, "400-3", "로또 범위(1~45)에 포함된 숫자가 아닙니다."),
        COUNT_OF_AUTO_LOTTO_ERROR(HttpStatus.BAD_REQUEST, "400-4", "자동 로또 구매 갯수가 잘 못 되었습니다."),
        ;

        private HttpStatus status;
        private String messageCode;
        private String message;

        ErrorCode(HttpStatus status, String messageCode, String message) {
            this.status = status;
            this.messageCode = messageCode;
            this.message = message;
        }

        public ApiCommonException throwException() {
            return new ApiCommonException(this.status, this.messageCode, this.message);
        }

    }
}
