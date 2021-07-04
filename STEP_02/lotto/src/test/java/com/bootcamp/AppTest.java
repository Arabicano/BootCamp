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

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private static LottoService lottoService;

    @BeforeClass
    static void initAll() {
        lottoService = new LottoService();
    }

// CASE1. 로또 구매 시
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
}
