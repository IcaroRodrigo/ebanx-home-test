package br.com.ebanx.home.test.service.impl;

import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.repository.AccountRepository;
import br.com.ebanx.home.test.service.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class Withdraw implements Event {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public AccountEntity action(Integer accountCode, BigDecimal amount) {
        AccountEntity accountEntity;
        accountEntity = accountRepository.findByAccountCode(accountCode);

        if(accountEntity != null) {
            BigDecimal balance = accountEntity.getAmount().subtract(amount);
            if(balance.compareTo(BigDecimal.ZERO) > 0) {
                accountEntity.setAmount(balance);
                return accountRepository.save(accountEntity);
            }
        }

        return accountEntity;
    }
}
