package br.com.ebanx.home.test.dto;

public class DepositDto {
   public BalanceDto destination;

    public DepositDto(BalanceDto balanceDto){
        this.destination = balanceDto;
    }
}
