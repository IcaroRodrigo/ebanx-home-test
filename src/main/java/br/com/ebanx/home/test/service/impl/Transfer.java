package br.com.ebanx.home.test.service.impl;

import br.com.ebanx.home.test.dto.EventDto;
import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.repository.AccountRepository;
import br.com.ebanx.home.test.service.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        AccountEntity accountEntityDestination;
        accountEntityDestination = accountRepository.findByAccountCode(eventDto.destination());

        if(accountEntityOrigin != null) {
            Integer balanceOrigin = accountEntityOrigin.getAmount() - eventDto.amount();


            if(balanceOrigin >= 0) {
                accountEntityOrigin.setAmount(balanceOrigin);
                account.add(accountRepository.save(accountEntityOrigin));
                if (accountEntityDestination != null) {
                    Integer balanceDestination = accountEntityDestination.getAmount() + eventDto.amount();
                    accountEntityDestination.setAmount(balanceDestination);
                    account.add(accountRepository.save(accountEntityDestination));
                }
                else{
                   AccountEntity accountEntityNew = new AccountEntity();
                    accountEntityNew.setAccountCode(eventDto.destination());
                    accountEntityNew.setAmount(eventDto.amount());
                    account.add(accountRepository.save(accountEntityNew));
                }

                return account;
            }

//            account.add(accountEntityOrigin);
//            account.add(accountRepository.findByAccountCode(eventDto.destination()));
//            return account;
        }
        account = null;
        return account;
    }
}
