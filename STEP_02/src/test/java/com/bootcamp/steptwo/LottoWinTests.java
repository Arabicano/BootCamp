package com.bootcamp.steptwo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.steptwo.lotto.model.Lotto;
import com.bootcamp.steptwo.lotto.model.consts.LottoConst;
import com.bootcamp.steptwo.lotto.model.dto.LottoCreateDto;
import com.bootcamp.steptwo.lotto.model.dto.WinningLottoDto;
import com.bootcamp.steptwo.lotto.service.CreateLottoService;
import com.bootcamp.steptwo.lotto.service.WinLottoService;
import com.bootcamp.steptwo.util.ApiCommonException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class LottoWinTests {
    
    @Autowired
    private WinLottoService winLottoService;

    @Autowired
    private CreateLottoService createLottoService;

    @Test
    void 비교할_로또가_없음() {
        List<Lotto> lottos = new ArrayList<>();
        
        ApiCommonException exception = assertThrows(ApiCommonException.class,
                () -> winLottoService.getResultLotto(lottos));

        ApiCommonException expectedException = LottoConst.ErrorCode.NOT_EXIST_LOTTO.throwException();

        assertEquals(expectedException.getHttpStatus(), exception.getHttpStatus());
        assertEquals(expectedException.getMessageCode(), exception.getMessageCode());
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
