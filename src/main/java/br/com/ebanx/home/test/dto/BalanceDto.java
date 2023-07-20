package br.com.ebanx.home.test.dto;

import br.com.ebanx.home.test.entity.AccountEntity;

import java.math.BigDecimal;

public class BalanceDto {
    public Integer id;
    public BigDecimal balance;
    public BalanceDto(AccountEntity accountEntity){
        this.id = accountEntity.getAccountCode();
        this.balance = accountEntity.getAmount();
    }
}
