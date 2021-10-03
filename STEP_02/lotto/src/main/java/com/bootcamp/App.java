package com.bootcamp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.bootcamp.lotto.model.Lotto;
import com.bootcamp.lotto.model.dto.ChangeDto;
import com.bootcamp.lotto.model.dto.LottoCreateDto;
import com.bootcamp.lotto.model.dto.WinningLottoDto;
import com.bootcamp.lotto.service.BuyLottoService;
import com.bootcamp.lotto.service.CreateLottoService;
import com.bootcamp.lotto.service.InputService;
import com.bootcamp.lotto.service.WinLottoService;
import com.bootcamp.lotto.service.impl.BuyLottoServiceImpl;
import com.bootcamp.lotto.service.impl.CreateLottoServiceImpl;
import com.bootcamp.lotto.service.impl.InputServiceImpl;
import com.bootcamp.lotto.service.impl.WinLottoServiceImpl;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {
        InputStream in = System.in;
        InputService inputService = new InputServiceImpl(in);

        System.out.println("★ 로또 구매 서비스! ★");

        // 서비스 이용여부 확인
        if(!inputService.insertYn("로또를 구매하시겠습니까? (Y/N)", "로또 구매 서비스 종료. 이용해주셔서 감사합니다.", "로또 구매")){
            in.close();
            return;
        }

        // 로또 구매
        System.out.println("로또 구매 금액을 입력해주세요. (단위: 원)");
        Integer money = inputService.insertInteger();
        BuyLottoService buyLottoService = new BuyLottoServiceImpl();
        ChangeDto change = buyLottoService.buyLotto(money);
        System.out.println("구매 로또: " + change.getLottoCount() + "장");
        System.out.println("거스름돈: " + change.getChange() + "원");

        // 로또 번호 생성
        LottoCreateDto lottoCreateDto = new LottoCreateDto();
        CreateLottoService createLottoService = new CreateLottoServiceImpl();

        System.out.println();
        System.out.println("----------------- " + "로또 번호 수동 입력" + " -----------------");
        if(inputService.insertYn("로또 번호를 수동으로 입력하시겠습니까? (Y/N)", "", "로또 번호 입력")) {
            // 수동 로또 번호 입력
            List<Integer[]> numAutoNumbers = new ArrayList<>();
            inputService.insertNonAutoLottoNumbers(numAutoNumbers, change.getLottoCount());

            lottoCreateDto.setNonAutoNumbers(numAutoNumbers);
            lottoCreateDto.setCountOfAuto(change.getLottoCount() - numAutoNumbers.size());
        } else {
            // 모두 자동 로또로
            lottoCreateDto.setCountOfAuto(change.getLottoCount());
        }

        List<Lotto> lottos = createLottoService.createLotto(lottoCreateDto);
        
        // 구매한 로또 번호 출력
        System.out.println();
        System.out.println("----------------- " + "구매한 로또 번호 출력" + " -----------------");
        for(Lotto lotto : lottos) {
           System.out.println(lotto.toString());
        }
        
        // 로또 결과
        System.out.println();
        System.out.println("로또 결과를 확인합니다.");
        System.out.println("----------------- " + "로또 결과" + " -----------------");
        WinLottoService winLottoService = new WinLottoServiceImpl();
        WinningLottoDto winResult = winLottoService.getResultLotto(lottos);

        System.out.println(winResult.toString());
        System.out.println("---------------------------------------------");
        System.out.println();

        // 로또 서비스 종료
        System.out.println("★ 로또 구매 서비스 종료. 이용해주셔서 감사합니다. ★");
        in.close();
    }
}
