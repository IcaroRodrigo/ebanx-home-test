package br.com.ebanx.home.test.service.impl;

import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.repository.AccountRepository;
import br.com.ebanx.home.test.service.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Deposit implements Event {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public AccountEntity action(Integer accountCode, BigDecimal amount) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountCode(accountCode);
        accountEntity.setAmount(amount);

        AccountEntity account = accountRepository.save(accountEntity);
        return account;
    }
}
