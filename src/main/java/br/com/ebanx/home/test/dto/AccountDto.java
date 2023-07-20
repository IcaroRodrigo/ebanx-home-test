package br.com.ebanx.home.test.dto;

import br.com.ebanx.home.test.entity.AccountEntity;

import java.math.BigDecimal;

public class AccountDto {
    public Integer accountCode;
    public BigDecimal amount;
    public AccountDto(AccountEntity accountEntity){
        this.accountCode = accountEntity.getAccountCode();
        this.amount = accountEntity.getAmount();
    }
}
