import lotto.service.LottoService;

public class App {
    
    public static void main(String[] args) throws Exception {
        LottoService lottoService = new LottoService();   
        lottoService.buyLotto();
    }
}
