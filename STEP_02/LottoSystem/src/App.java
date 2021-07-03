import java.util.Set;

import lotto.model.Lotto;
import lotto.service.LottoService;

public class App {
    
    public static void main(String[] args) throws Exception {
        LottoService lottoService = new LottoService();   
        Set<Lotto> lottos = lottoService.buyLotto();
        lottoService.getResultOfLotto(lottos);
    }
}
