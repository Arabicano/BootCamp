package me.gimun;

public class BuyLotto {
/*    입력한 금액을 로또 가격으로 나누어 총 구매 장수로 계산
    구매 금액이 로또 가격보다 작을 경우 예외처리
    입력한 금액을 로또 가격으로 나눈 금액의 나머지 처리*/

    // 금액 입력받아서 장당 천원씩 장수리턴
    // 입력한 금액이 천원보다 작을 경우 예외처리
    // 남은 금액 거슬러주기
    private int lottoCnt;
    private int balance;

    public BuyLotto(int amount) {
        if(amount < 1000){
            throw new IllegalArgumentException();
        }

        this.lottoCnt = (int) Math.floor(amount/1000);
        this.balance = amount%1000;
    }

    public int getLottoCnt() {
        return lottoCnt;
    }

    public int getBalance() {
        return balance;
    }


}
