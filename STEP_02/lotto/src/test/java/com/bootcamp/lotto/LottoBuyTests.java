package com.bootcamp.lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bootcamp.lotto.model.consts.LottoConst;
import com.bootcamp.lotto.model.dto.ChangeDto;
import com.bootcamp.lotto.service.BuyLottoService;
import com.bootcamp.lotto.service.impl.BuyLottoServiceImpl;
import com.bootcamp.utils.HandleException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LottoBuyTests {
    
    // 로또 구매 서비스
    private BuyLottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new BuyLottoServiceImpl();
    }

    @Test
    void 로또구매_금액미달() {
        int money = 300;
        HandleException exception = assertThrows(HandleException.class, () -> lottoService.buyLotto(money));

        HandleException expectedException = LottoConst.ErrorCode.LESS_THAN_PRICE.throwException();

        assertEquals(expectedException.getCode(), exception.getCode());
        assertEquals(expectedException.getMessage(), exception.getMessage());

        log.info("로또구매_금액미달 오류 테스트 성공");
        // HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        // String expectedMessage = "낸 금액이 로또의 가격보다 작습니다.";
        // assertEquals(expectedStatus, exception.getHttpStatus());
        // assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void 로또구매_성공() {
        int money = 12300;

        ChangeDto result = lottoService.buyLotto(money);

        int expectedLottoCount = 12;
        int expectedChnage = 300;
        assertEquals(expectedLottoCount, result.getLottoCount());
        assertEquals(expectedChnage, result.getChange());

        log.info("로또구매_성공 테스트 성공");
    }
}
