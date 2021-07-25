package com.bootcamp;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.bootcamp.lotto.service.InputService;
import com.bootcamp.lotto.service.LottoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static LottoService lottoService;
    private static InputService inputservice;

    @BeforeEach
    void initAll() {
        lottoService = new LottoService();
        inputservice = new InputService();
    }
    
    // Test1
    @Test
    public void 로또구매값받기() {
        String[] reqMsg = { "로또는 한 장당 1000원으로 판매됩니다.", "로또 구매를 위한 금액을 입력해주세요(숫자만입력)." };
        String errorMsg = "잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.";

        inputservice.reqInputInteger(reqMsg, errorMsg);
    }

    // STEP1. 로또 구매 시
    @Test
    public void 로또구매숫자값외의잘못된값입력() {
        
        // 숫자값외의 잘못된 값입력
        String input = "qwer";
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // lottoService.buyLotto();
        String expectErrorMessage = "잘못된 값을 입력하였습니다. 다시 입력하여 주십시오.";

        assertEquals(expectErrorMessage, lottoService.buyLotto());
    }

    @Test
    public void 로또값보다작은값입력() {
        // TODO
    }

    @Test
    public void 로또값보다큰값입력() {
        // TODO : success
    }

    // STEP2. 수동 로또장수 입력
    @Test
    void 수동로또숫자값외의잘못된값입력() {
        // TODO
    }

    @Test
    public void 총로또장수보다큰값입력() {
        // TODO
    }

    @Test
    public void 마이너스값입력() {
        // TODO
    }
    
    @Test
    public void 총로또장수보다작지만영이상의값입력시() {
        // TODO : success
    }

    // STEP3. 수동 로또번호 입력
    @Test
    void 로또번호숫자값외의잘못된값입력() {
        // TODO
    }
    
    @Test
    void 영이하의값입력() {
        // TODO
    }

    @Test
    void 사십오초과값입력() {
        // TODO
    }

    @Test
    void 이미입력한로또번호재입력시() {
        // TODO
    }

    @Test
    void 여섯의중복되지않은숫자값입력시() {
        // TODO : success
    }

    // STEP4. 자동로또번호 출력

    // STEP5. 로또당첨번호 입력
    @Test
    void 당첨번호입력시숫자외의잚못된값입력() {
        // TODO
    }

    @Test
    void 영이하의값또는사십오초과의값입력() {
        // TODO
    }

    @Test
    void 이미입력한당첨번호중복입력시() {
        // TODO
    }

    @Test
    void 일곱의중복되지않은당첨번호입력시() {
        // TODO : success
    }
}
