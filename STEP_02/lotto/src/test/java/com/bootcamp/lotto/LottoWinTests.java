package com.bootcamp.lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.lotto.model.Lotto;
import com.bootcamp.lotto.model.consts.LottoConst;
import com.bootcamp.lotto.model.dto.LottoCreateDto;
import com.bootcamp.lotto.model.dto.WinningLottoDto;
import com.bootcamp.lotto.service.CreateLottoService;
import com.bootcamp.lotto.service.WinLottoService;
import com.bootcamp.lotto.service.impl.CreateLottoServiceImpl;
import com.bootcamp.lotto.service.impl.WinLottoServiceImpl;
import com.bootcamp.utils.HandleException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LottoWinTests {
    
    private WinLottoService winLottoService;

    private CreateLottoService createLottoService;

    @BeforeEach
    void setUp() {
        winLottoService = new WinLottoServiceImpl();
        createLottoService = new CreateLottoServiceImpl();
    }


    @Test
    void 비교할_로또가_없음() {
        List<Lotto> lottos = new ArrayList<>();
        
        HandleException exception = assertThrows(HandleException.class,
                () -> winLottoService.getResultLotto(lottos));

        HandleException expectedException = LottoConst.ErrorCode.NOT_EXIST_LOTTO.throwException();

        assertEquals(expectedException.getCode(), exception.getCode());
        assertEquals(expectedException.getMessage(), exception.getMessage());

        log.info("비교할_로또가_없음 오류 테스트 성공");
    }

    @Test
    void 로또_당첨_확인() {
        // 로또 생성
        LottoCreateDto lottoCreateDto = new LottoCreateDto();
        lottoCreateDto.setCountOfAuto(1000);

        List<Lotto> createdLotto = createLottoService.createLotto(lottoCreateDto);

        // 로또 당첨 확인
        WinningLottoDto winningLottoDto = winLottoService.getResultLotto(createdLotto);

        log.info("로또_당첨_확인 결과: "+ winningLottoDto.toString());
    }

}
