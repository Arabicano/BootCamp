package com.bootcamp.steptwo.lotto.model.consts;

import com.bootcamp.steptwo.util.ApiCommonException;

import org.springframework.http.HttpStatus;

public class LottoConst {
    
    public enum ErrorCode {
        LESS_THAN_PRICE(HttpStatus.BAD_REQUEST, "400", "낸 금액이 로또의 가격보다 작습니다.");

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
