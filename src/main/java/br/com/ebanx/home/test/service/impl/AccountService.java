package br.com.ebanx.home.test.service.impl;

import br.com.ebanx.home.test.dto.DtoInterface;
import br.com.ebanx.home.test.dto.imp.DepositDto;
import br.com.ebanx.home.test.dto.imp.TransferDto;
import br.com.ebanx.home.test.dto.imp.WithdrawDto;
import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.repository.AccountRepository;
import br.com.ebanx.home.test.service.AccountServiceInterface;
import br.com.ebanx.home.test.service.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class AccountService implements AccountServiceInterface {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private ApplicationContext applicationContext;
    public AccountEntity balance(Integer accountCode){
        AccountEntity accountEntity =  accountRepository.findByAccountCode(accountCode);
        return accountEntity;
    }

    public Map<String, Event> eventMap(){
        Map<String, Event>  eventMap = new HashMap<>();
        eventMap.put("deposit", applicationContext.getBean(Deposit.class));
        eventMap.put("withdraw", applicationContext.getBean(Withdraw.class));
        eventMap.put("transfer", applicationContext.getBean(Transfer.class));
        return eventMap;
    }
    public Map<String, DtoInterface> eventDtoMap(){
        Map<String, DtoInterface> eventDtoMap = new HashMap<>();
        eventDtoMap.put("deposit", applicationContext.getBean(DepositDto.class));
        eventDtoMap.put("withdraw", applicationContext.getBean(WithdrawDto.class));
        eventDtoMap.put("transfer", applicationContext.getBean(TransferDto.class));
        return eventDtoMap;
    }
}
