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
import java.util.Optional;

@Component
public class Deposit implements Event {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public List<AccountEntity> action(EventDto eventDto) {
        List<AccountEntity> account = new ArrayList<>();
        AccountEntity accountEntity;
        accountEntity = accountRepository.findByAccountCode(eventDto.destination());
       if(accountEntity != null) {

           Integer balance = accountEntity.getAmount() + eventDto.amount();
           accountEntity.setAmount(balance);
       }
        else{
            accountEntity = new AccountEntity();
            accountEntity.setAccountCode(eventDto.destination());
            accountEntity.setAmount(eventDto.amount());
        }

        account.add(accountRepository.save(accountEntity));
        return account;
    }
}
