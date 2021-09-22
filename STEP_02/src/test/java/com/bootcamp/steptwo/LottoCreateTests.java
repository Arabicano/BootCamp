package com.bootcamp.steptwo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.steptwo.lotto.model.Lotto;
import com.bootcamp.steptwo.lotto.model.consts.LottoConst;
import com.bootcamp.steptwo.lotto.model.dto.LottoCreateDto;
import com.bootcamp.steptwo.lotto.service.CreateLottoService;
import com.bootcamp.steptwo.util.ApiCommonException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class LottoCreateTests {
    
    @Autowired
    private CreateLottoService createLottoService;

    @Test
    void 수동_로또숫자갯수_미달() {
        LottoCreateDto lottoCreateDto = new LottoCreateDto();

        Integer[] nums = { 1, 2, 3, 4 };
        List<Integer[]> nonAutoNumbers = new ArrayList<>();
        nonAutoNumbers.add(nums);
        lottoCreateDto.setNonAutoNumbers(nonAutoNumbers);

        ApiCommonException exception = assertThrows(ApiCommonException.class, () -> createLottoService.createLotto(lottoCreateDto));

        ApiCommonException expectedException = LottoConst.ErrorCode.THE_NUMBER_OF_NUMBERS_ERROR.throwException();

        assertEquals(expectedException.getHttpStatus(), exception.getHttpStatus());
        assertEquals(expectedException.getMessageCode(), exception.getMessageCode());
        assertEquals(expectedException.getMessage(), exception.getMessage());

        log.info("수동_로또숫자갯수_미달 오류 테스트 성공");

    }

    @Test
    void 수동_로또숫자갯수_초과() {
        LottoCreateDto lottoCreateDto = new LottoCreateDto();

        Integer[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        List<Integer[]> nonAutoNumbers = new ArrayList<>();
        nonAutoNumbers.add(nums);
        lottoCreateDto.setNonAutoNumbers(nonAutoNumbers);

        ApiCommonException exception = assertThrows(ApiCommonException.class,
                () -> createLottoService.createLotto(lottoCreateDto));

        ApiCommonException expectedException = LottoConst.ErrorCode.THE_NUMBER_OF_NUMBERS_ERROR.throwException();

        assertEquals(expectedException.getHttpStatus(), exception.getHttpStatus());
        assertEquals(expectedException.getMessageCode(), exception.getMessageCode());
        assertEquals(expectedException.getMessage(), exception.getMessage());

        log.info("수동_로또숫자갯수_초과 오류 테스트 성공");

    }

    @Test
    void 수동_중복으로_로또숫자갯수_오류() {
        LottoCreateDto lottoCreateDto = new LottoCreateDto();

        Integer[] nums = { 1, 1, 3, 4, 5, 6 };
        List<Integer[]> nonAutoNumbers = new ArrayList<>();
        nonAutoNumbers.add(nums);
        lottoCreateDto.setNonAutoNumbers(nonAutoNumbers);

        ApiCommonException exception = assertThrows(ApiCommonException.class,
                () -> createLottoService.createLotto(lottoCreateDto));

        ApiCommonException expectedException = LottoConst.ErrorCode.THE_NUMBER_OF_NUMBERS_ERROR.throwException();

        assertEquals(expectedException.getHttpStatus(), exception.getHttpStatus());
        assertEquals(expectedException.getMessageCode(), exception.getMessageCode());
        assertEquals(expectedException.getMessage(), exception.getMessage());

        log.info("수동_중복으로_로또숫자갯수_오류 오류 테스트 성공");
    }
    
    @Test
    void 수동_범위_아래_로또숫자() {
        LottoCreateDto lottoCreateDto = new LottoCreateDto();

        Integer[] nums = { 1, 2, 3, 4, 5, 0 };
        List<Integer[]> nonAutoNumbers = new ArrayList<>();
        nonAutoNumbers.add(nums);
        lottoCreateDto.setNonAutoNumbers(nonAutoNumbers);

        ApiCommonException exception = assertThrows(ApiCommonException.class,
                () -> createLottoService.createLotto(lottoCreateDto));

        ApiCommonException expectedException = LottoConst.ErrorCode.NOT_INCLUDED_LOTTO_NUMBER.throwException();

        assertEquals(expectedException.getHttpStatus(), exception.getHttpStatus());
        assertEquals(expectedException.getMessageCode(), exception.getMessageCode());
        assertEquals(expectedException.getMessage(), exception.getMessage());

        log.info("수동_범위_아래_로또숫자 오류 테스트 성공");
    }
    
    @Test
    void 수동_범위_위_로또숫자() {
        LottoCreateDto lottoCreateDto = new LottoCreateDto();

        Integer[] nums = { 1, 2, 3, 4, 5, 46 };
        List<Integer[]> nonAutoNumbers = new ArrayList<>();
        nonAutoNumbers.add(nums);
        lottoCreateDto.setNonAutoNumbers(nonAutoNumbers);

        ApiCommonException exception = assertThrows(ApiCommonException.class,
                () -> createLottoService.createLotto(lottoCreateDto));

        ApiCommonException expectedException = LottoConst.ErrorCode.NOT_INCLUDED_LOTTO_NUMBER.throwException();

        assertEquals(expectedException.getHttpStatus(), exception.getHttpStatus());
        assertEquals(expectedException.getMessageCode(), exception.getMessageCode());
        assertEquals(expectedException.getMessage(), exception.getMessage());

        log.info("수동_범위_위_로또숫자 오류 테스트 성공");
    }

    @Test
    void 자동_로또_갯수_오류() {
        LottoCreateDto lottoCreateDto = new LottoCreateDto();
        lottoCreateDto.setCountOfAuto(-2);

        ApiCommonException exception = assertThrows(ApiCommonException.class,
                () -> createLottoService.createLotto(lottoCreateDto));

        ApiCommonException expectedException = LottoConst.ErrorCode.COUNT_OF_AUTO_LOTTO_ERROR.throwException();

        assertEquals(expectedException.getHttpStatus(), exception.getHttpStatus());
        assertEquals(expectedException.getMessageCode(), exception.getMessageCode());
        assertEquals(expectedException.getMessage(), exception.getMessage());

        log.info("자동_로또_갯수_오류 오류 테스트 성공");
    }


    @Test
    void 자동_로또_생성() {
        LottoCreateDto lottoCreateDto = new LottoCreateDto();
        lottoCreateDto.setCountOfAuto(2);

        List<Lotto> createdLotto = createLottoService.createLotto(lottoCreateDto);
        
        // 자동생성된 lotto의 번호 갯수가 6개이고 1~45 사이의 숫자로 이루어졌는지 테스트
        int COUNT_OF_LOTTO_NUMBER = 6;
        for(Lotto lotto : createdLotto) {
            assertEquals(COUNT_OF_LOTTO_NUMBER, lotto.getNumbers().size());
            assertTrue(lotto.getNumbers().stream().allMatch(num -> num > 0 && num < 46));
        }

        log.info("자동_로또_생성 테스트 성공");
    }

    @Test
    void 토탈_로또_생성() {
        LottoCreateDto lottoCreateDto = new LottoCreateDto();
        lottoCreateDto.setCountOfAuto(3);

        List<Integer[]> nonAutoNumbers = new ArrayList<>();
        Integer[] nums1 = { 11, 20, 4, 25, 6, 30 };
        nonAutoNumbers.add(nums1);
        Integer[] nums2 = { 34, 2, 41, 39, 12, 44 };
        nonAutoNumbers.add(nums2);

        // 로또 생성
        List<Lotto> createdLotto = createLottoService.createLotto(lottoCreateDto);

        // 생성된 lotto의 번호 갯수가 6개이고 1~45 사이의 숫자로 이루어졌는지 테스트
        int COUNT_OF_LOTTO_NUMBER = 6;
        for (Lotto lotto : createdLotto) {
            assertEquals(COUNT_OF_LOTTO_NUMBER, lotto.getNumbers().size());
            assertTrue(lotto.getNumbers().stream().allMatch(num -> num > 0 && num < 46));
        }

        log.info("토탈_로또_생성 테스트 성공");
    }

}
