package com.bootcamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.bootcamp.lotto.service.LottoService;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import jdk.jfr.Timestamp;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static LottoService lottoService;

    @BeforeClass
    static void initAll() {
        lottoService = new LottoService();
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

    @test
    public void 로또값보다작은값입력() {
        // TODO
    }

    @test
    public void 로또값보다큰값입력() {
        // TODO : success
    }

    // STEP2. 수동 로또장수 입력
    @test
    void 수동로또숫자값외의잘못된값입력() {
        // TODO
    }

    @test
    public void 총로또장수보다큰값입력() {
        // TODO
    }

    @test
    public void 마이너스값입력() {
        // TODO
    }
    
    @test
    public void 총로또장수보다작지만영이상의값입력시() {
        // TODO : success
    }

    // STEP3. 수동 로또번호 입력
    @test
    void 로또번호숫자값외의잘못된값입력() {
        // TODO
    }
    
    @test
    void 영이하의값입력() {
        // TODO
    }

    @test
    void 사십오초과값입력() {
        // TODO
    }

    @test
    void 이미입력한로또번호재입력시() {
        // TODO
    }

    @test
    void 여섯의중복되지않은숫자값입력시() {
        // TODO : success
    }

    // STEP4. 자동로또번호 출력

    // STEP5. 로또당첨번호 입력
    @test
    void 당첨번호입력시숫자외의잚못된값입력() {
        // TODO
    }

    @test
    void 영이하의값또는사십오초과의값입력() {
        // TODO
    }

    @test
    void 이미입력한당첨번호중복입력시() {
        // TODO
    }

    @test
    void 일곱의중복되지않은당첨번호입력시() {
        // TODO : success
    }
}
