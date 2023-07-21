package br.com.ebanx.home.test.dto;

import br.com.ebanx.home.test.entity.AccountEntity;

public class BalanceDto {
    public Integer id;
    public Integer balance;
    public BalanceDto(AccountEntity accountEntity){
        this.id = accountEntity.getAccountCode();
        this.balance = accountEntity.getAmount();
    }
}
