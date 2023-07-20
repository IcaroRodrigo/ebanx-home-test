package br.com.ebanx.home.test.service.impl;

import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.repository.AccountRepository;
import br.com.ebanx.home.test.service.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountService implements AccountServiceInterface {
    @Autowired
    AccountRepository accountRepository;
    public AccountEntity balance(Integer accountCode){
        AccountEntity accountEntity =  accountRepository.findByAccountCode(accountCode);
        return accountEntity;
    }
}
