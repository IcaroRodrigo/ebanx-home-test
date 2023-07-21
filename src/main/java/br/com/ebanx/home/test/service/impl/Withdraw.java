package br.com.ebanx.home.test.service.impl;

import br.com.ebanx.home.test.dto.EventDto;
import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.repository.AccountRepository;
import br.com.ebanx.home.test.service.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Withdraw implements Event {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public List<AccountEntity> action(EventDto eventDto) {
        List<AccountEntity> account = new ArrayList<>();
        AccountEntity accountEntity;
        Integer accountCode = (eventDto.destination() == null) ? eventDto.origin() : eventDto.destination();
        accountEntity = accountRepository.findByAccountCode(accountCode);

        if(accountEntity != null) {
            Integer balance = accountEntity.getAmount() - eventDto.amount();
            if(balance > 0) {
                accountEntity.setAmount(balance);
                account.add(accountRepository.save(accountEntity));
                return account;
            }
            account.add(accountEntity);
            return account;
        }
        account = null;
        return account;
    }
}
