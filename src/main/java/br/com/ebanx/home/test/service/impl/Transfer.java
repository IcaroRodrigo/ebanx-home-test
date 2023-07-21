package br.com.ebanx.home.test.service.impl;

import br.com.ebanx.home.test.dto.EventDto;
import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.repository.AccountRepository;
import br.com.ebanx.home.test.service.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Transfer implements Event {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public List<AccountEntity> action(EventDto eventDto) {
        List<AccountEntity> account = new ArrayList<>();
        AccountEntity accountEntityOrigin;
        accountEntityOrigin = accountRepository.findByAccountCode(eventDto.origin());



        if(accountEntityOrigin != null) {
            BigDecimal balanceOrigin = accountEntityOrigin.getAmount().subtract(eventDto.amount());
            AccountEntity accountEntityDestination;

            if(balanceOrigin.compareTo(BigDecimal.ZERO) > 0) {
                accountEntityOrigin.setAmount(balanceOrigin);
                account.add(accountRepository.save(accountEntityOrigin));

                accountEntityDestination = accountRepository.findByAccountCode(eventDto.destination());

                BigDecimal balanceDestination = accountEntityDestination.getAmount().add(eventDto.amount());
                accountEntityDestination.setAmount(balanceDestination);
                account.add(accountRepository.save(accountEntityDestination));

                return account;
            }

            account.add(accountEntityOrigin);
            account.add(accountEntityDestination = accountRepository.findByAccountCode(eventDto.destination()));
            return account;
        }
        account = null;
        return account;
    }
}
